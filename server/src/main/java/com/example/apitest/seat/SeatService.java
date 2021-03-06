package com.example.apitest.seat;

import com.example.apitest.menu.Menu;
import com.example.apitest.message.Message;
import com.example.apitest.store.Store;
import com.example.apitest.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    public void createSeat(String storeName) {
        Seat seat = null;

        for (int i = 1; i < 5; i++) {
            seat = Seat.addSeat(i, 1, storeName);
            seatRepository.save(seat);
        }
    }

    public List<Seat> getSeat(String storeName) {
        return seatRepository.findAllByStoreName(storeName);
    }

    @Transactional
    public Message changeSeatState(String storeName, Integer tableStatus, Integer tableNumber) throws Exception {
        seatRepository.changeSeatState(storeName, tableStatus, tableNumber);
        return null;
    }

    @Transactional
    public Message changeSeatStateToUse(String storeName, Integer tableStatus, Integer tableNumber) throws Exception {
        seatRepository.changeSeatStateToUse(storeName, tableStatus, tableNumber);
        return null;
    }
}
