package com.example.myapplication.owner.ui.menu_manage;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto {


    String menuName;
    String menuPrice;
    String menuDesc;
    String loginId;
    String image;

    public MenuDto(String menuName, String menuPrice, String menuDesc,String loginId) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDesc = menuDesc;
        this.loginId = loginId;
    }
}
