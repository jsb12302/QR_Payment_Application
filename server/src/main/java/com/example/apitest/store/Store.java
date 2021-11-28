package com.example.apitest.store;

import com.example.apitest.Role;
import lombok.*;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String ownerId;
    String storeName;
    String storeHp;
    String storeLoc;        // 가게 주소
    Double storeLatitude;   // 가게 위도
    Double storeLongitude;  // 가게 경도
    String storePic;
    String ownerNum;

    public static Store addStore(String ownerId, String storeName, String storeHp, String storeLoc,
                                 Double storeLatitude, Double storeLongitude, String storePic, String ownerNum){

        Store store = new Store();
        store.ownerId = ownerId;
        store.storeName = storeName;
        store.storeHp = storeHp;
        store.storeLoc = storeLoc;
        store.storeLatitude = storeLatitude;
        store.storeLongitude = storeLongitude;
        store.storePic = storePic;
        store.ownerNum = ownerNum;
        return store;
    }
}
