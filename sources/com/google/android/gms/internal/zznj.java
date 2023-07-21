package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzni;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@zzme
public final class zznj {
    /* access modifiers changed from: private */
    public WeakHashMap<Context, zza> zzVb = new WeakHashMap<>();

    private class zza {
        public final long zzVd = zzw.zzcS().currentTimeMillis();
        public final zzni zzVe;

        public zza(zznj zznj, zzni zzni) {
            this.zzVe = zzni;
        }

        public boolean hasExpired() {
            return zzgd.zzDw.get().longValue() + this.zzVd < zzw.zzcS().currentTimeMillis();
        }
    }

    public Future<zzni> zzA(final Context context) {
        return zzpn.zza(new Callable<zzni>() {
            /* renamed from: zzjD */
            public zzni call() {
                zza zza = (zza) zznj.this.zzVb.get(context);
                zzni zzjC = (zza == null || zza.hasExpired() || !zzgd.zzDv.get().booleanValue()) ? new zzni.zza(context).zzjC() : new zzni.zza(context, zza.zzVe).zzjC();
                zznj.this.zzVb.put(context, new zza(zznj.this, zzjC));
                return zzjC;
            }
        });
    }
}
