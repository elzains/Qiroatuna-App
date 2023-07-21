package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.zzg;
import java.util.concurrent.Callable;

@zzme
public class zzgc {
    private final ConditionVariable zzBc = new ConditionVariable();
    /* access modifiers changed from: private */
    @Nullable
    public SharedPreferences zzBd = null;
    private final Object zzrJ = new Object();
    private volatile boolean zztZ = false;

    public void initialize(Context context) {
        if (!this.zztZ) {
            synchronized (this.zzrJ) {
                if (!this.zztZ) {
                    try {
                        Context remoteContext = zzg.getRemoteContext(context);
                        if (remoteContext != null) {
                            this.zzBd = zzw.zzcW().zzn(remoteContext);
                            this.zztZ = true;
                            this.zzBc.open();
                        }
                    } finally {
                        this.zzBc.open();
                    }
                }
            }
        }
    }

    public <T> T zzd(final zzfz<T> zzfz) {
        if (!this.zzBc.block(5000)) {
            throw new IllegalStateException("Flags.initialize() was not called!");
        }
        if (!this.zztZ) {
            synchronized (this.zzrJ) {
                if (!this.zztZ) {
                    T zzfr = zzfz.zzfr();
                    return zzfr;
                }
            }
        }
        return zzqb.zzb(new Callable<T>() {
            public T call() {
                return zzfz.zza(zzgc.this.zzBd);
            }
        });
    }
}
