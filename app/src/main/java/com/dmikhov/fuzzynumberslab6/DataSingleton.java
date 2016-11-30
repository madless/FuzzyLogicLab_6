package com.dmikhov.fuzzynumberslab6;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCondition;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class DataSingleton {
    private static DataSingleton singleton = new DataSingleton();
    private FuzzyCondition condition;

    private DataSingleton() {}

    public static DataSingleton get() {
        return singleton;
    }

    public FuzzyCondition getCondition() {
        return condition;
    }

    public void setCondition(FuzzyCondition condition) {
        this.condition = condition;
    }
}
