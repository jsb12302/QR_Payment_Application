package com.example.myapplication.user.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.example.myapplication.retrofit2.HttpClient;
import com.example.myapplication.retrofit2.HttpService;
import com.example.myapplication.user.UserMain;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    //검색 창에 접근하기 위한 객체 생성
    private SearchView searchView;

    // 마커 정보를 담기 위한 리스트 생성
    List<Marker> marker = new ArrayList<>();

    // 서버에서 받아온 가게 정보를 저장할 리스트 생성
    public static List<Stores> storeList = null;

    // 네이버 지도 객체 생성
    private NaverMap naverMap;

    // 레이아웃 위에 띄울 또 다른 레이아웃 객체 생성
    LinearLayout storeInfo_layout = null;

    private FusedLocationSource locationSource;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private static final String[] PERMISSION = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_shop);
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                for (int i = 0; i < storeList.size(); i++) {

                    // 검색창이 공백인 경우
                    if (s.equals("")) {
                        for (int k = 0; k < storeList.size(); k++) {
                            marker.get(k).setMap(naverMap);
                        }
                        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
                        searchView.clearFocus();
                        storeInfo_layout.setVisibility(View.INVISIBLE);
                        break;
                    }

                    // 검색 값과 일치하는 가게명이 있는 경우
                    if (storeList.get(i).storeName.equals(s)) {
                        CameraPosition cameraPosition =
                                new CameraPosition(new LatLng(
                                        storeList.get(i).storeLatitude, storeList.get(i).storeLongitude),
                                        17);
                        naverMap.moveCamera(CameraUpdate.toCameraPosition(cameraPosition));

                        for (int j = 0; j < storeList.size(); j++) {
                            marker.get(j).setMap(null);
                        }

                        marker.get(i).setMap(naverMap);
                        searchView.clearFocus();

                        showStoreInfo(storeList.get(i).storeName, storeList.get(i).storeLoc, storeList.get(i).storeHp);
                        break;
                    }
                }
                // 입력받은 문자열 처리
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // 입력란의 문자열이 바뀔 때 처리
                if(s.equals("")){
                    CameraUpdate cameraUpdate = CameraUpdate.zoomTo(14);
                    naverMap.moveCamera(cameraUpdate);
                    this.onQueryTextSubmit("");
                }
                return false;
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);

        new Thread(new ConnectRunner()).start();
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setMapType(NaverMap.MapType.Basic);
        naverMap.setSymbolScale(1.5f);
        naverMap.setIndoorEnabled(true);

        naverMap.setLocationSource(locationSource);
        ActivityCompat.requestPermissions(this, PERMISSION, LOCATION_PERMISSION_REQUEST_CODE);

        naverMap.setOnMapClickListener((coord, point) -> {
            if (storeInfo_layout != null && storeInfo_layout.getVisibility() == View.VISIBLE) {
                storeInfo_layout.setVisibility(View.INVISIBLE);
            }
        });
        for (int i = 0; i < storeList.size(); i++) {
            System.out.println(storeList.get(i).storeName);
            Marker mk = new Marker();
            mk.setPosition(new LatLng(storeList.get(i).storeLatitude, storeList.get(i).storeLongitude));
            mk.setMap(naverMap);

            mk.setCaptionText(storeList.get(i).storeName);
            mk.setCaptionHaloColor(Color.WHITE);
            mk.setCaptionTextSize(10);

            mk.setIcon(OverlayImage.fromResource(R.drawable.marker_resize));

            marker.add(mk);
        }

        Overlay.OnClickListener listener = overlay -> {
            Marker overlay_marker = (Marker)overlay;
            if (storeInfo_layout != null && storeInfo_layout.getVisibility() == View.VISIBLE) {
                storeInfo_layout.setVisibility(View.INVISIBLE);
            }

            else if (storeInfo_layout == null || storeInfo_layout.getVisibility() == View.INVISIBLE) {
                for (int i = 0; i < storeList.size(); i++) {
                    if (marker.get(i) == overlay_marker) {
                        showStoreInfo(storeList.get(i).storeName, storeList.get(i).storeLoc, storeList.get(i).storeHp);
                    }
                }
            }
            return true;
        };

        for (int i = 0; i < storeList.size(); i++) {
            marker.get(i).setOnClickListener(listener);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)){
            if(!locationSource.isActivated()){
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
                return;
            }else{
                naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public class ConnectRunner implements Runnable {

        @Override
        public void run() {
            HttpService httpService = HttpClient.getApiService();
            try {
                Response<List<Stores>> store = httpService.getStore().execute();
                storeList = store.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void showStoreInfo(String selectStoreName, String selectStoreLoc, String selectStoreHP) {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // store_info 레이아웃 객체 생성
        storeInfo_layout = (LinearLayout)inflater.inflate(R.layout.store_info, null);

        TextView storeName = (TextView) storeInfo_layout.findViewById(R.id.storeName);
        storeName.setText(selectStoreName);

        TextView storeLoc = (TextView) storeInfo_layout.findViewById(R.id.storeLoc);
        storeLoc.setText(selectStoreLoc);

        TextView storeHP = (TextView) storeInfo_layout.findViewById(R.id.storeHP);
        storeHP.setText(selectStoreHP);

        // 현재 레이아웃 위에 store_info 레이아웃 겹치기
        LinearLayout.LayoutParams paramll = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT);
        addContentView(storeInfo_layout, paramll);
    }

    public void goStore(View v) {
        Intent intent = new Intent(getApplicationContext(), UserMain.class);
        startActivity(intent);
    }

}

