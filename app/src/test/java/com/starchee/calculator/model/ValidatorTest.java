package com.starchee.calculator.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidatorTest {

    private Validator validator;
    private Converter converter;

    @Before
    public void setUp() {
        validator = new Validator();
        converter = new Converter();
    }

    @Test
    public void isValidate() {

        Assert.assertTrue(validator.isValidate(converter.convertToList("55+55")));
        Assert.assertTrue(validator.isValidate(converter.convertToList("-55+55")));
        Assert.assertFalse(validator.isValidate(converter.convertToList("×+55")));
        Assert.assertFalse(validator.isValidate(converter.convertToList("55+×")));

        Assert.assertTrue(validator.isValidate(converter.convertToList("(55+55)×(55-55)")));
        Assert.assertTrue(validator.isValidate(converter.convertToList("-(55+55)×(-5)")));
        Assert.assertFalse(validator.isValidate(converter.convertToList("(55×)")));
        Assert.assertFalse(validator.isValidate(converter.convertToList("(×55)")));
        Assert.assertFalse(validator.isValidate(converter.convertToList("(55+55)(55+55)")));
        Assert.assertFalse(validator.isValidate(converter.convertToList("55+55)")));

    }
}