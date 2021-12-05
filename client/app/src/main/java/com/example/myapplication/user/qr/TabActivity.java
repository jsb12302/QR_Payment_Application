package com.example.myapplication.user.qr;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.user.qr.basket.BasketListActivity;
import com.example.myapplication.user.qr.menu.MenuItem;
import com.example.myapplication.user.qr.menu.MenuListActivity;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;
    private String table_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_menu);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);
        TextView tx = (TextView) findViewById(R.id.set_store_name);
        Intent intent = getIntent();
        tx.setText(intent.getStringExtra("store_name"));
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
            bundle.putString("Cost", dto.getMenuCost());
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
                        System.out.println(table_num);
                        TabActivity.this.finish();
                    }
                })
                .setCancelable(false)
                .show();
    }
}
