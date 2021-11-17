package com.example.apitest.menu;

import com.example.apitest.Role;
import lombok.*;
import javax.persistence.*;

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

    private String storeName;
    private String menuName;
    private String path;
    private String fileName;

    public static Menu JoinMenu(String storeName, String menuName, String path, String fileName) {
        Menu menu = new Menu();
        menu.storeName = storeName;
        menu.menuName = menuName;
        menu.path = path;
        menu.fileName = fileName;

        return menu;
    }

}
