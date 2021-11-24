package com.example.apitest.signup.owner;

import com.example.apitest.signup.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner,Long> {

    Owner findByOwnerId(String ownerId);
    Owner findByStoreHP(String storeHP);
}
