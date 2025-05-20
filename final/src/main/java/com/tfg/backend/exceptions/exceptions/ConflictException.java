package com.tfg.backend.exceptions.exceptions;
//Excepcion personalizada lanzada cuando los datos entran en conflicto
public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
