package com.adri.ej31.exception.handler;

import com.adri.ej31.exception.CustomError;
import com.adri.ej31.exception.DeleteNotAcceptedException;
import com.adri.ej31.exception.IncorrectRolException;
import com.adri.ej31.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<CustomError> handleNotFoundException(NotFoundException ex) {
        CustomError error = new CustomError(
                new Date(),
                404,
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectRolException.class)
    public final ResponseEntity<CustomError> handleNotAssignableRolException(IncorrectRolException ex) {
        CustomError error = new CustomError(
                new Date(),
                406,
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DeleteNotAcceptedException.class)
    public final ResponseEntity<CustomError> handleDeleteNotAcceptedExceptionException(DeleteNotAcceptedException ex) {
        CustomError error = new CustomError(
                new Date(),
                406,
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

//  Captura de excepción propia (Lanzamiento manual)
//    @ExceptionHandler(UnprocesableException.class)
//    public final ResponseEntity<CustomError> handleUnprocesabeException(UnprocesableException ex) {
//        CustomError error = new CustomError(
//                new Date(),
//                422,
//                ex.getMessage()
//        );
//        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
//    }

//  Captura de excepción automática de @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        CustomError error = new CustomError(
                new Date(),
                422,
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
