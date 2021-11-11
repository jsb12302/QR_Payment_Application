package com.example.myapplication.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.signup.OwnerSignUpRequest;
import com.example.myapplication.signup.SignUpUserRequest;


public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void go_owner_sign_page(View v){
        Intent intent = new Intent(getApplicationContext(), OwnerSignUpRequest.class);
        startActivity(intent);
    }

    public void go_user_sign_page(View v){
        Intent intent = new Intent(getApplicationContext(), SignUpUserRequest.class);
        startActivity(intent);
    }


}
