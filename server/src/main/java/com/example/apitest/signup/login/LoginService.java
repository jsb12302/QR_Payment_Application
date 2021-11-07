package com.example.apitest.signup.login;

import com.example.apitest.Role;
import com.example.apitest.exception.LoginFailException;
import com.example.apitest.exception.UserIdNotFoundException;
import com.example.apitest.signup.Message;
import com.example.apitest.signup.owner.Owner;
import com.example.apitest.signup.owner.OwnerRepository;
import com.example.apitest.signup.user.User;
import com.example.apitest.signup.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OwnerRepository ownerRepository;

    public Role login(LoginDto loginDto) {
        Message message=new Message();
        User user = userRepository.findByUserId(loginDto.getId());
        Owner owner = ownerRepository.findByOwnerId(loginDto.getId());

        if (Objects.isNull(user)&&Objects.isNull(owner)){
            throw new LoginFailException("존재하지 않는 아이디");
        }

        if(!Objects.isNull(user)) {

            if (user.getRole().equals(Role.ROLE_USER)) { //사용자 역할 확인
                if (user.getUserPwd().equals(user.getUserPwd())) {
                    return user.getRole();
                } else {
                    throw new LoginFailException("로그인 실패");
                }
            }
        }
        if (!Objects.isNull(owner)){
            if (owner.getOwnerId().equals(owner.getOwnerPwd())) {
                return owner.getRole();
            } else {
                throw new LoginFailException("로그인 실패");
            }
        }
        return null;
    }

}
