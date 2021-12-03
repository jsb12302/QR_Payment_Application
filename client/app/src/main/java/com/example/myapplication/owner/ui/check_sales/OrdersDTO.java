package com.example.myapplication.owner.ui.check_sales;

import java.util.Date;

import lombok.*;

@Getter
@Setter
public class OrdersDTO {
    String userId;
    String storeName;
    String menuName;
    int menuPrice;
    int menuCount;
    Date orderDate;
    int tableNum;
    Integer orderState;

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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
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

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public OrdersDTO(String userId, String storeName, String menuName, int menuPrice, int menuCount, Date orderDate, int tableNum, Integer orderState) {
        this.userId = userId;
        this.storeName = storeName;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCount = menuCount;
        this.orderDate = orderDate;
        this.tableNum = tableNum;
        this.orderState = orderState;
    }
}