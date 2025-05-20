package com.tfg.backend.data.ErrorMessages;

import com.tfg.backend.data.AttributesDatabase;

public abstract class ErrorMessageRNF {

    public static final String RNF = " no encontrado.";
    
    // ENTITIES \\
    public static final String USER_RNF = AttributesDatabase.USER_ENTITY + RNF;
    public static final String COUNTRY_RNF = AttributesDatabase.COUNTRY_ENTITY + RNF;
    public static final String TYPE_USER_RNF = AttributesDatabase.USER_TYPE_ENTITY + RNF;
    public static final String BOARDGAME_RNF = AttributesDatabase.BOARDGAME_ENTITY + RNF;
    public static final String BOARDGAME_TYPE_RNF = AttributesDatabase.BOARDGAME_TYPE_ENTITY + RNF;
    public static final String BOARDGAME_GENDER_RNF = AttributesDatabase.BOARDGAME_GENDER_ENTITY + RNF;
    public static final String PACK_RNF = AttributesDatabase.PACK_ENTITY + RNF;
    public static final String SESSION_RNF = AttributesDatabase.SESSION_ENTITY + RNF;
    public static final String STOCK_RNF = AttributesDatabase.STOCK_ENTITY + RNF;
    public static final String LOAN_STATE_RNF = AttributesDatabase.LOAN_STATE_ENTITY + RNF;
    public static final String LOAN_RNF = AttributesDatabase.LOAN_ENTITY + RNF;
    public static final String COLLECTION_RNF = AttributesDatabase.COLLECTION_ENTITY + RNF;
    public static final String USER_ASSOCIATE_RNF = AttributesDatabase.USER_ASSOCIATE_ENTITY + RNF;

    // ATTRIBUTES \\
    public static final String USER_MAIL_RNF = AttributesDatabase.USER_ATTRIBUTE_EMAIL + RNF;
}
