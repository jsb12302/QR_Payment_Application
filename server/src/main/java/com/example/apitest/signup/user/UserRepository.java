package com.example.apitest.signup.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserId(String userId);
    User findByUserHP(String userHP);

}
