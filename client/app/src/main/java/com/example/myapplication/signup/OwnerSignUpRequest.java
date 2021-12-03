package com.example.myapplication.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.domain.Role;
import com.example.myapplication.message.Message;
import com.example.myapplication.owner.OwnerMain;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.store.StoreSignUpDto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import lombok.SneakyThrows;
import retrofit2.Response;

public class OwnerSignUpRequest extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.signup_owner);
    }

    private String ownerId;
    private String ownerPwd;
    private String ownerPwd2;
    private String ownerNum; //사업자 번호
    private String storeName; //가맹점명
    private String storeLoc; //가맹점 위치
    private String ownerName; //가맹점주 이름
    private String storeHP; //가맹점 전화번호
    private static Double storeLatitude;
    private static Double storeLongitude;

    public void SignUp_OwnerRequest(View v) throws UnsupportedEncodingException, JSONException, InterruptedException {

        EditText ownerIdText = (EditText) findViewById(R.id.signup_ower_id);
        EditText ownerPwdText = (EditText) findViewById(R.id.signup_ower_pwd);
        EditText ownerPwd2Text = (EditText) findViewById(R.id.signup_ower_pwd2);
        EditText ownerNumText = (EditText) findViewById(R.id.signup_ower_num);
        EditText ownerStoreNameText = (EditText) findViewById(R.id.signup_owner_storename);
        EditText ownerStoreLocText = (EditText) findViewById(R.id.signup_owner_storeloc);
        EditText ownerNameText = (EditText) findViewById(R.id.signup_ower_ownername);
        EditText ownerStoreHpText = (EditText) findViewById(R.id.signup_ower_storehp);

        ownerId=ownerIdText.getText().toString();
        ownerPwd = ownerPwdText.getText().toString();
        ownerPwd2=ownerPwd2Text.getText().toString();
        ownerNum=ownerNumText.getText().toString();
        storeName=ownerStoreNameText.getText().toString();
        storeLoc=ownerStoreLocText.getText().toString();
        ownerName=ownerNameText.getText().toString();
        storeHP=ownerStoreHpText.getText().toString();

        new Thread(new ApiRunner()).start();
        Thread.sleep(1000);
        new Thread(new ConnectRunner()).start();
    }

    public class ApiRunner implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("storeloc"+storeLoc);
            String storeLocEncode = URLEncoder.encode(storeLoc, "UTF-8");
            System.out.println(storeLocEncode);

            String url = "https://dapi.kakao.com/v2/local/search/address.json?analyze_type=similar&page=1&size=10&query="+storeLocEncode;
            String authorizationKey = "KakaoAK a85dda23e2cf591d865b03498582c750";
            URL urlObject = null;
            HttpURLConnection con = null;
            StringBuffer response = new StringBuffer();
            String result="";
            try {
                urlObject = new URL(url);
                con = (HttpURLConnection) urlObject.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Authorization", authorizationKey);

                int responseCode = con.getResponseCode();
                System.out.println("Response Code : " + responseCode);

                BufferedReader iny = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String output;

                while ((output = iny.readLine()) != null) {
                    response.append(output);
                }

                iny.close();
                result = response.toString();
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            }

            JSONObject jObject = new JSONObject(result);

            JSONArray jArray = jObject.getJSONArray("documents");
            JSONObject jsonObject = jArray.getJSONObject(0);
            JSONObject jsonObject2 = jsonObject.getJSONObject("address");

            storeLatitude=jsonObject2.getDouble("y");
            storeLongitude=jsonObject2.getDouble("x");
            System.out.println(storeLatitude);
            System.out.println(storeLongitude);
        }
    }

    public class ConnectRunner implements Runnable {

        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();

            try {
                System.out.println(storeLatitude.getClass());
                System.out.println(storeLatitude);
                OwnerSignUpDto ownerSignUpDto = new OwnerSignUpDto(
                        ownerId, ownerPwd,storeHP,Role.ROLE_OWNER);
                StoreSignUpDto storeSignUpDto=new StoreSignUpDto(
                        ownerId,storeName,storeHP,storeLoc,storeLatitude,storeLongitude,null,ownerNum);
                Response<Message> loginResponse = httpService.OwnerSignUpRequest(ownerSignUpDto).execute();
                Response<Message> StoreSignUpResponse=httpService.StoreSignUpRequest(storeSignUpDto).execute();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }catch (IOException e){

                Handler handler=new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(OwnerSignUpRequest.this);

                        builder.setTitle("SignUP Failed").setMessage("중복된 회원 정보 입니다."+"\n"+"다시 입력 해주세요.");

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                },0);

                e.printStackTrace();
            }
        }
    }


}
