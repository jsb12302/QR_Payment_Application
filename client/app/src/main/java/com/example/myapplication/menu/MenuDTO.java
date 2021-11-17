package com.example.myapplication.menu;

import android.net.Uri;

public class MenuDTO {

    String storeName;
    String menuName;
    Uri fileName;

    public MenuDTO(String storeName, String menuName, Uri fileName) {
        this.storeName = storeName;
        this.menuName = menuName;
        this.fileName = fileName;
    }
}