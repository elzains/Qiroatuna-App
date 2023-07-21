package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class zzbya {
    private static String zzcvU;

    public static String zzcD(Context context) {
        if (zzcvU != null) {
            return zzcvU;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        String str = resolveActivity != null ? resolveActivity.activityInfo.packageName : null;
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : queryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setAction("android.support.customtabs.action.CustomTabsService");
            intent2.setPackage(next.activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                arrayList.add(next.activityInfo.packageName);
            }
        }
        if (arrayList.isEmpty()) {
            zzcvU = null;
        } else if (arrayList.size() == 1) {
            zzcvU = (String) arrayList.get(0);
        } else if (!TextUtils.isEmpty(str) && !zzo(context, intent) && arrayList.contains(str)) {
            zzcvU = str;
        } else if (arrayList.contains("com.android.chrome")) {
            zzcvU = "com.android.chrome";
        } else if (arrayList.contains("com.chrome.beta")) {
            zzcvU = "com.chrome.beta";
        } else if (arrayList.contains("com.chrome.dev")) {
            zzcvU = "com.chrome.dev";
        } else if (arrayList.contains("com.google.android.apps.chrome")) {
            zzcvU = "com.google.android.apps.chrome";
        }
        return zzcvU;
    }

    private static boolean zzo(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities == null || queryIntentActivities.size() == 0) {
                return false;
            }
            for (ResolveInfo next : queryIntentActivities) {
                IntentFilter intentFilter = next.filter;
                if (intentFilter != null && intentFilter.countDataAuthorities() != 0 && intentFilter.countDataPaths() != 0 && next.activityInfo != null) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException e) {
            Log.e("CustomTabsHelper", "Runtime exception while getting specialized handlers");
        }
    }
}
