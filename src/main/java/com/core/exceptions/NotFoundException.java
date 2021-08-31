package com.core.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
    }

    public NotFoundException(String string) {
        super(string);
    }
    
}
