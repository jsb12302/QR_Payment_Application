package com.example.apitest.login;

import lombok.Getter;
import lombok.Setter;
import com.example.apitest.Role;

@Getter
@Setter
public class LoginDto {

    String id;
    String pwd;
    Role role;

    public LoginDto(String id, String pwd, Role role) {
        this.id = id;
        this.pwd = pwd;
        this.role = role;
    }
}
