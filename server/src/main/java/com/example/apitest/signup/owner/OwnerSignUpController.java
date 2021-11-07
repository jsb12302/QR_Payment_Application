package com.example.apitest.signup.owner;

import com.example.apitest.exception.EmptyFieldFoundException;
import com.example.apitest.exception.SameSignUpInfoFoundException;
import com.example.apitest.signup.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class OwnerSignUpController {

    @Autowired
    OwnerSignUpService ownerSignUpService;

    @PostMapping("/ownerSingUpRequest")
    @ResponseBody
    public Message register(@RequestBody OwnerDTO ownerDTO){
        ownerSignUpService.ownerRegister(ownerDTO);
        Message message=new Message();
        message.setMessage("가맹점주 회원가입 성공");
        return message;
    }

    @ExceptionHandler({EmptyFieldFoundException.class, SameSignUpInfoFoundException.class})
    public void handleSignUpException(Exception e) {
        Message message = new Message();
        message.setMessage(e.getMessage());
    }
}
