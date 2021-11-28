package com.example.myapplication.owner.ui.menu_manage;

import android.graphics.Bitmap;

public class Menu {


    String menuName;
    String menuPrice;
    String menuDesc;
    Bitmap menuImage;

    public Menu(String menuName, String menuPrice, String menuDesc,Bitmap menuImage) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDesc = menuDesc;
        this.menuImage = menuImage;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public Bitmap getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(Bitmap menuImage) {
        this.menuImage = menuImage;
    }
}
