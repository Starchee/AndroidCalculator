package com.starchee.calculator;

public interface MainActivityPadListener {
    void setOperandInExpression(String operand);
    void setDotInExpression(String dot);
    void setBracketInExpression(String bracket);
    void setOperatorInExpression(String operator);
    void deleteExpressionToken();
    void setAnswer();
    void clearDisplay();
}
