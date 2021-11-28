package com.example.apitest.order;

import com.example.apitest.store.Store;
import com.example.apitest.store.StoreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    public Orders addOrder(String userId, String storeName, String menuName, int menuPrice,
                           int menuCount, Date orderDate, int tableNum, int orderState){
        Orders orders = Orders.addOrders(userId, storeName, menuName, menuPrice,
                menuCount, orderDate, tableNum, orderState);
        return ordersRepository.save(orders);
    }

    public void JoinOrder(OrdersDTO ordersDTO){
        addOrder(ordersDTO.getUserId(), ordersDTO.getStoreName(), ordersDTO.getMenuName(), ordersDTO.getMenuPrice(),
                ordersDTO.getMenuCount(), ordersDTO.getOrderDate(), ordersDTO.getTableNum(), ordersDTO.getOrderState());
    }

    public List<Orders> findOrder(String storeName){
        List<Orders> allByStoreName = ordersRepository.findAllByStoreName(storeName);
        System.out.println(storeName);
        System.out.println(allByStoreName.get(0).getStoreName()+"/"+ allByStoreName.get(0).getOrderDate());
        return allByStoreName;
    }
}
