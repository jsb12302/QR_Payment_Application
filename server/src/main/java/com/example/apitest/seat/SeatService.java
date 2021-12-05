package com.example.apitest.seat;

import com.example.apitest.store.Store;
import com.example.apitest.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;

    public void createSeat(String loginId, String storeName) {
        Seat seat = null;

        for (int i = 1; i < 5; i++) {
            seat = Seat.addSeat(i, 1, storeName);
            seatRepository.save(seat);
        }

    }
}
