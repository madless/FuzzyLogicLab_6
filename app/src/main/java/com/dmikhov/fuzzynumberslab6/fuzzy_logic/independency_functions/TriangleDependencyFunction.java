package com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities.FuzzyNumber;

/**
 * Created by dmikhov on 02.12.2016.
 */
public class TriangleDependencyFunction implements DependencyFunction {
    @Override
    public float getAlpha(FuzzyNumber fuzzy, float x) {
        if(x < fuzzy.getMaxValue()) {
            return (x - fuzzy.getLeftBorder()) / (fuzzy.getMaxValue() - fuzzy.getLeftBorder());
        }
        if(x == fuzzy.getMaxValue()) {
            return 1;
        }
        if(x > fuzzy.getMaxValue()) {
            return (fuzzy.getRightBorder() - x) / (fuzzy.getRightBorder() - fuzzy.getMaxValue());
        }
        throw new RuntimeException();
    }
}
