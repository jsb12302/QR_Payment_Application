package com.example.apitest.signup.user;

import com.example.apitest.exception.EmptyFieldFoundException;
import com.example.apitest.exception.SameSignUpInfoFoundException;
import com.example.apitest.message.Message;
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
        String status = signUpService.userRegister(userDTO);
        Message message=new Message();
        message.setMessage(status);
        return message;
    }

    @ExceptionHandler({EmptyFieldFoundException.class, SameSignUpInfoFoundException.class})
    public Message handleSignUpException(Exception e) {
        Message message = new Message();
        message.setMessage(e.getMessage());
        return message;
    }

}
