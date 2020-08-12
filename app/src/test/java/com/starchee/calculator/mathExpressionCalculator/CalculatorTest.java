package com.starchee.calculator.mathExpressionCalculator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    private MathExpressionCalculator mathExpressionCalculator;

    @Before
    public void setUp() {
      mathExpressionCalculator = new MathExpressionCalculator(new Validator(), new Converter());
    }

    @Test
    public void calculate() {
        Assert.assertEquals(mathExpressionCalculator.calculate("-(22+2+2)×(2+4)÷(-2)"), "78");
        Assert.assertEquals(mathExpressionCalculator.calculate("55÷2"), "27.5");
    }
}