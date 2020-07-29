package com.starchee.calculator.viewModels;

import android.app.Application;

import com.starchee.calculator.model.Expression;
import com.starchee.calculator.model.History;
import com.starchee.calculator.model.SavedDate;
import com.starchee.calculator.repositories.HistoryRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class HistoryViewModel extends AndroidViewModel {

    private HistoryRepository historyRepository;
    private LiveData<List<History>> liveDataHistory;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        historyRepository = new HistoryRepository(application);
    }


    private void getAllHistory(){
        liveDataHistory = LiveDataReactiveStreams.fromPublisher(historyRepository.getAll());
    }

    public LiveData<List<History>> getLiveDataHistory() {
        if (liveDataHistory == null ){
            getAllHistory();
        }
        return liveDataHistory;
    }


    public Completable clearHistory(){
        return historyRepository.clearHistory();
    }


}
