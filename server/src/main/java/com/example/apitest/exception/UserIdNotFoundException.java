package com.example.apitest.exception;

public class UserIdNotFoundException extends RuntimeException{

    public UserIdNotFoundException() {
        super();
    }
    public UserIdNotFoundException(String message) {
        super(message);
    }
}