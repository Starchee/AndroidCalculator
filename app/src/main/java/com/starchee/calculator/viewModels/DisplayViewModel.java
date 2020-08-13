package com.starchee.calculator.viewModels;

import android.util.Log;

import com.starchee.calculator.App;
import com.starchee.calculator.Utils.DisplayCalculator;
import com.starchee.calculator.Utils.SingleLiveEvent;
import com.starchee.calculator.model.HistoryExpression.History;
import com.starchee.calculator.model.HistoryExpression.SavedDate;
import com.starchee.calculator.model.currency.Currency;
import com.starchee.calculator.model.currency.NetworkService;
import com.starchee.calculator.model.currency.ServerResponse;
import com.starchee.calculator.repositories.HistoryRepository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DisplayViewModel extends ViewModel {

    private boolean visibleClrButton = false;
    private HistoryRepository historyRepository;
    private DisplayCalculator displayCalculator;
    private NetworkService networkService;
    private LiveData<List<History>> historyLiveData;
    private SingleLiveEvent<Boolean> visibleClrLiveData;

    @Inject
    public DisplayViewModel(
            HistoryRepository historyRepository,
            DisplayCalculator displayCalculator,
            NetworkService networkService
    ) {
        this.historyRepository = historyRepository;
        this.displayCalculator = displayCalculator;
        this.networkService = networkService;
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

    public SingleLiveEvent<Boolean> getVisibleClrLiveData() {
        if (visibleClrLiveData == null){
            visibleClrLiveData = new SingleLiveEvent<>();
        }
        return visibleClrLiveData;
    }

    public Completable clearHistory(){
        clearDisplay();
        return historyRepository.clearHistory();
    }
    
    public void deleteExpressionToken() {
        insertCurrentExpression(displayCalculator.deleteExpressionToken());
    }

    public void setOperandInExpression(String operand) {
        if (visibleClrButton){
            visibleClrLiveData.setValue(false);
        }
        insertCurrentExpression(displayCalculator.setOperandInExpression(operand));
    }

    public void setDotInExpression(String dot) {
        if (visibleClrButton){
            visibleClrLiveData.setValue(false);
        }
        insertCurrentExpression(displayCalculator.setDotInExpression(dot));
    }

    public void setBracketInExpression(String bracket) {
        if (visibleClrButton){
            visibleClrLiveData.setValue(false);
        }
        insertCurrentExpression(displayCalculator.setBracketInExpression(bracket));
    }

    public void setOperatorInExpression(String operator) {
        if (visibleClrButton){
            visibleClrLiveData.setValue(false);
        }
        insertCurrentExpression(displayCalculator.setOperatorInExpression(operator));
    }

    public void clearDisplay() {
        insertCurrentExpression(displayCalculator.clearDisplay());
    }

    public void setEquals(){
        History resultHistory = displayCalculator.setEquals();
        if (resultHistory != null){
            deleteCurrentExpression(new SavedDate("Current expression"));
            insertExpression(resultHistory);
            visibleClrLiveData.setValue(true);
            visibleClrButton = true;
        }

    }

    public void setCurrencyInExpression(String currency) {
        String charCode = "";
        if (currency.equals("$")) {
            charCode = "USD";
        } else if (currency.equals("€")) {
            charCode = "EUR";
        }

        String finalCharCode = charCode;
        networkService.getJSONApi().getAllCurrency()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ServerResponse>() {
                    @Override
                    public void onSuccess(ServerResponse serverResponse) {
                        for (Map.Entry<String, Currency> entry : serverResponse.getValute().entrySet()) {
                            Currency currency = entry.getValue();
                            String x = "";
                            if (currency.getCharCode().equals(finalCharCode)){
                                setOperandInExpression(String.valueOf(currency.getValue()));
                            }
                            Log.d("myCalc", x);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("myCalc", e.toString());
                    }
                });
    }
}
