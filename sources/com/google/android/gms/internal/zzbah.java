package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzg;

public final class zzbah {
    public static final Api<zzbaj> API = new Api<>("SignIn.API", zzaie, zzaid);
    public static final Api<zza> zzaKN = new Api<>("SignIn.INTERNAL_API", zzbEk, zzbEj);
    public static final Api.zzf<zzbat> zzaid = new Api.zzf<>();
    public static final Api.zza<zzbat, zzbaj> zzaie = new Api.zza<zzbat, zzbaj>() {
        public zzbat zza(Context context, Looper looper, zzg zzg, zzbaj zzbaj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new zzbat(context, looper, true, zzg, zzbaj == null ? zzbaj.zzbEm : zzbaj, connectionCallbacks, onConnectionFailedListener);
        }
    };
    public static final Scope zzakh = new Scope(Scopes.PROFILE);
    public static final Scope zzaki = new Scope("email");
    public static final Api.zzf<zzbat> zzbEj = new Api.zzf<>();
    static final Api.zza<zzbat, zza> zzbEk = new Api.zza<zzbat, zza>() {
        public zzbat zza(Context context, Looper looper, zzg zzg, zza zza, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            return new zzbat(context, looper, false, zzg, zza.zzPH(), connectionCallbacks, onConnectionFailedListener);
        }
    };

    public static class zza implements Api.ApiOptions.HasOptions {
        private final Bundle zzbEl;

        public Bundle zzPH() {
            return this.zzbEl;
        }
    }
}
