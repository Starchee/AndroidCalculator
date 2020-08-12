package com.starchee.calculator.mathExpressionCalculator;

import java.util.List;

public interface ExpressionConverter {

    List<String> convertToList(String expression);

    List<String> convertToPRMExpression(List<String> expression);

}
