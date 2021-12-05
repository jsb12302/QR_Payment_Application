package com.example.myapplication.user.qr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.message.Message;
import com.example.myapplication.owner.ui.check_sales.OrdersDTO;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.user.qr.basket.BasketItem;
import com.example.myapplication.user.qr.basket.BasketListActivity;
import com.example.myapplication.user.qr.menu.MenuItem;
import com.example.myapplication.user.qr.menu.MenuListActivity;
import com.google.android.material.tabs.TabLayout;


import java.io.IOException;
import java.util.Date;

import lombok.SneakyThrows;
import retrofit2.Response;

public class TabActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private String table_num;
    private String storeName;
    String loginId=((MainActivity)MainActivity.context_main).var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_menu);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);
        TextView tx = (TextView) findViewById(R.id.set_store_name);
        Intent intent = getIntent();
        storeName = intent.getStringExtra("store_name");
        tx.setText(storeName);
        table_num = intent.getStringExtra("table_num");
        adapter = new FragmentAdapter(getSupportFragmentManager(), 1);

        //FragmentAdapter에 컬렉션 담기
        adapter.addFragment(new MenuListActivity());
        adapter.addFragment(new BasketListActivity());

        //ViewPager Fragment 연결
        viewPager.setAdapter(adapter);

        //ViewPager과 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("메뉴");
        tabLayout.getTabAt(1).setText("장바구니");

    }

    public void click(View v) {
        if (v.getId() == R.id.menu_list_button){
            Bundle bundle = new Bundle();
            MenuItem dto = (MenuItem) v.getTag();
            bundle.putString("Name", dto.getMenuName());
            bundle.putString("Cost", dto.getMenuPrice());
            getSupportFragmentManager().setFragmentResult("requestKey", bundle);
        }
    }

    public void pay(View v){
        TextView tv = v.findViewById(R.id.Total);
        String pay = tv.getText().toString().split(" ")[0];

        AlertDialog.Builder oDialog = new AlertDialog.Builder(this, R.style.Base_Theme_AppCompat_Light_Dialog);

        oDialog.setMessage("결제 요청 금액 : "+pay)
                .setTitle("결제 요청")
                .setPositiveButton("아니오", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Dialog", "취소");
                            }
                        })
                .setNeutralButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new ConnectGetRunner()).start();
                        TabActivity.this.finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    public class ConnectGetRunner implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            ListView listView = findViewById(R.id.basket_list);
            HttpService httpService= HttpClient.getApiService();

            for (int i = 0; i<listView.getCount();i++){
                BasketItem dto = (BasketItem) listView.getItemAtPosition(i);
                Date today = new Date();
                String temp = today.toString();


                OrdersDTO ordersDTO = new OrdersDTO(loginId, storeName, dto.getMenuName(),
                        Integer.parseInt(dto.getMenuCost()), Integer.parseInt(dto.getMenuNum()),
                        null, Integer.parseInt(table_num), 1);
                try {
                    Response<Message> order = httpService.addOrder(temp, ordersDTO).execute();
                    Response<Message> seat =  httpService.updateSeatStateToUse(storeName, 0, Integer.parseInt(table_num)).execute();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }


        }
    }
}
