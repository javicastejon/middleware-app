package com.tfg.backend.models;

import java.util.Date;

import javax.xml.crypto.Data;

import com.tfg.backend.config.DataBaseConfig;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = DataBaseConfig.USER_ASSOCIATE_ENTITY)
public class UserAssociate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseConfig.PK_USER_ASSOCIATE_ENTITY)
    private Integer UserAssociateId;

    @ManyToOne
    @JoinColumn(name = DataBaseConfig.USER_ASSOCIATE_COLUMN_HOST_USER, nullable = false)
    private User fkHostUser;

    @ManyToOne
    @JoinColumn(name = DataBaseConfig.USER_ASSOCIATE_COLUMN_ASSOCIATE_USER, nullable = false)
    private User fkAssociatedUser;

    @Column(name = DataBaseConfig.USER_ASSOCIATE_COLUMN_ASSOCIATION_DATE, columnDefinition = DataBaseConfig.USER_ASSOCIATE_COLUMN_ASSOCIATION_DATE_DEFINITION)
    @Temporal(TemporalType.DATE)
    private Date associationDate;

    public UserAssociate() {
    }

    public Integer getUserAssociateId() {
        return UserAssociateId;
    }

    public void setUserAssociateId(Integer userAssociateId) {
        UserAssociateId = userAssociateId;
    }

    public User getFkHostUser() {
        return fkHostUser;
    }

    public void setFkHostUser(User fkHostUser) {
        this.fkHostUser = fkHostUser;
    }

    public User getFkAssociatedUser() {
        return fkAssociatedUser;
    }

    public void setFkAssociatedUser(User fkAssociatedUser) {
        this.fkAssociatedUser = fkAssociatedUser;
    }

    public Date getAssociationDate() {
        return associationDate;
    }

    public void setAssociationDate(Date associationDate) {
        this.associationDate = associationDate;
    }
}