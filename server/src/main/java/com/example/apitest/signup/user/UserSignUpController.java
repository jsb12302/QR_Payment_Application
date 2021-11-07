package com.example.apitest.signup.user;

import com.example.apitest.exception.EmptyFieldFoundException;
import com.example.apitest.exception.SameSignUpInfoFoundException;
import com.example.apitest.signup.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserSignUpController {

    @Autowired
    UserSignUpService signUpService;

    @PostMapping("userSignUpRequest")
    @ResponseBody
    public Message register(@RequestBody UserDTO userDTO){
        signUpService.userRegister(userDTO);
        Message message=new Message();
        message.setMessage("사용자 회원가입 성공");
        return message;
    }

    @ExceptionHandler({EmptyFieldFoundException.class, SameSignUpInfoFoundException.class})
    public Message handleSignUpException(Exception e) {
        Message message = new Message();
        message.setMessage(e.getMessage());
        return message;
    }

}
