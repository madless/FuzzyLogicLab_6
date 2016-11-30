package com.dmikhov.fuzzynumberslab6.fuzzy_logic;

import java.util.Locale;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class FuzzyCell {
    float aCoord;
    float bCoord;
    float valueCoord;
    float a;
    float b;
    float min;

    public FuzzyCell(float aCoord, float bCoord, float valueCoord, float a, float b) {
        this.aCoord = aCoord;
        this.bCoord = bCoord;
        this.valueCoord = valueCoord;
        this.a = a;
        this.b = b;
        this.min = a < b ? a : b;
    }

    public FuzzyCell(float aCoord, float bCoord, float a, float b) {
        this.aCoord = aCoord;
        this.bCoord = bCoord;
        this.a = a;
        this.b = b;
        this.min = a < b ? a : b;
    }

    public FuzzyCell() {
    }

    public float getMin() {
        return min;
    }

    public float getValueCoord() {
        return valueCoord;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%.2f", min) + "/" + String.format(Locale.US, "%.2f", valueCoord);
//                "(" + aCoord + ", " + bCoord + ")" +
//                " val=" + String.format(Locale.US, "%.2f", valueCoord) +
//                " min=" + String.format(Locale.US, "%.2f", min);
//                " a=" + a +
//                " b=" + b;
    }
}
