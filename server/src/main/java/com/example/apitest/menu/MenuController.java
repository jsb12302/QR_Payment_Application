package com.example.apitest.menu;

import com.example.apitest.message.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping("/addMenuRequest")
    @ResponseBody
    public Status regist_menu(@RequestBody MenuDTO menuDTO){
        menuService.addmenu(menuDTO);
        Status status=new Status();
        status.setStatus("ok");
        return status;
    }

    @PostMapping("/getMenuInfo")
    @ResponseBody
    public List<Menu> returnMenu(@RequestParam String loginId){
        return menuService.findMenu(loginId);
    }

//    @PostMapping("/getMenuInfo")
//    public void returnMenu(){
//        menuService.findMenu("1");
//    }
}
