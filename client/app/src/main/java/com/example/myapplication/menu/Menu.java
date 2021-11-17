package com.example.myapplication.menu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import android.webkit.MimeTypeMap;
import com.example.myapplication.R;
import com.example.myapplication.domain.Role;
import com.example.myapplication.message.Message;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.signup.OwnerSignUpDto;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class Menu extends AppCompatActivity {

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_test);

        imageview = (ImageView)findViewById(R.id.imageView);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
        }
    }

    private String storeName;
    private String menuName;

    public void menuRequest(View v){

        EditText storeNameText = (EditText) findViewById(R.id.storeName);
        EditText menuNameText = (EditText) findViewById(R.id.menuName);

        storeName = storeNameText.getText().toString();
        menuName = menuNameText.getText().toString();


        new Thread(new ConnectRunner()).start();
    }
    public class ConnectRunner implements Runnable {

        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();
            String sn = storeName;
            String mn = menuName;
            HashMap<String, RequestBody> map = new HashMap<>();

            RequestBody sName = RequestBody.create(MediaType.parse("text/plain"), sn);
            RequestBody mName = RequestBody.create(MediaType.parse("text/plain"), mn);
            map.put("storeName", sName);
            map.put("menuName", mName);

            File file = new File(selectedImageUri.getPath());

            // 파일 확장자 불러와서 ext에 저장
            String mimeType = getContentResolver().getType(selectedImageUri);
            int pos = mimeType.lastIndexOf( "/" );
            String ext = mimeType.substring( pos + 1 );

            InputStream inputStream = null;
            try {
                inputStream = imageview.getContext().getContentResolver().openInputStream(selectedImageUri);
            }catch(IOException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
            RequestBody requestBody = RequestBody.create(MediaType.parse(mimeType), byteArrayOutputStream.toByteArray());
            MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("fileName", file.getName()+"."+ext, requestBody);

            try {
                Response<Message> menuResponse = httpService.menuRequest(uploadFile, map).execute();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
