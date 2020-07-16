package com.starchee.calculator.repositories;

import android.app.Application;

import com.starchee.calculator.model.History;
import com.starchee.calculator.model.HistoryDao;
import com.starchee.calculator.model.HistoryDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class HistoryRepository {

    private HistoryDao historyDao;

    public HistoryRepository(Application application) {
        HistoryDatabase database = HistoryDatabase.getDatabase(application);
        historyDao = database.getHistoryDao();
    }

    public Completable insert(History history) {
        return historyDao.insert(history);
    }

    public Flowable<List<String>> getAllDates() {

        return historyDao.getAllDates();
    }

    public Flowable<List<History>> getAll(String date) {
        return historyDao.getAllHistoryByDate(date);
    }

    public Completable clear(){
        return historyDao.clear();

    }
}
