package com.tfg.backend.models;

import com.tfg.backend.config.DataBaseConfig;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = DataBaseConfig.LOAN_STATE_ENTITY)
public class LoanState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseConfig.PK_LOAN_STATE_ENTITY)
    private Integer loanStateId;

    @Column(name = DataBaseConfig.LOAN_STATE_COLUMN_LOAN_STATE_NAME, columnDefinition = DataBaseConfig.LOAN_STATE_COLUMN_LOAN_STATE_NAME_DEFINITION)
    private String loanStateName;

    public LoanState() {
    }

    public Integer getLoanStateId() {
        return loanStateId;
    }

    public void setLoanStateId(Integer loanStateId) {
        this.loanStateId = loanStateId;
    }

    public String getLoanStateName() {
        return loanStateName;
    }

    public void setLoanStateName(String loanStateName) {
        this.loanStateName = loanStateName;
    }
}
