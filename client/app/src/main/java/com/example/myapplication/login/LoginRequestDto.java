package com.example.myapplication.login;

import com.example.myapplication.domain.Role;

public class LoginRequestDto {

    String id;
    String pwd;
    Role role;

    public LoginRequestDto(String id, String pwd, Role role) {
        this.id = id;
        this.pwd = pwd;
        this.role = role;
    }
}
