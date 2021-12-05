package com.example.myapplication.user.map.seat;

public class SeatDTO {
    String storeName;
    int tableNumber;
    int tableStatus;

    public SeatDTO(String storeName, int tableNumber, int tableStatus) {
        this.storeName = storeName;
        this.tableNumber = tableNumber;
        this.tableStatus = tableStatus;
    }
}
