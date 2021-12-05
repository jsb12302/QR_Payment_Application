package com.example.myapplication.user.map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.user.map.menu.ShopMenuListActivity;
import com.example.myapplication.user.map.seat.ShopSeatActivity;

import com.google.android.material.tabs.TabLayout;

public class ShopTabActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ShopFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_menu);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);
        TextView tx = (TextView) findViewById(R.id.set_store_name);
        Intent intent = getIntent();
        tx.setText(intent.getStringExtra("store_name"));
        adapter = new ShopFragmentAdapter(getSupportFragmentManager(), 1);

        //FragmentAdapter에 컬렉션 담기
        adapter.addFragment(new ShopMenuListActivity());
        adapter.addFragment(new ShopSeatActivity());

        //ViewPager Fragment 연결
        viewPager.setAdapter(adapter);

        //ViewPager과 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("메뉴");
        tabLayout.getTabAt(1).setText("좌석");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
