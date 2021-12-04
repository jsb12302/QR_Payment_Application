package com.example.myapplication.user.qr.basket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.user.qr.menu.MenuItem;
import com.example.myapplication.user.qr.menu.MenuListAdapter;
import com.example.myapplication.user.qr.menu.SendViewModel;

public class BasketListActivity extends Fragment {
    private ListView listView;
    private BasketListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.basket_list, container, false);
        listView = (ListView) v.findViewById(R.id.basket_list);
        adapter = new BasketListAdapter();
        return v;
    }

    public void dataSetting(String name, String cost, String num){
        BasketItem dto = new BasketItem();
        dto.setMenuName(name);
        dto.setMenuCost(cost);
        dto.setMenuNum(num);
        String total = Integer.toString(Integer.parseInt(cost)*Integer.parseInt(num));
        dto.setMenuTotal(total);
        adapter.addItem(dto);
        listView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String name = result.getString("Name");
                System.out.println("Name : "+name);
                String cost = result.getString("Cost");
                System.out.println("Cost : "+cost);
                int len = listView.getCount();
                if (len > 0) {
                    for (int i = 0; i < len; i++) {
                        BasketItem item = (BasketItem) listView.getItemAtPosition(i);
                        if (item.getMenuName().equals(name)) {
                            int num = Integer.parseInt(item.getMenuNum());
                            int total = Integer.parseInt(item.getMenuTotal());
                            num += 1;
                            total *= num;
                            item.setMenuNum(Integer.toString(num));
                            item.setMenuTotal(Integer.toString(total));
                            adapter.notifyDataSetChanged();
                            listView.setAdapter(adapter);
                            break;
                        }else if (i == len-1){
                            dataSetting(name, cost, "1");
                        }
                    }
                }else{
                    dataSetting(name, cost, "1");
                }
            }
        });

    }
}