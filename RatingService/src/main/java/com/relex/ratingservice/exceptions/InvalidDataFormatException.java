package com.relex.ratingservice.exceptions;

public class InvalidDataFormatException extends RuntimeException{

    public InvalidDataFormatException(String message) {
        super(message);
    }
}
