package com.example.myapplication.owner.ui.menu_manage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.message.Message;
import com.example.myapplication.message.Status;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.user.UserMain;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import java.util.List;

import retrofit2.Response;

public class AddMenuRequest extends AppCompatActivity{

    String loginId=((MainActivity)MainActivity.context_main).var; //로그인 아이디 가져오기

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);

        imageview = (ImageView)findViewById(R.id.menuImage);
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

    private String menuName;
    private String menuPrice;
    private String menuDesc;

    public void menu_add_button(View v){

        EditText menuNameText = (EditText) findViewById(R.id.munu_name);
        EditText menuDescText = (EditText) findViewById(R.id.menu_desc);
        EditText menuPriceText = (EditText) findViewById(R.id.menu_price);

        menuName=menuNameText.getText().toString();
        menuPrice=menuPriceText.getText().toString();
        menuDesc=menuDescText.getText().toString();


        new Thread(new ConnectRunner()).start();
    }

    public class ConnectRunner implements Runnable {

        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();

            HashMap<String, RequestBody> map = new HashMap<>();

            RequestBody sMenuName = RequestBody.create(MediaType.parse("text/plain"), menuName);
            RequestBody sMenuPrice = RequestBody.create(MediaType.parse("text/plain"), menuPrice);
            RequestBody sMenuDesc = RequestBody.create(MediaType.parse("text/plain"), menuDesc);
            RequestBody sLoginId = RequestBody.create(MediaType.parse("text/plain"), loginId);

            map.put("menuName",sMenuName);
            map.put("menuPrice",sMenuPrice);
            map.put("menuDesc",sMenuDesc);
            map.put("loginId",sLoginId);

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

    public class ConnectGetRunner implements Runnable {

        @Override
        public void run() {
            HttpService httpService=HttpClient.getApiService();
            try {
                Response<List<MenuDto>> menu=httpService.getMenu(loginId).execute();
                List<MenuDto> menuList= menu.body();
                for(int i=0;i<menuList.size();i++) {
                    System.out.println(menuList.get(i).getMenuName());
                }
                System.out.println("메뉴 사이즈"+menuList.size());
                finish();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void add_menu(View v){
        EditText menuNameText=findViewById(R.id.munu_name);
        EditText menuPriceText=findViewById(R.id.menu_price);
        EditText menuDescText=findViewById(R.id.menu_desc);

        menuName =menuNameText.getText().toString();
        menuPrice=menuPriceText.getText().toString();
        menuDesc=menuDescText.getText().toString();

        new Thread(new ConnectRunner()).start();

    }
}
