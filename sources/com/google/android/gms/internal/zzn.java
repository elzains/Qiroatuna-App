package com.google.android.gms.internal;

import com.google.android.gms.internal.zzb;

public class zzn<T> {
    public final T result;
    public final zzb.zza zzaf;
    public final zzs zzag;
    public boolean zzah;

    public interface zza {
        void zze(zzs zzs);
    }

    public interface zzb<T> {
        void zzb(T t);
    }

    private zzn(zzs zzs) {
        this.zzah = false;
        this.result = null;
        this.zzaf = null;
        this.zzag = zzs;
    }

    private zzn(T t, zzb.zza zza2) {
        this.zzah = false;
        this.result = t;
        this.zzaf = zza2;
        this.zzag = null;
    }

    public static <T> zzn<T> zza(T t, zzb.zza zza2) {
        return new zzn<>(t, zza2);
    }

    public static <T> zzn<T> zzd(zzs zzs) {
        return new zzn<>(zzs);
    }

    public boolean isSuccess() {
        return this.zzag == null;
    }
}
