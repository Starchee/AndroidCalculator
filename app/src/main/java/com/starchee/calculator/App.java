package com.starchee.calculator;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

import com.starchee.calculator.di.components.AppComponent;
import com.starchee.calculator.di.components.DaggerAppComponent;

import androidx.annotation.NonNull;


public class App extends Application {

    private AppComponent appComponent;
    private static boolean isInternetAvailable;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().withApplication(this).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public boolean isInternetAvailable(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        cm.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){
            @Override
            public void onAvailable(@NonNull Network network) {
                isInternetAvailable = true;
            }

            @Override
            public void onLost(@NonNull Network network) {
                isInternetAvailable = false;
            }
        });

        return isInternetAvailable;

    }
}
