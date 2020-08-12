package com.starchee.calculator.di.modules;

import android.app.Application;

import com.starchee.calculator.model.HistoryDao;
import com.starchee.calculator.model.HistoryDatabase;
import com.starchee.calculator.repositories.HistoryRepository;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module(includes = {DisplayCalculatorModule.class})
public class HistoryDatabaseModule {

    private final static String DATABASE_NAME = "history_database";

    @Singleton
    @Provides
    HistoryDatabase providesHistoryDatabase(Application application){
        HistoryDatabase historyDatabase = Room.databaseBuilder(application,
                HistoryDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
        return historyDatabase;
    }

    @Singleton
    @Provides
    HistoryDao providesHistoryDao(HistoryDatabase historyDatabase){
        return historyDatabase.getHistoryDao();
    }

    @Singleton
    @Provides
    HistoryRepository historyRepository(HistoryDao historyDao){
        return new HistoryRepository(historyDao);
    }

}
