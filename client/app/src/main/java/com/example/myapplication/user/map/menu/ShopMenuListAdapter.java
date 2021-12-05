package com.example.myapplication.user.map.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class ShopMenuListAdapter extends BaseAdapter {

    private ArrayList<ShopMenuItem> menuItems = new ArrayList<>();

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
            view = inflater.inflate(R.layout.shop_menu_list_item, viewGroup, false);
        }

        ImageView iv_image = (ImageView) view.findViewById(R.id.iv_shop_menu_image);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_shop_menu_name);
        TextView tv_contents = (TextView) view.findViewById(R.id.tv_shop_menu_contents);
        TextView tv_cost = (TextView) view.findViewById(R.id.tv_shop_menu_cost);

        ShopMenuItem menuItem = (ShopMenuItem) getItem(i);

        iv_image.setImageBitmap(menuItem.getMenuPicture());
        tv_name.setText(menuItem.getMenuName());
        tv_contents.setText(menuItem.getMenuDesc());
        tv_cost.setText(menuItem.getMenuPrice());

        return view;
    }

    public void addItem(ShopMenuItem item){
        menuItems.add(item);
    }
}
