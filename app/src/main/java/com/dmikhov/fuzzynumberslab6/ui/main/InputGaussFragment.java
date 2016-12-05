package com.dmikhov.fuzzynumberslab6.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.dmikhov.fuzzynumberslab6.DataSingleton;
import com.dmikhov.fuzzynumberslab6.R;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCondition;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyHelper;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities.FuzzyNumber;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities.GaussNumber;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.FuzzyFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions.DependencyFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions.GaussDependencyFunction;
import com.dmikhov.fuzzynumberslab6.utils.Const;
import com.dmikhov.fuzzynumberslab6.utils.IntentHelper;
import com.dmikhov.fuzzynumberslab6.utils.ValidationResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dmikhov on 02.12.2016.
 */
public class InputGaussFragment extends BaseInputFragment implements CompoundButton.OnCheckedChangeListener {
    @Bind(R.id.etAMax) EditText etAMax;
    @Bind(R.id.etAb) EditText etAb;
    @Bind(R.id.etBMax) EditText etBMax;
    @Bind(R.id.etBb) EditText etBb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_input_gauss, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etAb.getText().toString().isEmpty() && !etAMax.getText().toString().isEmpty()
                        && !etBb.getText().toString().isEmpty() && !etBMax.getText().toString().isEmpty()
                        && !etSteps.getText().toString().isEmpty()) {
                    FuzzyNumber a = getGaussNumber(etAb, etAMax);
                    FuzzyNumber b = getGaussNumber(etBb, etBMax);
                    FuzzyFunction fun = getSelectedFunction();
                    DependencyFunction depFun = new GaussDependencyFunction();
                    Integer steps = getSteps();
                    boolean isFullResult = isFullResult();
                    FuzzyCondition condition = new FuzzyCondition(a, b, fun, depFun, steps, isFullResult);

                    Log.d(Const.TAG, "a: " + a);
                    Log.d(Const.TAG, "b: " + b);

                    ValidationResponse validationResponse = FuzzyHelper.validate(condition);
                    if (validationResponse.isOk()) {
                        DataSingleton.get().setCondition(condition);
                        IntentHelper.startGraphActivity(getActivity());
                    } else {
                        Toast.makeText(getContext(), validationResponse.getErrorMsg(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error! Please input all data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public FuzzyNumber getGaussNumber(EditText etB, EditText etMax) {
        int b = Integer.valueOf(etB.getText().toString());
        int maxValue = Integer.valueOf(etMax.getText().toString());
        return new GaussNumber(b, maxValue);
    }
}
