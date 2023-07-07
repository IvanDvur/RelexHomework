package com.relex.ratingservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {



    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidDataFormatException.class)
    public ResponseEntity<Map<String,Object>> invalidDataFormatExceptionHandler(InvalidDataFormatException e){
        Map<String,Object> response = new HashMap<>();
        response.put("message",e.getMessage());
        response.put("status",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
