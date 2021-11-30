package com.example.apitest.order;

import com.example.apitest.message.Message;
import com.example.apitest.signup.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @PostMapping("/addOrder")
    @ResponseBody
    public Message addOrder(@RequestBody OrdersDTO ordersDTO){
        ordersService.JoinOrder(ordersDTO);
        Message message=new Message();
        message.setMessage("주문 성공");
        return message;
    }

    @PostMapping("/getOrder")
    @ResponseBody
    public List<Orders> returnOrders(@RequestParam String storeName) {
        return ordersService.findOrder(storeName);
    }

//    @PostMapping("detailTable")
//    @ResponseBody
//    public
}
