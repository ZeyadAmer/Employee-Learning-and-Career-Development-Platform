package org.example.Exceptions;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(){
        super("the password you entered is incorrect");
    }
}