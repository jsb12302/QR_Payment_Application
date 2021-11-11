package com.example.myapplication.signup;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.doain.Role;
import com.example.myapplication.message.Message;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;

import java.io.IOException;

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
    private String corpNum; //법인 번호
    private String storeName; //가맹점명
    private String storeLoc; //가맹점 위치
    private String ownerName; //가맹점주 이름
    private String storeHP; //가맹점 전화번호

    public void SignUp_OwnerRequest(View v){

        EditText ownerIdText = (EditText) findViewById(R.id.signup_ower_id);
        EditText ownerPwdText = (EditText) findViewById(R.id.signup_ower_pwd);
        EditText ownerPwd2Text = (EditText) findViewById(R.id.signup_ower_pwd2);
        EditText ownerNumText = (EditText) findViewById(R.id.signup_ower_num);
        EditText ownerCorpNameText = (EditText) findViewById(R.id.signup_owner_corpnum);
        EditText ownerStoreNameText = (EditText) findViewById(R.id.signup_owner_storename);
        EditText ownerStoreLocText = (EditText) findViewById(R.id.signup_owner_storeloc);
        EditText ownerNameText = (EditText) findViewById(R.id.signup_ower_ownername);
        EditText ownerStoreHpText = (EditText) findViewById(R.id.signup_ower_storehp);

        ownerId=ownerIdText.getText().toString();
        ownerPwd = ownerPwdText.getText().toString();
        ownerPwd2=ownerPwd2Text.getText().toString();
        ownerNum=ownerNumText.getText().toString();
        corpNum=ownerCorpNameText.getText().toString();
        storeName=ownerStoreNameText.getText().toString();
        storeLoc=ownerStoreLocText.getText().toString();
        ownerName=ownerNameText.getText().toString();
        storeHP=ownerStoreHpText.getText().toString();


        new Thread(new ConnectRunner()).start();
    }
    public class ConnectRunner implements Runnable {

        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();

            try {
                OwnerSignUpDto ownerSignUpDto = new OwnerSignUpDto(
                        ownerId, ownerPwd, ownerPwd2, ownerNum, corpNum,
                        storeName, storeLoc, ownerName, storeHP,Role.ROLE_OWNER);

                Response<Message> loginResponse = httpService.OwnerSignUpRequest(ownerSignUpDto).execute();
                System.out.println(ownerSignUpDto);
                Message message=loginResponse.body();
                System.out.println(message.getMessage());

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


}
