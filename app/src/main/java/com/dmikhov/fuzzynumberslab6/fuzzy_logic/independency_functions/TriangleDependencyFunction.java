package com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities.FuzzyNumber;

/**
 * Created by dmikhov on 02.12.2016.
 */
public class TriangleDependencyFunction implements DependencyFunction {
    @Override
    public float getAlpha(FuzzyNumber fuzzy, float x) {
        if(x < fuzzy.getX0()) {
            return (x - fuzzy.getX1()) / (fuzzy.getX0() - fuzzy.getX1());
        }
        if(x == fuzzy.getX0()) {
            return 1;
        }
        if(x > fuzzy.getX0()) {
            return (fuzzy.getX2() - x) / (fuzzy.getX2() - fuzzy.getX0());
        }
        throw new RuntimeException();
    }
}
