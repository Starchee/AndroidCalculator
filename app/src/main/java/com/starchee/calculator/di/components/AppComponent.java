package com.starchee.calculator.di.components;

import android.app.Application;

import com.starchee.calculator.di.modules.HistoryDatabaseModule;
import com.starchee.calculator.di.modules.ViewModelModule;
import com.starchee.calculator.ui.display.HistoryFragment;
import com.starchee.calculator.ui.keypad.PadAdvancedFragment;
import com.starchee.calculator.ui.keypad.PadNumberFragment;
import com.starchee.calculator.ui.keypad.PadOperationFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(dependencies = {}, modules = {HistoryDatabaseModule.class, ViewModelModule.class})
public interface AppComponent {

    void inject(HistoryFragment historyFragment);
    void inject(PadNumberFragment padNumberFragment);
    void inject(PadOperationFragment padOperationFragment);
    void inject(PadAdvancedFragment padAdvancedFragment);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder withApplication(Application application);
        AppComponent build();
    }
}
