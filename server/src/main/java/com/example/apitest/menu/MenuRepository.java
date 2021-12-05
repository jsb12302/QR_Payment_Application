package com.example.apitest.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findAllByLoginId(String loginId);
    List<Menu> findAllByStoreName(String storeName);
    Menu findById(String loginId);
}
