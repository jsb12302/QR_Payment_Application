package com.example.myapplication.owner.ui.check_sales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentOwnerCheckSalesBinding;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.signup.OwnerSignUpDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class CheckSalesFragment extends Fragment implements View.OnClickListener{
    private CheckSalesViewModel checkSalesViewModel;
    private FragmentOwnerCheckSalesBinding binding;
    private OrdersAdapter oAdapter;
    private RecyclerView recyclerView;
    String loginId=((MainActivity)MainActivity.context_main).var;
    public static ArrayList<Sales> InsertOrderList = new ArrayList<>();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        checkSalesViewModel =
                new ViewModelProvider(this).get(CheckSalesViewModel.class);

        binding = FragmentOwnerCheckSalesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button showSale_button=root.findViewById(R.id.check_sales);
        showSale_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new ConnectDBRunner()).start();
            }
        });
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView_sales);
        recyclerView.setHasFixedSize(true);
        oAdapter = new OrdersAdapter(InsertOrderList);
        RecyclerView.LayoutManager oLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(oLayoutManager);
        recyclerView.setAdapter(oAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class ConnectDBRunner implements Runnable {

        @Override
        public void run() {
            InsertOrderList.clear();
            HttpService httpService = HttpClient.getApiService();
            try {
                Response<OwnerSignUpDto> storename = httpService.getStoreName(loginId).execute();
                OwnerSignUpDto ownerSignUpDto = storename.body();

                String storeName = ownerSignUpDto.getStoreName();
                Response<List<OrdersDTO>> order = httpService.getOrder(storeName).execute();
                List<OrdersDTO> orderList = order.body();
                System.out.println(order.body().toString());
                for(int i=0; i<orderList.size(); i++){
                    InsertOrderList.add(new Sales(orderList.get(i).getMenuNameO(), orderList.get(i).getMenuCount(),
                            orderList.get(i).getMenuPriceO()));
                }
                System.out.printf(InsertOrderList.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view){

    }
}
