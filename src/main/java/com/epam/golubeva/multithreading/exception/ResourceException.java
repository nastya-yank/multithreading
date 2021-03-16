package com.epam.golubeva.multithreading.exception;

public class ResourceException extends Exception {
    public ResourceException(){}

    public ResourceException(String message){
        super(message);
    }

    public ResourceException(Throwable cause){
        super(cause);
    }

    public ResourceException(String message, Throwable cause){
        super(message,cause);
    }
}
