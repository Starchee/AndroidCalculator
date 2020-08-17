package com.starchee.calculator.Utils;

import android.content.res.Resources;

import com.starchee.calculator.R;
import com.starchee.calculator.mathExpressionCalculator.MathExpressionCalculator;
import com.starchee.calculator.model.HistoryExpression.Expression;
import com.starchee.calculator.model.HistoryExpression.History;
import com.starchee.calculator.model.HistoryExpression.SavedDate;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

import javax.inject.Inject;

public class DisplayCalculator {

    private final String SUBTRACTION;
    private final String DOT;
    private boolean answerSet = false;
    private History currentHistory;
    private Expression expression;
    private DisplayCalculator.MathEnabled currentMathEnabled;
    private StringBuilder currentExpression;
    private Deque<DisplayCalculator.MathEnabled> mathEnabledStack;
    private MathExpressionCalculator calculator;
    private Resources resources;

    @Inject
    public DisplayCalculator(MathExpressionCalculator calculator, Resources resources) {
        this.calculator  = calculator;
        this.resources = resources;
        currentMathEnabled = new DisplayCalculator.MathEnabled();
        currentExpression = new StringBuilder();
        mathEnabledStack = new ArrayDeque<>();
        currentHistory = new History();
        expression = new Expression();
        SavedDate savedDate = new SavedDate("Current expression");
        currentHistory = new History(savedDate, expression);
        SUBTRACTION = resources.getString(R.string.subtraction);
        DOT = resources.getString(R.string.dot);
    }


    private void setExpressionToken(String token) {
        currentExpression.append(token);
        expression.setExpression(currentExpression.toString());
        mathEnabledStack.push(
                new DisplayCalculator.MathEnabled(
                        currentMathEnabled.dotEnabled,
                        currentMathEnabled.operatorEnabled,
                        currentMathEnabled.calculateEnabled)
        );
    }

    private void setAnswerData() {
        expression.setAnswer(calculator.calculate(currentExpression.toString()));
    }

    private void clearExpression() {
        currentExpression = new StringBuilder();
        expression.setExpression("");
    }


    private void clearAnswer() {
        expression.setAnswer(null);
    }

    public History deleteExpressionToken() {
        if (currentExpression.length() > 0) {
            expression.setExpression(currentExpression.deleteCharAt(currentExpression.length() - 1).toString());
            currentMathEnabled = mathEnabledStack.pop();
            clearAnswer();
            if (currentMathEnabled.calculateEnabled && currentMathEnabled.operatorEnabled) {
                setAnswerData();
            }
        }

        if (currentExpression.length() == 0){
            clearExpression();
        }

        return currentHistory;
    }

    public History setOperandInExpression(String operand) {
        if (answerSet){
            clearDisplay();
            answerSet = false;
        }
        setExpressionToken(operand);
        currentMathEnabled.operatorEnabled = true;
        if (currentMathEnabled.calculateEnabled) {
            setAnswerData();
        }
        return currentHistory;
    }

    public History setCurrencyInExpression(String currency){
        for (int i = 0; i < currency.length()-1; i++){
            String x = currency.substring(i,i+1);
            if (x.equals(DOT)) {
                setDotInExpression(x);
            } else {
                setOperandInExpression(x);
            }
        }
        return  currentHistory;
    }

    public History setDotInExpression(String dot) {
        if (answerSet){
            clearDisplay();
            answerSet = false;
        }
        if (currentMathEnabled.dotEnabled) {
            setExpressionToken(dot);
            currentMathEnabled.dotEnabled = false;
        }

        return currentHistory;
    }

    public History setBracketInExpression(String bracket) {
        if (answerSet){
            clearDisplay();
            answerSet = false;
        }
        setExpressionToken(bracket);
        currentMathEnabled.operatorEnabled = true;
        if (currentMathEnabled.calculateEnabled) {
            setAnswerData();
        }
        return currentHistory;
    }

    public History setOperatorInExpression(String operator) {
        if (answerSet){
            answerSet = false;
        }
        //resourceProvider!!! need
        if (currentExpression.length() == 0 && operator.equals(SUBTRACTION)) {
            setExpressionToken(operator);

        } else if (currentExpression.length() == 1 &&
                currentExpression.toString().equals(SUBTRACTION) || currentExpression.length() == 1 &&
                currentExpression.toString().equals(DOT)){
            deleteExpressionToken();
        } else if (currentExpression.length() > 0) {
            if (!currentMathEnabled.operatorEnabled) {
                deleteExpressionToken();
            }
            setExpressionToken(operator);
            currentMathEnabled.dotEnabled = true;
            currentMathEnabled.calculateEnabled = true;
            currentMathEnabled.operatorEnabled = false;
        }

        if (expression.getExpression() == null){
            return null;
        }

        return currentHistory;
    }

    public History clearDisplay() {
        if (currentExpression.length() > 0) {
            currentMathEnabled = new DisplayCalculator.MathEnabled();
            clearExpression();
            clearAnswer();
        }

        if (expression.getExpression() == null){
            return null;
        }

        return currentHistory;
    }

    public History setEquals(){

        if (expression.getAnswer() == null){
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
        SavedDate savedDate = new SavedDate(simpleDateFormat.format(new Date()));
        History answer = new History();
        Expression answerExpression = new Expression();
        answerExpression.setExpression(expression.getExpression());
        answerExpression.setAnswer(expression.getAnswer());
        answerExpression.setSavedDate(savedDate.getDate());
        answer.setSavedDate(savedDate);
        answer.setExpression(answerExpression);

        currentExpression = new StringBuilder();
        currentExpression.append(expression.getAnswer());
        answerSet = true;
        clearAnswer();
        return answer;

    }


    private static class MathEnabled {

        private boolean dotEnabled = true;
        private boolean operatorEnabled = false;
        private boolean calculateEnabled = false;

        public MathEnabled() {
        }

        public MathEnabled(boolean dotEnabled, boolean operatorEnabled, boolean calculateEnabled) {
            this.dotEnabled = dotEnabled;
            this.operatorEnabled = operatorEnabled;
            this.calculateEnabled = calculateEnabled;
        }
    }
}
