package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzg;

public final class zzacf {
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Common.API", zzaie, zzaid);
    public static final zzacg zzaGM = new zzach();
    public static final Api.zzf<zzacj> zzaid = new Api.zzf<>();
    private static final Api.zza<zzacj, Api.ApiOptions.NoOptions> zzaie = new Api.zza<zzacj, Api.ApiOptions.NoOptions>() {
        /* renamed from: zzf */
        public zzacj zza(Context context, Looper looper, zzg zzg, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new zzacj(context, looper, zzg, connectionCallbacks, onConnectionFailedListener);
        }
    };
}
