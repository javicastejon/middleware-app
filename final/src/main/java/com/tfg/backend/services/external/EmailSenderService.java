package com.tfg.backend.services.external;

import java.util.List;
import java.util.Properties;

import com.tfg.backend.data.NotificationMessages;
import com.tfg.backend.data.ErrorMessages.ErrorMessageOperations;
import com.tfg.backend.exceptions.exceptions.OperationErrorException;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;



public class EmailSenderService {

    public static void sendEmail(List<String> recipients, String subject, String body) {
        for (String recipient : recipients) {
            sendEmail(recipient, subject, body);
        }
    }

    public static void sendEmail(String recipient, String subject, String body) {
        try {     
            Message message = new MimeMessage(generatedSession());
            message.setFrom(new InternetAddress(NotificationMessages.NO_REPLY_MAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(body);
            message.setContent(body, "text/html; charset=utf-8");                
            Transport.send(message);
        } catch (MessagingException e) {
            throw new OperationErrorException(ErrorMessageOperations.emailSenderFail(recipient));
        }
    }

    private static Properties generatedProperties(){
        String host = "smtp.gmail.com";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    private static Session generatedSession(){
        Session session = Session.getInstance(generatedProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(NotificationMessages.NO_REPLY_MAIL, NotificationMessages.NO_REPLY_MAIL_TOKEN);
            }
        });

        return session;
    }
}
