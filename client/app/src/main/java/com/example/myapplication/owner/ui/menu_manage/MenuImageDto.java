package com.example.myapplication.owner.ui.menu_manage;

import java.io.File;

public class MenuImageDto {

    String menuName;
    String menuPrice;
    String menuDesc;
    String loginId;
    File image;

    public MenuImageDto(String menuName, String menuPrice, String menuDesc, String loginId, File image) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDesc = menuDesc;
        this.loginId = loginId;
        this.image = image;
    }
}
