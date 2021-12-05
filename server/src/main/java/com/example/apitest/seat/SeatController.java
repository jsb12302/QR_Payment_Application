package com.example.apitest.seat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SeatController {

    @Autowired
    SeatService seatService;

    @PostMapping("/addSeat")
    @ResponseBody
    public void addSeat(@RequestParam String loginId, @RequestParam String storeName) {
        seatService.createSeat(loginId, storeName);
    }
}
