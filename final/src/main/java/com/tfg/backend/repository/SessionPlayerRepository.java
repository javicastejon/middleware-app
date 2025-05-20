package com.tfg.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.backend.models.Session;
import com.tfg.backend.models.SessionPlayer;
import com.tfg.backend.models.User;

@Repository
public interface SessionPlayerRepository extends JpaRepository<SessionPlayer, Integer> {

    boolean existsByFkSessionAndFkUser(Session session, User user);

    List<SessionPlayer> findByFkSession_SessionId(Integer sessionId);
}
