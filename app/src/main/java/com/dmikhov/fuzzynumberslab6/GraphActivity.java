package com.dmikhov.fuzzynumberslab6;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCell;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCellComparator;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCondition;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyLogic;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzySingleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class GraphActivity extends AppCompatActivity {
    LineChartView chart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        chart = (LineChartView) findViewById(R.id.chart);

        FuzzyCondition fuzzyCondition = DataSingleton.get().getCondition();

        FuzzyCellComparator comparator = new FuzzyCellComparator();
        float step = FuzzyLogic.getStep(fuzzyCondition.getA(), fuzzyCondition.getB(), fuzzyCondition.getSteps());
        ArrayList<FuzzySingleton> aSingletons = FuzzyLogic.convertToSingletons(fuzzyCondition.getA(), step);
        ArrayList<FuzzySingleton> bSingletons = FuzzyLogic.convertToSingletons(fuzzyCondition.getB(), step);
        FuzzyCell[][] matrix = FuzzyLogic.convertToMatrix(aSingletons, bSingletons, fuzzyCondition.getFun());
        ArrayList<FuzzyCell> fuzzyList = FuzzyLogic.convertToSortedList(matrix, comparator);
        FuzzyLogic.filterList(fuzzyList);

        List<PointValue> valuesC = new ArrayList<>();
        valuesC.add(new PointValue(0, 0));
        for (int i = 0; i < fuzzyList.size(); i++) {
            Log.w(Const.TAG, fuzzyList.get(i).toString());
            PointValue p = new PointValue(fuzzyList.get(i).getValueCoord(), fuzzyList.get(i).getMin());
            valuesC.add(p);
            if(i != fuzzyList.size() - 1) {
                PointValue pHelper = new PointValue(fuzzyList.get(i).getValueCoord(), fuzzyList.get(i+1).getMin());
                valuesC.add(pHelper);
            }
        }
        Line cLine = getLine(valuesC, Color.RED);

        List<PointValue> valuesA = getPoints(aSingletons, 0.02f);
        Line aLine = getLine(valuesA, Color.BLUE);

        List<PointValue> valuesB = getPoints(bSingletons, 0.04f);
        Line bLine = getLine(valuesB, Color.BLACK);

        initGraph(Arrays.asList(aLine, bLine, cLine));
    }

    private List<PointValue> getPoints(ArrayList<FuzzySingleton> singletons, float padding) {
        List<PointValue> values = new ArrayList<>();
        values.add(new PointValue(0, 0));
        for (int i = 0; i < singletons.size(); i++) {
            Log.w(Const.TAG, singletons.get(i).toString());
            PointValue p = new PointValue(singletons.get(i).getX(), singletons.get(i).getValue());
            values.add(p);
            if(i != singletons.size() - 1) {
                PointValue pHelper = new PointValue(singletons.get(i).getX() + padding, singletons.get(i+1).getValue());
                values.add(pHelper);
            }
        }
        return values;
    }

    private Line getLine(List<PointValue> values, int color) {
        Line line = new Line(values).setColor(color).setCubic(false);
        line.setStrokeWidth(1);
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
