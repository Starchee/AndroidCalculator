package com.starchee.calculator.model;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;


public class History {

    @Embedded
    private SavedDate savedDate;

    @Relation(parentColumn = "date" , entityColumn = "saved_date")
    private List<Expression> expressions;

    public History(SavedDate savedDate, List<Expression> expressions) {
        this.savedDate = savedDate;
        this.expressions = expressions;
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
