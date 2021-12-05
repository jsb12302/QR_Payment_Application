package com.example.myapplication.user.map.menu;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class ShopMenuItem {

    private Bitmap menuPicture;
    private String menuName;
    private String menuDesc;
    private String menuPrice;

    public ShopMenuItem(Bitmap menuPicture, String menuName, String menuContents, String menuCost) {
        this.menuPicture = menuPicture;
        this.menuName = menuName;
        this.menuDesc = menuContents;
        this.menuPrice = menuCost;
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
