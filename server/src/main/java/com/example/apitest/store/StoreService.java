package com.example.apitest.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    private Store store;

    public Store createStore(String ownerId, String storeName, String storeHp,
            String storeLoc, Double storeLatitude, Double storeLongitude, String storePic, String ownerNum){
        store=Store.addStore(ownerId, storeName, storeHp, storeLoc,storeLatitude, storeLongitude, storePic, ownerNum);
        return storeRepository.save(store);
    }
    public void JoinStore(StoreDTO storeDTO){
        createStore(storeDTO.getOwnerId(),storeDTO.getStoreName(),storeDTO.getStoreHp(),
                storeDTO.getStoreLoc(),storeDTO.getStoreLatitude(),storeDTO.getStoreLongitude(),
                storeDTO.getStorePic(),storeDTO.getOwnerNum());
    }
}
