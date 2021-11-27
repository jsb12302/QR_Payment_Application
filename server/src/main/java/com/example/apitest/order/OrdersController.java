package com.example.apitest.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrdersController {


    @Autowired
    OrdersService ordersService;

    @PostMapping("/getOrder")
    @ResponseBody
    public List<Orders> returnOrders(@RequestParam String storeName) {
        return ordersService.findOrder(storeName);
    }
}
