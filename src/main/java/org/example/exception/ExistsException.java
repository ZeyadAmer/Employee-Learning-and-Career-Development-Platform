package org.example.exception;

public class ExistsException extends RuntimeException{
    public ExistsException(String message){
        super(message);
    }
}
