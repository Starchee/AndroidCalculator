package com.starchee.calculator;

import android.app.Application;

import com.starchee.calculator.di.components.AppComponent;
import com.starchee.calculator.di.components.DaggerAppComponent;


public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().withApplication(this).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
