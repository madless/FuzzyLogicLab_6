package com.dmikhov.fuzzynumberslab6.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.entities.TriangleNumber;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.FuzzyFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions.DependencyFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.independency_functions.TriangleDependencyFunction;
import com.dmikhov.fuzzynumberslab6.utils.IntentHelper;
import com.dmikhov.fuzzynumberslab6.utils.ValidationResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dmikhov on 02.12.2016.
 */
public class InputTriangleFragment extends BaseInputFragment implements CompoundButton.OnCheckedChangeListener  {
    @Bind(R.id.etAX0) EditText etAX0;
    @Bind(R.id.etAX1) EditText etAX1;
    @Bind(R.id.etAX2) EditText etAX2;
    @Bind(R.id.etBX0) EditText etBX0;
    @Bind(R.id.etBX1) EditText etBX1;
    @Bind(R.id.etBX2) EditText etBX2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_input_triangle, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rbMax.setOnCheckedChangeListener(this);
        rbMin.setOnCheckedChangeListener(this);
        rbMult.setOnCheckedChangeListener(this);
        rbSub.setOnCheckedChangeListener(this);
        rbSum.setOnCheckedChangeListener(this);
        rbDivide.setOnCheckedChangeListener(this);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etAX0.getText().toString().isEmpty() && !etAX1.getText().toString().isEmpty()
                        && !etAX2.getText().toString().isEmpty() && !etBX0.getText().toString().isEmpty()
                        && !etBX1.getText().toString().isEmpty() && !etBX2.getText().toString().isEmpty()
                        && !etSteps.getText().toString().isEmpty()) {
                    FuzzyNumber a = getTriangleNumber(etAX1, etAX0, etAX2);
                    FuzzyNumber b = getTriangleNumber(etBX1, etBX0, etBX2);
                    FuzzyFunction fun = getSelectedFunction();
                    DependencyFunction depFun = new TriangleDependencyFunction();
                    Integer steps = getSteps();
                    boolean isFullResult = isFullResult();
                    FuzzyCondition condition = new FuzzyCondition(a, b, fun, depFun, steps, isFullResult);

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

    public FuzzyNumber getTriangleNumber(EditText etX1, EditText etX0, EditText etX2) {
        int x1 = Integer.valueOf(etX1.getText().toString());
        int x0 = Integer.valueOf(etX0.getText().toString());
        int x2 = Integer.valueOf(etX2.getText().toString());
        return new TriangleNumber(x1, x0, x2);
    }
}
