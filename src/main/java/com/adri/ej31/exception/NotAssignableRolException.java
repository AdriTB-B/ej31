package com.adri.ej31.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotAssignableRolException extends RuntimeException{
    public NotAssignableRolException(String mensaje){
        super(mensaje);
    }
}