package com.tfg.backend.exceptions.exceptions;
//Excepcion personalizada lanzada cuando una operacion de un servicio expecifico no ha podido realizarse con exito
public class OperationErrorException extends RuntimeException {
    public OperationErrorException(String message) {
        super(message);
    }
}
