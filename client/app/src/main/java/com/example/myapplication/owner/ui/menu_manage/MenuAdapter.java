package com.example.myapplication.owner.ui.menu_manage;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.message.Message;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>{

    private ArrayList<Menu> mDataset;
    public static String selectMenuName;
    public static Bitmap globalBitmap;
    String loginId=((MainActivity)MainActivity.context_main).var; //로그인 아이디 가져오기

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView menuName, menuPrice, menuDesc;
        public ImageView menuImage;

        //ViewHolder
        public MyViewHolder(View view) {
            super(view);
            menuName = (TextView) view.findViewById(R.id.menuName);
            menuPrice = (TextView) view.findViewById(R.id.menuPrice);
            menuDesc = (TextView) view.findViewById(R.id.menuDesc);
            menuImage= (ImageView) view.findViewById(R.id.ListMenuImage);
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

        Glide.with(holder.itemView.getContext())
                .load(mDataset.get(position).getMenuImage())
                .into(holder.menuImage);

        holder.menuName.setText(mDataset.get(position).getMenuName());
        holder.menuPrice.setText(mDataset.get(position).getMenuPrice());
        holder.menuDesc.setText(mDataset.get(position).getMenuDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String menuName=holder.menuName.getText().toString();
                selectMenuName=menuName;

                new Thread(new removeMenuRunner()).start();
            }
        });
    }

    public class removeMenuRunner implements Runnable{

        @Override
        public void run() {
            HttpService httpService= HttpClient.getApiService();
            try {
                Response<Message> menu=httpService.removeMenuRequest(loginId,selectMenuName).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}