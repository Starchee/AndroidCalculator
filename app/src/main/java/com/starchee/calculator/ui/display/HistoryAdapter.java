package com.starchee.calculator.ui.display;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.starchee.calculator.R;
import com.starchee.calculator.model.History;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private List<String> dates;
    private List<History> histories;


    public void setDates(List<String> dates) {

        this.dates = dates;
        notifyDataSetChanged();
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_recycler_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.titleTextView.setText(dates.get(position));

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.childRecyclerView.getContext());
        layoutManager.setReverseLayout(true);

        HistoryChildAdapter historyChildAdapter = new HistoryChildAdapter();
        historyChildAdapter.setHistory(histories);
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setAdapter(historyChildAdapter);
        holder.childRecyclerView.setRecycledViewPool(recycledViewPool);


    }

    @Override
    public int getItemCount() {
        if (dates == null){
            return 0;
        }
        return dates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public RecyclerView childRecyclerView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            childRecyclerView = itemView.findViewById(R.id.childRecyclerView);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
    }
    }
}
