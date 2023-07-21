package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.zzc;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.internal.zzaax;

public final class zzaaj<O extends Api.ApiOptions> extends zzc<O> {
    private final Api.zze zzaAJ;
    private final zzaag zzaAK;
    private final zzg zzaAL;
    private final Api.zza<? extends zzbai, zzbaj> zzazo;

    public zzaaj(@NonNull Context context, Api<O> api, Looper looper, @NonNull Api.zze zze, @NonNull zzaag zzaag, zzg zzg, Api.zza<? extends zzbai, zzbaj> zza) {
        super(context, api, looper);
        this.zzaAJ = zze;
        this.zzaAK = zzaag;
        this.zzaAL = zzg;
        this.zzazo = zza;
        this.zzayX.zzb((zzc<?>) this);
    }

    public Api.zze buildApiClient(Looper looper, zzaax.zza<O> zza) {
        this.zzaAK.zza(zza);
        return this.zzaAJ;
    }

    public zzabr createSignInCoordinator(Context context, Handler handler) {
        return new zzabr(context, handler, this.zzaAL, this.zzazo);
    }

    public Api.zze zzvU() {
        return this.zzaAJ;
    }
}
