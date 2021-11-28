package com.example.myapplication.user.qr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.user.qr.basket.BasketListActivity;
import com.example.myapplication.user.qr.menu.MenuItem;
import com.example.myapplication.user.qr.menu.MenuListActivity;
import com.example.myapplication.user.qr.menu.SendViewModel;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class TabActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragementAdapter adapter;
    private Button btnAdd, btnMinus;
    private TextView tv_total, tv_num;
    private int count, total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_menu);

        tabLayout=findViewById(R.id.tabs);
        viewPager=findViewById(R.id.pager);
        TextView tx = (TextView) findViewById(R.id.set_store_name);
        Intent intent = getIntent();
        tx.setText(intent.getStringExtra("store_name"));
        adapter=new FragementAdapter(getSupportFragmentManager(),1);

        //FragmentAdapter에 컬렉션 담기
        adapter.addFragment(new MenuListActivity());
        adapter.addFragment(new BasketListActivity());

        //ViewPager Fragment 연결
        viewPager.setAdapter(adapter);

        //ViewPager과 TabLayout 연결
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("메뉴");
        tabLayout.getTabAt(1).setText("장바구니");

//        btnAdd = findViewById(R.id.plus_button);
//        btnMinus = findViewById(R.id.minus_button);
//
//        tv_num = findViewById(R.id.tv_basket_num);
//        tv_total = findViewById(R.id.tv_basket_total);
//        tv_num.setText(count);
//        int cost = Integer.parseInt(findViewById(R.id.tv_basket_cost).toString());
//
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count++;
//                tv_num.setText(count);
//                tv_total.setText(cost*count);
//            }
//        });
//
//        btnMinus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count--;
//                tv_num.setText(count);
//                tv_total.setText(cost*count);
//            }
//        });
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
}
