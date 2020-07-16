package com.starchee.calculator.ui.display;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.starchee.calculator.R;
import com.starchee.calculator.model.History;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private List<History> history;

    public HistoryAdapter(Context context) {
        this.history = new ArrayList<>();
        this.context = context;
    }

    public void setHistory(List<History> history) {

        this.history = history;
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
        final  History history = this.history.get(position);

        holder.titleTextView.setText(history.getDate());
        holder.expressionTextView.setText(history.getExpression());
        holder.answerTextView.setText(history.getAnswer());
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView expressionTextView;
        public TextView answerTextView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textViewTitle);
            expressionTextView = itemView.findViewById(R.id.textViewExpression);
            answerTextView = itemView.findViewById(R.id.textViewAnswer);
        }
    }
}
