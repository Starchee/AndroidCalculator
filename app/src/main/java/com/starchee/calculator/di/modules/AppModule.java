package com.starchee.calculator.di.modules;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    Resources providesResources(Application application){
        return application.getResources();
    }
}
