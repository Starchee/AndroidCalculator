package com.starchee.calculator.ui.display;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starchee.calculator.R;
import com.starchee.calculator.model.History;
import com.starchee.calculator.viewModels.HistoryViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryFragment extends Fragment {

    private View rootView;
    private HistoryAdapter historyAdapter;
    private RecyclerView recyclerView;
    private HistoryViewModel historyViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.history_fragment, container, false);
        historyViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HistoryViewModel.class);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        historyAdapter = new HistoryAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        set();
        recyclerView.setAdapter(historyAdapter);

        return rootView;
    }

    public void set () {
        LiveData<List<String>> data = historyViewModel.getLiveDataDates();
        data.observe(this, dates -> {
            Log.d("TAGGGG","MAIN" +  dates.toString());
            for (String x : dates){
                get(x);
            }
        });
    }

    public void get(String date){
        LiveData<List<History>> data2 = historyViewModel.getLiveDataHistory(date);
        data2.observe(this, histories -> {
                historyAdapter.setHistory(histories);

        });
    }

}
