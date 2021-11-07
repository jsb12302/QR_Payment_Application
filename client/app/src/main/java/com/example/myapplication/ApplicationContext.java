package com.example.myapplication;

import android.app.Application;
import android.content.Context;


public class ApplicationContext extends Application {

    private static ApplicationContext instance;

    public ApplicationContext(){
        instance = this;
    }

    public static ApplicationContext getInstance() {
        return instance;
    }
    public static void setInstance(ApplicationContext instance) {
        ApplicationContext.instance = instance;
    }

    public static final Context context(){
        if(instance == null){
            throw new NullPointerException("empty ApplicationContext instance");
        }
        return instance.getApplicationContext();
    }

}
