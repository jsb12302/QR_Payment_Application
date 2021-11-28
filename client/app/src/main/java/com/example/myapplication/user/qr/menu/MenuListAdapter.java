package com.example.myapplication.user.qr.menu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.user.qr.MyApplication;
import com.example.myapplication.user.qr.TabActivity;
import com.example.myapplication.user.qr.basket.BasketListActivity;

import java.util.ArrayList;

public class MenuListAdapter extends BaseAdapter {

    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private SendViewModel sendViewModel;
    private Button listButton;

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int i) {
        return menuItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.menu_list_item, viewGroup, false);
        }

        ImageView iv_image = (ImageView) view.findViewById(R.id.iv_menu_image);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_menu_name);
        TextView tv_contents = (TextView) view.findViewById(R.id.tv_menu_contents);
        TextView tv_cost = (TextView) view.findViewById(R.id.tv_menu_cost);

        MenuItem menuItem = (MenuItem) getItem(i);

        iv_image.setImageDrawable(menuItem.getMenuPicture());
        tv_name.setText(menuItem.getMenuName());
        tv_contents.setText(menuItem.getMenuContents());
        tv_cost.setText(menuItem.getMenuCost());
        listButton = (Button) view.findViewById(R.id.menu_list_button);
        listButton.setTag(menuItem);


        return view;
    }

    public void addItem(MenuItem item){
        menuItems.add(item);
    }
}
