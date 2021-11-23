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
    private String storeHP; //가맹점 전화번호

    @Enumerated(EnumType.STRING)
    private Role role;


    public static Owner JoinOwner(String ownerId, String ownerPwd, String storeHP, Role role) {
        Owner owner=new Owner();
        owner.ownerId = ownerId;
        owner.ownerPwd = ownerPwd;
        owner.storeHP = storeHP;
        owner.role=role;
        return owner;
    }
}
