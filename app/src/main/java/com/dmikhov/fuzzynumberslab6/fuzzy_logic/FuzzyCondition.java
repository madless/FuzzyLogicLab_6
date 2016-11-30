package com.dmikhov.fuzzynumberslab6.fuzzy_logic;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions.FuzzyFunction;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class FuzzyCondition {
    FuzzyNumber a;
    FuzzyNumber b;
    FuzzyFunction fun;
    int steps;

    public FuzzyCondition(FuzzyNumber a, FuzzyNumber b, FuzzyFunction fun, int steps) {
        this.a = a;
        this.b = b;
        this.fun = fun;
        this.steps = steps;
    }

    public FuzzyNumber getA() {
        return a;
    }

    public void setA(FuzzyNumber a) {
        this.a = a;
    }

    public FuzzyNumber getB() {
        return b;
    }

    public void setB(FuzzyNumber b) {
        this.b = b;
    }

    public FuzzyFunction getFun() {
        return fun;
    }

    public void setFun(FuzzyFunction fun) {
        this.fun = fun;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}
