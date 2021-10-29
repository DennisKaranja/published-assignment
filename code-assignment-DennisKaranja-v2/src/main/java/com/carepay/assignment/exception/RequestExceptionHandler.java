package com.carepay.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestException(RequestException e){
        //Create Exception Details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
         Exception apiException = new Exception(
                e.getMessage(),
                e, badRequest);

         //Return Response Entity
        return new ResponseEntity<>(apiException, badRequest);
    }
}
