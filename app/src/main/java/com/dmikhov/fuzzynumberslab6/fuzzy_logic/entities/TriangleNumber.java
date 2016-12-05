package com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities;

import java.util.Locale;

/**
 * Created by dmikhov on 05.12.2016.
 */
public class TriangleNumber extends FuzzyNumber {
    int x1, x2;

    public TriangleNumber(int x1, int maxValue, int x2) {
        super(maxValue);
        this.x1 = x1;
        this.x2 = x2;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    @Override
    public float getLeftBorder() {
        return x1;
    }

    @Override
    public float getRightBorder() {
        return x2;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "(%d, %d, %d)", x1, maxValue, x2);
    }
}
