package com.example.myapplication.owner.ui.check_sales;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;


public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {
    private ArrayList<Sales> mDataset;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView menuName_c, menuCount_c, menuPrice_c;

        public MyViewHolder(@NonNull View view) {
            super(view);
            menuName_c = (TextView) view.findViewById(R.id.menuName_c);
            menuCount_c = (TextView) view.findViewById(R.id.menuCount_c);
            menuPrice_c = (TextView) view.findViewById(R.id.menuPrice_c);
        }
    }

    public OrdersAdapter(ArrayList<Sales> myData) {this.mDataset = myData;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.check_sales_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.menuName_c.setText(mDataset.get(position).getMenuName());
        holder.menuCount_c.setText(String.valueOf(mDataset.get(position).getMenuCount()));
        holder.menuPrice_c.setText(String.valueOf(mDataset.get(position).getMenuPrice()));
    }

    @Override
    public int getItemCount() { return mDataset.size(); }

}

