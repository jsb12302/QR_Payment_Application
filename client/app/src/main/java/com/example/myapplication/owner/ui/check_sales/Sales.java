package com.example.myapplication.owner.ui.check_sales;

import java.sql.Date;

public class Sales {
    String menuNameS;
    int menuPriceS;
    int menuCountS;

    public Sales(String menuNameS,  int menuCountS, int menuPriceS) {
        this.menuNameS = menuNameS;
        this.menuPriceS = menuPriceS;
        this.menuCountS = menuCountS;
    }

    public String getMenuNameS() {
        return menuNameS;
    }

    public void setMenuNameS(String menuNameS) {
        this.menuNameS = menuNameS;
    }

    public int getMenuPriceS() {
        return menuPriceS;
    }

    public void setMenuPriceS(int menuPriceS) {
        this.menuPriceS = menuPriceS;
    }

    public int getMenuCountS() {
        return menuCountS;
    }

    public void setMenuCountS(int menuCountS) {
        this.menuCountS = menuCountS;
    }

}
