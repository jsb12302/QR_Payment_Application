package com.example.myapplication.retrofit2;

import android.view.Menu;

import com.example.myapplication.login.LoginRequestDto;
import com.example.myapplication.owner.ui.menu_manage.MenuDto;
import com.example.myapplication.message.Message;
import com.example.myapplication.message.Status;
import com.example.myapplication.signup.OwnerSignUpDto;
import com.example.myapplication.signup.UserSignUpDto;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;


public interface HttpService {

    @Headers({"accept: application/json", "content-type: application/json"})

    @POST("/userSignUpRequest")
    Call<Message> UserSignUpRequest(@Body UserSignUpDto userSignUpDto);

    @POST("/ownerSignUpRequest")
    Call<Message> OwnerSignUpRequest(@Body OwnerSignUpDto ownerSignUpDto);

    @POST("/login")
    Call<Message> login(@Body LoginRequestDto loginRequestDto);

    @POST("/addMenuRequest")
    Call<Status> addMenu(@Body MenuDto menuDto);

    @Multipart //이미지 전송
    @POST("/fileUpload")
    Call<Message> menuRequest(@Part MultipartBody.Part postImg,
                              @PartMap HashMap<String, RequestBody> data);

    @POST("/getMenuInfo")
    Call<List<MenuDto>> getMenu(@Query("loginId") String loginId);

//    @POST("/getMenuImage") //이미지 파일 까지 가져오기
//    Call<List<MenuImageDto>> getMenuImage(@Query("loginId") String loginId);
}
