package com.example.apitest.seat;

import com.example.apitest.message.Message;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
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

    @PostMapping("/changeSeatStateToUse")
    @ResponseBody
    public Message updateSeatStateToUse(@RequestParam String storeName, @RequestParam Integer tableStatus,
                                   @RequestParam Integer tableNumber) throws Exception {
        return seatService.changeSeatStateToUse(storeName, tableStatus, tableNumber);
    }

}
