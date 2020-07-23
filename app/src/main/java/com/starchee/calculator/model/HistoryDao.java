package com.starchee.calculator.model;

import java.util.List;

import androidx.room.Dao;
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
    public Completable insert(SavedDate savedDate, Expression expression){
        expression.setSavedDate(savedDate.getDate());
        return insertSavedDate(savedDate).andThen(insertExpression(expression));
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract Completable insertSavedDate(SavedDate savedDate);

    @Insert
    abstract Completable insertExpression(Expression expressions);

    @Query("DELETE FROM saved_date")
    public abstract Completable clear();


    @Transaction
    @Query("SELECT * FROM saved_date")
    public abstract Flowable<List<History>> getAllHistoryByDate();
}
