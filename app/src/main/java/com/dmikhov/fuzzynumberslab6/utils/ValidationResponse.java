package com.dmikhov.fuzzynumberslab6.utils;

/**
 * Created by dmikhov on 01.12.2016.
 */
public class ValidationResponse {
    boolean isOk;
    String errorMsg;

    public ValidationResponse(boolean isOk) {
        this.isOk = isOk;
    }

    public ValidationResponse(boolean isOk, String errorMsg) {
        this.isOk = isOk;
        this.errorMsg = errorMsg;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ValidationResponse{" +
                "isOk=" + isOk +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
