package com.example.myapplication.domain;


import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class User { //받을때 쓰는 객체


    @SerializedName("userId")
    private String userId;
    @SerializedName("userPwd")
    private String userPwd;
    @SerializedName("userPwd2")
    private String userPwd2;
    @SerializedName("userName")
    private String userName;
    @SerializedName("userHP")
    private String userHP;

}
