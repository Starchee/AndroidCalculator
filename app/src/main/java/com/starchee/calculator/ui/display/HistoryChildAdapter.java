package com.starchee.calculator.ui.display;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.starchee.calculator.R;
import com.starchee.calculator.model.History;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryChildAdapter extends RecyclerView.Adapter<HistoryChildAdapter.ViewHolder> {

    private List<History> history;

    public void setHistory(List<History> history) {

        this.history = history;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_child_recycler_item, parent, false);

        return new HistoryChildAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryChildAdapter.ViewHolder holder, int position) {
        final  History history = this.history.get(position);


        holder.expressionTextView.setText(history.getExpression());
        holder.answerTextView.setText(history.getAnswer());
    }

    @Override
    public int getItemCount() {
        if (history == null){
            return 0;
        }
        return history.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView expressionTextView;
        public TextView answerTextView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            expressionTextView = itemView.findViewById(R.id.textViewExpression);
            answerTextView = itemView.findViewById(R.id.textViewAnswer);
        }
    }
}
