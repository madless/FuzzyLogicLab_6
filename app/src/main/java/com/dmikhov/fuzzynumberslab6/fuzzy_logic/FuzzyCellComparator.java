package com.dmikhov.fuzzynumberslab6.fuzzy_logic;

import java.util.Comparator;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class FuzzyCellComparator implements Comparator<FuzzyCell> {
    @Override
    public int compare(FuzzyCell o1, FuzzyCell o2) {
        if(o1.valueCoord == o2.valueCoord) {
            return o1.min == o2.min ? 0 : o1.min > o2.min ? 1 : -1;
//            return 0;
        }
        if(o1.valueCoord > o2.valueCoord) {
            return 1;
        } else { // o1.valueCoord < o2.valueCoord
            return -1;
        }

    }
}
