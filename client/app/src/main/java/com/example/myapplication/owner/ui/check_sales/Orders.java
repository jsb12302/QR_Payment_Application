package com.example.myapplication.owner.ui.check_sales;

import java.sql.Date;

public class Orders {
    String userId;
    String storeName;
    String menuNameO;
    int menuPriceO;
    int menuCount;
    Date orderDate;
    int tableNum;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getMenuNameO() {
        return menuNameO;
    }

    public void setMenuNameO(String menuNameO) {
        this.menuNameO = menuNameO;
    }

    public int getMenuPriceO() {
        return menuPriceO;
    }

    public void setMenuPriceO(int menuPriceO) {
        this.menuPriceO = menuPriceO;
    }

    public int getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(int menuCount) {
        this.menuCount = menuCount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public Orders(String userId, String storeName, String menuNameO, int menuPriceO, int menuCount, Date orderDate, int tableNum) {
        this.userId = userId;
        this.storeName = storeName;
        this.menuNameO = menuNameO;
        this.menuPriceO = menuPriceO;
        this.menuCount = menuCount;
        this.orderDate = orderDate;
        this.tableNum = tableNum;
    }
}
