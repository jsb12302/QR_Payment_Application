package com.example.myapplication.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.doain.Role;
import com.example.myapplication.message.Message;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;

import java.io.IOException;

import retrofit2.Response;


public class SignUpUserRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_user);
    }

    private String userId;
    private String userPwd;
    private String userPwd2;
    private String userName;
    private String userHP;


    public void SignUp_UserRequest(View v) {

        EditText userIdText = (EditText) findViewById(R.id.signup_user_id);
        EditText userPwdText = (EditText) findViewById(R.id.signup_user_pwd);
        EditText userPwd2Text = (EditText) findViewById(R.id.signup_user_pwd2);
        EditText userNameText = (EditText) findViewById(R.id.signup_user_name);
        EditText userHPText = (EditText) findViewById(R.id.signup_user_phnum);

        userId = userIdText.getText().toString();
        userPwd = userPwdText.getText().toString();
        userPwd2 = userPwd2Text.getText().toString();
        userName = userNameText.getText().toString();
        userHP = userHPText.getText().toString();

        new Thread(new ConnectRunner()).start();
    }

    public class ConnectRunner implements Runnable {

        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();

            try {
                UserSignUpDto userSignUpDto = new UserSignUpDto(
                        userId, userPwd, userPwd2, userName, userHP, Role.ROLE_USER);

                Response<Message> loginResponse = httpService.UserSignUpRequest(userSignUpDto).execute();
                System.out.println(userSignUpDto);
                Message message=loginResponse.body();
                System.out.println(message.getMessage());

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
