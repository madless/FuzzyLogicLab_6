package com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class MinFunction implements FuzzyFunction {
    @Override
    public float calculate(float a, float b) {
        return a < b ? a : b;
    }
}