package com.dmikhov.fuzzynumberslab6.ui.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmikhov.fuzzynumberslab6.DataSingleton;
import com.dmikhov.fuzzynumberslab6.R;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCell;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCondition;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyLogic;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzySingleton;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities.FuzzyNumber;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities.GaussNumber;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.DivFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions.TriangleDependencyFunction;
import com.dmikhov.fuzzynumberslab6.utils.Const;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by dmikhov on 01.12.2016.
 */
public class GraphFragment extends Fragment {
    @Bind(R.id.chart) LineChartView chart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_graph, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FuzzyCondition fuzzyCondition = DataSingleton.get().getCondition();
        ArrayList<FuzzyCell> fuzzyList = null;
        ArrayList<FuzzySingleton> aSingletons = null;
        ArrayList<FuzzySingleton> bSingletons = null;
        ArrayList<FuzzySingleton> smoothList = null;

        FuzzyNumber a = fuzzyCondition.getA();
        FuzzyNumber b = fuzzyCondition.getB();
        float step = FuzzyLogic.getStep(a, b, fuzzyCondition.getSteps());
        aSingletons = FuzzyLogic.convertToSingletons(a, fuzzyCondition.getDepFun(), step);
        bSingletons = FuzzyLogic.convertToSingletons(b, fuzzyCondition.getDepFun(), step);
        FuzzyCell[][] matrix = FuzzyLogic.convertToMatrix(aSingletons, bSingletons, fuzzyCondition.getFun());
        fuzzyList = FuzzyLogic.convertToSortedList(matrix);
        FuzzyLogic.filterList(fuzzyList);

        List<PointValue> valuesC;
        List<PointValue> valuesA;
        List<PointValue> valuesB;
        if(fuzzyCondition.isFullRes()) {
            valuesC = new ArrayList<>();
            for (int i = 0; i < fuzzyList.size(); i++) {
                Log.w(Const.TAG, "p: " + fuzzyList.get(i).toString());
                PointValue p = new PointValue(fuzzyList.get(i).getValueCoord(), fuzzyList.get(i).getMin());
                valuesC.add(p);
                if(fuzzyCondition.isFullRes()) {
                    if (i != fuzzyList.size() - 1) {
                        PointValue pHelper = new PointValue(fuzzyList.get(i + 1).getValueCoord(), fuzzyList.get(i).getMin());
                        valuesC.add(pHelper);
                    }
                }
            }
            Log.w(Const.TAG, "--------------------------");
            valuesA = getPoints(aSingletons, 0.02f, fuzzyCondition.isFullRes());
            valuesB = getPoints(bSingletons, 0.04f, fuzzyCondition.isFullRes());
        } else {
            smoothList = FuzzyLogic.getSmoothCoordinatesList(fuzzyList);
            valuesC = getPoints(smoothList, 0, fuzzyCondition.isFullRes());
            if(fuzzyCondition.getDepFun() instanceof TriangleDependencyFunction) {
                valuesA = Arrays.asList(new PointValue(a.getLeftBorder(), 0), new PointValue(a.getMaxValue(), 1), new PointValue(a.getRightBorder(), 0));
                valuesB = Arrays.asList(new PointValue(b.getLeftBorder(), 0), new PointValue(b.getMaxValue(), 1), new PointValue(b.getRightBorder(), 0));
            } else {
                valuesA = getPoints(aSingletons, 0.02f, fuzzyCondition.isFullRes());
                valuesB = getPoints(bSingletons, 0.04f, fuzzyCondition.isFullRes());
            }
        }
        boolean isCLineCubic = !fuzzyCondition.isFullRes() && !((a instanceof GaussNumber) && (fuzzyCondition.getFun() instanceof DivFunction));
        Line cLine = getLine(valuesC, ContextCompat.getColor(getContext(), R.color.colorPrimaryDark), 3, isCLineCubic);
        Line aLine = getLine(valuesA, ContextCompat.getColor(getContext(), R.color.colorAccent), 1, false);
        Line bLine = getLine(valuesB, ContextCompat.getColor(getContext(), R.color.colorPrimaryLight), 1, false);

        initGraph(Arrays.asList(aLine, bLine, cLine));
    }

    private List<PointValue> getPoints(ArrayList<FuzzySingleton> singletons, float padding, boolean isFullResult) {
        List<PointValue> values = new ArrayList<>();
//        values.add(new PointValue(0, 0));
        for (int i = 0; i < singletons.size(); i++) {
            Log.w(Const.TAG, "p: " + singletons.get(i).toString());
            PointValue p = new PointValue(singletons.get(i).getX(), singletons.get(i).getValue());
            values.add(p);
            if(isFullResult) {
                if (i != singletons.size() - 1) {
                    PointValue pHelper = new PointValue(singletons.get(i + 1).getX() + padding, singletons.get(i).getValue());
                    values.add(pHelper);
                }
            }
        }
        Log.w(Const.TAG, "-----------------------------");
        return values;
    }

    private Line getLine(List<PointValue> values, int color, int width, boolean cubic) {
        Line line = new Line(values).setColor(color).setCubic(false);
        line.setStrokeWidth(width);
        line.setPointRadius(1);
        if(cubic) {
            line.setCubic(true);
        }
        return line;
    }

    private void initGraph(List<Line> lines) {
        chart.setInteractive(true);
        chart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        List<AxisValue> axisValuesForX = new ArrayList<>();
        for (int i = 1; i < 100; i++){
            axisValuesForX.add(new AxisValue(i));
        }

        List<AxisValue> axisValuesForY = new ArrayList<>();
        for (int i = 1; i < 2; i++){
            axisValuesForY.add(new AxisValue(i));
        }

        Axis axeX = new Axis(axisValuesForX);
        Axis axeY = new Axis(axisValuesForY);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        data.setAxisXBottom(axeX);
        data.setAxisYLeft(axeY);

        chart.setLineChartData(data);
    }
}
