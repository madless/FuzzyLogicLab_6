package com.dmikhov.fuzzynumberslab6;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCell;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCellComparator;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCondition;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyLogic;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzySingleton;
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

        float step = FuzzyLogic.getStep(fuzzyCondition.getA(), fuzzyCondition.getB(), fuzzyCondition.getSteps());
        aSingletons = FuzzyLogic.convertToSingletons(fuzzyCondition.getA(), step);
        bSingletons = FuzzyLogic.convertToSingletons(fuzzyCondition.getB(), step);
        FuzzyCell[][] matrix = FuzzyLogic.convertToMatrix(aSingletons, bSingletons, fuzzyCondition.getFun());
        fuzzyList = FuzzyLogic.convertToSortedList(matrix);
        FuzzyLogic.filterList(fuzzyList);

        List<PointValue> valuesC = new ArrayList<>();
        for (int i = 0; i < fuzzyList.size(); i++) {
            Log.w(Const.TAG, fuzzyList.get(i).toString());
            PointValue p = new PointValue(fuzzyList.get(i).getValueCoord(), fuzzyList.get(i).getMin());
            valuesC.add(p);
            if(fuzzyCondition.isFullRes()) {
                if (i != fuzzyList.size() - 1) {
                    PointValue pHelper = new PointValue(fuzzyList.get(i + 1).getValueCoord(), fuzzyList.get(i).getMin());
                    valuesC.add(pHelper);
                }
            }
        }
        Line cLine = getLine(valuesC, Color.RED, 3);

        List<PointValue> valuesA = getPoints(aSingletons, 0.02f, fuzzyCondition.isFullRes());
        Line aLine = getLine(valuesA, Color.BLUE, 1);

        List<PointValue> valuesB = getPoints(bSingletons, 0.04f, fuzzyCondition.isFullRes());
        Line bLine = getLine(valuesB, Color.BLACK, 1);

        initGraph(Arrays.asList(aLine, bLine, cLine));
    }

    private List<PointValue> getPoints(ArrayList<FuzzySingleton> singletons, float padding, boolean isFullResult) {
        List<PointValue> values = new ArrayList<>();
        values.add(new PointValue(0, 0));
        for (int i = 0; i < singletons.size(); i++) {
            Log.w(Const.TAG, singletons.get(i).toString());
            PointValue p = new PointValue(singletons.get(i).getX(), singletons.get(i).getValue());
            values.add(p);
            if(isFullResult) {
                if (i != singletons.size() - 1) {
                    PointValue pHelper = new PointValue(singletons.get(i + 1).getX() + padding, singletons.get(i).getValue());
                    values.add(pHelper);
                }
            }
        }
        return values;
    }

    private Line getLine(List<PointValue> values, int color, int width) {
        Line line = new Line(values).setColor(color).setCubic(false);
        line.setStrokeWidth(width);
        line.setPointRadius(1);
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
