package com.example.apitest.order;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String userId;
    String storeName;
    String menuName;
    int menuPrice;
    int menuCount;
    Date orderDate;
    int tableNum;

    @ColumnDefault("0")
    int orderState;

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
