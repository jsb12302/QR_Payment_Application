package com.example.apitest.menu;

import com.example.apitest.signup.owner.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findById(String storeMenu);
}
