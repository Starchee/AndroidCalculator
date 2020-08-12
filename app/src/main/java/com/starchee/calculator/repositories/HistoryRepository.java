package com.starchee.calculator.repositories;

import com.starchee.calculator.model.History;
import com.starchee.calculator.model.HistoryDao;
import com.starchee.calculator.model.SavedDate;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class HistoryRepository {

    private HistoryDao historyDao;

    @Inject
    public HistoryRepository(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    public Completable insert(History history) {
        return historyDao.insert(history);
    }

    public Completable insertCurrentExpression(History history){
        return historyDao.insertCurrent(history);
    }

    public Completable deleteCurrentExpression(SavedDate savedDate){
        return historyDao.deleteCurrentDate(savedDate);
    }


    public Flowable<List<History>> getAll() {
        return historyDao.getAllHistoryByDate();
    }

    public Completable clearHistory(){
        return historyDao.clearHistory();

    }


}
