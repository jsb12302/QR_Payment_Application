package com.example.apitest.order;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order {
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

    @ColumnDefault("false")
    private boolean state;

    public static Order addOrder(String userId, String storeName, String menuName, int menuPrice,
                                 int menuCount, Date orderDate, int tableNum, boolean state) {
        Order order = new Order();
        order.userId = userId;
        order.storeName = storeName;
        order.menuName = menuName;
        order.menuPrice = menuPrice;
        order.menuCount = menuCount;
        order.orderDate = orderDate;
        order.tableNum = tableNum;
        order.state = state;
        return order;
    }
}
