package com.example.apitest.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query(nativeQuery = true,value = "select * from Orders m where m.store_name =:storeName")
    List<Orders> findAllByStoreName (@Param("storeName")String storeName);

//    @Query(nativeQuery = true, value = "SELECT * FROM images WHERE image_address=:address")
//    List<Images> findByAddress(@Param("address") String image_address);
}
