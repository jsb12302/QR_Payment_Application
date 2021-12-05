package com.example.myapplication.user.qr.menu;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class MenuItem {

    private Bitmap menuPicture;
    private String menuName;
    private String menuDesc;

    public MenuItem(Bitmap menuPicture, String menuName, String menuDesc, String menuPrice) {
        this.menuPicture = menuPicture;
        this.menuName = menuName;
        this.menuDesc = menuDesc;
        this.menuPrice = menuPrice;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    private String menuPrice;

    public Bitmap getMenuPicture() {
        return menuPicture;
    }

    public void setMenuPicture(Bitmap menuPicture) {
        this.menuPicture = menuPicture;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

}
