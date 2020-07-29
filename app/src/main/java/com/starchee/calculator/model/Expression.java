package com.starchee.calculator.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "expression",
        foreignKeys = @ForeignKey(entity = SavedDate.class, parentColumns = "date",
        childColumns = "saved_date", onDelete = CASCADE))
public class Expression {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "saved_date")
    private String savedDate;

    private String expression;
    private String answer;

    @Ignore
    public Expression() {
    }

    public Expression(String expression, String answer) {
        this.expression = expression;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(String savedDate) {
        this.savedDate = savedDate;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
