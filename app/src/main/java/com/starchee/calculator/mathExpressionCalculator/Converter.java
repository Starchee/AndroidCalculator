package com.starchee.calculator.mathExpressionCalculator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Converter implements ExpressionConverter {

    private static final String SUBTRACT = Operator.SUBTRACT.getToken();
    private static final String OPENING_BRACKET = Bracket.OPENING_BRACKET.getToken();
    private static final String CLOSING_BRACKET = Bracket.CLOSING_BRACKET.getToken();

    private List<String> substitutionZeroBeforeNegativeNumber(List<String> expression) {
        List<String> preparedExpression = new ArrayList<>();

        for (int i = 0; i < expression.size(); i++) {
            String token = expression.get(i);
            if (SUBTRACT.equals(token) && i == 0) {
                preparedExpression.add("0");
            } else if (SUBTRACT.equals(token)
                    && OPENING_BRACKET.equals(expression.get(i - 1))) {
                preparedExpression.add("0");
            }

            preparedExpression.add(token);
        }
        return preparedExpression;
    }

    private boolean isOperand(char token) {

        return !Operator.isOperator(String.valueOf(token)) && !Bracket.isBracket(String.valueOf(token));
    }

    private List<String> convertToRPN(List<String> expressionList) {
        List<String> expressionRPNList = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        for (String token : expressionList) {
            if (Operator.isOperator(token)) {
                while (!stack.isEmpty()
                        && Operator.isOperator(stack.peek())
                        && Operator.getOperator(stack.peek()).getPriority() >= Operator.getOperator(token).getPriority()) {
                    expressionRPNList.add(stack.pop());
                }
                stack.push(token);
            } else if (OPENING_BRACKET.equals(token)) {
                stack.push(token);
            } else if (CLOSING_BRACKET.equals(token)) {
                while (stack.peek() != null && !OPENING_BRACKET.equals(stack.peek())) {
                    expressionRPNList.add(stack.pop());
                }
                stack.pop();
            } else {
                expressionRPNList.add(token);
            }
        }

        while (!stack.isEmpty()) {
            expressionRPNList.add(stack.pop());
        }
        return expressionRPNList;
    }

    @Override
    public List<String> convertToList(String expression) {
        StringBuilder currentNumber = new StringBuilder();
        char[] expressionArray = expression.toCharArray();
        List<String> expressionList = new ArrayList<>();
        for (int i = 0; i < expressionArray.length; i++) {
            char token = expressionArray[i];
            if (isOperand(token)) {
                while (isOperand(token)) {
                    currentNumber.append(token);
                    i++;
                    if (i == expressionArray.length) {
                        break;
                    }
                    token = expressionArray[i];
                }
                expressionList.add(currentNumber.toString());
                currentNumber.setLength(0);
            }
            if (!isOperand(token)) {
                expressionList.add(String.valueOf(token));
            }
        }
        return expressionList;
    }

    @Override
    public List<String> convertToPRMExpression(List<String> expression) {

        List<String> preparedExpression = substitutionZeroBeforeNegativeNumber(expression);

        return convertToRPN(preparedExpression);
    }
}
