package com.tfg.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Session_Player")
public class SessionPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_player_id")
    private Integer sessionPlayerId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_session", nullable = false)
    private Session fkSession;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User fkUser;

    public SessionPlayer() {
    }

    public Integer getSessionPlayerId() {
        return sessionPlayerId;
    }

    public void setSessionPlayerId(Integer sessionPlayerId) {
        this.sessionPlayerId = sessionPlayerId;
    }

    public Session getFkSession() {
        return fkSession;
    }

    public void setFkSession(Session fkSession) {
        this.fkSession = fkSession;
    }

    public User getFkUser() {
        return fkUser;
    }

    public void setFkUser(User fkUser) {
        this.fkUser = fkUser;
    }
}
