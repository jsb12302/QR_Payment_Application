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
import com.example.myapplication.user.qr.basket.BasketItem;
import com.example.myapplication.user.qr.basket.BasketListActivity;
import com.example.myapplication.user.qr.basket.BasketListAdapter;
import com.example.myapplication.user.qr.menu.MenuItem;
import com.example.myapplication.user.qr.menu.MenuListActivity;
import com.example.myapplication.user.qr.menu.SendViewModel;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class TabActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragementAdapter adapter;
    private BasketListAdapter basketadapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_menu);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);
        TextView tx = (TextView) findViewById(R.id.set_store_name);
        Intent intent = getIntent();
        tx.setText(intent.getStringExtra("store_name"));
        adapter = new FragementAdapter(getSupportFragmentManager(), 1);

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

    public void plus(View v) {
        if (v.getId() == R.id.plus_button){
            listView = v.findViewById(R.id.basket_list);
            basketadapter = new BasketListAdapter();
            BasketItem dto = (BasketItem) v.getTag();
            String num = Integer.toString(Integer.parseInt(dto.getMenuNum())+1);
            String total = Integer.toString(Integer.parseInt(dto.getMenuCost())*Integer.parseInt(num));
            dto.setMenuTotal(total);
            dto.setMenuNum(num);
            basketadapter.notifyDataSetChanged();
            listView.setAdapter(basketadapter);
        }
    }

    public void minus(View v) {
//        if (v.getId() == R.id.minus_button){
//            BasketItem dto = (BasketItem) v.getTag();
//            String num = Integer.toString(Integer.parseInt(dto.getMenuNum())-1);
//            if (num.equals("0")){
//                basketListActivity.adapter.removeItem(dto);
//            }
//            String total = Integer.toString(Integer.parseInt(dto.getMenuCost())*Integer.parseInt(num));
//            dto.setMenuTotal(total);
//            dto.setMenuNum(num);
//            basketListActivity.adapter.notifyDataSetChanged();
//            basketListActivity.listView.setAdapter(basketListActivity.adapter);
//        }
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
