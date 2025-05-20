package com.tfg.backend.services.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.backend.data.NotificationMessages;
import com.tfg.backend.models.Session;
import com.tfg.backend.models.SessionPlayer;
import com.tfg.backend.models.User;
import com.tfg.backend.repository.SessionRepository;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SessionNotificationService {

    @Autowired
    private SessionRepository sessionRepository;

    @Transactional
    @Scheduled(cron = NotificationMessages.SESSION_NOTICE_TIME) 
    public void sendSessionReminders() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        List<Session> sessions = sessionRepository.findBySessionDate(tomorrow);
        sessions.forEach(this::sendReminderForSession);
    }

    private void sendReminderForSession(Session session) {
        Set<User> participants = new HashSet<>();
        participants.add(session.getFkUser());        
        session.getSessionPlayers().stream()
            .map(SessionPlayer::getFkUser)
            .forEach(participants::add);

        Date expirationDate = session.getSessionDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedExpirationDate = dateFormat.format(expirationDate);
        
        participants.stream()
            .filter(User::isEmailNotifications)
            .forEach(user -> {
                String subject = NotificationMessages.SESSION_NOTICE_SUBJECT;
                String body = NotificationMessages.generateSessionReminderEmail(
                    user.getUserName(),
                    session.getSessionName(),
                    formattedExpirationDate
                );
                EmailSenderService.sendEmail(user.getEmail(), subject, body);
            });
    }
}
