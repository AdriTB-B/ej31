package com.adri.ej31.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocesableException extends RuntimeException {
    public UnprocesableException(String mensaje){
        super(mensaje);
    }
}
