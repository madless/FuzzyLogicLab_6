package com.dmikhov.fuzzynumberslab6.fuzzy_logic;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities.FuzzyNumber;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.FuzzyFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions.DependencyFunction;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class FuzzyCondition {
    FuzzyNumber a;
    FuzzyNumber b;
    FuzzyFunction fun;
    DependencyFunction depFun;
    int steps;
    boolean isFullRes;

    public FuzzyCondition(FuzzyNumber a, FuzzyNumber b, FuzzyFunction fun, DependencyFunction depFun, int steps, boolean isFullRes) {
        this.a = a;
        this.b = b;
        this.fun = fun;
        this.depFun = depFun;
        this.steps = steps;
        this.isFullRes = isFullRes;
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

    public boolean isFullRes() {
        return isFullRes;
    }

    public void setFullRes(boolean fullRes) {
        isFullRes = fullRes;
    }

    public DependencyFunction getDepFun() {
        return depFun;
    }

    public void setDepFun(DependencyFunction depFun) {
        this.depFun = depFun;
    }

    @Override
    public String toString() {
        return "FuzzyCondition{" +
                "a=" + a +
                ", b=" + b +
                ", fun=" + fun +
                ", steps=" + steps +
                ", isFullRes=" + isFullRes +
                '}';
    }
}
