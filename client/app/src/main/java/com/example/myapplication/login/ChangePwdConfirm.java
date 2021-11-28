package com.example.myapplication.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.domain.Role;
import com.example.myapplication.message.Message;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.user.UserMain;

import java.io.IOException;
import retrofit2.Response;

public class ChangePwdConfirm extends AppCompatActivity {

    RadioGroup radioGroup;
    EditText IdText;
    EditText PwdText;
    Role loginUserType = Role.ROLE_OWNER;
    //메뉴 저장할때 로그인한 사용자 아이디 넘기기 위해
    public static Context context_main; // context 변수 선언
    public Role varRole=Role.ROLE_OWNER;
    public String changeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pwd_confirm);
        context_main=this;
        IdText = (EditText) findViewById(R.id.login_Id);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.owner_choice:
                        loginUserType = Role.ROLE_OWNER;
                        varRole=loginUserType;
                        break;
                    case R.id.user_choice:
                        loginUserType = Role.ROLE_USER;
                        varRole=loginUserType;
                        break;
                }
            }
        });
    }
    private String changeConfirmId;
    private String changeConfirmPhone;

    public void bt_comfirm_info(View v){
        EditText changeConfirmIdText=(EditText) findViewById(R.id.change_confirm_id);
        EditText changeConfirmPhoneText=(EditText) findViewById(R.id.change_confirm_phone);

        changeConfirmId=changeConfirmIdText.getText().toString();
        changeConfirmPhone=changeConfirmPhoneText.getText().toString();
        changeId=changeConfirmId;
        new Thread(new ConfirmRunner()).start();
    }
    public class ConfirmRunner implements Runnable{

        @Override
        public void run() {

            HttpService httpService = HttpClient.getApiService();

            try {
                Response<Boolean> message=httpService.getId_Phone(changeConfirmId,changeConfirmPhone,loginUserType).execute();
                if (message.body()==true){
                    Intent intent = new Intent(getApplicationContext(), ChangePwd.class);
                    startActivity(intent);
                }
                else{
                    Handler handler=new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ChangePwdConfirm.this);
                            builder.setTitle("Failed").setMessage("올바르지 않은 회원정보 입니다."+"\n"+"다시 입력 해주세요.");
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    },0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
