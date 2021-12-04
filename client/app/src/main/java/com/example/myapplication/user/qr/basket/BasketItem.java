package com.example.myapplication.user.qr.basket;

import android.graphics.drawable.Drawable;

public class BasketItem {

    private String menuName;
    private String menuCost;
    private String menuTotal;
    private String menuNum;

    public String getMenuTotal() {
        return menuTotal;
    }

    public void setMenuTotal(String menuTotal) {
        this.menuTotal = menuTotal;
    }

    public String getMenuNum() {
        return menuNum;
    }

    public void setMenuNum(String menuNum) {
        this.menuNum = menuNum;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuCost() {
        return menuCost;
    }

    public void setMenuCost(String menuCost) {
        this.menuCost = menuCost;
    }

}
