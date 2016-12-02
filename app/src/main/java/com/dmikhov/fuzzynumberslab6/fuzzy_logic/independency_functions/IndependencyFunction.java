package com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyNumber;

/**
 * Created by dmikhov on 02.12.2016.
 */
public interface IndependencyFunction {
    float getAlpha(FuzzyNumber fuzzy, float x);
}
