package com.example.myapplication.owner.ui.seat_manage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class SeatOrderAdapter extends RecyclerView.Adapter<SeatOrderAdapter.MyViewHolder>{
    private ArrayList<SeatOrder> mDataset;

    String loginId=((MainActivity)MainActivity.context_main).var; //로그인 아이디 가져오기

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView menuName, menuPrice, menuCount;

        //ViewHolder
        public MyViewHolder(View view) {
            super(view);
            menuName = (TextView) view.findViewById(R.id.menuName);
            menuPrice = (TextView) view.findViewById(R.id.menuPrice);
            menuCount = (TextView) view.findViewById(R.id.menuCount);

        }
    }

    public SeatOrderAdapter(ArrayList<SeatOrder> myData){
        this.mDataset = myData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list, parent, false);
        return new SeatOrderAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatOrderAdapter.MyViewHolder holder, int position) {

        holder.menuName.setText(mDataset.get(position).getMenuName());

        String SmenuPrice=mDataset.get(position).getMenuPrice().toString();
        holder.menuPrice.setText(SmenuPrice);

        String SmenuCount=mDataset.get(position).getMenuCount().toString();
        holder.menuCount.setText(SmenuCount);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}