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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = DataBaseConfig.COUNTRY_ENTITY)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseConfig.PK_COUNTRY_ENTITY)
    private Integer countryId;

    @Column(name = DataBaseConfig.COUNTRY_COLUMN_COUNTRY_NAME, columnDefinition = DataBaseConfig.COUNTRY_COLUMN_COUNTRY_NAME_DEFINITION)
    private String countryName;

    @JsonIgnore
    @OneToMany(mappedBy = "fkCountry", cascade = CascadeType.ALL)
    private List<User> users;

    public Country() {
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}