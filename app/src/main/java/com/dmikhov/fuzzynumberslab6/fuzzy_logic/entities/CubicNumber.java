package com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities;

/**
 * Created by dmikhov on 05.12.2016.
 */
public class CubicNumber extends FuzzyNumber {
    int b;

    public CubicNumber(int maxValue, int b) {
        super(maxValue);
        this.b = b;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public float getLeftBorder() {
        return (float) ((2 * maxValue - Math.sqrt(4 * maxValue * maxValue - 4 * (maxValue * maxValue - b * b))) / 2);
    }

    @Override
    public float getRightBorder() {
        return (float) ((2 * maxValue + Math.sqrt(4 * maxValue * maxValue - 4 * (maxValue * maxValue - b * b))) / 2);
    }
}
