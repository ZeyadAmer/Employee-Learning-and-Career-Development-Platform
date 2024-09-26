package org.example.Exceptions;

public class NotManagerException extends RuntimeException{
    public NotManagerException(){
        super("this email is not associated to a manager");
    }
}