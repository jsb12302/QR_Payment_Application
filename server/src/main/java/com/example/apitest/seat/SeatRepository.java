package com.example.apitest.seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
    List<Seat> findAllByStoreName(String storeName);

    @Modifying
    @Query(value = "update Seat m set m.tableStatus=:tableStatus where m.storeName=:storeName and m.tableNumber=:tableNumber and m.tableStatus=0")
    void changeSeatState(@Param("storeName")String storeName,
                          @Param("orderState")Integer orderState,
                          @Param("tableNumber") Integer tableNumber) throws Exception;
}
