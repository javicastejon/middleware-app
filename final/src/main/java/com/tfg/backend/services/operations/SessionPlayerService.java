package com.tfg.backend.services.operations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.backend.data.ErrorMessages.ErrorMessageRNF;
import com.tfg.backend.exceptions.exceptions.ResourceNotFoundException;
import com.tfg.backend.models.Session;
import com.tfg.backend.models.SessionPlayer;
import com.tfg.backend.models.User;
import com.tfg.backend.repository.SessionPlayerRepository;
import com.tfg.backend.repository.SessionRepository;
import com.tfg.backend.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;


@Service
public class SessionPlayerService {

    @Autowired
    SessionPlayerRepository sessionPlayerRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void addPlayersToSession(Integer sessionId, List<Integer> playersIds) {
        Session session = sessionRepository.findById(sessionId)
        .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.SESSION_RNF));
        for (Integer userId : playersIds) {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.USER_RNF));
            if (!sessionPlayerRepository.existsByFkSessionAndFkUser(session, user)) {
                SessionPlayer sessionPlayer = new SessionPlayer();
                sessionPlayer.setFkSession(session);
                sessionPlayer.setFkUser(user);           
                sessionPlayerRepository.save(sessionPlayer);
            }
        }
    }

    @Transactional
    public void updatePlayersToSession(Integer sessionId, List<Integer> playersIds) {
        if (playersIds == null) {
            return;
        }
        Session session = sessionRepository.findById(sessionId)
        .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.SESSION_RNF));
        List<SessionPlayer> currentPlayers = sessionPlayerRepository.findByFkSession_SessionId(sessionId);
        Set<Integer> newPlayerIds = new HashSet<>(playersIds);
        Set<Integer> playersToKeep = new HashSet<>();
        for (SessionPlayer player : currentPlayers) {
            Integer userId = player.getFkUser().getUserId();
            if (newPlayerIds.contains(userId)) {
                playersToKeep.add(userId); 
            } else {
                sessionPlayerRepository.delete(player); 
            }
        }
        for (Integer newPlayerId : playersIds) {
            if (!playersToKeep.contains(newPlayerId)) {
                User user = userRepository.findById(newPlayerId)
                    .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.USER_RNF));
                
                SessionPlayer newPlayer = new SessionPlayer();
                newPlayer.setFkSession(session);
                newPlayer.setFkUser(user);
                sessionPlayerRepository.save(newPlayer);
            }
        }
    }
    

    @Transactional
    public List<SessionPlayer> getAllPlayersToSession(Integer sessionId) {
        sessionRepository.findById(sessionId).orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.SESSION_RNF));
        return sessionPlayerRepository.findByFkSession_SessionId(sessionId);
    }
}
