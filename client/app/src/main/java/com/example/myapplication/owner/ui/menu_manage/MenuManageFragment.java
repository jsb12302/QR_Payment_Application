package com.example.myapplication.owner.ui.menu_manage;

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
import com.example.myapplication.databinding.FragmentOwnerMenuManageBinding;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Response;

public class MenuManageFragment extends Fragment implements View.OnClickListener{

    public static ArrayList<Menu> InsertMenuList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuAdapter mAdapter;

    String loginId=((MainActivity)MainActivity.context_main).var; //로그인 아이디 가져오기
    private MenuManageViewModel menuManageViewModel;
    private FragmentOwnerMenuManageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        menuManageViewModel =
                new ViewModelProvider(this).get(MenuManageViewModel.class);
        binding = FragmentOwnerMenuManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button add_menu=root.findViewById(R.id.bt_go_menu_page);

        add_menu.setOnClickListener(new View.OnClickListener(){ //프레그먼트에서 엑티비티 호출할때
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddMenuRequest.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView_menu);
        recyclerView.setHasFixedSize(true);
        mAdapter = new MenuAdapter(InsertMenuList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        return root;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new ConnectGetRunner()).start();
    }

    public class ConnectGetRunner implements Runnable {

        @Override
        public void run() {
            InsertMenuList.clear();
            HttpService httpService= HttpClient.getApiService();
            try {
                Response<List<MenuDto>> menu=httpService.getMenu(loginId).execute();
                List<MenuDto> menuList= menu.body();
                for(int i=0;i<menuList.size();i++) {
                    InsertMenuList.add(new Menu(menuList.get(i).getMenuName(),menuList.get(i).getMenuPrice(),
                            menuList.get(i).getMenuDesc()));
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {

    }

}
