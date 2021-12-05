package com.example.apitest.order;

import com.example.apitest.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    public Orders addOrder(String userId, String storeName, String menuName, int menuPrice,
                           int menuCount, Date orderDate, int tableNum){
        Orders orders = Orders.addOrders(userId, storeName, menuName, menuPrice,
                menuCount, orderDate, tableNum, 0);
        return ordersRepository.save(orders);
    }

    public void JoinOrder(OrdersDTO ordersDTO){
        addOrder(ordersDTO.getUserId(), ordersDTO.getStoreName(), ordersDTO.getMenuName(), ordersDTO.getMenuPrice(),
                ordersDTO.getMenuCount(), ordersDTO.getOrderDate(), ordersDTO.getTableNum());
    }

    public List<Orders> findOrder(String storeName){
        List<Orders> allByStoreName = ordersRepository.findAllByStoreName(storeName);
        System.out.println(storeName);
        System.out.println(allByStoreName.get(0).getStoreName()+"/"+ allByStoreName.get(0).getOrderDate());
        return allByStoreName;
    }

    public List<Orders> findTableStatus(String storeName, Integer orderState){
        List<Orders> allByTableStatus=ordersRepository.findAllByTableStatus(storeName,orderState);
//        for(int i=0;i<allByTableStatus.size();i++){
//            System.out.println(allByTableStatus.get(i).getOrderState());
//            System.out.println(allByTableStatus.get(i).getTableNum());
//        }
        return allByTableStatus;
    }

    public List<Orders> findSeatOrders(Integer tableNum, Integer orderState, String storeName){
        List<Orders> allBySeatOrders=ordersRepository.findAllBySeatOrders(tableNum,orderState,storeName);
        return allBySeatOrders;
    }

    @Transactional
    public Message changeState(String storeName, Integer orderState, Integer tableNum) throws Exception {
        ordersRepository.changeOrderState(storeName, orderState, tableNum);
        return null;
    }
}
