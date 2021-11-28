package com.example.myapplication.user.qr.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.user.qr.ScanQRActivity;


public class MenuListActivity extends Fragment {

    private ListView listView;
    private SendViewModel sendViewModel;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_list, container, false);
        listView = (ListView) v.findViewById(R.id.menu_list);
        dataSetting();


        return v;
    }

    private void dataSetting(){
        MenuListAdapter adapter = new MenuListAdapter();

        String[] name = getResources().getStringArray(R.array.Name);
        String[] contents = getResources().getStringArray(R.array.Contents);
        String[] cost = getResources().getStringArray(R.array.Cost);

        for (int i = 0; i < name.length; i++) {
            MenuItem dto = new MenuItem();
            dto.setMenuName(name[i]);
            dto.setMenuContents(contents[i]);
            dto.setMenuCost(cost[i]);

            adapter.addItem(dto);
        }
        listView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



}
