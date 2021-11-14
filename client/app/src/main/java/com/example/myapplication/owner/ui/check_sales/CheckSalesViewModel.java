package com.example.myapplication.owner.ui.check_sales;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CheckSalesViewModel extends ViewModel {
    private MutableLiveData<String> salesText;

    public CheckSalesViewModel() {
        salesText = new MutableLiveData<>();
    }

    public LiveData<String> getText() { return salesText; }
}
