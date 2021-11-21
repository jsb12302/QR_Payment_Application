package com.example.apitest.store;

import com.example.apitest.Role;
import lombok.*;

@Getter
@Setter
public class StoreDTO {

    String ownerId;
    String storeName;
    String storeHp;
    String storeLoc;
    Double storeLatitude;
    Double storeLongitude;
    String storePic;
    String ownerNum;

    public StoreDTO(String ownerId, String storeName, String storeHp, String storeLoc, Double storeLatitude, Double storeLongitude, String storePic, String ownerNum) {
        this.ownerId = ownerId;
        this.storeName = storeName;
        this.storeHp = storeHp;
        this.storeLoc = storeLoc;
        this.storeLatitude = storeLatitude;
        this.storeLongitude = storeLongitude;
        this.storePic = storePic;
        this.ownerNum = ownerNum;
    }
}
