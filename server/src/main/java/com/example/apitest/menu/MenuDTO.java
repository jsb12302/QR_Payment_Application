package com.example.apitest.menu;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {

    String menuName;
    String menuPrice;
    String menuDesc;
    String loginId;

    public MenuDTO(String menuName, String menuPrice, String menuDesc, String loginId) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDesc = menuDesc;
        this.loginId = loginId;
    }
}
