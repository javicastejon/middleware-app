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
@Table(name = DataBaseConfig.USER_TYPE_ENTITY)
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseConfig.PK_USER_TYPE_ENTITY)
    private Integer userTypeId;

    @Column(name = DataBaseConfig.USER_TYPE_COLUMN_USER_TYPE_NAME, columnDefinition = DataBaseConfig.USER_TYPE_COLUMN_USER_TYPE_NAME_DEFINITION)
    private String userTypeName;

    @JsonIgnore
    @OneToMany(mappedBy = "fkUserType", cascade = CascadeType.ALL)
    private List<User> users;

    public UserType() {
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}