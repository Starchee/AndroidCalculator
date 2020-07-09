package com.starchee.calculator.model;

import java.util.List;

public interface ExpressionConverter {

    List<String> convertToList(String expression);

    List<String> convertToPRMExpression(List<String> expression);

}
