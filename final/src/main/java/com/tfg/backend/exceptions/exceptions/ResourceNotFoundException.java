package com.tfg.backend.exceptions.exceptions;
//Excepcion lanzada cuando el recurso no es encontrado
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
