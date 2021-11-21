package com.example.apitest.order;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;
    private String storeName;
    private String menuName;
    private int menuPrice;
    private int menuCount;
    private Date orderDate;
    private int tableNum;

    @ColumnDefault("0")
    private int orderState;

    public static Orders addOrders(String userId, String storeName, String menuName, int menuPrice,
                                  int menuCount, Date orderDate, int tableNum, int orderState) {
        Orders orders = new Orders();
        orders.userId = userId;
        orders.storeName = storeName;
        orders.menuName = menuName;
        orders.menuPrice = menuPrice;
        orders.menuCount = menuCount;
        orders.orderDate = orderDate;
        orders.tableNum = tableNum;
        orders.orderState = orderState;
        return orders;
    }
}
