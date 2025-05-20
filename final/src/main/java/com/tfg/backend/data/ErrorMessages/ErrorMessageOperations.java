package com.tfg.backend.data.ErrorMessages;

public class ErrorMessageOperations {
    
    //USER
    public static final String FAIL_LOGIN = "El correo electrónico o la contraseña son incorrectos.";

    public static String emailSenderFail (){
        return "No se ha podido mandar la notificación email.";
    }

    public static String emailSenderFail(String recipient) {
        return "No se ha podido mandar la notificación email a " + recipient;
    }
}
