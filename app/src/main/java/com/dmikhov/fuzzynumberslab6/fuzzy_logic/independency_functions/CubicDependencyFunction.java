package com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities.CubicNumber;

/**
 * Created by dmikhov on 02.12.2016.
 */
public class CubicDependencyFunction implements DependencyFunction<CubicNumber> {
    @Override
    public float getAlpha(CubicNumber fuzzy, float x) {
        return (((x - fuzzy.getMaxValue()) * (x - fuzzy.getMaxValue())) / (fuzzy.getB() * fuzzy.getB())) < 1 ?
                1 - (((x - fuzzy.getMaxValue()) * (x - fuzzy.getMaxValue())) / (fuzzy.getB() * fuzzy.getB())) :
                0;
    }
}
