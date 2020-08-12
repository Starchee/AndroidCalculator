package com.starchee.calculator.mathExpressionCalculator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ConverterTest {

    private String inputExpression;
    private List<String> expressionList;
    private List<String> expressionRPNList;
    private Converter converter;


    @Before
    public void setUp() {
        converter = new Converter();

        inputExpression = "-(-12+2)×3+2÷(-2)";


        expressionList = new ArrayList<>();
        expressionList.add("-");
        expressionList.add("(");
        expressionList.add("-");
        expressionList.add("12");
        expressionList.add("+");
        expressionList.add("2");
        expressionList.add(")");
        expressionList.add("×");
        expressionList.add("3");
        expressionList.add("+");
        expressionList.add("2");
        expressionList.add("÷");
        expressionList.add("(");
        expressionList.add("-");
        expressionList.add("2");
        expressionList.add(")");

        expressionRPNList = new ArrayList<>();
        expressionRPNList.add("0");
        expressionRPNList.add("0");
        expressionRPNList.add("12");
        expressionRPNList.add("-");
        expressionRPNList.add("2");
        expressionRPNList.add("+");
        expressionRPNList.add("3");
        expressionRPNList.add("×");
        expressionRPNList.add("-");
        expressionRPNList.add("2");
        expressionRPNList.add("0");
        expressionRPNList.add("2");
        expressionRPNList.add("-");
        expressionRPNList.add("÷");
        expressionRPNList.add("+");

    }

    @Test
    public void convertToList(){
        List<String> actualExpressionList = converter.convertToList(inputExpression);
        Assert.assertEquals(actualExpressionList, expressionList);
    }

    @Test
    public void convertToPRMExpression() {
        List<String> actualExpressionList = converter.convertToPRMExpression(expressionList);
        Assert.assertEquals(actualExpressionList, expressionRPNList);
}

    @After
    public void tearDown() {

        expressionList.clear();
        expressionRPNList.clear();
    }

}