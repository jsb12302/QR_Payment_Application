package com.example.apitest.signup.owner;

import com.example.apitest.signup.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OwnerRepository extends JpaRepository<Owner,Long> {

    Owner findByOwnerId(String ownerId);
    Owner findByStoreHP(String storeHP);

    @Modifying(clearAutomatically = true)
    @Query("update Owner m SET m.ownerPwd =:pwd WHERE m.ownerId=:id")
    public void UpdatePwd(@Param("id")String id,@Param("pwd")String pwd);

}
