package com.starchee.calculator.ui.display;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.starchee.calculator.R;
import com.starchee.calculator.model.History;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private List<History> histories;

    public void setHistories(List<History> histories) {
        this.histories = histories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.history_recycler_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final History history = histories.get(position);
        holder.titleTextView.setText(history.getSavedDate().getDate());

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.childRecyclerView.getContext());
        HistoryChildAdapter historyChildAdapter = new HistoryChildAdapter();
        historyChildAdapter.setExpressions(history.getExpressions());
        holder.childRecyclerView.setLayoutManager(layoutManager);
        holder.childRecyclerView.setAdapter(historyChildAdapter);
        holder.childRecyclerView.setRecycledViewPool(recycledViewPool);
    }

    @Override
    public int getItemCount() {
        if (histories == null){
            return 0;
        }
        return histories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public RecyclerView childRecyclerView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            childRecyclerView = itemView.findViewById(R.id.childRecyclerView);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
    }
    }
}
