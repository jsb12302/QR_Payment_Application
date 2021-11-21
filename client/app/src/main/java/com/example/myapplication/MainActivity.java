package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.myapplication.domain.Role;
import com.example.myapplication.login.LoginRequestDto;
import com.example.myapplication.owner.OwnerMain;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.message.Message;
import com.example.myapplication.signup.SignUp;
import com.example.myapplication.user.UserMain;


import org.json.JSONException;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    EditText IdText;
    EditText PwdText;
    Role loginUserType = Role.ROLE_OWNER;
    public static String loginId;
    private String loginPwd;

    //메뉴 저장할때 로그인한 사용자 아이디 넘기기 위해
    public static Context context_main; // context 변수 선언
    public String var; // 다른 Activity에서 접근할 변수



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen); //메인 화면
        context_main=this;
        IdText = (EditText) findViewById(R.id.login_Id);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.owner_choice:
                        IdText.setHint("가맹점주 아이디 입력");
                        loginUserType = Role.ROLE_OWNER;
                        break;
                    case R.id.user_choice:
                        IdText.setHint("일반사용자 아이디 입력");
                        loginUserType = Role.ROLE_USER;
                        break;
                }
            }
        });
    }

    public void go_login(View v) throws JSONException {

        IdText = (EditText) findViewById(R.id.login_Id);
        PwdText = (EditText) findViewById(R.id.login_Pwd);

        loginId = IdText.getText().toString();
        loginPwd = PwdText.getText().toString();

        new Thread(new ConnectRunner()).start();
    }

    public class ConnectRunner implements Runnable {

        @Override
        public void run() {

            HttpService httpService = HttpClient.getApiService();

            LoginRequestDto loginRequestDto = new LoginRequestDto(loginId, loginPwd, loginUserType);

            try {
                Response<Message> loginResponse = httpService.login(loginRequestDto).execute();
                Message message=loginResponse.body();
                if (message.getRole()== Role.ROLE_OWNER){
                    var=loginId;
                    Intent intent = new Intent(getApplicationContext(), OwnerMain.class);
                    startActivity(intent);
                }
                else if (message.getRole()== Role.ROLE_USER){
                    Intent intent = new Intent(getApplicationContext(), UserMain.class);
                    startActivity(intent);
                }
            }catch (Exception e){
                System.out.println("오류");
            }
        }
    }

    public void go_signup(View v){
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        startActivity(intent);
    }
}