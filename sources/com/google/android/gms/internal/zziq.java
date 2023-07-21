package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzw;

@zzme
public class zziq extends zzpj {
    final zzqw zzIs;
    final zzis zzIw;
    private final String zzIx;

    zziq(zzqw zzqw, zzis zzis, String str) {
        this.zzIs = zzqw;
        this.zzIw = zzis;
        this.zzIx = str;
        zzw.zzdj().zza(this);
    }

    public void onStop() {
        this.zzIw.abort();
    }

    public void zzco() {
        try {
            this.zzIw.zzad(this.zzIx);
        } finally {
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    zzw.zzdj().zzb(zziq.this);
                }
            });
        }
    }
}
