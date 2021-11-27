package com.example.myapplication.owner.ui.check_sales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentOwnerCheckSalesBinding;
import com.example.myapplication.owner.ui.menu_manage.MenuDto;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.signup.OwnerSignUpDto;
import com.example.myapplication.store.StoreSignUpDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import lombok.SneakyThrows;
import retrofit2.Response;

public class CheckSalesFragment extends Fragment implements View.OnClickListener{
    private CheckSalesViewModel checkSalesViewModel;
    private FragmentOwnerCheckSalesBinding binding;
    private OrdersAdapter oAdapter;
    private RecyclerView recyclerView;
    String loginId=((MainActivity)MainActivity.context_main).var;
    public static ArrayList<Sales> InsertOrderList = new ArrayList<>();
    static LocalDate date;



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
            @SneakyThrows
            @Override
            public void onClick(View view) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                EditText text = (EditText) root.findViewById(R.id.check_date);
                String temp;
                temp = text.getText().toString();
                date = LocalDate.parse(temp, formatter);
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
                Response<StoreSignUpDto> storenames = httpService.getStoreName(loginId).execute();
                StoreSignUpDto storeSignUpDto = storenames.body();
                String storeName = storeSignUpDto.getStoreName();
                Response<List<OrdersDTO>> order = httpService.getOrder(storeName).execute();
                List<OrdersDTO> orderList = order.body();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                Response<List<MenuDto>> menu=httpService.getMenu(loginId).execute();
                List<MenuDto> menuList= menu.body();
                for(int i=0; i<menuList.size();i++){
                    InsertOrderList.add(i, new Sales(menuList.get(i).getMenuName(), 0, 0));
                }
                for(int i=0; i<orderList.size(); i++){
                    if (sdf.format(orderList.get(i).getOrderDate()).equals(date.toString())){
                        for(int j=0; j<menuList.size(); j++){
                            if (orderList.get(i).getMenuName().equals(menuList.get(j).getMenuName())){
                                InsertOrderList.set(j, new Sales(orderList.get(i).getMenuName(),
                                        InsertOrderList.get(j).getMenuPrice()+orderList.get(i).getMenuCount(),
                                        orderList.get(i).getMenuPrice()));
                            }
                        }
                    }
                }
                for(int i=0; i< InsertOrderList.size();i++){
                    InsertOrderList.set(i, new Sales(InsertOrderList.get(i).getMenuName(),
                            InsertOrderList.get(i).getMenuPrice(),
                            InsertOrderList.get(i).getMenuPrice()*InsertOrderList.get(i).menuCount));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View view){

    }
}
