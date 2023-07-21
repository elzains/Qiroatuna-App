package com.google.android.gms.internal;

import java.util.concurrent.Future;

@zzme
public abstract class zzpj implements zzpq<Future> {
    /* access modifiers changed from: private */
    public volatile Thread zzXh;
    private boolean zzXi;
    private final Runnable zzw;

    public zzpj() {
        this.zzw = new Runnable() {
            public final void run() {
                Thread unused = zzpj.this.zzXh = Thread.currentThread();
                zzpj.this.zzco();
            }
        };
        this.zzXi = false;
    }

    public zzpj(boolean z) {
        this.zzw = new Runnable() {
            public final void run() {
                Thread unused = zzpj.this.zzXh = Thread.currentThread();
                zzpj.this.zzco();
            }
        };
        this.zzXi = z;
    }

    public final void cancel() {
        onStop();
        if (this.zzXh != null) {
            this.zzXh.interrupt();
        }
    }

    public abstract void onStop();

    public abstract void zzco();

    /* renamed from: zzkG */
    public final Future zziP() {
        return this.zzXi ? zzpn.zza(1, this.zzw) : zzpn.zza(this.zzw);
    }
}
