package com.example.apitest.exception;

public class SameSignUpInfoFoundException extends RuntimeException{

    public SameSignUpInfoFoundException() {
        super();
    }
    public SameSignUpInfoFoundException(String message) {
        super(message);
    }
}
