package com.example.apitest.seat;

import com.example.apitest.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SeatController {

    @Autowired
    SeatService seatService;

    @PostMapping("/addSeat")
    @ResponseBody
    public void addSeat(@RequestParam String storeName) {
        seatService.createSeat(storeName);
    }

    @PostMapping("/getSeat")
    @ResponseBody
    public List<Seat> getSeat(@RequestParam String storeName) {
        return seatService.getSeat(storeName);
    }

    @PostMapping("/changeSeatState")
    @ResponseBody
    public Message updateSeatState(@RequestParam String storeName, @RequestParam Integer tableStatus,
                                    @RequestParam Integer tableNumber) throws Exception {
        return seatService.changeSeatState(storeName, tableStatus, tableNumber);
    }
}
