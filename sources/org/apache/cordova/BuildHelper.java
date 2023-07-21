package org.apache.cordova;

import android.content.Context;

public class BuildHelper {
    private static String TAG = "BuildHelper";

    public static Object getBuildConfigValue(Context ctx, String key) {
        try {
            return Class.forName(ctx.getPackageName() + ".BuildConfig").getField(key).get((Object) null);
        } catch (ClassNotFoundException e) {
            LOG.m21d(TAG, "Unable to get the BuildConfig, is this built with ANT?");
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException e2) {
            LOG.m21d(TAG, key + " is not a valid field. Check your build.gradle");
            return null;
        } catch (IllegalAccessException e3) {
            LOG.m21d(TAG, "Illegal Access Exception: Let's print a stack trace.");
            e3.printStackTrace();
            return null;
        }
    }
}
