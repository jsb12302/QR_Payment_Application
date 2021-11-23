package com.example.myapplication.signup;


import com.example.myapplication.domain.Role;

public class OwnerSignUpDto {

    private String ownerId;
    private String ownerPwd;
    private String storeHP; //가맹점 전화번호
    Role role;

    public OwnerSignUpDto(String ownerId, String ownerPwd,
                          String storeHP, Role role) {
        this.ownerId = ownerId;
        this.ownerPwd = ownerPwd;
        this.storeHP = storeHP;
        this.role=role;
    }
}
