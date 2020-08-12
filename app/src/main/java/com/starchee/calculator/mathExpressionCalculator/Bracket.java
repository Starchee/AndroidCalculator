package com.starchee.calculator.mathExpressionCalculator;

import java.util.HashMap;
import java.util.Map;

public enum Bracket {
    OPENING_BRACKET("("),
    CLOSING_BRACKET(")");

    private final String token;

    Bracket(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    private static final Map<String, Bracket> map;

    static {
        map = new HashMap<>();
        for (Bracket bracket : Bracket.values()) {
            map.put(bracket.getToken(), bracket);
        }
    }

    public static boolean isBracket(String value) {
        return map.containsKey(value);
    }
}
