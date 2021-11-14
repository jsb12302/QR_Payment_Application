package com.example.apitest.signup.owner;

import com.example.apitest.Role;
import com.example.apitest.exception.EmptyFieldFoundException;
import com.example.apitest.exception.SameSignUpInfoFoundException;
import com.example.apitest.signup.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class OwnerSignUpService {

    @Autowired
    OwnerRepository ownerRepository;

    private Owner owner;

    public Owner createOwner(String ownerId, String ownerPwd, String ownerPwd2,
                             String ownerNum, String storeName,
                             String storeLoc, String ownerName, String storeHP, Role role) {
        owner = Owner.JoinOwner(ownerId, ownerPwd, ownerPwd2,
                ownerNum, storeName,
                storeLoc, ownerName, storeHP,role);
        return ownerRepository.save(owner);
    }

    public void ownerRegister(OwnerDTO ownerDTO) {
        Message message=new Message();
        if (ownerDTO.getOwnerId().trim().isEmpty() || ownerDTO.getOwnerPwd().trim().isEmpty()
                || ownerDTO.getOwnerPwd2().trim().isEmpty() || ownerDTO.getOwnerNum().trim().isEmpty()
                || ownerDTO.getStoreName().trim().isEmpty()
                || ownerDTO.getStoreLoc().trim().isEmpty() || ownerDTO.getOwnerName().trim().isEmpty()
                || ownerDTO.getStoreHP().trim().isEmpty()) {
            throw new EmptyFieldFoundException("빈칸 존재");
        } else if (ownerRepository.findByOwnerId(ownerDTO.getOwnerId()) != null) {
            throw new SameSignUpInfoFoundException("동일 아이디 존재");
        } else if (ownerRepository.findByStoreHP(ownerDTO.getStoreHP()) != null) {
            throw new SameSignUpInfoFoundException("동일 휴대폰 번호 존재");
        }else if (!ownerDTO.getOwnerPwd().equals(ownerDTO.getOwnerPwd2())) {
            throw new SameSignUpInfoFoundException("재입력 비밀번호 불일치");
        }else{
            createOwner(ownerDTO.getOwnerId(), ownerDTO.getOwnerPwd(), ownerDTO.getOwnerPwd2(),
                    ownerDTO.getOwnerNum(), ownerDTO.getStoreName(),
                    ownerDTO.getStoreLoc(), ownerDTO.getOwnerName(), ownerDTO.getStoreHP(),ownerDTO.getRole());
        }

    }
}
