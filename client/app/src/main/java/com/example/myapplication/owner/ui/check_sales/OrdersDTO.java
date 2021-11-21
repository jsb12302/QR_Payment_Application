package com.example.myapplication.owner.ui.check_sales;

import java.util.Date;

import lombok.*;

@Getter
@Setter
public class OrdersDTO {
    String userId;
    String storeName;
    String menuNameO;
    int menuPriceO;
    int menuCount;
    Date orderDate;
    int tableNum;

    public OrdersDTO(String userId, String storeName, String menuNameO, int menuPriceO, int menuCount, Date orderDate, int tableNum) {
        this.userId = userId;
        this.storeName = storeName;
        this.menuNameO = menuNameO;
        this.menuPriceO = menuPriceO;
        this.menuCount = menuCount;
        this.orderDate = orderDate;
        this.tableNum = tableNum;
    }
}
