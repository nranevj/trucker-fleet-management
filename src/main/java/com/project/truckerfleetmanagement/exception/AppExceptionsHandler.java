package com.project.truckerfleetmanagement.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoSuchVehicleException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchVehicleException(NoSuchVehicleException ex){
        String errorMessageDescription = ex.getLocalizedMessage();
        if(errorMessageDescription == null) errorMessageDescription = ex.toString();
        return new ResponseEntity<>(
          errorMessageDescription, new HttpHeaders(), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = {InvalidDataException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex){
        String errorMessageDescription = ex.getLocalizedMessage();
        if(errorMessageDescription == null) errorMessageDescription = ex.toString();
        return new ResponseEntity<>(
                errorMessageDescription, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY
        );
    }
}
