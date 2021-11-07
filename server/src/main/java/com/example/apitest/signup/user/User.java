package com.example.apitest.signup.user;

import com.example.apitest.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@Builder
@NoArgsConstructor
//@AllArgsConstructor
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userId;
    private String userPwd;
    private String userPwd2;
    private String userName;
    private String userHP;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public static User JoinUser(String userId, String userPwd, String userPwd2,
                                String userName, String userHP,Role role){
        User user=new User();
        user.userId=userId;
        user.userPwd=userPwd;
        user.userPwd2=userPwd2;
        user.userName=userName;
        user.userHP=userHP;
        user.role=role;
        return user;
    }

//    public User(int id, String userId, String userPwd, String userPwd2, String userName, String userHP) {
//        this.id = id;
//        this.userId = userId;
//        this.userPwd = userPwd;
//        this.userPwd2 = userPwd2;
//        this.userName = userName;
//        this.userHP = userHP;
//    }

}
