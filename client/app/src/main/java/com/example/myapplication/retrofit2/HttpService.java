package com.example.myapplication.retrofit2;

import com.example.myapplication.domain.Role;
import com.example.myapplication.login.LoginRequestDto;
import com.example.myapplication.owner.ui.menu_manage.MenuDto;
import com.example.myapplication.message.Message;
import com.example.myapplication.message.Status;
import com.example.myapplication.owner.ui.menu_manage.MenuDto;
import com.example.myapplication.signup.OwnerSignUpDto;
import com.example.myapplication.signup.UserSignUpDto;
import com.example.myapplication.store.StoreSignUpDto;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;


public interface HttpService {

    @Headers({"accept: application/json", "content-type: application/json"})

    @POST("/userSignUpRequest")
    Call<Message> UserSignUpRequest(@Body UserSignUpDto userSignUpDto);

    @POST("/storeSignUpRequest") //회원가입할 때 스토어 정보 가입 요청
    Call<Message> StoreSignUpRequest(@Body StoreSignUpDto storeSignUpDto);

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

    @POST("/getMenuInfo") //메뉴 사진,가격,이름 가져오기
    Call<List<MenuDto>> getMenu(@Query("loginId") String loginId);

    @POST("/confirmInfo") //비밀번호 변경 시 아이디, 가게 전화번호 확인
    Call<Boolean> getId_Phone(@Query("loginId") String loginId,
                              @Query("storeHP") String storeHP,
                              @Query("role") Role role);

    @POST("/changePwd")
    Call<Message> changePwdRequest(@Query("changePwd") String changePwd,@Query("role") Role role);

//    @POST("/getMenuImage") //이미지 파일 까지 가져오기
//    Call<List<MenuImageDto>> getMenuImage(@Query("loginId") String loginId);
}
