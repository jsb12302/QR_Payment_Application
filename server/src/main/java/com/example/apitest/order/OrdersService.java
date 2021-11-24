package com.example.apitest.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersService {


    @Autowired
    OrdersRepository ordersRepository;

    public List<Orders> findOrder(String storeName){
        List<Orders> allByStoreName = ordersRepository.findAllByStoreName(storeName);

        System.out.println(storeName);
        System.out.println(allByStoreName.size());
        return allByStoreName;
    }
}
