package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag;
import java.util.concurrent.Callable;

public class zzbp implements Callable {
    private final zzbd zzpz;
    private final zzag.zza zzqV;

    public zzbp(zzbd zzbd, zzag.zza zza) {
        this.zzpz = zzbd;
        this.zzqV = zza;
    }

    /* renamed from: zzbk */
    public Void call() throws Exception {
        if (this.zzpz.zzaS() != null) {
            this.zzpz.zzaS().get();
        }
        zzag.zza zzaR = this.zzpz.zzaR();
        if (zzaR == null) {
            return null;
        }
        try {
            synchronized (this.zzqV) {
                zzbxt.zza(this.zzqV, zzbxt.zzf(zzaR));
            }
            return null;
        } catch (zzbxs e) {
            return null;
        }
    }
}
