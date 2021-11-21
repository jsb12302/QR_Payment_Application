package com.example.apitest.order;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class OrderDTO {

    private String userId;
    private String storeName;
    private String menuName;
    private int menuPrice;
    private int menuCount;
    private Date orderDate;
    private int tableNum;
    private boolean state;

    public OrderDTO(String userId, String storeName, String menuName, int menuPrice,
                    int menuCount, Date orderDate, int tableNum, boolean state) {
        this.userId = userId;
        this.storeName = storeName;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCount = menuCount;
        this.orderDate = orderDate;
        this.tableNum = tableNum;
        this.state = state;
    }
}
