package com.starchee.calculator.viewModels;

import android.app.Application;

import com.starchee.calculator.model.History;
import com.starchee.calculator.repositories.HistoryRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

public class HistoryViewModel extends AndroidViewModel {

    private HistoryRepository historyRepository;
    private LiveData<List<String>> liveDataDates;
    private LiveData<List<History>> liveDataHistory;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        historyRepository = new HistoryRepository(application);
    }

    private void getAllDates(){
        liveDataDates = LiveDataReactiveStreams.fromPublisher(historyRepository.getAllDates());

    }

    private void getAllHistoryByDate(String date){
        liveDataHistory = LiveDataReactiveStreams.fromPublisher(historyRepository.getAll(date));
    }

    public LiveData<List<String>> getLiveDataDates() {
        if (liveDataDates == null){
            getAllDates();
        }

        return liveDataDates;
    }

    public LiveData<List<History>> getLiveDataHistory(String date) {
        if (liveDataHistory == null ){
            getAllHistoryByDate(date);
        }
        return liveDataHistory;
    }


}
