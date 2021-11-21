package com.example.apitest.menu;

import com.example.apitest.exception.EmptyFieldFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    private Menu menu;

    public Menu registMenu(String menuName, String menuPrice, String menuDesc,String loginId){
        menu=Menu.addMenu(menuName,menuPrice,menuDesc,loginId);
        return menuRepository.save(menu);
    }

    public void addmenu(MenuDTO menuDTO){
        if(menuDTO.getMenuName().trim().isEmpty() || menuDTO.getMenuPrice().trim().isEmpty() ||
        menuDTO.getMenuDesc().trim().isEmpty()){
            throw new EmptyFieldFoundException("빈칸 존재");
        }else{
            registMenu(menuDTO.getMenuName(),menuDTO.getMenuPrice(),menuDTO.getMenuDesc(),
                    menuDTO.getLoginId());
        }
    }

    public List<Menu> findMenu(String loginId){
        List<Menu> allByLoginId = menuRepository.findAllByLoginId(loginId);
        return allByLoginId;
//        for(int i=0;i<allByLoginId.size();i++){
//            System.out.println(allByLoginId.get);
//        }
    }
}
