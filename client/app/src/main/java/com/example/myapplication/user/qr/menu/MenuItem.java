package com.example.myapplication.user.qr.menu;

import android.graphics.drawable.Drawable;

public class MenuItem {

    private Drawable menuPicture;
    private String menuName;
    private String menuContents;
    private String menuCost;

    public Drawable getMenuPicture() {
        return menuPicture;
    }

    public void setMenuPicture(Drawable menuPicture) {
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
