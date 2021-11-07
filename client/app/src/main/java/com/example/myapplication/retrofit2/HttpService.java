package com.example.myapplication.retrofit2;

import com.example.myapplication.doain.User;
import com.example.myapplication.login.LoginRequestDto;
import com.example.myapplication.message.Message;
import com.example.myapplication.signup.OwnerSignUpDto;
import com.example.myapplication.signup.UserSignUpDto;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.*;


public interface HttpService {

    @Headers({"accept: application/json", "content-type: application/json"})

<<<<<<< .merge_file_a03948
    @POST("/userSignUp")
    Call<Message> userSignUp(@Body UserSignUpDto userSignUpDto);

//    @GET("/userSignUp")
//    Call<User> userSignUp(@Query("userId") String userId,@Query("userPwd") String userPwd,
//                    @Query("userPwd2") String userPwd2,@Query("userName") String userName
//                    ,@Query("userHP") String userHP);
=======
    @POST("/userSignUpRequest")
    Call<Message> UserSignUpRequest(@Body UserSignUpDto userSignUpDto);
>>>>>>> .merge_file_a17732

    @POST("/ownerSingUpRequest")
    Call<Message> OwnerSignUpRequest(@Body OwnerSignUpDto ownerSignUpDto);

    @POST("/login")
    Call<Message> login(@Body LoginRequestDto loginRequestDto);



}
