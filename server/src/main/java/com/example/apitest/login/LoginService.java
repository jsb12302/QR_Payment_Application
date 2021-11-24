package com.example.apitest.login;

import com.example.apitest.Role;
import com.example.apitest.exception.LoginFailException;
import com.example.apitest.exception.UserIdNotFoundException;
import com.example.apitest.message.Message;
import com.example.apitest.signup.owner.Owner;
import com.example.apitest.signup.owner.OwnerRepository;
import com.example.apitest.signup.user.User;
import com.example.apitest.signup.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OwnerRepository ownerRepository;

    public Role login(LoginDto loginDto) {
        Message message = new Message();

        if (loginDto.role.equals(Role.ROLE_OWNER)) {
            Owner owner = ownerRepository.findByOwnerId(loginDto.getId());

            // 아이디 체크
            if (Objects.isNull(owner)) {
                throw new LoginFailException("존재하지 않는 아이디");
            }

            // 비밀번호 확인
            if (owner.getOwnerPwd().equals(loginDto.pwd)) {
                return owner.getRole();
            } else {
                throw new LoginFailException("로그인 실패");
            }

        } else if (loginDto.role.equals(Role.ROLE_USER)) {
            User user = userRepository.findByUserId(loginDto.getId());

            if (Objects.isNull(user)) {
                throw new LoginFailException("존재하지 않는 아이디");
            }

            if (user.getUserPwd().equals(loginDto.pwd)) {
                return user.getRole();
            } else {
                throw new LoginFailException("로그인 실패");
            }

        }
        return null;
    }

    public Boolean ConfirmService(String loginId, String storeHP, Role role) {
        if (role.equals(Role.ROLE_USER)) {
            User user = userRepository.findByUserId(loginId);
            if (Objects.isNull(user)) {
                return false;
            } else {
                if (loginId.equals(user.getUserId()) && storeHP.equals(user.getUserHP())) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        else if (role.equals(Role.ROLE_OWNER)) {
            Owner owner = ownerRepository.findByOwnerId(loginId);
            if (Objects.isNull(owner)) {
                return false;
            } else {
                if (loginId.equals(owner.getOwnerId()) && storeHP.equals(owner.getStoreHP())) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    @Transactional
    public void ChangePwd(String changePwd,Role role){
        System.out.println(role);
        if (role.equals(Role.ROLE_USER)) {
            userRepository.UpdatePwd(changePwd);
            System.out.println(changePwd);
        }
        else{
            ownerRepository.UpdatePwd(changePwd);
            System.out.println(changePwd);
        }

    }

}
