package com.starchee.calculator.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_date")
public class SavedDate {

    @NonNull
    @PrimaryKey
    private String date;

    public SavedDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
