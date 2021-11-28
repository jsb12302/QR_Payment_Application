package com.example.myapplication.user.qr.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SendViewModel extends ViewModel {

    private final MutableLiveData<String> liveData = new MutableLiveData<>();

    public LiveData<String> getLiveData(){
        return liveData;
    }

    public void setLiveData(String str){
        liveData.setValue(str);
    }
}
