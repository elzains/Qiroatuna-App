package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.zzaqf;
import java.util.concurrent.Callable;

public class zzb {
    private static SharedPreferences zzaXu = null;

    public static SharedPreferences zzn(final Context context) {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferences.class) {
            if (zzaXu == null) {
                zzaXu = (SharedPreferences) zzaqf.zzb(new Callable<SharedPreferences>() {
                    /* renamed from: zzDH */
                    public SharedPreferences call() {
                        return context.getSharedPreferences("google_sdk_flags", 1);
                    }
                });
            }
            sharedPreferences = zzaXu;
        }
        return sharedPreferences;
    }
}
