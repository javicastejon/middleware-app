package com.tfg.backend.models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tfg.backend.config.DataBaseConfig;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = DataBaseConfig.BOARDGAME_ENTITY)
public class Boardgame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseConfig.PK_BOARDGAME_ENTITY)
    private Integer boardgameId;

    @Column(name = DataBaseConfig.BOARDGAME_COLUMN_BOARDGAME_NAME, columnDefinition = DataBaseConfig.BOARDGAME_COLUMN_BOARDGAME_NAME_DEFINITION)
    private String boardgameName;

    @Column(name = DataBaseConfig.BOARDGAME_COLUMN_PLAYERS_MIN, columnDefinition = DataBaseConfig.BOARDGAME_COLUMN_PLAYERS_MIN_DEFINITION)
    private Integer minPlayers;

    @Column(name = DataBaseConfig.BOARDGAME_COLUMN_PLAYERS_MAX, columnDefinition = DataBaseConfig.BOARDGAME_COLUMN_PLAYERS_MAX_DEFINITION)
    private Integer maxPlayers;

    @Column(name = DataBaseConfig.BOARDGAME_COLUMN_RELEASE_YEAR, columnDefinition = DataBaseConfig.BOARDGAME_COLUMN_RELEASE_YEAR_DEFINITION)
    private Integer releaseYear;

    @Column(name = DataBaseConfig.BOARDGAME_COLUMN_DESCRIPTION, columnDefinition = DataBaseConfig.BOARDGAME_COLUMN_DESCRIPTION_DEFINITION)
    private String boardgameDescription;

    @Column(name = DataBaseConfig.BOARDGAME_COLUMN_IMAGE_ENDPOINT, columnDefinition = DataBaseConfig.BOARDGAME_COLUMN_IMAGE_ENDPOINT_DEFINITION)
    private String boardgameImageUrl;

    @Column(name = DataBaseConfig.BOARDGAME_COLUMN_API_BGG_REF, columnDefinition = DataBaseConfig.BOARDGAME_COLUMN_API_BGG_REF_DEFINITION)
    private String apiBggRef;

    //MANY TO ONE
    @ManyToOne
    @JoinColumn(name = DataBaseConfig.BOARDGAME_COLUMN_BOARDGAME_GENDER, nullable = false)
    private BoardgameGender fkBoardgameGender;

    @ManyToOne
    @JoinColumn(name = DataBaseConfig.BOARDGAME_COLUMN_BOARDGAME_TYPE, nullable = false)
    private BoardgameType fkBoardgameType;

    @ManyToOne
    @JoinColumn(name = DataBaseConfig.BOARDGAME_COLUMN_BOARDGAME_BASE, nullable = true)
    private Boardgame fkBoardgameBase;

    // ONE TO MANY
    @JsonIgnore
    @OneToMany(mappedBy = "fkBoardgame", cascade = CascadeType.ALL)
    private List<Collection> collections;

    @JsonIgnore
    @OneToMany(mappedBy = "fkBoardgame", cascade = CascadeType.ALL)
    private List<BoardgamePack> boardGamesPacks;

    @JsonIgnore
    @OneToMany(mappedBy = "fkBoardgame", cascade = CascadeType.ALL)
    private List<Meeting> meetings;

    @JsonIgnore
    @OneToMany(mappedBy = "fkBoardgameBase", cascade = CascadeType.ALL)
    private List<Boardgame> boardgames;

    @JsonIgnore
    @OneToMany(mappedBy = "fkBoardgame", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    public Boardgame() {
    }

    public Integer getBoardgameId() {
        return boardgameId;
    }

    public void setBoardgameId(Integer boardgameId) {
        this.boardgameId = boardgameId;
    }

    public String getBoardgameName() {
        return boardgameName;
    }

    public void setBoardgameName(String boardgameName) {
        this.boardgameName = boardgameName;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getBoardgameDescription() {
        return boardgameDescription;
    }

    public void setBoardgameDescription(String boardgameDescription) {
        this.boardgameDescription = boardgameDescription;
    }

    public String getBoardgameImageUrl() {
        return boardgameImageUrl;
    }

    public void setBoardgameImageUrl(String boardgameImageUrl) {
        this.boardgameImageUrl = boardgameImageUrl;
    }

    public String getApiBggRef() {
        return apiBggRef;
    }

    public void setApiBggRef(String apiBggRef) {
        this.apiBggRef = apiBggRef;
    }

    public BoardgameGender getFkBoardgameGender() {
        return fkBoardgameGender;
    }

    public void setFkBoardgameGender(BoardgameGender fkBoardgameGender) {
        this.fkBoardgameGender = fkBoardgameGender;
    }

    public BoardgameType getFkBoardgameType() {
        return fkBoardgameType;
    }

    public void setFkBoardgameType(BoardgameType fkBoardgameType) {
        this.fkBoardgameType = fkBoardgameType;
    }

    public Boardgame getFkBoardgameBase() {
        return fkBoardgameBase;
    }

    public void setFkBoardgameBase(Boardgame fkBoardgameBase) {
        this.fkBoardgameBase = fkBoardgameBase;
    }

    public List<Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<Collection> collections) {
        this.collections = collections;
    }

    public List<BoardgamePack> getBoardGamesPacks() {
        return boardGamesPacks;
    }

    public void setBoardGamesPacks(List<BoardgamePack> boardGamesPacks) {
        this.boardGamesPacks = boardGamesPacks;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    public List<Boardgame> getBoardgames() {
        return boardgames;
    }

    public void setBoardgames(List<Boardgame> boardgames) {
        this.boardgames = boardgames;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}