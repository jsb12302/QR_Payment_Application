package com.example.myapplication.owner.ui.seat_manage;

import android.content.Intent;

public class SeatOrder {

    String menuName;
    Integer menuPrice;
    Integer menuCount;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(Integer menuPrice) {
        this.menuPrice = menuPrice;
    }

    public Integer getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(Integer menuCount) {
        this.menuCount = menuCount;
    }

    public SeatOrder(String menuName, Integer menuPrice, Integer menuCount) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCount = menuCount;
    }
}
