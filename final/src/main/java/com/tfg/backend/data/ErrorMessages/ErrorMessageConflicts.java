package com.tfg.backend.data.ErrorMessages;

import com.tfg.backend.data.AttributesDatabase;

public abstract class ErrorMessageConflicts {
    

    // USER
    public static String userNameExists (String nameUser){
        return "Ya existe un "+ AttributesDatabase.USER_ENTITY + " con el nombre: " + nameUser;
    }

    public static String emailExists (String email){
        return "Ya existe un "+ AttributesDatabase.USER_ENTITY + " con el " + AttributesDatabase.USER_ATTRIBUTE_EMAIL + ": " + email;
    }

    // BOARDGAME
    public static String boardGameNameExists (String boardgameName){
        return "Ya existe un "+ AttributesDatabase.BOARDGAME_ENTITY + " con el nombre: " + boardgameName;
    }

    // PACK
    public static String packNameByUserExists (String packName){
        return "Ya existe un " + AttributesDatabase.PACK_ENTITY + " con el nombre: " + packName;
    }

    public static String boardgameExistsInPack (){
        return "El " + AttributesDatabase.BOARDGAME_ENTITY + " ya está en el " + AttributesDatabase.PACK_ENTITY + " del " + AttributesDatabase.USER_ENTITY;
    }

    // COLLECTION
    public static String boardgameExistsInCollection (){
        return "El " + AttributesDatabase.BOARDGAME_ENTITY + " ya está en la " + AttributesDatabase.COLLECTION_ENTITY + " del " + AttributesDatabase.USER_ENTITY;
    }

    public static String boardGameApiRefExists() {
        return "La referencia a BGG ya existe."; 
    }


}
