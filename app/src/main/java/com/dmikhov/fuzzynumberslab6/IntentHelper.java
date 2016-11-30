package com.dmikhov.fuzzynumberslab6;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by dmikhov on 30.11.2016.
 */
public class IntentHelper {
    public static void startGraphActivity(Activity activity) {
        Intent intent = new Intent(activity, GraphActivity.class);
        activity.startActivity(intent);
    }
}
