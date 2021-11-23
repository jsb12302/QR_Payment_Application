package com.example.apitest.signup.owner;

import com.example.apitest.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class OwnerDTO {

    private String ownerId;
    private String ownerPwd;
    private String storeHP; //가맹점 전화번호
    private Role role;

    public OwnerDTO(String ownerId, String ownerPwd, String storeHP, Role role) {
        this.ownerId = ownerId;
        this.ownerPwd = ownerPwd;
        this.storeHP = storeHP;
        this.role = role;
    }
}
