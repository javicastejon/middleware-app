package com.tfg.backend.data;

public abstract class AttributesDatabase {

    // ENTITIES
    public static final String USER_ENTITY = "usuario";
    public static final String COUNTRY_ENTITY = "pais";
    public static final String USER_TYPE_ENTITY = "tipo de usuario";
    public static final String BOARDGAME_ENTITY = "juego de mesa";
    public static final String BOARDGAME_GENDER_ENTITY = "género";
    public static final String BOARDGAME_TYPE_ENTITY = "tipo de juego";
    public static final String PACK_ENTITY = "pack";
    public static final String COLLECTION_ENTITY = "colección";
    public static final String SESSION_ENTITY = "sesión";
    public static final String MEETING_ENTITY = "partida";
    public static final String STOCK_ENTITY = "stock";
    public static final String LOAN_ENTITY = "préstamo";
    public static final String LOAN_STATE_ENTITY = "estado del " + LOAN_ENTITY;
    public static final String USER_ASSOCIATE_ENTITY = "asociación de " + USER_ENTITY;

    // USER - ATTRIBUTES
    public static final String USER_ATTRIBUTE_NAME_USER = "nombre de " + USER_ENTITY;
    public static final String USER_ATTRIBUTE_PASS_HASH = "contraseña";
    public static final String USER_ATTRIBUTE_EMAIL = "email";
    public static final String USER_ATTRIBUTE_PROFILE_IMAGE = "imagen de perfil";
    public static final String USER_ATTRIBUTE_PHONE_NUMBER = "número de teléfono";
    public static final String USER_ATTRIBUTE_EMAIL_NOTIFICATIONS = "notificaciones";
    public static final String USER_ATTRIBUTE_BIRTHDAY_DATE = "fecha de nacimiento";
    
    // COUNTRY - ATTRIBUTES      
    // USER_TYPE - ATTRIBUTES
    
    // BOARDGAME - ATTRIBUTRES
    public static final String BOARDGAME_ATTRIBUTE_NAME = "nombre del " + BOARDGAME_ENTITY;
    public static final String BOARDGAME_ATTRIBUTE_API_BGG_REF = "referencia de la API";
    public static final String BOARDGAME_ATTRIBUTE_PLAYERS_MIN = "minimo de jugadores";
    public static final String BOARDGAME_ATTRIBUTE_PLAYERS_MAX = "maximo de jugadores";
    public static final String BOARDGAME_ATTRIBUTE_RELEASE_YEAR = "año de lanzamiento";
    public static final String BOARDGAME_ATTRIBUTE_DESCRIPTION = "descripcion";
    public static final String BOARDGAME_ATTRIBUTE_IMAGE_ENDPOINT = "endpoint de la imagen";
    public static final String BOARDGAME_ATTRIBUTE_TYPE_BOARDGAME = "tipo de " + BOARDGAME_ENTITY;
    public static final String BOARDGAME_ATTRIBUTE_BOARDGAME_BASE = "referencia del " + BOARDGAME_ENTITY + " base";
    public static final String BOARDGAME_ATTRIBUTE_GENDER = BOARDGAME_GENDER_ENTITY;

    //BOARDGAME_GENDER - ATTRIBUTES
    //BOARDGAME_TYPE - ATTRIBUTES    

    // PACK - ATTRIBUTES
    public static final String PACK_ATTRIBUTE_NAME = "nombre del " + PACK_ENTITY;

    // SESSION - ATTRIBUTES
    public static final String SESSION_ATTRIBUTE_NAME = "nombre de la " + SESSION_ENTITY;
    public static final String SESSION_ATTRIBUTE_DATE = "fecha de la " + SESSION_ENTITY;

    // MEETING - ATTRIBUTES
    public static final String MEETING_ATTRIBUTE_DURATION = "duración de la "+ MEETING_ENTITY;
    
    // STOCK - ATTRIBUTES
    public static final String STOCK_ATTRIBUTE_UNITS = "cantidad de unidades";

    // LOAN - ATTRIBUTES
    public static final String LOAN_ATTRIBUTE_DATE = "fecha del " + LOAN_ENTITY;
    public static final String LOAN_ATTRIBUTE_EXPIRATION_DATE = "fecha de vencimiento del " + LOAN_ENTITY;
}
