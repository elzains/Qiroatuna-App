package com.google.android.gms.ads.internal;

import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import java.lang.ref.WeakReference;

@zzme
public class zzt {
    private final zza zzul;
    /* access modifiers changed from: private */
    @Nullable
    public zzec zzum;
    /* access modifiers changed from: private */
    public boolean zzun;
    private boolean zzuo;
    private long zzup;
    private final Runnable zzw;

    public static class zza {
        private final Handler mHandler;

        public zza(Handler handler) {
            this.mHandler = handler;
        }

        public boolean postDelayed(Runnable runnable, long j) {
            return this.mHandler.postDelayed(runnable, j);
        }

        public void removeCallbacks(Runnable runnable) {
            this.mHandler.removeCallbacks(runnable);
        }
    }

    public zzt(zza zza2) {
        this(zza2, new zza(zzpo.zzXC));
    }

    zzt(zza zza2, zza zza3) {
        this.zzun = false;
        this.zzuo = false;
        this.zzup = 0;
        this.zzul = zza3;
        final WeakReference weakReference = new WeakReference(zza2);
        this.zzw = new Runnable() {
            public void run() {
                boolean unused = zzt.this.zzun = false;
                zza zza = (zza) weakReference.get();
                if (zza != null) {
                    zza.zzd(zzt.this.zzum);
                }
            }
        };
    }

    public void cancel() {
        this.zzun = false;
        this.zzul.removeCallbacks(this.zzw);
    }

    public void pause() {
        this.zzuo = true;
        if (this.zzun) {
            this.zzul.removeCallbacks(this.zzw);
        }
    }

    public void resume() {
        this.zzuo = false;
        if (this.zzun) {
            this.zzun = false;
            zza(this.zzum, this.zzup);
        }
    }

    public void zza(zzec zzec, long j) {
        if (this.zzun) {
            zzpk.zzbh("An ad refresh is already scheduled.");
            return;
        }
        this.zzum = zzec;
        this.zzun = true;
        this.zzup = j;
        if (!this.zzuo) {
            zzpk.zzbg(new StringBuilder(65).append("Scheduling ad refresh ").append(j).append(" milliseconds from now.").toString());
            this.zzul.postDelayed(this.zzw, j);
        }
    }

    public boolean zzcy() {
        return this.zzun;
    }

    public void zzg(zzec zzec) {
        this.zzum = zzec;
    }

    public void zzh(zzec zzec) {
        zza(zzec, 60000);
    }
}
