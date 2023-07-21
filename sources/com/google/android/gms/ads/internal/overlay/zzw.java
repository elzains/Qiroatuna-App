package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpo;
import java.util.concurrent.TimeUnit;

@zzme
@TargetApi(14)
public class zzw {
    private final long zzOk = TimeUnit.MILLISECONDS.toNanos(zzgd.zzBH.get().longValue());
    private long zzOl;
    private boolean zzOm = true;

    zzw() {
    }

    public void zza(SurfaceTexture surfaceTexture, final zzi zzi) {
        if (zzi != null) {
            long timestamp = surfaceTexture.getTimestamp();
            if (this.zzOm || Math.abs(timestamp - this.zzOl) >= this.zzOk) {
                this.zzOm = false;
                this.zzOl = timestamp;
                zzpo.zzXC.post(new Runnable(this) {
                    public void run() {
                        zzi.zzhY();
                    }
                });
            }
        }
    }

    public void zzhV() {
        this.zzOm = true;
    }
}
