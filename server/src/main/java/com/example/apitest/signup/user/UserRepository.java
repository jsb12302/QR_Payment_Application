package com.example.apitest.signup.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserId(String userId);
    User findByUserHP(String userHP);

    @Modifying(clearAutomatically = true)
    @Query("update User m SET m.userPwd =:pwd")
    public void UpdatePwd(@Param("pwd")String pwd);
}
