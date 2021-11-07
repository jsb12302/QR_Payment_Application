package com.example.myapplication.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    private String Id;
    private String Pwd;

    public void login(View v){

        EditText userIdText = (EditText) findViewById(R.id.login_Id);
        EditText userPwdText = (EditText) findViewById(R.id.login_Pwd);

        Id = userIdText.getText().toString();
        Pwd = userPwdText.getText().toString();

        new Thread(new Login.ConnectRunner()).start();
    }

    public class ConnectRunner implements Runnable {

        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();

//            try {
//                Response<Message> loginMessage = httpService.login(Id,Pwd).execute();
//                Message message= loginMessage.body();
//                System.out.println(message.getMessage());
//
//            }catch (IOException e){
//                e.printStackTrace();
//            }
        }
    }

}
