package com.tfg.backend.data.ErrorMessages;

import com.tfg.backend.config.DataBaseConfig;
import com.tfg.backend.data.AttributesDatabase;

public abstract class ErrorMessageRequests {
    
    //GENERAL REQUEST
    private static final String VALUE_NULL = " necesita un valor.";
    private static final String VALUE_NOT_VALID = " no es válido.";

    // SHARED
    public static final String USER_NULL = "El" + AttributesDatabase.USER_ENTITY + VALUE_NULL;
    public static final String USER_NOT_VALID = "El" + AttributesDatabase.USER_ENTITY + VALUE_NOT_VALID;
    public static final String SESSION_NULL = "La" + AttributesDatabase.SESSION_ENTITY + VALUE_NULL;
    public static final String BOARDGAME_NULL = "El" + AttributesDatabase.BOARDGAME_ENTITY + VALUE_NULL;
    public static final String LOAN_NULL = "El" + AttributesDatabase.LOAN_ENTITY + VALUE_NULL;
    public static final String STOCK_NULL = "El" + AttributesDatabase.STOCK_ENTITY + VALUE_NULL;
    
    
    // Utils
    public static final String FUTURE_OR_PRESENT_DATE = "La fecha no puede ser anterior al día actual";
    public static final String ASSOCIATE_NOT_VALID =  "El " + AttributesDatabase.USER_ENTITY + " anfitrión y asociado no pueden ser el mismo.";

    // USER REQUEST
    public static final String USER_NAME_USER_NULL = "El " + AttributesDatabase.USER_ATTRIBUTE_NAME_USER + VALUE_NULL;
    public static final String USER_NAME_USER_SIZE = "El " +  AttributesDatabase.USER_ATTRIBUTE_NAME_USER + " debe tener entre " + DataBaseConfig.USER_NAME_MIN_CHARACTERS + " y " + DataBaseConfig.USER_NAME_MAX_CHARACTERS + " caracteres.";
    public static final String USER_PASS_HASH_NULL = "La " + AttributesDatabase.USER_ATTRIBUTE_PASS_HASH + VALUE_NULL;
    public static final String USER_PASS_HASH_SIZE = "La " + AttributesDatabase.USER_ATTRIBUTE_PASS_HASH + " debe tener un máximo de " + DataBaseConfig.USER_PASS_HASH_MAX_CHARACTERS + " caracteres.";
    public static final String USER_EMAIL_NULL = "El " + AttributesDatabase.USER_ATTRIBUTE_EMAIL + VALUE_NULL;
    public static final String USER_EMAIL_SIZE = "El " + AttributesDatabase.USER_ATTRIBUTE_EMAIL + " debe tener entre " + DataBaseConfig.USER_EMAIL_MIN_CHARACTERS + " y " + DataBaseConfig.USER_EMAIL_MAX_CHARACTERS + " caracteres.";
    public static final String USER_EMAIL_VALID = "El " + AttributesDatabase.USER_ATTRIBUTE_EMAIL + VALUE_NOT_VALID;
    public static final String USER_COUNTRY_NULL = "El " + AttributesDatabase.COUNTRY_ENTITY + VALUE_NULL;
    public static final String USER_EMAIL_NOTIFICATION_NULL= "El permiso para " + AttributesDatabase.USER_ATTRIBUTE_EMAIL_NOTIFICATIONS + VALUE_NULL;
    public static final String USER_CREATION_DATE_NULL = "La " + AttributesDatabase.USER_ATTRIBUTE_BIRTHDAY_DATE + VALUE_NULL;
    public static final String USER_TYPE_NULL = "El " + AttributesDatabase.USER_TYPE_ENTITY + VALUE_NULL;

