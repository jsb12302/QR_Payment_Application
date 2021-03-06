package com.example.apitest.menu;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {

    String image; //이미지 경로
    String menuName;
    String menuPrice;
    String menuDesc;
    String loginId;
    String storeName;

    public MenuDTO(String image, String menuName, String menuPrice, String menuDesc, String loginId,String storeName) {
        this.image = image;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDesc = menuDesc;
        this.loginId = loginId;
        this.storeName=storeName;
    }
}
