package com.dmikhov.fuzzynumberslab6.fuzzy_logic;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class FuzzySingleton {
    float x;
    float value;

    public FuzzySingleton(float x, float value) {
        this.x = x;
        this.value = value;
    }

    public float getX() {
        return x;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "fuzzy_logic.FuzzySingleton{" +
                "x=" + x +
                ", valueCoord=" + value +
                '}';
    }
}
