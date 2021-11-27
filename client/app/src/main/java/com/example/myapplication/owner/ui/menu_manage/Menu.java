package com.example.myapplication.owner.ui.menu_manage;

public class Menu {

    String menuName;
    String menuPrice;
    String menuDesc;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public Menu(String menuName, String menuPrice, String menuDesc) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuDesc = menuDesc;
    }
}
