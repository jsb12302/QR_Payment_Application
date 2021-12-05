package com.example.myapplication.user.map.seat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.owner.ui.check_sales.OrdersDTO;
import com.example.myapplication.owner.ui.seat_manage.SeatManageFragment;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.store.StoreSignUpDto;
import com.example.myapplication.user.map.ShopTabActivity;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class ShopSeatActivity extends Fragment {

    public static int[] table = {1, 1, 1, 1};

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new ConnetGetTableStatus()).start();
    }

    private class ConnetGetTableStatus implements Runnable {
        TextView tv = ((ShopTabActivity) getActivity()).findViewById(R.id.set_store_name);
        String storeName = tv.getText().toString();

        @Override
        public void run() {
            HttpService httpService= HttpClient.getApiService();
            try {
                Response<List<SeatDTO>> seatOrders=httpService.getSeat(storeName).execute();
                List<SeatDTO> seatOrder=seatOrders.body();

                for(int i=0;i<seatOrder.size();i++){
                    if(seatOrder.get(i).tableStatus == 0){
                        table[i] = 0;
                    }
                    else {
                        table[i] = 1;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shop_seat, container, false);

//        TextView[] seat = {v.findViewById(R.id.seat1), v.findViewById(R.id.seat2),
//                v.findViewById(R.id.seat3), v.findViewById(R.id.seat4)};
//
//        for(int i = 0; i < 4; i++) {
//            System.out.println(i+": "+table[i]);
//            if(table[i]==1){
//                seat[i].setBackgroundResource(R.drawable.active_button);
//            }
//            else{
//                seat[i].setText("사용중");
//            }
//        }

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView[] seat = {view.findViewById(R.id.seat1), view.findViewById(R.id.seat2),
                view.findViewById(R.id.seat3), view.findViewById(R.id.seat4)};

        for(int i = 0; i < 4; i++) {
            System.out.println(i+": "+table[i]);
            if(table[i]==1){
                seat[i].setBackgroundResource(R.drawable.active_button);
            }
            else{
                seat[i].setText("사용중");
            }
        }

    }
}
