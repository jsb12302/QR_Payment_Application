package com.example.myapplication.user.qr.basket;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.ActivityChooserView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.user.qr.TabActivity;
import com.example.myapplication.user.qr.menu.MenuItem;

import java.util.ArrayList;

public class BasketListAdapter extends BaseAdapter {

    private ArrayList<BasketItem> basketItems = new ArrayList<>();
    private OnItemClick click;

    BasketListAdapter(OnItemClick onItemClick){
        this.click = onItemClick;
    }

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
        BasketHolder basketHolder;


        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.basket_list_item, viewGroup, false);

            basketHolder = new BasketHolder();
            basketHolder.menuName = view.findViewById(R.id.tv_basket_name);
            basketHolder.menuCost = view.findViewById(R.id.tv_basket_cost);
            basketHolder.menuNum = view.findViewById(R.id.tv_basket_num);
            basketHolder.menuTotal = view.findViewById(R.id.tv_basket_total);
            basketHolder.add_btn = view.findViewById(R.id.plus_button);
            basketHolder.minus_btn = view.findViewById(R.id.minus_button);



            view.setTag(basketHolder);
        }else{
            basketHolder = (BasketHolder) view.getTag();
        }

//        TextView tv_name = (TextView) view.findViewById(R.id.tv_basket_name);
//        TextView tv_cost = (TextView) view.findViewById(R.id.tv_basket_cost);

        BasketItem basketItem = (BasketItem) getItem(i);

        basketHolder.menuName.setText(basketItem.getMenuName());
        basketHolder.menuCost.setText(basketItem.getMenuCost());
        basketHolder.menuNum.setText(basketItem.getMenuNum());
        basketHolder.menuTotal.setText(basketItem.getMenuTotal());
        BasketListActivity activity = new BasketListActivity();
        basketHolder.add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(basketHolder.menuNum.getText().toString())+1;
                int cost = Integer.parseInt(basketHolder.menuCost.getText().toString());
                basketItem.setMenuNum(Integer.toString(num));
                basketItem.setMenuTotal(Integer.toString(cost * num));
                basketHolder.menuNum.setText(Integer.toString(num));
                basketHolder.menuTotal.setText(Integer.toString(cost * num));

                click.onClick(Integer.toString(cost*num));
            }
        });

        basketHolder.minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(basketHolder.menuNum.getText().toString())-1;
                int cost = Integer.parseInt(basketHolder.menuCost.getText().toString());

                if(num == 0){
                    removeItem(basketItem);
                    notifyDataSetChanged();
                }

                basketItem.setMenuNum(Integer.toString(num));
                basketItem.setMenuTotal(Integer.toString(cost * num));
                basketHolder.menuNum.setText(Integer.toString(num));
                basketHolder.menuTotal.setText(Integer.toString(cost * num));

                click.onClick(Integer.toString(cost*num));
            }
        });

        return view;
    }

    public void addItem(BasketItem basketItem){
        basketItems.add(basketItem);
    }

    public void removeItem(BasketItem basketItem){
        basketItems.remove(basketItem);
    }
}
