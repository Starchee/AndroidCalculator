package com.starchee.calculator.viewModels;

import com.starchee.calculator.ExpressionCalculator.Main;
import com.starchee.calculator.model.Expression;
import com.starchee.calculator.model.History;
import com.starchee.calculator.model.SavedDate;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

public class InputDisplay {

    private final String SUBTRACTION = "-";
    private History currentHistory;
    private Expression expression;
    private SavedDate savedDate;

    private InputDisplay.MathEnabled currentMathEnabled;
    private StringBuilder currentExpression;
    private Deque<InputDisplay.MathEnabled> mathEnabledStack;

    public InputDisplay() {
        currentMathEnabled = new InputDisplay.MathEnabled();
        currentExpression = new StringBuilder();
        mathEnabledStack = new ArrayDeque<>();
        currentHistory = new History();
        expression = new Expression();
        savedDate = new SavedDate("Current expression");
        currentHistory = new History(savedDate, expression);
    }


    private void setExpressionToken(String token) {
        currentExpression.append(token);
        expression.setExpression(currentExpression.toString());
        mathEnabledStack.push(
                new InputDisplay.MathEnabled(
                        currentMathEnabled.dotEnabled,
                        currentMathEnabled.operatorEnabled,
                        currentMathEnabled.calculateEnabled)
        );
    }

    private void setAnswerData() {
        expression.setAnswer(Main.calculate(currentExpression.toString()));
    }

    private void clearExpression() {
        currentExpression = new StringBuilder();
        expression.setExpression(null);
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
            return null;
        }

        return currentHistory;
    }

    public History setOperandInExpression(String operand) {
        setExpressionToken(operand);
        currentMathEnabled.operatorEnabled = true;
        if (currentMathEnabled.calculateEnabled) {
            setAnswerData();
        }
        return currentHistory;
    }

    public History setDotInExpression(String dot) {
        if (currentMathEnabled.dotEnabled) {
            setExpressionToken(dot);
            currentMathEnabled.dotEnabled = false;
        }

        return currentHistory;
    }

    public History setBracketInExpression(String bracket) {
        setExpressionToken(bracket);
        currentMathEnabled.operatorEnabled = true;
        if (currentMathEnabled.calculateEnabled) {
            setAnswerData();
        }
        return currentHistory;
    }

    public History setOperatorInExpression(String operator) {
        //resourceProvider!!! need
        if (currentExpression.length() == 0 && operator.equals(SUBTRACTION)) {
            setExpressionToken(operator);

        } else if (currentExpression.length() == 1 &&
                currentExpression.toString().equals(SUBTRACTION)){
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
            currentMathEnabled = new InputDisplay.MathEnabled();
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
        History history = new History();
        Expression nExpression = new Expression();
        nExpression.setExpression(expression.getExpression());
        nExpression.setAnswer(expression.getAnswer());
        nExpression.setSavedDate(savedDate.getDate());
        history.setSavedDate(savedDate);
        history.setExpression(nExpression);
        currentExpression = new StringBuilder();
        currentExpression.append(expression.getAnswer());
        clearAnswer();
        return history;

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
