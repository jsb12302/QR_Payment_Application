package com.example.myapplication.owner.ui.menu_manage;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto {

    String image; //이미지 임시로 만들어놓음
    String menuName;
    String menuPrice;
    String menuDesc;
    String loginId;

    public MenuDto(String menuName, String menuPrice, String menuDesc,String loginId) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDesc = menuDesc;
        this.loginId = loginId;
    }
}
