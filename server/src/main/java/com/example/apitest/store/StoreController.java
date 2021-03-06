package com.example.apitest.store;

import com.example.apitest.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping("/storeSignUpRequest")
    @ResponseBody
    public Message storeRegister(@RequestBody StoreDTO storeDTO){
        storeService.JoinStore(storeDTO);
        Message message=new Message();
        message.setMessage("가맹점 등록 성공");
        return message;
    }

    @PostMapping("/getStoreName")
    @ResponseBody
    public Store returnStore(@RequestParam String loginId) {
        return storeService.findStoreName(loginId);
    }

    @PostMapping("/getStoreInfo")
    @ResponseBody
    public List<Store> getStoreInfo() {
        return storeService.findStore();
    }

}