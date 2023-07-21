package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzaad;
import java.util.Collections;

public class zzaas implements zzaau {
    private final zzaav zzaBk;

    public zzaas(zzaav zzaav) {
        this.zzaBk = zzaav;
    }

    public void begin() {
        this.zzaBk.zzwu();
        this.zzaBk.zzaAw.zzaBR = Collections.emptySet();
    }

    public void connect() {
        this.zzaBk.zzws();
    }

    public boolean disconnect() {
        return true;
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
    }

    public <A extends Api.zzb, R extends Result, T extends zzaad.zza<R, A>> T zza(T t) {
        this.zzaBk.zzaAw.zzaAU.add(t);
        return t;
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T zzb(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
