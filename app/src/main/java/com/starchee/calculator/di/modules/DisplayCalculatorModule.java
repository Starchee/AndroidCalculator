package com.starchee.calculator.di.modules;

import android.content.res.Resources;

import com.starchee.calculator.mathExpressionCalculator.Converter;
import com.starchee.calculator.mathExpressionCalculator.ExpressionConverter;
import com.starchee.calculator.mathExpressionCalculator.ExpressionValidator;
import com.starchee.calculator.mathExpressionCalculator.MathExpressionCalculator;
import com.starchee.calculator.mathExpressionCalculator.Validator;
import com.starchee.calculator.Utils.DisplayCalculator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = AppModule.class)
public class DisplayCalculatorModule {

    @Singleton
    @Provides
    ExpressionValidator providesExpressionValidator(){
        return new Validator();
    }

    @Singleton
    @Provides
    ExpressionConverter providesExpressionConverter(){
        return new Converter();
    }


    @Singleton
    @Provides
    MathExpressionCalculator providesMathExpressionCalculator(ExpressionValidator expressionValidator, ExpressionConverter expressionConverter){
        return new MathExpressionCalculator(expressionValidator, expressionConverter);
    }

    @Singleton
    @Provides
    DisplayCalculator providesHistoryDatabase(MathExpressionCalculator calculator, Resources resources){
        return new DisplayCalculator(calculator, resources);
    }
}
