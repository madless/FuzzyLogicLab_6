package com.dmikhov.fuzzynumberslab6.fuzzy_logic;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.FuzzyFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions.IndependencyFunction;
import com.dmikhov.fuzzynumberslab6.utils.Const;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class FuzzyLogic {

    public static float getStep(FuzzyNumber a, FuzzyNumber b, int steps) {
        float aDist = a.getX2() - a.getX1();
        float bDist = b.getX2() - b.getX1();
        float dist = aDist < bDist ? aDist : bDist;
        return dist / steps;
    }

    public static ArrayList<FuzzySingleton> convertToSingletons(FuzzyNumber fuzzy, IndependencyFunction indepFun, float step) {
        ArrayList<FuzzySingleton> fuzzySingletons = new ArrayList<>();
        float x = fuzzy.getX1();
        float alpha = indepFun.getAlpha(fuzzy, x);
        fuzzySingletons.add(new FuzzySingleton(x, alpha));
        while (x < fuzzy.getX2() && (Math.abs(x - fuzzy.getX2()) > Const.EPSILON)) {
            x += step;
            if(x > fuzzy.getX2() && (Math.abs(x - fuzzy.getX2()) > Const.EPSILON)) {
                x = fuzzy.getX2();
            }
            alpha = indepFun.getAlpha(fuzzy, x);
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

    public static ArrayList<FuzzySingleton> getSmoothCoordinatesList(ArrayList<FuzzyCell> filteredList) {
        ArrayList<FuzzySingleton> smoothList = new ArrayList<>();
        int step = (filteredList.size() / 10) > 3 ? (filteredList.size() / 10) : 3;
        smoothList.add(new FuzzySingleton(filteredList.get(0).getValueCoord(), filteredList.get(0).getMin())); // first
        for (int i = 0; i < filteredList.size(); i+=step) {
            System.out.println("i: " + i);
            int lastIndex = i + step;
            if(lastIndex >= filteredList.size()) {
                lastIndex = filteredList.size() - 1;
            }
            List<FuzzyCell> subList = filteredList.subList(i, lastIndex);
            if(subList != null && subList.size() > 0) {
                FuzzyCell max = subList.get(0);
                float maxMin = subList.get(0).getMin();
                for (int j = 0; j < subList.size(); j++) {
                    if (subList.get(j).getMin() > maxMin) {
                        maxMin = subList.get(j).getMin();
                        max = subList.get(j);
                    }
                }
                System.out.println("max: " + max);
                if (max != null) {
                    smoothList.add(new FuzzySingleton(max.getValueCoord(), max.getMin()));
                }
            }
        }
        smoothList.add(new FuzzySingleton(filteredList.get(filteredList.size() - 1).getValueCoord(), filteredList.get(filteredList.size() - 1).getMin())); // last
        return smoothList;
    }

//    public static ArrayList<FuzzySingleton> getSmoothCoordinatesList(ArrayList<FuzzyCell> filteredList) {
//        ArrayList<FuzzySingleton> smoothList = new ArrayList<>();
//        for (int i = 0; i < filteredList.size(); i++) {
//            FuzzySingleton singleton;
//            if(i == 0) {
//                singleton = new FuzzySingleton(filteredList.get(i).getValueCoord(), filteredList.get(i).getMin());
//            } else  if(i == filteredList.size() - 1) {
//                FuzzySingleton helperSingleton = getCenterBetweenDots(filteredList.get(i-1), filteredList.get(i));
//                smoothList.add(helperSingleton);
//                singleton = new FuzzySingleton(filteredList.get(i).getValueCoord(), filteredList.get(i).getMin());
//            } else {
//                singleton = getCenterBetweenDots(filteredList.get(i-1), filteredList.get(i));
//            }
//            smoothList.add(singleton);
//            if(FuzzyHelper.compareIfEquals(filteredList.get(i).getMin(), 1)) {
//                FuzzySingleton helperSingleton = new FuzzySingleton(filteredList.get(i).getValueCoord(), filteredList.get(i).getMin());
//                smoothList.add(helperSingleton);
//            }
//        }
//        return smoothList;
//    }

//    public static FuzzySingleton getCenterBetweenDots(FuzzyCell a, FuzzyCell b) {
//        float x = (a.getValueCoord() + b.getValueCoord()) / 2;
//        float y = (a.getMin() + b.getMin()) / 2;
//        FuzzySingleton singleton = new FuzzySingleton(x, y);
//        return singleton;
//    }

}
