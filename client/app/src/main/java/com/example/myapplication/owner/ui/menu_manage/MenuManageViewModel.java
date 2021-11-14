package com.example.myapplication.owner.ui.menu_manage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MenuManageViewModel extends ViewModel {

    private MutableLiveData<String> menuText;

    public MenuManageViewModel() {
        menuText = new MutableLiveData<>();
    }

    public LiveData<String> getText() { return menuText;}
}
