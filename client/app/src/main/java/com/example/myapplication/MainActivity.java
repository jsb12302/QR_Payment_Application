package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;

import com.example.myapplication.doain.Role;
import com.example.myapplication.login.Login;
import com.example.myapplication.login.LoginRequestDto;
import com.example.myapplication.owner.OwnerMain;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.message.Message;
import com.example.myapplication.doain.User;


import org.json.JSONException;

import java.io.IOException;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen); //메인 화면
    }
    private String loginId;
    private String loginPwd;
    boolean go_owner_main=false;

    public void go_login(View v) throws JSONException {

        EditText userIdText = (EditText) findViewById(R.id.userId);
        EditText userPwdText = (EditText) findViewById(R.id.userPwd);

        EditText IdText = (EditText) findViewById(R.id.login_Id);
        EditText PwdText = (EditText) findViewById(R.id.login_Pwd);

        loginId=IdText.getText().toString();
        loginPwd=PwdText.getText().toString();

        new Thread(new ConnectRunner()).start();
    }

    public class ConnectRunner implements Runnable {

        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();

            LoginRequestDto loginRequestDto=new LoginRequestDto(loginId,loginPwd);

            try {
                Response<Message> loginResponse = httpService.login(loginRequestDto).execute();
                Message message=loginResponse.body();
                if (message.getRole()== Role.ROLE_OWNER){
                    Intent intent = new Intent(getApplicationContext(), OwnerMain.class);
                    startActivity(intent);
                }
            }catch (Exception e){
                System.out.println("오류");
            }
        }
    }

    public void move_login(View v){
        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
    }
    public void go_signup(View v){
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
    }
}