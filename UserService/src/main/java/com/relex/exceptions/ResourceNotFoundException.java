package com.relex.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(){
        super("Ресурс не найден");
    }
    /*Для кастомного сообщения*/
    public ResourceNotFoundException(String message){
        super(message);
    }

}
