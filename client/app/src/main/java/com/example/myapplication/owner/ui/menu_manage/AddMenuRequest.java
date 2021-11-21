package com.example.myapplication.owner.ui.menu_manage;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.message.Status;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class AddMenuRequest extends AppCompatActivity{

    String loginId=((MainActivity)MainActivity.context_main).var; //로그인 아이디 가져오기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);
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

    private String menuName;
    private String menuPrice;
    private String menuDesc;

    public void add_menu(View v){
        EditText menuNameText=findViewById(R.id.munu_name);
        EditText menuPriceText=findViewById(R.id.menu_price);
        EditText menuDescText=findViewById(R.id.menu_desc);

        menuName =menuNameText.getText().toString();
        menuPrice=menuPriceText.getText().toString();
        menuDesc=menuDescText.getText().toString();

        new Thread(new ConnectRunner()).start();
        //new Thread(new ConnectGetRunner()).start();

    }
    public class ConnectRunner implements Runnable {

        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();
            MenuDto menuDto=new MenuDto(menuName,menuPrice,menuDesc,loginId);
            try {
                Response<Status> menu=httpService.addMenu(menuDto).execute();
                finish();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
