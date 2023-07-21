package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.internal.zzaad;

public class zzaay<O extends Api.ApiOptions> extends zzaap {
    private final zzc<O> zzaCK;

    public zzaay(zzc<O> zzc) {
        super("Method is not supported by connectionless client. APIs supporting connectionless client must not call this method.");
        this.zzaCK = zzc;
    }

    public Context getContext() {
        return this.zzaCK.getApplicationContext();
    }

    public Looper getLooper() {
        return this.zzaCK.getLooper();
    }

    public <A extends Api.zzb, R extends Result, T extends zzaad.zza<R, A>> T zza(@NonNull T t) {
        return this.zzaCK.doRead(t);
    }

    public void zza(zzabx zzabx) {
    }

    public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T zzb(@NonNull T t) {
        return this.zzaCK.doWrite(t);
    }

    public void zzb(zzabx zzabx) {
    }
}
