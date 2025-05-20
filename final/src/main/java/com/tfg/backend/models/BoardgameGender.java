package com.tfg.backend.models;

import java.util.List;

import org.hibernate.boot.model.relational.Database;

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
@Table(name = DataBaseConfig.BOARDGAME_GENDER_ENTITY)
public class BoardgameGender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseConfig.PK_BOARDGAME_GENDER_ENTITY)
    private Integer boardgameGenderId;

    @Column(name = DataBaseConfig.BOARDGAME_GENDER_COLUMN_GENDER_NAME, columnDefinition = DataBaseConfig.BOARDGAME_GENDER_COLUMN_GENDER_NAME_DEFINITION)
    private String boardgameGenderName;

    @JsonIgnore
    @OneToMany(mappedBy = "fkBoardgameGender", cascade = CascadeType.ALL)
    private List<Boardgame> boardgames;

    public BoardgameGender() {
    }

    public Integer getBoardgameGenderId() {
        return boardgameGenderId;
    }

    public void setBoardgameGenderId(Integer boardgameGenderId) {
        this.boardgameGenderId = boardgameGenderId;
    }

    public String getBoardgameGenderName() {
        return boardgameGenderName;
    }

    public void setBoardgameGenderName(String boardgameGenderName) {
        this.boardgameGenderName = boardgameGenderName;
    }

    public List<Boardgame> getBoardgames() {
        return boardgames;
    }

    public void setBoardgames(List<Boardgame> boardgames) {
        this.boardgames = boardgames;
    }
}
