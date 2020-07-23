package com.starchee.calculator.repositories;

import android.app.Application;

import com.starchee.calculator.model.Expression;
import com.starchee.calculator.model.History;
import com.starchee.calculator.model.HistoryDao;
import com.starchee.calculator.model.HistoryDatabase;
import com.starchee.calculator.model.SavedDate;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class HistoryRepository {

    private HistoryDao historyDao;

    public HistoryRepository(Application application) {
        HistoryDatabase database = HistoryDatabase.getDatabase(application);
        historyDao = database.getHistoryDao();
    }

    public Completable insert(SavedDate savedDate, Expression expression) {
        return historyDao.insert(savedDate, expression);
    }

    public Flowable<List<History>> getAll() {
        return historyDao.getAllHistoryByDate();
    }

    public Completable clear(){
        return historyDao.clear();

    }
}
