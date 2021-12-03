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

    @PostMapping("/getTableStatus")
    @ResponseBody
    public List<Orders> returnTableStatus(@RequestParam String storeName,@RequestParam Integer orderState){
        return ordersService.findTableStatus(storeName,orderState);
    }

    @PostMapping("/getSeatOrders")
    @ResponseBody
    public List<Orders> returnSeatOrders(@RequestParam Integer tableNum, @RequestParam Integer orderState,
                                         @RequestParam String storeName){
        return ordersService.findSeatOrders(tableNum,orderState,storeName);
    }

    @PostMapping("/changeOrderState")
    @ResponseBody
    public Message updateOrderState(@RequestParam String storeName, @RequestParam Integer orderState,
                                    @RequestParam Integer tableNum) throws Exception {
        return ordersService.changeState(storeName, orderState, tableNum);
    }
}
