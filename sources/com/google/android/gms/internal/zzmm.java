package com.google.android.gms.internal;

import com.google.android.gms.internal.zzmh;
import com.google.android.gms.internal.zzmu;
import java.lang.ref.WeakReference;

@zzme
public final class zzmm extends zzmu.zza {
    private final WeakReference<zzmh.zza> zzSl;

    public zzmm(zzmh.zza zza) {
        this.zzSl = new WeakReference<>(zza);
    }

    public void zza(zzmn zzmn) {
        zzmh.zza zza = (zzmh.zza) this.zzSl.get();
        if (zza != null) {
            zza.zza(zzmn);
        }
    }
}
