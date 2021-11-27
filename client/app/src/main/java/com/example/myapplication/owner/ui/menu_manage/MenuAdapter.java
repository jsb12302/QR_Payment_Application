package com.example.myapplication.owner.ui.menu_manage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>{
    private ArrayList<Menu> mDataset;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView menuName, menuPrice, menuDesc;

        //ViewHolder
        public MyViewHolder(View view) {
            super(view);
            menuName = (TextView) view.findViewById(R.id.menuName);
            menuPrice = (TextView) view.findViewById(R.id.menuPrice);
            menuDesc = (TextView) view.findViewById(R.id.menuDesc);
        }
    }

    public MenuAdapter(ArrayList<Menu> myData){
        this.mDataset = myData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MyViewHolder holder, int position) {

        holder.menuName.setText(mDataset.get(position).getMenuName());
        holder.menuPrice.setText(mDataset.get(position).getMenuPrice());
        holder.menuDesc.setText(mDataset.get(position).getMenuDesc());
        //클릭이벤트
//        holder.name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}