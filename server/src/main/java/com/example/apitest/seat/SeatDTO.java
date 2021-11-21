package com.example.apitest.seat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatDTO {
    int tableNumber;
    int tableStatus;
    String storeName;

    public SeatDTO(int Num, int Status, String Name){
        this.tableNumber = Num;
        this.tableStatus = Status;
        this.storeName = Name;
    }
}
