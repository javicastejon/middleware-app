package com.tfg.backend.services.external;

import java.util.Random;

public abstract class PasswordRecoveryService {

    public static String generatePassword() {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder(10);
        
        for (int i = 0; i < 10; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }      
        return password.toString();
    }
}
