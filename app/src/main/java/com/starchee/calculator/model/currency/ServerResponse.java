package com.starchee.calculator.model.currency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class ServerResponse {

    @SerializedName("Valute")
    @Expose
    private HashMap<String, Currency> valute;


    public HashMap<String, Currency> getValute() {
        return valute;
    }

    public void setValute(HashMap<String, Currency> valute) {
        this.valute = valute;
    }
}
