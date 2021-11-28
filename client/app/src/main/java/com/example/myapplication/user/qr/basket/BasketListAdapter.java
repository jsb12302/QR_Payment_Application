package com.example.myapplication.user.qr.basket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.user.qr.menu.MenuItem;

import java.util.ArrayList;

public class BasketListAdapter extends BaseAdapter {

    private ArrayList<BasketItem> basketItems = new ArrayList<>();

    @Override
    public int getCount() {
        return basketItems.size();
    }

    @Override
    public Object getItem(int i) {
        return basketItems.get(i);
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
            view = inflater.inflate(R.layout.basket_list_item, viewGroup, false);
        }

        TextView tv_name = (TextView) view.findViewById(R.id.tv_basket_name);
        TextView tv_cost = (TextView) view.findViewById(R.id.tv_basket_cost);

        BasketItem basketItem = (BasketItem) getItem(i);

        tv_name.setText(basketItem.getMenuName());
        tv_cost.setText(basketItem.getMenuCost());

        return view;
    }

    public void addItem(String name, String cost){
        BasketItem basketItem = new BasketItem();

        basketItem.setMenuName(name);
        basketItem.setMenuCost(cost);

        basketItems.add(basketItem);
    }

}
