package com.starchee.calculator.model.HistoryExpression;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Expression.class, SavedDate.class}, version = 1 , exportSchema = false)
public abstract class HistoryDatabase extends RoomDatabase {

    public abstract HistoryDao getHistoryDao();

}
