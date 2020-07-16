package com.starchee.calculator.domain;

import java.util.List;

public interface ExpressionValidator {

    boolean isValidate(List<String> expression);
}
