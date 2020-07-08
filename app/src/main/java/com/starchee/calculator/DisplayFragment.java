package com.starchee.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DisplayFragment extends Fragment {

    private TextView expressionTextView;
    private TextView answerTextView;
    private View.OnClickListener onClickListener;
    private StringBuilder stringBuilder;
    private boolean isDotEnabled = true;
    private boolean isOperatorEnabled = true;


    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setDotEnabled(boolean isDotEnabled) {
        this.isDotEnabled = isDotEnabled;
    }

    public boolean isDotEnabled() {
        return isDotEnabled;
    }

    public boolean isOperatorEnabled() {
        return isOperatorEnabled;
    }

    public void setOperatorEnabled(boolean operatorEnabled) {
        isOperatorEnabled = operatorEnabled;
    }

    public String getExpression(){
        return expressionTextView.getText().toString();
    }

    public void setExpressionToken(String token) {
        stringBuilder = new StringBuilder();
        expressionTextView.setText(stringBuilder.append(expressionTextView.getText().toString()).append(token).toString());
    }

    public void deleteExpressionToken(){
        stringBuilder = new StringBuilder();
        stringBuilder.append(expressionTextView.getText().toString());
        if (stringBuilder.length() > 0){
            expressionTextView.setText(stringBuilder.deleteCharAt(stringBuilder.length()-1));
        }
    }

    public void setAnswer(String answer){
        answerTextView.setText(answer);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.display_fragment, container, false);
        ImageView arrowButton = rootView.findViewById(R.id.arrow_display);
        expressionTextView = rootView.findViewById(R.id.expressionTextView);
        answerTextView  = rootView.findViewById(R.id.answerTextView);
        arrowButton.setOnClickListener(onClickListener);
        return rootView;
    }
}
