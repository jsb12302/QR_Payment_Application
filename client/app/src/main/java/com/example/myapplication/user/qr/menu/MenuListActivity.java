package com.example.myapplication.user.qr.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.user.map.ShopTabActivity;
import com.example.myapplication.user.map.menu.ShopMenuItem;
import com.example.myapplication.user.map.menu.ShopMenuListActivity;
import com.example.myapplication.user.qr.ScanQRActivity;
import com.example.myapplication.user.qr.TabActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Response;


public class MenuListActivity extends Fragment {

    private ListView listView;
    public static ArrayList<MenuItem> InsertMenuList = new ArrayList<>();

    @SneakyThrows
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        new Thread(new ConnectGetRunner()).start();
        Thread.sleep(1000);
    }

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

        for (int i = 0; i < InsertMenuList.size(); i++) {
            adapter.addItem(InsertMenuList.get(i));
        }

        listView.setAdapter(adapter);
    }

    public class ConnectGetRunner implements Runnable {
        TextView tv = ((TabActivity) getActivity()).findViewById(R.id.set_store_name);
        String storeName = tv.getText().toString();

        @Override
        public void run() {
            InsertMenuList.clear();
            HttpService httpService= HttpClient.getApiService();
            try {

                Response<List<ShopMenuItem>> menu=httpService.getMenus(storeName).execute();


                List<ShopMenuItem> menuList= menu.body();
                Bitmap bitmap = null;

                for(int i=0;i<menuList.size();i++) {

                    String menuName=menuList.get(i).getMenuName();
                    String menuCost=menuList.get(i).getMenuPrice();
                    String menuDesc=menuList.get(i).getMenuDesc();

                    try{
                        URL myFileUrl = new URL("http://42.82.69.82:8080/img?storeName=" + storeName + "&menuName=" + menuName);
                        HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
                        conn.setDoInput(true);
                        conn.connect();

                        InputStream is = conn.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);
                        is.close();
                    }catch(IOException e){
                        e.printStackTrace();
                    }

                    InsertMenuList.add(new MenuItem(bitmap, menuName, menuDesc, menuCost));
                }


            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
