package com.example.myapplication.user.map.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;


public class ShopMenuListActivity extends Fragment {

    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shop_menu_list, container, false);
        listView = (ListView) v.findViewById(R.id.menu_shop_list);
        dataSetting();

        return v;
    }

    private void dataSetting(){
        ShopMenuListAdapter adapter = new ShopMenuListAdapter();

        String[] name = getResources().getStringArray(R.array.Name);
        String[] contents = getResources().getStringArray(R.array.Contents);
        String[] cost = getResources().getStringArray(R.array.Cost);

        for (int i = 0; i < name.length; i++) {
            ShopMenuItem dto = new ShopMenuItem();
            dto.setMenuName(name[i]);
            dto.setMenuContents(contents[i]);
            dto.setMenuCost(cost[i]);
            adapter.addItem(dto);
        }
        listView.setAdapter(adapter);
    }
}
