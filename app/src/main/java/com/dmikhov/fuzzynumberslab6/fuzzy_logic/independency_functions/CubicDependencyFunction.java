package com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities.FuzzyNumber;

/**
 * Created by dmikhov on 02.12.2016.
 */
public class CubicDependencyFunction implements DependencyFunction {
    @Override
    public float getAlpha(FuzzyNumber fuzzy, float x) {
        return (x - fuzzy.getX0()) * (x - fuzzy.getX0());
    }
}
