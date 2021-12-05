package com.example.myapplication.owner.ui.seat_manage;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentOwnerSeatManageBinding;
import com.example.myapplication.message.Message;
import com.example.myapplication.owner.ui.check_sales.OrdersDTO;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.store.StoreSignUpDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;


public class SeatManageFragment extends Fragment {

    Dialog dialog;
    public static String storeName;
    public static int tableNum=0;
    public static Boolean status=Boolean.FALSE;
    public static ArrayList<SeatOrder> TableOrderList = new ArrayList<>();
    String loginId=((MainActivity)MainActivity.context_main).var; //로그인 아이디 가져오기

    //리사리클러 뷰
    private RecyclerView recyclerView;
    private SeatOrderAdapter mAdapter;

    private SeatManageViewModel seatManageViewModel;
    private FragmentOwnerSeatManageBinding binding;


    //테이블 상태 변수
    public static int table1=1;
    public static int table2=1;
    public static int table3=1;
    public static int table4=1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        seatManageViewModel =
                new ViewModelProvider(this).get(SeatManageViewModel.class);
        binding = FragmentOwnerSeatManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        dialog = new Dialog(getContext());       // Dialog 초기화
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dialog.setContentView(R.layout.order_confirm);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switch1=root.findViewById(R.id.seat_switch1);
        if(table1==0){
            switch1.setChecked(true);
            Button btn1 = root.findViewById(R.id.seat1);
            btn1.setBackgroundResource(R.drawable.active_button);
        }
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switch2=root.findViewById(R.id.seat_switch2);
        if(table2==0){
            switch2.setChecked(true);
            Button btn2 = root.findViewById(R.id.seat2);
            btn2.setBackgroundResource(R.drawable.active_button);
        }
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switch3=root.findViewById(R.id.seat_switch3);
        if(table3==0){
            switch3.setChecked(true);
            Button btn3 = root.findViewById(R.id.seat3);
            btn3.setBackgroundResource(R.drawable.active_button);
        }
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switch4=root.findViewById(R.id.seat_switch4);
        if(table4==0){
            switch4.setChecked(true);
            Button btn4 = root.findViewById(R.id.seat4);
            btn4.setBackgroundResource(R.drawable.active_button);
        }


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //status
                    Button btn1 = root.findViewById(R.id.seat1);
                    btn1.setBackgroundResource(R.drawable.active_button);
                } else {
                    Button btn1 = root.findViewById(R.id.seat1);
                    btn1.setBackgroundResource(R.drawable.inactive_button);
                    tableNum = 1;
                    new Thread(new ConnectSetState()).start();
                    new Thread(new ConnectSeatState()).start();
                    table1 = 1;
                }
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //status
                    Button btn2 = root.findViewById(R.id.seat2);
                    btn2.setBackgroundResource(R.drawable.active_button);
                } else {
                    Button btn2 = root.findViewById(R.id.seat2);
                    btn2.setBackgroundResource(R.drawable.inactive_button);
                    tableNum = 2;
                    new Thread(new ConnectSetState()).start();
                    new Thread(new ConnectSeatState()).start();
                    table2 = 1;
                }
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Button btn3 = root.findViewById(R.id.seat3);
                    btn3.setBackgroundResource(R.drawable.active_button);
                    //status
                } else {
                    Button btn3 = root.findViewById(R.id.seat3);
                    btn3.setBackgroundResource(R.drawable.inactive_button);
                    tableNum = 3;
                    new Thread(new ConnectSetState()).start();
                    new Thread(new ConnectSeatState()).start();
                    table3 = 1;
                }
            }
        });
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //status
                    Button btn4 = root.findViewById(R.id.seat4);
                    btn4.setBackgroundResource(R.drawable.active_button);
                } else {
                    Button btn4 = root.findViewById(R.id.seat4);
                    btn4.setBackgroundResource(R.drawable.inactive_button);
                    tableNum = 4;
                    new Thread(new ConnectSetState()).start();
                    new Thread(new ConnectSeatState()).start();
                    table4 = 1;
                }
            }
        });

        Button close=dialog.findViewById(R.id.bt_close);
        close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        Button seat1=root.findViewById(R.id.seat1);
        Button seat2=root.findViewById(R.id.seat2);
        Button seat3=root.findViewById(R.id.seat3);
        Button seat4=root.findViewById(R.id.seat4);

        seat1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableNum=1;
                new Thread(new ConnectGetOrders()).start();
                recyclerView=dialog.findViewById(R.id.recyclerView_table);
                recyclerView.setHasFixedSize(true);
                mAdapter = new SeatOrderAdapter(TableOrderList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                dialog.show();
            }
        });

        seat2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableNum=2;
                new Thread(new ConnectGetOrders()).start();
                recyclerView=dialog.findViewById(R.id.recyclerView_table);
                recyclerView.setHasFixedSize(true);
                mAdapter = new SeatOrderAdapter(TableOrderList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                dialog.show();
            }
        });

        seat3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableNum=3;
                new Thread(new ConnectGetOrders()).start();
                recyclerView=dialog.findViewById(R.id.recyclerView_table);
                recyclerView.setHasFixedSize(true);
                mAdapter = new SeatOrderAdapter(TableOrderList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                dialog.show();
            }
        });

        seat4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tableNum=4;
                new Thread(new ConnectGetOrders()).start();
                recyclerView=dialog.findViewById(R.id.recyclerView_table);
                recyclerView.setHasFixedSize(true);
                mAdapter = new SeatOrderAdapter(TableOrderList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mAdapter);
                dialog.show();
            }
        });



        return root;

    }


    public void onCreate(@Nullable Bundle savedInstanceState) {
        //가맹점주 로그인 후 주문 상태에 따라 테이블 스위치 토글
        super.onCreate(savedInstanceState);
        new Thread(new ConnetGetTableStatus()).start();
    }

    private class ConnetGetTableStatus implements Runnable {
        @Override
        public void run() {
            HttpService httpService= HttpClient.getApiService();
            try {
                Response<StoreSignUpDto> storenames = httpService.getStoreName(loginId).execute();
                String storename=storenames.body().getStoreName();
                storeName=storename;

                Response<List<OrdersDTO>> seatOrders=httpService.getTableStatus(storeName,0).execute();
                List<OrdersDTO> seatOrder=seatOrders.body();
                for(int i=0;i<seatOrder.size();i++){
                    if(seatOrder.get(i).getOrderState()==0){
                        if (seatOrder.get(i).getTableNum()==1){table1=0; }
                        if (seatOrder.get(i).getTableNum()==2){table2=0; }
                        if (seatOrder.get(i).getTableNum()==3){table3=0; }
                        if (seatOrder.get(i).getTableNum()==4){table4=0; }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    public class ConnectGetOrders implements Runnable {

        @Override
        public void run() {
            TableOrderList.clear();
            HttpService httpService= HttpClient.getApiService();

            try {
                Response<List<OrdersDTO>> seatOrders=httpService.getSeatOrders(tableNum,0,storeName).execute();
                List<OrdersDTO> seatOrder=seatOrders.body();
                System.out.println(seatOrder.size());
                for(int i=0;i<seatOrder.size();i++){
                    TableOrderList.add(new SeatOrder(seatOrder.get(i).getMenuName(),seatOrder.get(i).getMenuPrice(),
                            seatOrder.get(i).getMenuCount()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class ConnectSetState implements Runnable {

        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();
            try {
               Response<Message> changeResponse = httpService.updateOrderState(storeName, 1, tableNum).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class ConnectSeatState implements Runnable {

        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();
            try {
                Response<Message> changeResponses = httpService.updateSeatState(storeName,1, tableNum).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
