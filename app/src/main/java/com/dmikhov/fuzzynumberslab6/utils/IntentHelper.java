package com.dmikhov.fuzzynumberslab6.utils;

import android.app.Activity;
import android.content.Intent;

import com.dmikhov.fuzzynumberslab6.ResultActivity;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class IntentHelper {
    public static void startGraphActivity(Activity activity) {
        Intent intent = new Intent(activity, ResultActivity.class);
        activity.startActivity(intent);
    }
}
