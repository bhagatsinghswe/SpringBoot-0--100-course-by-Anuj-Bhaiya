package com.bhagat.springbootweek2.Spring.Boot.Week2.exceptions;



public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
