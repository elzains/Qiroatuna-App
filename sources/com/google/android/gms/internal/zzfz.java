package com.google.android.gms.internal;

import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzw;

@zzme
public abstract class zzfz<T> {
    private final int zzAW;
    private final String zzAX;
    private final T zzAY;

    private zzfz(int i, String str, T t) {
        this.zzAW = i;
        this.zzAX = str;
        this.zzAY = t;
        zzw.zzcX().zza(this);
    }

    public static zzfz<String> zza(int i, String str) {
        zzfz<String> zza = zza(i, str, (String) null);
        zzw.zzcX().zzb(zza);
        return zza;
    }

    public static zzfz<Float> zza(int i, String str, float f) {
        return new zzfz<Float>(i, str, Float.valueOf(f)) {
            /* renamed from: zze */
            public Float zza(SharedPreferences sharedPreferences) {
                return Float.valueOf(sharedPreferences.getFloat(getKey(), ((Float) zzfr()).floatValue()));
            }
        };
    }

    public static zzfz<Integer> zza(int i, String str, int i2) {
        return new zzfz<Integer>(i, str, Integer.valueOf(i2)) {
            /* renamed from: zzc */
            public Integer zza(SharedPreferences sharedPreferences) {
                return Integer.valueOf(sharedPreferences.getInt(getKey(), ((Integer) zzfr()).intValue()));
            }
        };
    }

    public static zzfz<Long> zza(int i, String str, long j) {
        return new zzfz<Long>(i, str, Long.valueOf(j)) {
            /* renamed from: zzd */
            public Long zza(SharedPreferences sharedPreferences) {
                return Long.valueOf(sharedPreferences.getLong(getKey(), ((Long) zzfr()).longValue()));
            }
        };
    }

    public static zzfz<Boolean> zza(int i, String str, Boolean bool) {
        return new zzfz<Boolean>(i, str, bool) {
            /* renamed from: zzb */
            public Boolean zza(SharedPreferences sharedPreferences) {
                return Boolean.valueOf(sharedPreferences.getBoolean(getKey(), ((Boolean) zzfr()).booleanValue()));
            }
        };
    }

    public static zzfz<String> zza(int i, String str, String str2) {
        return new zzfz<String>(i, str, str2) {
            /* renamed from: zzf */
            public String zza(SharedPreferences sharedPreferences) {
                return sharedPreferences.getString(getKey(), (String) zzfr());
            }
        };
    }

    public static zzfz<String> zzb(int i, String str) {
        zzfz<String> zza = zza(i, str, (String) null);
        zzw.zzcX().zzc(zza);
        return zza;
    }

    public T get() {
        return zzw.zzcY().zzd(this);
    }

    public String getKey() {
        return this.zzAX;
    }

    /* access modifiers changed from: protected */
    public abstract T zza(SharedPreferences sharedPreferences);

    public T zzfr() {
        return this.zzAY;
    }
}
