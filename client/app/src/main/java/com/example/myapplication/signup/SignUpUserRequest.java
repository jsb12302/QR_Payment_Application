package com.example.myapplication.signup;

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
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.store.StoreSignUpDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class SignUpUserRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
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
            UserSignUpDto userSignUpDto = new UserSignUpDto(
                    userId, userPwd, userPwd2, userName, userHP, Role.ROLE_USER);
            Response<Message> loginResponse = null;

            try {
                loginResponse = httpService.UserSignUpRequest(userSignUpDto).execute();
            }catch (IOException e){
                e.printStackTrace();
            }

            String status = loginResponse.body().getMessage();

            if (!status.equals("가입 성공")) {
                Handler handler=new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpUserRequest.this);

                        builder.setTitle("SignUP Failed").setMessage(status+"\n"+"다시 입력 해주세요.");

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                },0);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }

}
