package com.starchee.calculator.model.currency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {

    @SerializedName("CharCode")
    @Expose
    private String charCode;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("Value")
    @Expose
    private float value;

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
