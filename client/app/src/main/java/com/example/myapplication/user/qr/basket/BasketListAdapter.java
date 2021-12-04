package com.example.myapplication.user.qr.basket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.user.qr.menu.MenuItem;

import java.util.ArrayList;

public class BasketListAdapter extends BaseAdapter {

    private ArrayList<BasketItem> basketItems = new ArrayList<>();
    private Button add_btn;
    private Button minus_btn;

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

        add_btn = (Button) view.findViewById(R.id.plus_button);
        add_btn.setTag(basketItem);

        minus_btn = (Button) view.findViewById(R.id.minus_button);
        minus_btn.setTag(basketItem);

        return view;
    }

    public void addItem(BasketItem basketItem){
        basketItems.add(basketItem);
    }

    public void removeItem(BasketItem basketItem){
        basketItems.remove(basketItem);
    }

}
