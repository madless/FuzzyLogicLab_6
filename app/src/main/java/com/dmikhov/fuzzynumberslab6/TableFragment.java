package com.dmikhov.fuzzynumberslab6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCell;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCondition;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyLogic;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzySingleton;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dmikhov on 01.12.2016.
 */
public class TableFragment extends Fragment {

    @Bind(R.id.table) TableLayout table;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_table, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FuzzyCondition fuzzyCondition = DataSingleton.get().getCondition();

        float step = FuzzyLogic.getStep(fuzzyCondition.getA(), fuzzyCondition.getB(), fuzzyCondition.getSteps());
        ArrayList<FuzzySingleton> aSingletons = FuzzyLogic.convertToSingletons(fuzzyCondition.getA(), step);
        ArrayList<FuzzySingleton> bSingletons = FuzzyLogic.convertToSingletons(fuzzyCondition.getB(), step);
        FuzzyCell[][] matrix = FuzzyLogic.convertToMatrix(aSingletons, bSingletons, fuzzyCondition.getFun());
        ArrayList<FuzzyCell> fuzzyList = FuzzyLogic.convertToSortedList(matrix);
        FuzzyLogic.filterList(fuzzyList);

        TableRow header = new TableRow(getContext());
        header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        header.addView(getHeaderView("X"));
        header.addView(getHeaderView("Y"));
        table.addView(header);

        for (int i = 0; i < fuzzyList.size(); i++) {
            TableRow row = new TableRow(getContext());
            row.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            row.addView(getCellView(fuzzyList.get(i).getValueCoord()), 0);
            row.addView(getCellView(fuzzyList.get(i).getMin()), 1);
            table.addView(row);
        }
    }

    public TextView getCellView(float data) {
        TextView tv = new TextView(getContext());
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(2, 2, 2, 2);
        tv.setPadding(15, 15, 15, 15);
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        System.out.println(tv.getLayoutParams());
        tv.setBackground(ContextCompat.getDrawable(getContext(), R.color.colorGray));
        tv.setText(String.format(Locale.US, "%.2f", data));
        return tv;
    }

    public TextView getHeaderView(String title) {
        TextView tv = new TextView(getContext());
        TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(2, 2, 2, 2);
        tv.setPadding(20, 20, 20, 20);
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        System.out.println(tv.getLayoutParams());
        tv.setBackground(ContextCompat.getDrawable(getContext(), R.color.colorGrayDark));
        tv.setTextColor(ContextCompat.getColor(getContext(), R.color.colorWhite));
        tv.setText(title);
        return tv;
    }
}