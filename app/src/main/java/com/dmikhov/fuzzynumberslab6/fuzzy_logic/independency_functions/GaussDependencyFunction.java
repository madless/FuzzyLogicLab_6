package com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities.FuzzyNumber;

/**
 * Created by dmikhov on 02.12.2016.
 */
public class GaussDependencyFunction implements DependencyFunction {
    @Override
    public float getAlpha(FuzzyNumber fuzzy, float x) {
        return (float) Math.exp(-(x * x )/ (2 * fuzzy.getMaxValue() * fuzzy.getMaxValue()));
    }
}
