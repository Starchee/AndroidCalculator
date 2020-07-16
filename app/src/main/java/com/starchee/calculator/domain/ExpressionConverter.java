package com.starchee.calculator.domain;

import java.util.List;

public interface ExpressionConverter {

    List<String> convertToList(String expression);

    List<String> convertToPRMExpression(List<String> expression);

}
