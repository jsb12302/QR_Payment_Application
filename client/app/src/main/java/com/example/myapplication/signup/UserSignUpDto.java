package com.example.myapplication.signup;

import com.example.myapplication.doain.Role;

import lombok.Getter;
import lombok.Setter;
import retrofit2.http.Query;

@Getter
@Setter
public class UserSignUpDto { //보낼때 쓰는 객체
    String userId;
    String userPwd;
    String userPwd2;
    String userName;
    String userHP;
    Role role;

    public UserSignUpDto(String userId, String userPwd, String userPwd2, String userName, String userHP, Role role) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userPwd2 = userPwd2;
        this.userName = userName;
        this.userHP = userHP;
        this.role=role;
    }

}
