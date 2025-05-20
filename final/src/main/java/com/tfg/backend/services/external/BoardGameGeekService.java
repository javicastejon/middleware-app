package com.tfg.backend.services.external;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tfg.backend.models.Boardgame;
import com.tfg.backend.models.BoardgameGender;
import com.tfg.backend.models.BoardgameType;
import com.tfg.backend.repository.BoardgameGenderRepository;
import com.tfg.backend.repository.BoardgameRepository;
import com.tfg.backend.repository.BoardgameTypeRepository;
import com.tfg.backend.services.operations.BoardgameTypeService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BoardGameGeekService {
    
    private static final String BGG_API_URL = "https://boardgamegeek.com/xmlapi2";
    private final RestTemplate restTemplate;
    private final BoardgameTypeRepository boardgameTypeRepository;
    private final BoardgameGenderRepository genderRepository;
    private final BoardgameRepository boardgameRepository;
    private final int nGamesSearch = 50;

    @Autowired
    public BoardGameGeekService(RestTemplate restTemplate, BoardgameGenderRepository genderRepository, BoardgameTypeRepository boardgameTypeRepository, BoardgameRepository boardgameRepository) {
        this.restTemplate = restTemplate;
        this.genderRepository = genderRepository;
        this.boardgameTypeRepository = boardgameTypeRepository;
        this.boardgameRepository = boardgameRepository;
    }

    public List<Boardgame> searchTopRelevantGames(String gameName) {
        try {
            // 1. Buscar IDs de los primeros N juegos
            List<String> gameIds = findTopGameIdsByName(gameName, nGamesSearch);
            
            // 2. Obtener detalles de cada juego en paralelo
            return gameIds.parallelStream()
                    .map(this::fetchGameDetailsWithGenre)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            
        } catch (Exception e) {
            throw new RuntimeException("Error en la búsqueda aproximada", e);
        }
    }

    public String getBaseGameForExpansion(String expansionName) {
        try {
            String expansionId = findGameIdByName(expansionName, "boardgameexpansion");
            if (expansionId == null) return null;
            
            String detailsUrl = BGG_API_URL + "/thing?id=" + expansionId + "&stats=1";
            String detailsResponse = restTemplate.getForObject(detailsUrl, String.class);
            
            return parseBaseGameName(detailsResponse);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el juego base para: " + expansionName, e);
        }
    }

    // --- Métodos auxiliares ---

    private List<String> findTopGameIdsByName(String gameName, int limit) throws Exception {
        String encodedName = URLEncoder.encode(gameName, StandardCharsets.UTF_8);
        String searchUrl = BGG_API_URL + "/search?query=" + encodedName + 
                         "&type=boardgame&exact=0";
        
        String searchResponse = restTemplate.getForObject(searchUrl, String.class);
        return parseTopNGameIdsFromSearch(searchResponse, limit);
    }

    private List<String> parseTopNGameIdsFromSearch(String xmlResponse, int limit) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));

        NodeList items = doc.getElementsByTagName("item");
        List<String> ids = new ArrayList<>();
        
        int maxResults = Math.min(items.getLength(), limit);
        for (int i = 0; i < maxResults; i++) {
            ids.add(((Element) items.item(i)).getAttribute("id"));
        }
        
        return ids;
    }

    private Boardgame fetchGameDetailsWithGenre(String gameId) {
        try {
            String detailsUrl = BGG_API_URL + "/thing?id=" + gameId;
            String detailsResponse = restTemplate.getForObject(detailsUrl, String.class);
            
            Boardgame game = convertToBoardGameEntity(detailsResponse, gameId);
            String genreName = parseMainGenreFromXml(detailsResponse);
            
            // Buscar o crear el género
            BoardgameGender gender = genderRepository.findByBoardgameGenderName(genreName)
                    .orElseGet(() -> {
                        BoardgameGender newGender = new BoardgameGender();
                        newGender.setBoardgameGenderName(genreName);
                        return genderRepository.save(newGender);
                    });
            
            // Asignar el ID del género al juego
            game.setFkBoardgameGender(gender);

            if (gender.getBoardgameGenderName().equals("Expansion for Base-game")) {
                game.setFkBoardgameType(boardgameTypeRepository.getByBoardgameTypeName("Expansión"));
                String boardgameBaseName = getBaseGameForExpansion(game.getBoardgameName());
                System.out.println("EL NOMBRE DEL JUEGO BASE ES: " + boardgameBaseName);
                boardgameRepository.findByBoardgameName(boardgameBaseName).ifPresent(game::setFkBoardgameBase);
            }else{
                game.setFkBoardgameType(boardgameTypeRepository.getByBoardgameTypeName("Juego Base"));
            }           
            return game;
        } catch (Exception e) {
            return null;
        }
    }

    private String parseBaseGameName(String xml) throws Exception {
    Document doc = DocumentBuilderFactory.newInstance()
            .newDocumentBuilder()
            .parse(new ByteArrayInputStream(xml.getBytes()));
    
    Element item = (Element) doc.getElementsByTagName("item").item(0);
    if (item == null || !"boardgameexpansion".equals(item.getAttribute("type"))) {
        return null;
    }
    
    // Buscar todos los links de tipo boardgame
    NodeList links = doc.getElementsByTagName("link");
    for (int i = 0; i < links.getLength(); i++) {
        Element link = (Element) links.item(i);
        if ("boardgame".equals(link.getAttribute("type"))) {
            // Priorizar relaciones de expansión
            if ("expansion".equals(link.getAttribute("rel"))) {
                return link.getAttribute("value");
            }
        }
    }
    
    // Fallback: primer link de tipo boardgame si no se encontró relación explícita
    for (int i = 0; i < links.getLength(); i++) {
        Element link = (Element) links.item(i);
        if ("boardgame".equals(link.getAttribute("type"))) {
            return link.getAttribute("value");
        }
    }
    
    return null;
}

    // Métodos existentes que se mantienen igual...
    private String findGameIdByName(String gameName, String type) throws Exception {
        String encodedName = URLEncoder.encode(gameName, StandardCharsets.UTF_8);
        String searchUrl = BGG_API_URL + "/search?query=" + encodedName + 
                         "&type=" + type + "&exact=0";
        
        String searchResponse = restTemplate.getForObject(searchUrl, String.class);
        return parseFirstGameIdFromSearch(searchResponse);
    }

    private Boardgame convertToBoardGameEntity(String xmlResponse, String bggId) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));

        Element item = (Element) doc.getElementsByTagName("item").item(0);
        if (item == null) return null;

        Boardgame boardgame = new Boardgame();
        
        // Mapeo de campos básicos
        boardgame.setBoardgameName(getPrimaryName(item));
        boardgame.setBoardgameDescription(sanitizeDescription(getElementValue(item, "description")));
        boardgame.setBoardgameImageUrl(getElementValue(item, "image"));
        boardgame.setApiBggRef(bggId);
        
        // Campos numéricos con manejo de valores nulos
        boardgame.setMinPlayers(getNumericAttributeValue(item, "minplayers", "value"));
        boardgame.setMaxPlayers(getNumericAttributeValue(item, "maxplayers", "value"));
        boardgame.setReleaseYear(getNumericAttributeValue(item, "yearpublished", "value"));
        
        // El género se asignará posteriormente en fetchGameDetailsWithGenre
        return boardgame;
    }

    private String sanitizeDescription(String description) {
        if (description == null) return null;
        // Eliminar caracteres especiales y HTML tags
        return description.replaceAll("<[^>]*>", "").replaceAll("&[^;]*;", "");
    }

    private Integer getNumericAttributeValue(Element parent, String tagName, String attributeName) {
        try {
            NodeList elements = parent.getElementsByTagName(tagName);
            if (elements.getLength() > 0) {
                Element element = (Element) elements.item(0);
                String value = element.getAttribute(attributeName);
                return value.isEmpty() ? null : Integer.parseInt(value);
            }
        } catch (NumberFormatException e) {
            // Log error if needed
        }
        return null;
    }

    private String getPrimaryName(Element item) {
        NodeList names = item.getElementsByTagName("name");
        for (int i = 0; i < names.getLength(); i++) {
            Element name = (Element) names.item(i);
            if ("primary".equals(name.getAttribute("type"))) {
                return name.getAttribute("value");
            }
        }
        return null;
    }

    private String getElementValue(Element parent, String tagName) {
        NodeList elements = parent.getElementsByTagName(tagName);
        if (elements.getLength() > 0) {
            return elements.item(0).getTextContent();
        }
        return null;
    }

    private String parseMainGenreFromXml(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));

        NodeList links = doc.getElementsByTagName("link");
        for (int i = 0; i < links.getLength(); i++) {
            Node node = links.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                if ("boardgamecategory".equals(elem.getAttribute("type"))) {
                    return elem.getAttribute("value");
                }
            }
        }
        return "Desconocido";
    }

    private String parseFirstGameIdFromSearch(String xmlResponse) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));

        NodeList items = doc.getElementsByTagName("item");
        return items.getLength() > 0 ? ((Element) items.item(0)).getAttribute("id") : null;
    }


    // /**
    //  * Busca un juego de mesa por nombre exacto en BGG y devuelve la entidad Boardgame
    //  * @param gameName Nombre exacto del juego a buscar
    //  * @return Entidad Boardgame con la información del juego o null si no se encuentra
    //  */
    // public Boardgame searchAndConvertBoardGame(String gameName) {
    //     try {
    //         // Primero buscamos el ID del juego
    //         @SuppressWarnings("deprecation")
    //         String searchUrl = UriComponentsBuilder.fromHttpUrl(BGG_API_URL + "/search")
    //                 .queryParam("query", gameName)
    //                 .queryParam("type", "boardgame")
    //                 .queryParam("exact", "0")
    //                 .toUriString();

    //         String searchResponse = restTemplate.getForObject(searchUrl, String.class);
    //         String gameId = parseFirstGameIdFromSearch(searchResponse);

    //         if (gameId == null) {
    //             return null;
    //         }

    //         // Obtenemos los detalles del juego
    //         @SuppressWarnings("deprecation")
    //         String detailsUrl = UriComponentsBuilder.fromHttpUrl(BGG_API_URL + "/thing")
    //                 .queryParam("id", gameId)
    //                 .toUriString();

    //         String detailsResponse = restTemplate.getForObject(detailsUrl, String.class);
    //         return convertToBoardGameEntity(detailsResponse, gameId);

    //     } catch (Exception e) {
    //         throw new RuntimeException("Error al consultar la API de BoardGameGeek", e);
    //     }
    // }

    // private String parseFirstGameIdFromSearch(String xmlResponse) throws Exception {
    //     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    //     DocumentBuilder builder = factory.newDocumentBuilder();
    //     Document doc = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));

    //     NodeList items = doc.getElementsByTagName("item");
    //     if (items.getLength() == 0) {
    //         return null;
    //     }
    //     return ((Element) items.item(0)).getAttribute("id");
    // }

    // private Boardgame convertToBoardGameEntity(String xmlResponse, String bggId) throws Exception {
    //     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    //     DocumentBuilder builder = factory.newDocumentBuilder();
    //     Document doc = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));
    
    //     Element item = (Element) doc.getElementsByTagName("item").item(0);
    //     if (item == null) return null;
    
    //     Boardgame boardgame = new Boardgame();
        
    //     // Mapeo de campos
    //     boardgame.setBoardgameName(getPrimaryName(item));
    //     boardgame.setBoardgameDescription(getElementValue(item, "description"));
    //     boardgame.setBoardgameImageUrl(getElementValue(item, "image"));
    //     boardgame.setApiBggRef(bggId);
        
    //     // Campos numéricos (ahora usando getAttributeValue)
    //     boardgame.setMinPlayers(getAttributeValue(item, "minplayers", "value"));
    //     boardgame.setMaxPlayers(getAttributeValue(item, "maxplayers", "value"));
    //     boardgame.setReleaseYear(getAttributeValue(item, "yearpublished", "value"));
    
    //     return boardgame;
    // }
    
    // // Método para obtener valores de atributos
    // private Integer getAttributeValue(Element parent, String tagName, String attributeName) {
    //     NodeList elements = parent.getElementsByTagName(tagName);
    //     if (elements.getLength() > 0) {
    //         Element element = (Element) elements.item(0);
    //         String value = element.getAttribute(attributeName);
    //         try {
    //             return value.isEmpty() ? null : Integer.parseInt(value);
    //         } catch (NumberFormatException e) {
    //             return null;
    //         }
    //     }
    //     return null;
    // }
    
    // // Método original para texto dentro de elementos (description, image)
    // private String getElementValue(Element parent, String tagName) {
    //     NodeList elements = parent.getElementsByTagName(tagName);
    //     if (elements.getLength() > 0) {
    //         return elements.item(0).getTextContent();
    //     }
    //     return null;
    // }

    // private String getPrimaryName(Element item) {
    //     NodeList names = item.getElementsByTagName("name");
    //     for (int i = 0; i < names.getLength(); i++) {
    //         Element name = (Element) names.item(i);
    //         if ("primary".equals(name.getAttribute("type"))) {
    //             return name.getAttribute("value");
    //         }
    //     }
    //     return null;
    // }




    

    // // THIS IS CALLED IN MAIN METHOD
    // private String getGameGenreByName(String gameName) {
    //     try {
    //         String gameId = findGameIdByName(gameName);
    //         if (gameId == null) {
    //             return "No encontrado";
    //         }
    //         String detailsUrl = BGG_API_URL + "/thing?id=" + gameId;
    //         String detailsResponse = restTemplate.getForObject(detailsUrl, String.class);
    //         return parseMainGenreFromXml(detailsResponse); // Usa el nuevo método
    //     } catch (Exception e) {
    //         throw new RuntimeException("Error al obtener el género desde BGG", e);
    //     }
    // }

    // private String findGameIdByName(String gameName) throws Exception {
    //     String encodedName = java.net.URLEncoder.encode(gameName, "UTF-8");
    //     String searchUrl = BGG_API_URL + "/search?query=" + encodedName + "&type=boardgame&exact=1";
    //     String searchResponse = restTemplate.getForObject(searchUrl, String.class);
        
    //     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    //     DocumentBuilder builder = factory.newDocumentBuilder();
    //     Document doc = builder.parse(new ByteArrayInputStream(searchResponse.getBytes()));
        
    //     NodeList items = doc.getElementsByTagName("item");
    //     return items.getLength() > 0 ? ((Element) items.item(0)).getAttribute("id") : null;
    // }


    // private String parseMainGenreFromXml(String xml) throws Exception {
    //     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    //     DocumentBuilder builder = factory.newDocumentBuilder();
    //     Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
    
    //     NodeList links = doc.getElementsByTagName("link");
    //     for (int i = 0; i < links.getLength(); i++) {
    //         Node node = links.item(i);
    //         if (node.getNodeType() == Node.ELEMENT_NODE) {
    //             Element elem = (Element) node;
    //             if ("boardgamecategory".equals(elem.getAttribute("type"))) {
    //                 System.out.println( "ESTE ES EL GENERO: " + elem.getAttribute("value"));
    //                 return elem.getAttribute("value"); // Devuelve el primer género encontrado
    //             }
    //         }
    //     }
        
    //     return "Desconocido"; // Si no se encuentra ningún género
    // }
}