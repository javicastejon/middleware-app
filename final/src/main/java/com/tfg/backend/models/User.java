package com.tfg.backend.models;

import java.sql.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = DataBaseConfig.USER_ENTITY)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DataBaseConfig.PK_USER_ENTITY)
    private Integer userId;

    @Column(name = DataBaseConfig.USER_COLUMN_USER_NAME,  columnDefinition = DataBaseConfig.USER_COLUMN_USER_NAME_DEFINITION)
    private String userName;

    @Column(name = DataBaseConfig.USER_COLUMN_PASS_HASH, columnDefinition = DataBaseConfig.USER_COLUMN_PASS_HASH_DEFINITION)
    private String passHash;

    @Column(name = DataBaseConfig.USER_COLUMN_EMAIL, columnDefinition = DataBaseConfig.USER_COLUMN_EMAIL_DEFINITION)
    private String email;
       
    @Column(name = DataBaseConfig.USER_COLUMN_PROFILE_IMAGE, columnDefinition = DataBaseConfig.USER_COLUMN_PROFILE_IMAGE_DEFINITION)
    private String profileImage;

    @Column(name = DataBaseConfig.USER_COLUMN_PHONE_NUMBER, columnDefinition = DataBaseConfig.USER_COLUMN_PHONE_NUMBER_DEFINITION)
    private Integer phoneNumber;

    @Column(name = DataBaseConfig.USER_COLUMN_EMAIL_NOTIFICATIONS, columnDefinition = DataBaseConfig.USER_COLUMN_EMAIL_NOTIFICATIONS_DEFINITION)
    private boolean emailNotifications;
  
    @Column(name = DataBaseConfig.USER_COLUMN_CREATION_DATE, columnDefinition = DataBaseConfig.USER_COLUMN_CREATION_DATE_DEFINITION)
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = DataBaseConfig.USER_COLUMN_COUNTRY, nullable = false)
    private Country fkCountry;

    @ManyToOne
    @JoinColumn(name = DataBaseConfig.USER_COLUMN_USER_TYPE, nullable = false)
    private UserType fkUserType;

    @JsonIgnore
    @OneToMany(mappedBy = "fkHostUser", cascade = CascadeType.ALL)
    private List<UserAssociate> hostedUsers;

    @JsonIgnore
    @OneToMany(mappedBy = "fkAssociatedUser", cascade = CascadeType.ALL)
    private List<UserAssociate> associatedUsers;
  
    @JsonIgnore
    @OneToMany(mappedBy = "fkUser", cascade = CascadeType.ALL)
    private List<Collection> numerarys;

    @JsonIgnore
    @OneToMany(mappedBy = "fkUser", cascade = CascadeType.ALL)
    private List<Pack> packs;

    @JsonIgnore
    @OneToMany(mappedBy = "fkUser", cascade = CascadeType.ALL)
    private List<Session> sessions;

    @JsonIgnore
    @OneToMany(mappedBy = "fkUser", cascade = CascadeType.ALL)
    private List<SessionPlayer> sessionPlayers;

    @JsonIgnore
    @OneToMany(mappedBy = "fkUser", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    @JsonIgnore
    @OneToMany(mappedBy = "fkUser", cascade = CascadeType.ALL)
    private List<Loan> loans;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Country getFkCountry() {
        return fkCountry;
    }

    public void setFkCountry(Country fkCountryId) {
        this.fkCountry = fkCountryId;
    }

    public UserType getFkUserType() {
        return fkUserType;
    }

    public void setFkUserType(UserType fkUserTypeId) {
        this.fkUserType = fkUserTypeId;
    }

    public boolean isEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public List<Collection> getNumerarys() {
        return numerarys;
    }

    public void setNumerarys(List<Collection> numerarys) {
        this.numerarys = numerarys;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<SessionPlayer> getSessionPlayers() {
        return sessionPlayers;
    }

    public void setSessionPlayers(List<SessionPlayer> sessionPlayers) {
        this.sessionPlayers = sessionPlayers;
    }

    public List<UserAssociate> getHostedUsers() {
        return hostedUsers;
    }

    public void setHostedUsers(List<UserAssociate> hostedUsers) {
        this.hostedUsers = hostedUsers;
    }

    public List<UserAssociate> getAssociatedUsers() {
        return associatedUsers;
    }

    public void setAssociatedUsers(List<UserAssociate> associatedUsers) {
        this.associatedUsers = associatedUsers;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
