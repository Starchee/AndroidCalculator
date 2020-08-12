package com.starchee.calculator.mathExpressionCalculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class OperatorTest {

    private static final String MULTIPLY = "×";
    private static final String DIVIDE = "÷";
    private static final String ADD = "+";
    private static final String SUBTRACT = "-";

    @Test
    public void getToken() {
        assertEquals(Operator.MULTIPLY.getToken(), MULTIPLY);
        assertEquals(Operator.DIVIDE.getToken(), DIVIDE);
        assertEquals(Operator.ADD.getToken(), ADD);
        assertEquals(Operator.SUBTRACT.getToken(), SUBTRACT);
    }

    @Test
    public void apply() {

    }

    @Test
    public void contains() {
        assertTrue(Operator.isOperator(MULTIPLY));
        assertTrue(Operator.isOperator(DIVIDE));
        assertTrue(Operator.isOperator(ADD));
        assertTrue(Operator.isOperator(SUBTRACT));
    }

    @Test
    public void getOperator() {
        assertEquals(Operator.getOperator(MULTIPLY), Operator.MULTIPLY);
        assertEquals(Operator.getOperator(DIVIDE), Operator.DIVIDE);
        assertEquals(Operator.getOperator(ADD), Operator.ADD);
        assertEquals(Operator.getOperator(SUBTRACT), Operator.SUBTRACT);
    }

    @Test
    public void getPriority(){
        assertEquals(Operator.MULTIPLY.getPriority(), 1);
        assertEquals(Operator.DIVIDE.getPriority(), 1);
        assertEquals(Operator.ADD.getPriority(), 0);
        assertEquals(Operator.SUBTRACT.getPriority(), 0);
    }
}