package com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities;

import com.dmikhov.fuzzynumberslab6.utils.Const;

/**
 * Created by dmikhov on 05.12.2016.
 */
public class GaussNumber extends FuzzyNumber {
    int b;

    public GaussNumber(int maxValue, int b) {
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
        return (float) ((2 * b - Math.sqrt(4 * b * b - 4 * (b * b + 2 * Math.log(Const.MIN_GAUSS_VALUE) * maxValue * maxValue))) / 2);
    }

    @Override
    public float getRightBorder() {
        return (float) ((2 * b + Math.sqrt(4 * b * b - 4 * (b * b + 2 * Math.log(Const.MIN_GAUSS_VALUE) * maxValue * maxValue))) / 2);
    }
}
