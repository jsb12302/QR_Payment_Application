package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< .merge_file_a03852
import com.example.myapplication.signup.OwnerSignUpRequest;
=======
import com.example.myapplication.signup.SignUpOwnerRequest;
import com.example.myapplication.signup.SignUpUserRequest;
>>>>>>> .merge_file_a19996

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
