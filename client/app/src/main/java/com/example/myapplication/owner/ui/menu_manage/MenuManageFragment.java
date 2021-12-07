package com.example.myapplication.owner.ui.menu_manage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.databinding.FragmentOwnerMenuManageBinding;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Response;

public class MenuManageFragment extends Fragment implements View.OnClickListener{

    public static ArrayList<Menu> InsertMenuList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuAdapter mAdapter;

    String imgUrl = "http://42.82.69.82:8080/img?loginId=";

    public static Bitmap globalBitmap;


    String loginId=((MainActivity)MainActivity.context_main).var; //로그인 아이디 가져오기
    private MenuManageViewModel menuManageViewModel;
    private FragmentOwnerMenuManageBinding binding;


    @SneakyThrows
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        new Thread(new ConnectGetRunner()).start();
        Thread.sleep(1000);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        menuManageViewModel =
                new ViewModelProvider(this).get(MenuManageViewModel.class);
        binding = FragmentOwnerMenuManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button add_menu=binding.btGoMenuPage;
        add_menu.setOnClickListener(new View.OnClickListener(){ //프레그먼트에서 엑티비티 호출할때
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddMenuRequest.class);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) binding.recyclerViewMenu;
        recyclerView.setHasFixedSize(true);
        mAdapter = new MenuAdapter(InsertMenuList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        return root;
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
                            menuList.get(i).getMenuDesc(),"http://42.82.69.82:8080/img?storeName=" + menuList.get(i).storeName + "&menuName=" + menuList.get(i).menuName));
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

    private class Back extends AsyncTask<String, Integer,Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            try{
                
                URL myFileUrl = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();

                InputStream is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                is.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap img){
            globalBitmap=img;
        }
    }

}
