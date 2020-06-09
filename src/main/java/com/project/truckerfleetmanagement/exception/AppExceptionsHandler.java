package com.project.truckerfleetmanagement.exception;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchVehicleException.class})
    public ResponseEntity<Object> handleNoSuchVehicleException(NoSuchVehicleException ex){
        String errorMessageDescription = ex.getLocalizedMessage();
        if(errorMessageDescription == null) errorMessageDescription = ex.toString();
        return new ResponseEntity<>(
          errorMessageDescription, new HttpHeaders(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = {InvalidDataException.class})
    public ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex){
        String errorMessageDescription = ex.getLocalizedMessage();
        if(errorMessageDescription == null) errorMessageDescription = ex.toString();
        return new ResponseEntity<>(
                errorMessageDescription, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY
        );
    }
}
