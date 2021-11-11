package com.example.myapplication.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.user.map.MapActivity;
import com.example.myapplication.user.qr.ScanQRActivity;

public class UserMain extends AppCompatActivity {
    private Button buttonScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main); //사용자 메인화면

        buttonScan = (Button) findViewById(R.id.ScanButton);

    }

    public void scan(View view){
        Intent intent = new Intent(getApplicationContext(), ScanQRActivity.class);
        startActivity(intent);
    }

    public void map(View view){
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
    }

}
