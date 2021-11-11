package com.example.myapplication.login;

public class LoginRequestDto {

    String id;
    String pwd;

    public LoginRequestDto(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
}
