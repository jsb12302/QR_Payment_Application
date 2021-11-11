package com.example.myapplication.retrofit2;

import com.example.myapplication.login.LoginRequestDto;
import com.example.myapplication.message.Message;
import com.example.myapplication.signup.OwnerSignUpDto;
import com.example.myapplication.signup.UserSignUpDto;

import retrofit2.Call;
import retrofit2.http.*;


public interface HttpService {

    @Headers({"accept: application/json", "content-type: application/json"})

    @POST("/userSignUpRequest")
    Call<Message> UserSignUpRequest(@Body UserSignUpDto userSignUpDto);

    @POST("/ownerSingUpRequest")
    Call<Message> OwnerSignUpRequest(@Body OwnerSignUpDto ownerSignUpDto);

    @POST("/login")
    Call<Message> login(@Body LoginRequestDto loginRequestDto);


}
