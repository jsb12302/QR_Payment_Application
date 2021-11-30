package com.example.apitest.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("select m from Orders m where m.storeName =:storeName")
    List<Orders> findAllByStoreName (@Param("storeName")String storeName);

    @Query("select m from Orders m where m.storeName=:storeName and m.orderState=:orderState")
    List<Orders> findAllByTableStatus(@Param("storeName")String storeName,
                                      @Param("orderState")Integer orderState);

    @Query("select m from Orders m where m.storeName=:storeName and m.orderState=:orderState and m.tableNum=:tableNum")
    List<Orders> findAllBySeatOrders(@Param("tableNum") Integer tableNum,
                                     @Param("orderState")Integer orderState,
                                     @Param("storeName")String storeName);

}

