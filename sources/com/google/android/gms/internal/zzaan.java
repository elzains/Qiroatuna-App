package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzabh;

public abstract class zzaan<L> implements zzabh.zzc<L> {
    private final DataHolder zzaBi;

    protected zzaan(DataHolder dataHolder) {
        this.zzaBi = dataHolder;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(L l, DataHolder dataHolder);

    public final void zzs(L l) {
        zza(l, this.zzaBi);
    }

    public void zzwc() {
        if (this.zzaBi != null) {
            this.zzaBi.close();
        }
    }
}
