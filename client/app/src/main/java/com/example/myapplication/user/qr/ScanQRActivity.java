package com.example.myapplication.user.qr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

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
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(ScanQRActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ScanQRActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();

                Intent intents = new Intent(getApplicationContext(), TabActivity.class);
                intents.putExtra("store_name", result.getContents().split(",")[0]);
                intents.putExtra("table_num", result.getContents().split(",")[1]);
                startActivity(intents);
                ScanQRActivity.this.finish();
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
