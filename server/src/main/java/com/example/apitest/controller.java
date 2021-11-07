package com.example.apitest;

import com.example.apitest.signup.user.User;
import com.example.apitest.signup.user.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class controller {

    @GetMapping("/test2")
    @ResponseBody
    public Item test(@RequestParam("name") String name){
        System.out.println(name);
        Item item=new Item("123",name+"hi");
        return item;
    }

//    @GetMapping("/test") //필드 각각 테스트
//    @ResponseBody
//    public void test(@RequestParam("userId") String userId,@RequestParam("userPwd") String userPwd,
//                     @RequestParam("userPwd2") String userPwd2,@RequestParam("userName") String userName
//                    ,@RequestParam("userHP") String userHP) {
//        System.out.println(userId);
//        System.out.println(userHP);
//    }

//    @PostMapping("/test3") //유저 객체 테스트
//    @ResponseBody
//    public User test(@RequestBody UserDTO userDTO) {
//        System.out.println("connect");
//        User user=new User(1,userDTO.getUserId(), userDTO.getUserPwd(),
//                userDTO.getUserPwd2(), userDTO.getUserName(), userDTO.getUserHP());
//        System.out.println("connect2");
//        return user;
//    }

}
