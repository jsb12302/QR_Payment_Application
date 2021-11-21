package com.example.apitest.seat;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    int tableNumber;

    @Column(columnDefinition = "integer default 1")
    int tableStatus;

    String storeName;

    public static Seat addSeat(int Num, int Status, String Name) {
        Seat seat = new Seat();
        seat.tableNumber = Num;
        seat.tableStatus = Status;
        seat.storeName = Name;
        return seat;
    }
}
