package com.example.apitest.signup.user;

import com.example.apitest.Role;
import com.example.apitest.exception.EmptyFieldFoundException;
import com.example.apitest.exception.SameSignUpInfoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserSignUpService {

    @Autowired
    UserRepository userRepository;

    private User user;

    public User createUser(String userId, String userPwd, String userPwd2,
                           String userName, String userHP, Role role){
        user=User.JoinUser(userId,userPwd,userPwd2,userName,userHP,role);
        return userRepository.save(user);
    }

    //DTO로 회원가입
    public void userRegister(UserDTO userDTO){
        if (userDTO.getUserId().trim().isEmpty() || userDTO.getUserPwd().trim().isEmpty()
                || userDTO.getUserPwd2().trim().isEmpty() || userDTO.getUserName().trim().isEmpty()
                || userDTO.getUserHP().trim().isEmpty()){
            throw new EmptyFieldFoundException("빈칸 존재");
        }else if (userRepository.findByUserId(userDTO.getUserId())!=null){
            throw new SameSignUpInfoFoundException("동일 아이디 존재");
        }else if (userRepository.findByUserHP(userDTO.getUserHP())!=null){
            throw new SameSignUpInfoFoundException("동일 휴대폰 번호 존재");
        }else if (!userDTO.getUserPwd().equals(userDTO.getUserPwd2())){
            throw new SameSignUpInfoFoundException("재입력 비밀번호 불일치");
        }else{
            createUser(userDTO.getUserId(),userDTO.getUserPwd(),userDTO.getUserPwd2(),
                    userDTO.getUserName(), userDTO.getUserHP(),userDTO.getRole());
        }
    }


}