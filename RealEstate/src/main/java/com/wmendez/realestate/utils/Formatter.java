package com.wmendez.realestate.utils;

public class Formatter {

    public static String currency(float value) {
        return String.format("$%.0f", value);
    }
}
