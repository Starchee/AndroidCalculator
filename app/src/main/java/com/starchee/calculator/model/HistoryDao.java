package com.starchee.calculator.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface HistoryDao {

    @Insert
    Completable insert(History history);

    @Query("DELETE FROM history")
    Completable clear();

    @Query("SELECT date FROM history GROUP BY date")
    Flowable<List<String>> getAllDates();

    @Query("SELECT * FROM history WHERE date = :date")
    Flowable<List<History>> getAllHistoryByDate(String date);
}
