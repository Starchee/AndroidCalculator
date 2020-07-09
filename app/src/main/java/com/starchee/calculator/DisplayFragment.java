package com.starchee.calculator;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.Deque;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DisplayFragment extends Fragment {

    private View rootView;
    private TextView expressionTextView;
    private TextView answerTextView;
    private View.OnClickListener onClickListener;
    private StringBuilder stringBuilder;
    private boolean dotEnabled = true;
    private boolean operatorEnabled = false;
    private boolean calculateEnabled = false;
    private Deque<MathEnabled> stack = new ArrayDeque<>();



    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setDotEnabled(boolean dotEnabled) {
        this.dotEnabled = dotEnabled;
    }

    public boolean isDotEnabled() {
        return dotEnabled;
    }

    public boolean isOperatorEnabled() {
        return operatorEnabled;
    }

    public boolean isCalculateEnabled() {
        return calculateEnabled;
    }

    public void setCalculateEnabled(boolean calculateEnabled) {
        this.calculateEnabled = calculateEnabled;
    }

    public void setOperatorEnabled(boolean operatorEnabled) {
        this.operatorEnabled = operatorEnabled;
    }

    public String getExpression(){
        return expressionTextView.getText().toString();
    }

    public void setExpressionToken(String token) {
        stringBuilder = new StringBuilder();
        expressionTextView.setText(stringBuilder.append(expressionTextView.getText().toString()).append(token).toString());
        stack.push(new MathEnabled(dotEnabled, operatorEnabled, calculateEnabled));
    }

    public void deleteExpressionToken(){
        stringBuilder = new StringBuilder();
        stringBuilder.append(expressionTextView.getText().toString());
        if (stringBuilder.length() > 0){
            expressionTextView.setText(stringBuilder.deleteCharAt(stringBuilder.length()-1));
            stack.pop().setEnabledValues();
            clearAnswer();
        }
    }

    public void clearExpression(){
        expressionTextView.setText("");
    }

    public void setAnswer(String answer){
        answerTextView.setText(answer);
    }

    public String getAnswer(){
        return answerTextView.getText().toString();
    }

    public void clearAnswer(){
        answerTextView.setText("");
    }

    public void clearDisplay(){
        clearAnswer();
        clearExpression();
        animator();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.display_fragment, container, false);
        ImageView arrowButton = rootView.findViewById(R.id.arrow_display);
        expressionTextView = rootView.findViewById(R.id.expressionTextView);
        answerTextView  = rootView.findViewById(R.id.answerTextView);
        arrowButton.setOnClickListener(onClickListener);
        return rootView;
    }

    private void animator(){
        ValueAnimator animator = new ValueAnimator();
        int a = getResources().getColor(R.color.colorPrimaryLightBackground);
        int b = getResources().getColor(R.color.colorPrimary);
        animator.setIntValues(a ,b , a);
        animator.setEvaluator(new ArgbEvaluator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                rootView.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        animator.setDuration(500);
        animator.start();
    }

    private class MathEnabled {

        private boolean dotEnabled;
        private boolean operatorEnabled;
        private boolean calculateEnabled;

        public MathEnabled(boolean dotEnabled, boolean operatorEnabled, boolean calculateEnabled) {
            this.dotEnabled = dotEnabled;
            this.operatorEnabled = operatorEnabled;
            this.calculateEnabled = calculateEnabled;
        }

        public void setEnabledValues(){
            DisplayFragment.this.dotEnabled = this.dotEnabled;
            DisplayFragment.this.operatorEnabled = this.operatorEnabled;
            DisplayFragment.this.calculateEnabled = this.calculateEnabled;
        }
    }
}