    // BOARDGAME REQUEST
    public static final String BOARDGAME_NAME_NULL = "El " + AttributesDatabase.BOARDGAME_ATTRIBUTE_NAME + VALUE_NULL;
    public static final String BOARDGAME_NAME_SIZE = "El " +  AttributesDatabase.BOARDGAME_ATTRIBUTE_NAME + " debe tener entre " + DataBaseConfig.BOARDGAME_NAME_MIN_CHARACTERS + " y " + DataBaseConfig.BOARDGAME_NAME_MAX_CHARACTERS + " caracteres.";
    public static final String BOARDGAME_PLAYERS_MIN_NULL = "El " + AttributesDatabase.BOARDGAME_ATTRIBUTE_PLAYERS_MIN + VALUE_NULL;
    public static final String BOARDGAME_PLAYERS_MAX_NULL = "El " + AttributesDatabase.BOARDGAME_ATTRIBUTE_PLAYERS_MAX + VALUE_NULL;
    public static final String BOARDGAME_DESCRIPTION_SIZE = "La " + AttributesDatabase.BOARDGAME_ATTRIBUTE_DESCRIPTION + " debe tener máximo " + DataBaseConfig.BOARDGAME_DESCRIPTION_MAX_CHARACTERS + " carácteres.";
    public static final String BOARDGAME_REF_API_NULL = "La " + AttributesDatabase.BOARDGAME_ATTRIBUTE_API_BGG_REF + VALUE_NULL;
    public static final String BOARDGAME_TYPE_NULL = "El " + AttributesDatabase.BOARDGAME_ATTRIBUTE_TYPE_BOARDGAME + VALUE_NULL;
    public static final String BOARDGAME_GENDER_NULL = "El " + AttributesDatabase.BOARDGAME_ATTRIBUTE_GENDER + VALUE_NULL;
 
    // PACK REQUEST
    public static final String PACK_NAME_NULL = "El " + AttributesDatabase.PACK_ATTRIBUTE_NAME + VALUE_NULL;
    public static final String PACK_NAME_SIZE = "El " + AttributesDatabase.PACK_ATTRIBUTE_NAME + " debe tener entre " + DataBaseConfig.PACK_NAME_MIN_CHARACTERS + " y " + DataBaseConfig.PACK_NAME_MAX_CHARACTERS + " caracteres.";

    // SESSION REQUEST
    public static final String SESSION_NAME_NULL = "El " + AttributesDatabase.SESSION_ATTRIBUTE_NAME + VALUE_NULL;
    public static final String SESSION_NAME_SIZE = "El " + AttributesDatabase.SESSION_ATTRIBUTE_NAME + " debe tener entre " + DataBaseConfig.SESSION_NAME_MIN_CHARACTERS + " y " + DataBaseConfig.SESSION_NAME_MAX_CHARACTERS + " caracteres.";
    public static final String SESSION_DATE_NULL = "El " + AttributesDatabase.SESSION_ATTRIBUTE_DATE + VALUE_NULL;

    // MEETING REQUEST
    public static final String MEETING_DURATION_NULL = "La " + AttributesDatabase.MEETING_ATTRIBUTE_DURATION + VALUE_NULL;
    public static final String MEETING_DURATION_NOT_VALID = "El valor de la " + AttributesDatabase.MEETING_ATTRIBUTE_DURATION + VALUE_NOT_VALID;

    // STOCK REQUEST
    public static final String STOCK_UNITS_NULL = "El " + AttributesDatabase.STOCK_ATTRIBUTE_UNITS + VALUE_NULL;
    public static final String STOCK_UNITS_NOT_VALID = "El valor de la " + AttributesDatabase.STOCK_ATTRIBUTE_UNITS + VALUE_NOT_VALID;

    // LOAN REQUEST
    public static final String LOAN_DATE_NOT_VALID = "El " + AttributesDatabase.LOAN_ATTRIBUTE_DATE + VALUE_NOT_VALID;
    public static final String LOAN_EXPIRATION_DATE_NULL = "El " + AttributesDatabase.LOAN_ATTRIBUTE_EXPIRATION_DATE + VALUE_NULL;
    public static final String LOAN_EXPIRATION_DATE_NOT_VALID = "El " + AttributesDatabase.LOAN_ATTRIBUTE_EXPIRATION_DATE + VALUE_NOT_VALID;
    public static final String LOAN_STATE_NULL = "El " + AttributesDatabase.LOAN_STATE_ENTITY + VALUE_NULL;
}




 
 
