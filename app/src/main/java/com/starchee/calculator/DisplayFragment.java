package com.starchee.calculator;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.starchee.calculator.model.Main;

import java.util.ArrayDeque;
import java.util.Deque;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DisplayFragment extends Fragment implements
        View.OnClickListener,
        MainActivityPadListener {

    private View rootView;
    private TextView expressionTextView;
    private TextView answerTextView;
    private ImageView arrowButton;
    private StringBuilder stringBuilder;
    private boolean dotEnabled = true;
    private boolean operatorEnabled = false;
    private boolean calculateEnabled = false;
    private boolean expressionEmpty = true;
    private Deque<MathEnabled> stack = new ArrayDeque<>();
    private DisplayFragmentOnClickListener displayFragmentOnClickListener;

    public interface DisplayFragmentOnClickListener{
        void arrowButtonOnClickListener();
    }

    private String getExpression() {
        return expressionTextView.getText().toString();
    }

    private void setExpressionToken(String token) {
        stringBuilder = new StringBuilder();
        expressionTextView.setText(stringBuilder.append(expressionTextView.getText().toString()).append(token).toString());
        stack.push(new MathEnabled(dotEnabled, operatorEnabled, calculateEnabled));
        expressionEmpty = false;
    }

    private void clearExpression() {
        expressionTextView.setText("");
    }

    private void setAnswer(String answer) {
        answerTextView.setText(answer);
    }

    private String getAnswer() {
        return answerTextView.getText().toString();
    }

    private void clearAnswer() {
        answerTextView.setText("");
    }


    private void animator() {
        ValueAnimator animator = new ValueAnimator();
        int a = getResources().getColor(R.color.colorPrimaryLightBackground);
        int b = getResources().getColor(R.color.colorPrimary);
        animator.setIntValues(a, b, a);
        animator.setEvaluator(new ArgbEvaluator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                rootView.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
                arrowButton.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        animator.setDuration(500);
        animator.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.display_fragment, container, false);
        arrowButton = rootView.findViewById(R.id.arrow_display);
        expressionTextView = rootView.findViewById(R.id.expressionTextView);
        answerTextView = rootView.findViewById(R.id.answerTextView);
        arrowButton.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            displayFragmentOnClickListener = (DisplayFragment.DisplayFragmentOnClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement DisplayFragment.DisplayFragmentOnClickListener");
        }
    }

    @Override
    public void onClick(View view) {
        displayFragmentOnClickListener.arrowButtonOnClickListener();
    }

    @Override
    public void setOperandInExpression(String operand) {
        setExpressionToken(operand);
        operatorEnabled = true;
        if (calculateEnabled) {
            setAnswer(Main.calculate(getExpression()));
        }
    }

    @Override
    public void setDotInExpression(String dot) {
        if (dotEnabled) {
            setExpressionToken(dot);
            dotEnabled = false;
        }
    }

    @Override
    public void setBracketInExpression(String bracket) {
        setAnswer(bracket);
        operatorEnabled = true;
        if (calculateEnabled) {
            setAnswer(Main.calculate(getExpression()));
        }
    }

    @Override
    public void setOperatorInExpression(String operator) {
        if (expressionEmpty && operator.equals(getResources().getString(R.string.subtraction))){
            setExpressionToken(operator);

        } else if (!expressionEmpty && expressionTextView.getText().toString().length() > 1){
            if (!operatorEnabled) {
                deleteExpressionToken();
            }
            setExpressionToken(operator);
            dotEnabled = true;
            calculateEnabled = true;
            operatorEnabled = false;
        }
    }

    @Override
    public void deleteExpressionToken() {
        stringBuilder = new StringBuilder();
        stringBuilder.append(expressionTextView.getText().toString());
        if (stringBuilder.length() > 0) {
            expressionTextView.setText(stringBuilder.deleteCharAt(stringBuilder.length() - 1));
            stack.pop().setEnabledValues();
            clearAnswer();
        } else {
            expressionEmpty = true;
        }
    }

    @Override
    public void setAnswer() {
        calculateEnabled = false;
        clearExpression();
        setExpressionToken(getAnswer());
        clearAnswer();
    }

    @Override
    public void clearDisplay() {
     if (!expressionEmpty){
         clearAnswer();
         clearExpression();
         animator();
     }
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

        public void setEnabledValues() {
            DisplayFragment.this.dotEnabled = this.dotEnabled;
            DisplayFragment.this.operatorEnabled = this.operatorEnabled;
            DisplayFragment.this.calculateEnabled = this.calculateEnabled;
        }
    }
}
