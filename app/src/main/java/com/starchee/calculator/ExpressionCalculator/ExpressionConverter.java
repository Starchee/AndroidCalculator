package com.starchee.calculator.ExpressionCalculator;

import java.util.List;

public interface ExpressionConverter {

    List<String> convertToList(String expression);

    List<String> convertToPRMExpression(List<String> expression);

}
