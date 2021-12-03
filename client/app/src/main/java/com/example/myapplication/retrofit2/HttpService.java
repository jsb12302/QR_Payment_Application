package com.example.myapplication.retrofit2;

import com.example.myapplication.domain.Role;

import com.example.myapplication.login.LoginRequestDto;
import com.example.myapplication.owner.ui.check_sales.OrdersDTO;
import com.example.myapplication.owner.ui.menu_manage.MenuDto;
import com.example.myapplication.message.Message;
import com.example.myapplication.message.Status;
import com.example.myapplication.signup.OwnerSignUpDto;
import com.example.myapplication.signup.UserSignUpDto;
import com.example.myapplication.store.StoreSignUpDto;
import com.example.myapplication.user.map.Stores;

import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.http.*;


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

    @POST("/getOrder")
    Call<List<OrdersDTO>> getOrder(@Query("storeName")String storeName);

    @POST("/getStoreName")
    Call<StoreSignUpDto> getStoreName(@Query("loginId") String loginId);


    @POST("/confirmInfo") //비밀번호 변경 시 아이디, 가게 전화번호 확인
    Call<Boolean> getId_Phone(@Query("loginId") String loginId,
                              @Query("storeHP") String storeHP,
                              @Query("role") Role role);

    @POST("/changePwd")
    Call<Message> changePwdRequest(@Query("changeId")String changeId,
                                   @Query("changePwd") String changePwd,@Query("role") Role role);

    @POST("/removeMenu")
    Call<Message> removeMenuRequest(@Query("loginId")String loginId,
                                    @Query("menuName")String menuName);

    @GET("/img")
    Call<Void> uploadImage(@Query("loginId")String loginId,@Query("menuName")String menuName);

    @POST("/getStoreInfo")
    Call<List<Stores>> getStore();

    @POST("/getSeatOrders")
    Call<List<OrdersDTO>> getSeatOrders(@Query("tableNum")Integer tableNum,
                                   @Query("orderState")Integer orderState,
                                   @Query("storeName")String storeName);

    @POST("/getTableStatus")
    Call<List<OrdersDTO>> getTableStatus(@Query("storeName")String storeName,
                                         @Query("orderState")Integer orderState);

    @POST("/changeOrderState")
    Call<Message> updateOrderState(@Query("storeName")String storeName,
                          @Query("orderState")Integer orderState,
                          @Query("tableNum")Integer tableNum);
}
