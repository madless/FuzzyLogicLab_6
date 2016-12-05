package com.dmikhov.fuzzynumberslab6.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.dmikhov.fuzzynumberslab6.R;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.DivFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.FuzzyFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.MaxFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.MinFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.MultFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.SubFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.SumFunction;

/**
 * Created by dmikhov on 05.12.2016.
 */
public class BaseInputFragment extends Fragment implements CompoundButton.OnCheckedChangeListener{
    protected Button buttonCalculate;
    protected CheckBox cbFullRes;
    protected EditText etSteps;
    protected RadioButton rbDivide;
    protected RadioButton rbMax;
    protected RadioButton rbMin;
    protected RadioButton rbMult;
    protected RadioButton rbSub;
    protected RadioButton rbSum;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonCalculate = (Button) view.findViewById(R.id.buttonCalculate);
        cbFullRes = (CheckBox) view.findViewById(R.id.cbFullRes);
        etSteps = (EditText) view.findViewById(R.id.etSteps);
        rbDivide = (RadioButton) view.findViewById(R.id.rbDivide);
        rbMax = (RadioButton) view.findViewById(R.id.rbMax);
        rbMin = (RadioButton) view.findViewById(R.id.rbMin);
        rbMult = (RadioButton) view.findViewById(R.id.rbMult);
        rbSub = (RadioButton) view.findViewById(R.id.rbSub);
        rbSum = (RadioButton) view.findViewById(R.id.rbSum);

        rbMax.setOnCheckedChangeListener(this);
        rbMin.setOnCheckedChangeListener(this);
        rbMult.setOnCheckedChangeListener(this);
        rbSub.setOnCheckedChangeListener(this);
        rbSum.setOnCheckedChangeListener(this);
        rbDivide.setOnCheckedChangeListener(this);
    }

    public boolean isFullResult() {
        return cbFullRes.isChecked();
    }

    public Integer getSteps() {
        return Integer.valueOf(etSteps.getText().toString());
    }

    public FuzzyFunction getSelectedFunction() {
        if (rbMax.isChecked()) return new MaxFunction();
        if (rbDivide.isChecked()) return new DivFunction();
        if (rbMin.isChecked()) return new MinFunction();
        if (rbMult.isChecked()) return new MultFunction();
        if (rbSub.isChecked()) return new SubFunction();
        if (rbSum.isChecked()) return new SumFunction();
        throw new RuntimeException();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            rbMax.setChecked(false);
            rbMin.setChecked(false);
            rbMult.setChecked(false);
            rbSub.setChecked(false);
            rbSum.setChecked(false);
            rbDivide.setChecked(false);
            compoundButton.setChecked(true);
        }
    }
}
