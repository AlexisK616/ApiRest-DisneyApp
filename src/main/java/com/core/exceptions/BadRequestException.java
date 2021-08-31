package com.core.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException() {
    }

    public BadRequestException(String string) {
        super(string);
    }
    
}
