package com.tfg.backend.models;


import java.util.List;

import javax.xml.crypto.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tfg.backend.config.DataBaseConfig;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = DataBaseConfig.BOARDGAME_TYPE_ENTITY)
public class BoardgameType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseConfig.PK_BOARDGAME_TYPE_ENTITY)
    private Integer boardgameTypeId;

    @Column(name = DataBaseConfig.BOARDGAME_TYPE_COLUMN_TYPE_NAME, columnDefinition = DataBaseConfig.BOARDGAME_TYPE_COLUMN_TYPE_NAME_DEFINITION)
    private String boardgameTypeName;

    @JsonIgnore
    @OneToMany(mappedBy = "fkBoardgameType", cascade = CascadeType.ALL)
    private List<Boardgame> boardgames;

    public BoardgameType() {
    }

    public Integer getBoardgameTypeId() {
        return boardgameTypeId;
    }

    public void setBoardgameTypeId(Integer boardgameTypeId) {
        this.boardgameTypeId = boardgameTypeId;
    }

    public String getBoardgameTypeName() {
        return boardgameTypeName;
    }

    public void setBoardgameTypeName(String boardgameTypeName) {
        this.boardgameTypeName = boardgameTypeName;
    }

    public List<Boardgame> getBoardgames() {
        return boardgames;
    }

    public void setBoardgames(List<Boardgame> boardgames) {
        this.boardgames = boardgames;
    }
}