package com.example.myapplication.owner.ui.seat_manage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SeatManageViewModel extends ViewModel {
    private MutableLiveData<String> SeatText;

    public SeatManageViewModel() {
        SeatText = new MutableLiveData<>();
    }
    public LiveData<String> getText() {return SeatText;}
}
