package com.example.myapplication.user.map;

public class Stores {
    String storeName;
    String storeHp;
    String storeLoc;
    Double storeLatitude;
    Double storeLongitude;

    public Stores(String storeName, String storeHp, String storeLoc, Double storeLatitude, Double storeLongitude) {
        this.storeName = storeName;
        this.storeHp = storeHp;
        this.storeLoc = storeLoc;
        this.storeLatitude = storeLatitude;
        this.storeLongitude = storeLongitude;
    }
}