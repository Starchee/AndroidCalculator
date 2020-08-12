package com.starchee.calculator.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Expression.class, SavedDate.class}, version = 1)
public abstract class HistoryDatabase extends RoomDatabase {

    public abstract HistoryDao getHistoryDao();

}
