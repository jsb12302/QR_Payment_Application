package com.example.apitest.signup.owner;

import com.example.apitest.Role;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ownerId;
    private String ownerPwd;
    private String ownerPwd2;
    private String ownerNum; //사업자 번호
    private String storeName; //가맹점명
    private String storeLoc; //가맹점 위치
    private String ownerName; //가맹점주 이름
    private String storeHP; //가맹점 전화번호

    @Enumerated(EnumType.STRING)
    private Role role;


    public static Owner JoinOwner(String ownerId, String ownerPwd, String ownerPwd2,
                                  String ownerNum, String storeName,
                                  String storeLoc, String ownerName, String storeHP, Role role) {
        Owner owner=new Owner();
        owner.ownerId = ownerId;
        owner.ownerPwd = ownerPwd;
        owner.ownerPwd2 = ownerPwd2;
        owner.ownerNum = ownerNum;
        owner.storeName = storeName;
        owner.storeLoc = storeLoc;
        owner.ownerName = ownerName;
        owner.storeHP = storeHP;
        owner.role=role;
        return owner;
    }
}
