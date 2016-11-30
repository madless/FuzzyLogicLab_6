package com.dmikhov.fuzzynumberslab6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyCondition;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.FuzzyNumber;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions.FuzzyFunction;
import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_functions.MultFunction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FuzzyNumber a = new FuzzyNumber(1, 3, 10);
        FuzzyNumber b = new FuzzyNumber(0, 2, 5);
        FuzzyFunction fun = new MultFunction();
        int steps = 4;
        FuzzyCondition condition = new FuzzyCondition(a, b, fun, steps);
        DataSingleton.get().setCondition(condition);
        IntentHelper.startGraphActivity(this);
    }
}
