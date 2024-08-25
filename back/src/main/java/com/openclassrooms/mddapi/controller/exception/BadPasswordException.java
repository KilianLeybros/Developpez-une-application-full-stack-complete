package com.openclassrooms.mddapi.controller.exception;

public class BadPasswordException extends RuntimeException{
    public BadPasswordException(String message){
        super(message);
    }
}
