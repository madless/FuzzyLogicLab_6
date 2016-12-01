package com.dmikhov.fuzzynumberslab6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCondition;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyNumber;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions.DivFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions.FuzzyFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions.MaxFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions.MinFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions.MultFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions.SubFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions.SumFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyHelper;
import com.dmikhov.fuzzynumberslab6.utils.IntentHelper;
import com.dmikhov.fuzzynumberslab6.utils.ValidationResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Bind(R.id.buttonCalculate) Button buttonCalculate;
    @Bind(R.id.cbFullRes) CheckBox cbFullRes;
    @Bind(R.id.etAX0) EditText etAX0;
    @Bind(R.id.etAX1) EditText etAX1;
    @Bind(R.id.etAX2) EditText etAX2;
    @Bind(R.id.etBX0) EditText etBX0;
    @Bind(R.id.etBX1) EditText etBX1;
    @Bind(R.id.etBX2) EditText etBX2;
    @Bind(R.id.etSteps) EditText etSteps;
    @Bind(R.id.rbDivide) RadioButton rbDivide;
    @Bind(R.id.rbMax) RadioButton rbMax;
    @Bind(R.id.rbMin) RadioButton rbMin;
    @Bind(R.id.rbMult) RadioButton rbMult;
    @Bind(R.id.rbSub) RadioButton rbSub;
    @Bind(R.id.rbSum) RadioButton rbSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rbMax.setOnCheckedChangeListener(this);
        rbMin.setOnCheckedChangeListener(this);
        rbMult.setOnCheckedChangeListener(this);
        rbSub.setOnCheckedChangeListener(this);
        rbSum.setOnCheckedChangeListener(this);
        rbDivide.setOnCheckedChangeListener(this);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FuzzyNumber a = getFuzzyNumber(etAX1, etAX0, etAX2);
                FuzzyNumber b = getFuzzyNumber(etBX1, etBX0, etBX2);
                FuzzyFunction fun = getSelectedFunction();
                Integer steps = getSteps();
                boolean isFullResult = isFullResult();
                FuzzyCondition condition = new FuzzyCondition(a, b, fun, steps, isFullResult);

                ValidationResponse validationResponse = FuzzyHelper.validate(condition);
                if(validationResponse.isOk()) {
                    DataSingleton.get().setCondition(condition);
                    IntentHelper.startGraphActivity(MainActivity.this);
                } else {
                    Toast.makeText(MainActivity.this, validationResponse.getErrorMsg(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean isFullResult() {
        return cbFullRes.isChecked();
    }

    public Integer getSteps() {
        return Integer.valueOf(etSteps.getText().toString());
    }

    public FuzzyNumber getFuzzyNumber(EditText etX1, EditText etX0, EditText etX2) {
        int x1 = Integer.valueOf(etX1.getText().toString());
        int x0 = Integer.valueOf(etX0.getText().toString());
        int x2 = Integer.valueOf(etX2.getText().toString());
        return new FuzzyNumber(x1, x0, x2);
    }

    public FuzzyFunction getSelectedFunction() {
        if(rbMax.isChecked()) return new MaxFunction();
        if(rbDivide.isChecked()) return new DivFunction();
        if(rbMin.isChecked()) return new MinFunction();
        if(rbMult.isChecked()) return new MultFunction();
        if(rbSub.isChecked()) return new SubFunction();
        if(rbSum.isChecked()) return new SumFunction();
        throw new RuntimeException();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b) {
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
