package com.starchee.calculator.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public abstract class HistoryDao {

    @Transaction
    @Insert
    public Completable insert(History history){
        for (Expression expression : history.getExpressions()){
            expression.setSavedDate(history.getSavedDate().getDate());
        }
        return insertSavedDate(history.getSavedDate()).andThen(insertExpression(history.getExpressions()));
    }

    @Transaction
    @Insert
    public Completable insertCurrent(History history){
        for (Expression expression : history.getExpressions()){
            expression.setSavedDate(history.getSavedDate().getDate());
        }
        return insertCurrentDate(history.getSavedDate()).andThen(insertExpression(history.getExpressions()));
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract Completable insertSavedDate(SavedDate savedDate);

    @Insert
    abstract Completable insertExpression(List<Expression>expressions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract Completable insertCurrentDate(SavedDate date);

    @Delete
    public abstract Completable deleteCurrentDate(SavedDate date);

    @Query("DELETE FROM saved_date")
    public abstract Completable clearHistory();


    @Transaction
    @Query("SELECT * FROM saved_date")
    public abstract Flowable<List<History>> getAllHistoryByDate();
}
