package com.starchee.calculator.viewModels;

import android.app.Application;

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

public class DisplayViewModel extends AndroidViewModel {


    private InputDisplay inputDisplay;
    private LiveData<List<History>> historyLiveData;
    private HistoryRepository historyRepository;

    public DisplayViewModel(@NonNull Application application) {
        super(application);
        historyRepository = new HistoryRepository(application);
        inputDisplay = new InputDisplay();
    }

    private void insertCurrentExpression(History history){
        if (history == null){
            deleteCurrentExpression(new SavedDate("Current expression"));
            return;
        }

        historyRepository.insertCurrentExpression(history)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void insertExpression(History history){
        historyRepository.insert(history)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void deleteCurrentExpression(SavedDate savedDate){
        historyRepository.deleteCurrentExpression(savedDate)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void getAllHistory(){
        historyLiveData = LiveDataReactiveStreams.fromPublisher(historyRepository.getAll());
    }

    public LiveData<List<History>> getHistoryLiveData() {
        if (historyLiveData == null ){
            getAllHistory();
        }
        return historyLiveData;
    }


    public Completable clearHistory(){
        clearDisplay();
        return historyRepository.clearHistory();
    }



    public void deleteExpressionToken() {
        insertCurrentExpression(inputDisplay.deleteExpressionToken());
    }

    public void setOperandInExpression(String operand) {
        insertCurrentExpression(inputDisplay.setOperandInExpression(operand));
    }

    public void setDotInExpression(String dot) {
        insertCurrentExpression(inputDisplay.setDotInExpression(dot));
    }

    public void setBracketInExpression(String bracket) {
        insertCurrentExpression(inputDisplay.setBracketInExpression(bracket));
    }

    public void setOperatorInExpression(String operator) {
        insertCurrentExpression(inputDisplay.setOperatorInExpression(operator));
    }

    public void clearDisplay() {
        insertCurrentExpression(inputDisplay.clearDisplay());
    }

    public void setEquals(){
        History resultHistory = inputDisplay.setEquals();
        if (resultHistory != null){
            deleteCurrentExpression(new SavedDate("Current expression"));
            insertExpression(resultHistory);
        }

    }
}