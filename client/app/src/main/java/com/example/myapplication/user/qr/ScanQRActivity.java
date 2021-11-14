package com.example.myapplication.user.qr;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.user.menu.TabActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ScanQRActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);
        qrScan = new IntentIntegrator(this);

        qrScan.setPrompt("Scanning...");
        qrScan.setOrientationLocked(false);
        qrScan.setCaptureActivity(CaptureActivity.class);
        qrScan.initiateScan();
    }
    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        TextView textViewName = (TextView) findViewById(R.id.set_store_name);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(ScanQRActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(ScanQRActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
//                try {
                    //data를 json으로 변환
//                    JSONObject obj = new JSONObject(result.getContents());
                String str = result.getContents();
                System.out.println(str);
                Intent intents = new Intent(getApplicationContext(), TabActivity.class);
                intents.putExtra("store_name", result.getContents());
                startActivity(intents);
//                textViewName.setText(result.getContents());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    //Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
//                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
