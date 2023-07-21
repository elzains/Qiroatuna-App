package com.google.android.gms.internal;

import android.content.Context;
import android.os.SystemClock;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzpb;

@zzme
public abstract class zzlp extends zzpj {
    protected final Context mContext;
    protected final zzlq.zza zzPQ;
    protected final zzpb.zza zzPR;
    protected zzmn zzPS;
    protected final Object zzPU = new Object();
    protected final Object zzrJ = new Object();

    protected static final class zza extends Exception {
        private final int zzPY;

        public zza(String str, int i) {
            super(str);
            this.zzPY = i;
        }

        public int getErrorCode() {
            return this.zzPY;
        }
    }

    protected zzlp(Context context, zzpb.zza zza2, zzlq.zza zza3) {
        super(true);
        this.mContext = context;
        this.zzPR = zza2;
        this.zzPS = zza2.zzWm;
        this.zzPQ = zza3;
    }

    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public abstract zzpb zzR(int i);

    public void zzco() {
        synchronized (this.zzrJ) {
            zzpk.zzbf("AdRendererBackgroundTask started.");
            int i = this.zzPR.errorCode;
            try {
                zzh(SystemClock.elapsedRealtime());
            } catch (zza e) {
                int errorCode = e.getErrorCode();
                if (errorCode == 3 || errorCode == -1) {
                    zzpk.zzbg(e.getMessage());
                } else {
                    zzpk.zzbh(e.getMessage());
                }
                if (this.zzPS == null) {
                    this.zzPS = new zzmn(errorCode);
                } else {
                    this.zzPS = new zzmn(errorCode, this.zzPS.zzKL);
                }
                zzpo.zzXC.post(new Runnable() {
                    public void run() {
                        zzlp.this.onStop();
                    }
                });
                i = errorCode;
            }
            final zzpb zzR = zzR(i);
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    synchronized (zzlp.this.zzrJ) {
                        zzlp.this.zzn(zzR);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zzh(long j) throws zza;

    /* access modifiers changed from: protected */
    public void zzn(zzpb zzpb) {
        this.zzPQ.zzb(zzpb);
    }
}
