package com.example.apitest.login;


import com.example.apitest.Role;
import com.example.apitest.exception.LoginFailException;
import com.example.apitest.exception.UserIdNotFoundException;
import com.example.apitest.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public Message loginAuth(@RequestBody LoginDto loginDto){
        Role role=loginService.login(loginDto);
        Message message=new Message();
        message.setMessage("로그인 성공");
        message.setId(loginDto.getId());
        message.setRole(role);
        return message;
    }

    @ExceptionHandler({LoginFailException.class, UserIdNotFoundException.class})
    @ResponseStatus
    public void handleSignUpException(Exception e) {
        Message message = new Message();
        message.setMessage(e.getMessage());

    }
}
