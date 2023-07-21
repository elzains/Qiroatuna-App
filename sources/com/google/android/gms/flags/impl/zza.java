package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import com.google.android.gms.internal.zzaqf;
import java.util.concurrent.Callable;

public abstract class zza<T> {

    /* renamed from: com.google.android.gms.flags.impl.zza$zza  reason: collision with other inner class name */
    public static class C0783zza extends zza<Boolean> {
        public static Boolean zza(final SharedPreferences sharedPreferences, final String str, final Boolean bool) {
            return (Boolean) zzaqf.zzb(new Callable<Boolean>() {
                /* renamed from: zzbX */
                public Boolean call() {
                    return Boolean.valueOf(sharedPreferences.getBoolean(str, bool.booleanValue()));
                }
            });
        }
    }

    public static class zzb extends zza<Integer> {
        public static Integer zza(final SharedPreferences sharedPreferences, final String str, final Integer num) {
            return (Integer) zzaqf.zzb(new Callable<Integer>() {
                /* renamed from: zzDF */
                public Integer call() {
                    return Integer.valueOf(sharedPreferences.getInt(str, num.intValue()));
                }
            });
        }
    }

    public static class zzc extends zza<Long> {
        public static Long zza(final SharedPreferences sharedPreferences, final String str, final Long l) {
            return (Long) zzaqf.zzb(new Callable<Long>() {
                /* renamed from: zzDG */
                public Long call() {
                    return Long.valueOf(sharedPreferences.getLong(str, l.longValue()));
                }
            });
        }
    }

    public static class zzd extends zza<String> {
        public static String zza(final SharedPreferences sharedPreferences, final String str, final String str2) {
            return (String) zzaqf.zzb(new Callable<String>() {
                /* renamed from: zzbY */
                public String call() {
                    return sharedPreferences.getString(str, str2);
                }
            });
        }
    }
}
