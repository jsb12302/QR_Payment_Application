package com.example.apitest.signup.owner;

import com.example.apitest.Role;
import com.example.apitest.exception.EmptyFieldFoundException;
import com.example.apitest.exception.SameSignUpInfoFoundException;
import com.example.apitest.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerSignUpService {

    @Autowired
    OwnerRepository ownerRepository;

    private Owner owner;

    public Owner createOwner(String ownerId, String ownerPwd, String storeHP, Role role) {
        owner = Owner.JoinOwner(ownerId, ownerPwd, storeHP,role);
        return ownerRepository.save(owner);
    }

    public void ownerRegister(OwnerDTO ownerDTO) {
        Message message=new Message();
        if (ownerDTO.getOwnerId().trim().isEmpty() || ownerDTO.getOwnerPwd().trim().isEmpty()
                || ownerDTO.getStoreHP().trim().isEmpty()) {
            throw new EmptyFieldFoundException("빈칸 존재");
        } else if (ownerRepository.findByOwnerId(ownerDTO.getOwnerId()) != null) {
            throw new SameSignUpInfoFoundException("동일 아이디 존재");
        } else if (ownerRepository.findByStoreHP(ownerDTO.getStoreHP()) != null) {
            throw new SameSignUpInfoFoundException("동일 휴대폰 번호 존재");
        }
        else{
            createOwner(ownerDTO.getOwnerId(), ownerDTO.getOwnerPwd(),ownerDTO.getStoreHP(),ownerDTO.getRole());
        }

    }

}
