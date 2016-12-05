package com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class FuzzyNumber {
    int x1, x0, x2;

    public FuzzyNumber(int x1, int x0, int x2) {
        this.x1 = x1;
        this.x0 = x0;
        this.x2 = x2;
    }

    public FuzzyNumber() {
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }
}
