package com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities;

import java.util.Locale;

/**
 * Created by dmikhov on 30.11.2016.
 */
public abstract class FuzzyNumber {
    protected int maxValue;

    public FuzzyNumber(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public abstract float getLeftBorder();

    public abstract float getRightBorder();

    @Override
    public String toString() {
        return String.format(Locale.US, "(%.2f, %d, %.2f)", getLeftBorder(), getMaxValue(), getRightBorder());
    }
}
