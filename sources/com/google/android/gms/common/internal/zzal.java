package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.api.Api;

public class zzal<T extends IInterface> extends zzl<T> {
    private final Api.zzg<T> zzaGJ;

    /* access modifiers changed from: protected */
    public String zzeA() {
        return this.zzaGJ.zzeA();
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return this.zzaGJ.zzez();
    }

    /* access modifiers changed from: protected */
    public T zzh(IBinder iBinder) {
        return this.zzaGJ.zzh(iBinder);
    }

    public Api.zzg<T> zzyn() {
        return this.zzaGJ;
    }
}
