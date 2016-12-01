package com.dmikhov.fuzzynumberslab6.fuzzy_logic;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions.FuzzyFunction;
import com.dmikhov.fuzzynumberslab6.utils.Const;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class FuzzyLogic {

    public static float getAlpha(FuzzyNumber fuzzy, float x) {
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

    public static float getStep(FuzzyNumber a, FuzzyNumber b, int steps) {
        float aDist = a.getX2() - a.getX1();
        float bDist = b.getX2() - b.getX1();
        float dist = aDist < bDist ? aDist : bDist;
        return dist / steps;
    }

    public static ArrayList<FuzzySingleton> convertToSingletons(FuzzyNumber fuzzy, float step) {
        ArrayList<FuzzySingleton> fuzzySingletons = new ArrayList<>();
        float x = fuzzy.getX1();
        float alpha = getAlpha(fuzzy, x);
        fuzzySingletons.add(new FuzzySingleton(x, alpha));
        while (x < fuzzy.getX2() && (Math.abs(x - fuzzy.getX2()) > Const.EPSILON)) {
            x += step;
            if(x > fuzzy.getX2() && (Math.abs(x - fuzzy.getX2()) > Const.EPSILON)) {
                x = fuzzy.getX2();
            }
            alpha = getAlpha(fuzzy, x);
            fuzzySingletons.add(new FuzzySingleton(x, alpha));

        }
        return fuzzySingletons;
    }

    public static FuzzyCell[][] convertToMatrix(ArrayList<FuzzySingleton> aSingletons, ArrayList<FuzzySingleton> bSingletons, FuzzyFunction fun) {
        FuzzyCell[][] matrix = new FuzzyCell[aSingletons.size()][bSingletons.size()];
        for (int i = 0; i < matrix.length; i++) {
            FuzzySingleton aSingleton = aSingletons.get(i);
            for (int j = 0; j < matrix[0].length; j++) {
                FuzzySingleton bSingleton = bSingletons.get(j);
                FuzzyCell cell = new FuzzyCell(aSingleton.x, bSingleton.x, aSingleton.value, bSingleton.value);
                cell.valueCoord = fun.calculate(cell.aCoord, cell.bCoord);
                matrix[i][j] = cell;
            }
        }
        return matrix;
    }

    public static ArrayList<FuzzyCell> convertToSortedList(FuzzyCell[][] matrix) {
        FuzzyCellComparator comparator = new FuzzyCellComparator();
        int n = matrix.length * matrix[0].length;
        ArrayList<FuzzyCell> list = new ArrayList<>(n);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                list.add(matrix[i][j]);
            }
        }
        Collections.sort(list, comparator);
        return list;
    }

    public static void filterList(ArrayList<FuzzyCell> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if(FuzzyHelper.compareIfEquals(list.get(i).valueCoord, list.get(i + 1).valueCoord)) {
                list.remove(i);
                i--;
            }
        }
    }

}
