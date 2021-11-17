package com.example.apitest.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDTO {

    private String storeName;
    private String menuName;
    private String path;
    private String fileName;

    @Builder
    public MenuDTO(String storeName, String menuName, String path, String fileName) {
        this.storeName = storeName;
        this.menuName = menuName;
        this.path = path;
        this.fileName = fileName;
    }
}
