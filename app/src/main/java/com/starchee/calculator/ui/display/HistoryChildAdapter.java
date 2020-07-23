package com.starchee.calculator.ui.display;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.starchee.calculator.R;
import com.starchee.calculator.model.Expression;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryChildAdapter extends RecyclerView.Adapter<HistoryChildAdapter.ViewHolder> {

    private List<Expression> expressions;

    public void setExpressions(List<Expression> expressions) {

        this.expressions = expressions;
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
        final Expression expression = this.expressions.get(position);


        holder.expressionTextView.setText(expression.getExpression());
        holder.answerTextView.setText(expression.getAnswer());
    }

    @Override
    public int getItemCount() {
        if (expressions == null){
            return 0;
        }
        return expressions.size();
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
