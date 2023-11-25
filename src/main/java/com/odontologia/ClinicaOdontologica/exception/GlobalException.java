package com.odontologia.ClinicaOdontologica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> tratamientoResourceNotFoundException(ResourceNotFoundException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> tratamientoBadRequestException(BadRequestException bre){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bre.getMessage());
    }

}
