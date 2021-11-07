package com.example.apitest.signup.owner;

import com.example.apitest.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerDTO {

    private String ownerId;
    private String ownerPwd;
    private String ownerPwd2;
    private String ownerNum; //사업자 번호
    private String corpNum; //법인 번호
    private String storeName; //가맹점명
    private String storeLoc; //가맹점 위치
    private String ownerName; //가맹점주 이름
    private String storeHP; //가맹점 전화번호
    private Role role;

    public OwnerDTO(String ownerId, String ownerPwd, String ownerPwd2,
                    String ownerNum, String corpNum, String storeName,
                    String storeLoc, String ownerName, String storeHP, Role role) {
        this.ownerId = ownerId;
        this.ownerPwd = ownerPwd;
        this.ownerPwd2 = ownerPwd2;
        this.ownerNum = ownerNum;
        this.corpNum = corpNum;
        this.storeName = storeName;
        this.storeLoc = storeLoc;
        this.ownerName = ownerName;
        this.storeHP = storeHP;
        this.role = role;
    }
}
