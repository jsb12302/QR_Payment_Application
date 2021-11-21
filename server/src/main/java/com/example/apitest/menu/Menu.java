package com.example.apitest.menu;


import com.example.apitest.Role;
import lombok.*;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String image; //이미지 임시로 만들어놓음
    String menuName;
    String menuPrice;
    String menuDesc;
    String loginId;

    public static Menu addMenu(String menuName, String menuPrice, String menuDesc, String loginId) {
        Menu menu=new Menu();
        menu.menuName = menuName;
        menu.menuPrice = menuPrice;
        menu.menuDesc = menuDesc;
        menu.loginId = loginId;
        return menu;
    }

}
