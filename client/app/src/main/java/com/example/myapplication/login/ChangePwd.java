package com.example.myapplication.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.domain.Role;
import com.example.myapplication.message.Message;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;

import java.io.IOException;

import retrofit2.Response;

public class ChangePwd extends AppCompatActivity {

    //ChangePwdConfirm에서 선택한 사용자 role 가져옴
    Role varRole=((ChangePwdConfirm)ChangePwdConfirm.context_main).varRole;
    String changeId=((ChangePwdConfirm)ChangePwdConfirm.context_main).changeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pwd); //메인 화면
    }

    private String change_pwd;
    private String change_pwd2;

    public void bt_change_pwd(View v){
        EditText change_pwdText=(EditText) findViewById(R.id.change_pwd);
        EditText change_pwd2Text=(EditText) findViewById(R.id.change_pwd2);

        change_pwd=change_pwdText.getText().toString();
        change_pwd2=change_pwd2Text.getText().toString();

        if (change_pwd.equals(change_pwd2)){
            new Thread(new ChangePwdRunner()).start();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(ChangePwd.this);
            builder.setTitle("Failed").setMessage("변경할 비밀번호와 재입력한 비밀번호가 다릅니다."+"\n"+"다시 입력 해주세요.");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }


    }

    private class ChangePwdRunner implements Runnable {
        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();
            try {
                Response<Message> message=httpService.changePwdRequest(changeId,change_pwd,varRole).execute();
                Handler handler=new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ChangePwd.this);
                        builder.setTitle("Change Password").setMessage("비밀번호가 변경되었습니다.");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                },0);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
