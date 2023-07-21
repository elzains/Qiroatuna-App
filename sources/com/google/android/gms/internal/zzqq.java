package com.google.android.gms.internal;

import com.google.android.gms.internal.zzqp;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@zzme
public class zzqq<T> implements zzqp<T> {
    protected int zzJO = 0;
    protected final BlockingQueue<zza> zzZo = new LinkedBlockingQueue();
    protected T zzZp;
    private final Object zzrJ = new Object();

    class zza {
        public final zzqp.zzc<T> zzZq;
        public final zzqp.zza zzZr;

        public zza(zzqq zzqq, zzqp.zzc<T> zzc, zzqp.zza zza) {
            this.zzZq = zzc;
            this.zzZr = zza;
        }
    }

    public int getStatus() {
        return this.zzJO;
    }

    public void reject() {
        synchronized (this.zzrJ) {
            if (this.zzJO != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzJO = -1;
            for (zza zza2 : this.zzZo) {
                zza2.zzZr.run();
            }
            this.zzZo.clear();
        }
    }

    public void zza(zzqp.zzc<T> zzc, zzqp.zza zza2) {
        synchronized (this.zzrJ) {
            if (this.zzJO == 1) {
                zzc.zzd(this.zzZp);
            } else if (this.zzJO == -1) {
                zza2.run();
            } else if (this.zzJO == 0) {
                this.zzZo.add(new zza(this, zzc, zza2));
            }
        }
    }

    public void zzg(T t) {
        synchronized (this.zzrJ) {
            if (this.zzJO != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzZp = t;
            this.zzJO = 1;
            for (zza zza2 : this.zzZo) {
                zza2.zzZq.zzd(t);
            }
            this.zzZo.clear();
        }
    }
}
