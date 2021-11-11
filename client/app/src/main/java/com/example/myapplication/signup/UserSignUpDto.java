package com.example.myapplication.signup;

import com.example.myapplication.domain.Role;

public class UserSignUpDto {

    private String userId;
    private String userPwd;
    private String userPwd2;
    private String userName;
    private String userHP;
    Role role;

    public UserSignUpDto(String userId, String userPwd,
                         String userPwd2, String userName,
                         String userHP, Role role) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userPwd2 = userPwd2;
        this.userName = userName;
        this.userHP = userHP;
        this.role = role;
    }
}