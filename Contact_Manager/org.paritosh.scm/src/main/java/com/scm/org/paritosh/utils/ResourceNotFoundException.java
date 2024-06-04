package com.scm.org.paritosh.utils;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(){
        super("Resource Not found");
    }

}
