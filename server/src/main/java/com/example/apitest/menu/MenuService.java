package com.example.apitest.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    public MenuDTO createMenu(String storeName, String menuName, String path, String fileName) {
        Menu menu = Menu.builder().storeName(storeName)
                .menuName((menuName))
                .path(path)
                .fileName(fileName)
                .build();
        menu = menuRepository.save(menu);

        return MenuDTO.builder()
                .storeName(menu.getStoreName())
                .menuName(menu.getMenuName())
                .path(menu.getPath())
                .fileName(menu.getFileName())
                .build();
    }

    public String getStoreByStoreName(String storeName) {
        Menu menu = menuRepository.findById(storeName);
        return menu.getPath()+menu.getFileName();
    }
}
