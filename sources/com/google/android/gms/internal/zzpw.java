package com.google.android.gms.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.common.internal.zzac;

@zzme
public class zzpw {
    private Handler mHandler = null;
    private HandlerThread zzYy = null;
    /* access modifiers changed from: private */
    public int zzYz = 0;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();

    public Looper zzlb() {
        Looper looper;
        synchronized (this.zzrJ) {
            if (this.zzYz != 0) {
                zzac.zzb(this.zzYy, (Object) "Invalid state: mHandlerThread should already been initialized.");
            } else if (this.zzYy == null) {
                zzpk.m19v("Starting the looper thread.");
                this.zzYy = new HandlerThread("LooperProvider");
                this.zzYy.start();
                this.mHandler = new Handler(this.zzYy.getLooper());
                zzpk.m19v("Looper thread started.");
            } else {
                zzpk.m19v("Resuming the looper thread");
                this.zzrJ.notifyAll();
            }
            this.zzYz++;
            looper = this.zzYy.getLooper();
        }
        return looper;
    }

    public void zzlc() {
        synchronized (this.zzrJ) {
            zzac.zzb(this.zzYz > 0, (Object) "Invalid state: release() called more times than expected.");
            int i = this.zzYz - 1;
            this.zzYz = i;
            if (i == 0) {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        synchronized (zzpw.this.zzrJ) {
                            zzpk.m19v("Suspending the looper thread");
                            while (zzpw.this.zzYz == 0) {
                                try {
                                    zzpw.this.zzrJ.wait();
                                    zzpk.m19v("Looper thread resumed");
                                } catch (InterruptedException e) {
                                    zzpk.m19v("Looper thread interrupted.");
                                }
                            }
                        }
                    }
                });
            }
        }
    }
}
