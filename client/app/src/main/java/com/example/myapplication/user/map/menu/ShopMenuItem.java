package com.example.myapplication.user.map.menu;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class ShopMenuItem {

    private Bitmap menuPicture;
    private String menuName;
    private String menuContents;
    private String menuCost;

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

    public String getMenuContents() {
        return menuContents;
    }

    public void setMenuContents(String menuContents) {
        this.menuContents = menuContents;
    }

    public String getMenuCost() {
        return menuCost;
    }

    public void setMenuCost(String menuCost) {
        this.menuCost = menuCost;
    }
}
