package com.example.apitest.signup.user;

import com.example.apitest.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String userId;
    private String userPwd;
    private String userPwd2;
    private String userName;
    private String userHP;
    private Role role;

    public UserDTO(String userId, String userPwd, String userPwd2, String userName, String userHP, Role role) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userPwd2 = userPwd2;
        this.userName = userName;
        this.userHP = userHP;
        this.role=role;
    }
}
