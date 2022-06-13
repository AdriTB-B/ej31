package com.adri.ej31.exception;

public class DeleteNotAcceptedException extends RuntimeException{
    public DeleteNotAcceptedException(String mensaje){
        super(mensaje);
    }
}
