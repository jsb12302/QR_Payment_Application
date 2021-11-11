package com.example.apitest.signup.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    String id;
    String pwd;

    public LoginDto(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
}
