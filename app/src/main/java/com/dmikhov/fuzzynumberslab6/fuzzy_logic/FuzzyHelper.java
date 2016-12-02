package com.dmikhov.fuzzynumberslab6.fuzzy_logic;


import com.dmikhov.fuzzynumberslab6.fuzzy_logic.fuzzy_math_functions.DivFunction;
import com.dmikhov.fuzzynumberslab6.utils.Const;
import com.dmikhov.fuzzynumberslab6.utils.ValidationResponse;

/**
 * Created by dmikhov on 01.12.2016.
 */
public class FuzzyHelper {
    public static ValidationResponse validate(FuzzyCondition con) {
        if(con.getFun() instanceof DivFunction && (con.getB().getX0() == 0 || con.getB().getX1() == 0 || con.getB().getX2() == 0)) {
            return new ValidationResponse(false, "Error! Dividing by zero");
        } else if(!validateFuzzyNumber(con.getA())) {
            return new ValidationResponse(false, "Error! Fuzzy number A is not valid");
        } else if(!validateFuzzyNumber(con.getB())) {
            return new ValidationResponse(false, "Error! Fuzzy number B is not valid");
        } else if(con.getSteps() > 50) {
            return new ValidationResponse(false, "The amount of steps it too big, mobile phone can't process such data, sorry :c");
        }
        return new ValidationResponse(true);
    }

    public static boolean validateFuzzyNumber(FuzzyNumber number) {
        return number.getX1() < number.getX0() & number.getX1() < number.getX2() & number.getX0() < number.getX2();
    }

    public static boolean compareIfEquals(float a, float b) {
        if(Math.abs(a - b) < Const.EPSILON) {
            return true;
        } else {
            return false;
        }
    }
}
