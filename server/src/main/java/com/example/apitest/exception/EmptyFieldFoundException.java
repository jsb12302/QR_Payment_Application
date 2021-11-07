package com.example.apitest.exception;

public class EmptyFieldFoundException extends RuntimeException{

    public EmptyFieldFoundException() {
        super();
    }
    public EmptyFieldFoundException(String message) {
        super(message);
    }
}
