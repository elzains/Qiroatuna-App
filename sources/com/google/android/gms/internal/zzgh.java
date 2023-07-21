package com.google.android.gms.internal;

import android.support.annotation.Nullable;

@zzme
public class zzgh {
    @Nullable
    public static zzgj zza(@Nullable zzgl zzgl, long j) {
        if (zzgl == null) {
            return null;
        }
        return zzgl.zzc(j);
    }

    public static boolean zza(@Nullable zzgl zzgl, @Nullable zzgj zzgj, long j, String... strArr) {
        if (zzgl == null || zzgj == null) {
            return false;
        }
        return zzgl.zza(zzgj, j, strArr);
    }

    public static boolean zza(@Nullable zzgl zzgl, @Nullable zzgj zzgj, String... strArr) {
        if (zzgl == null || zzgj == null) {
            return false;
        }
        return zzgl.zza(zzgj, strArr);
    }

    @Nullable
    public static zzgj zzb(@Nullable zzgl zzgl) {
        if (zzgl == null) {
            return null;
        }
        return zzgl.zzfB();
    }
}
