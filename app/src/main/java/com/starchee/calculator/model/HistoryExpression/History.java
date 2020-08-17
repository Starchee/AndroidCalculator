package com.starchee.calculator.model.HistoryExpression;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;


public class History {

    @Embedded
    private SavedDate savedDate;

    @Relation(parentColumn = "date" , entityColumn = "saved_date")
    private List<Expression> expressions;

    public History() {
    }

    @Ignore
    public History(SavedDate savedDate, Expression expression) {
        this.savedDate = savedDate;
        this.expressions = new ArrayList<>();
        expressions.add(expression);
    }

    public void setExpression(Expression expression){
        this.expressions = new ArrayList<>();
        expressions.add(expression);
    }

    public SavedDate getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(SavedDate savedDate) {
        this.savedDate = savedDate;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }
}
