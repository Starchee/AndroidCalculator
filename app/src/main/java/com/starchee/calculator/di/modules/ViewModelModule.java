package com.starchee.calculator.di.modules;

import com.starchee.calculator.viewModels.DisplayViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DisplayViewModel.class)
    abstract ViewModel displayViewModel(DisplayViewModel displayViewModel);
}
