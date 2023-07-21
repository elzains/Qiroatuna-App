package com.google.android.gms.internal;

public class zzs extends Exception {
    private long zzB;
    public final zzj zzai;

    public zzs() {
        this.zzai = null;
    }

    public zzs(zzj zzj) {
        this.zzai = zzj;
    }

    public zzs(Throwable th) {
        super(th);
        this.zzai = null;
    }

    /* access modifiers changed from: package-private */
    public void zza(long j) {
        this.zzB = j;
    }
}
