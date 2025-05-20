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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = DataBaseConfig.STOCK_ENTITY)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseConfig.PK_STOCK_ENTITY)
    private Integer stockId;

    @Column(name = DataBaseConfig.STOCK_COLUMN_STOCK_UNITS, columnDefinition = DataBaseConfig.STOCK_COLUMN_STOCK_UNITS_DEFINITION)
    private Integer units;

    @ManyToOne
    @JoinColumn(name = DataBaseConfig.STOCK_COLUMN_USER, nullable = false)
    private User fkUser;

    @ManyToOne
    @JoinColumn(name = DataBaseConfig.STOCK_COLUMN_BOARDGAME, nullable = false)
    private Boardgame fkBoardgame;

    @JsonIgnore
    @OneToMany(mappedBy = "fkStock", cascade = CascadeType.ALL)
    private List<Loan> loans;

    public Stock() {
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public User getFkUser() {
        return fkUser;
    }

    public void setFkUser(User fkUser) {
        this.fkUser = fkUser;
    }

    public Boardgame getFkBoardgame() {
        return fkBoardgame;
    }

    public void setFkBoardgame(Boardgame fkBoardgame) {
        this.fkBoardgame = fkBoardgame;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
