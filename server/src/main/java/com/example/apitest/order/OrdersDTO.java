package com.example.apitest.order;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

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
    int orderState;

    public OrdersDTO(String userId, String storeName, String menuName, int menuPrice,
                     int menuCount, Date orderDate, int tableNum, int orderState) {
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
