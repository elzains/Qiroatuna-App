package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.zzc;
import java.util.Set;

public abstract class zzl<T extends IInterface> extends zzf<T> implements Api.zze, zzm.zza {
    private final zzg zzaAL;
    private final Account zzahh;
    private final Set<Scope> zzakq;

    protected zzl(Context context, Looper looper, int i, zzg zzg, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzn.zzaU(context), GoogleApiAvailability.getInstance(), i, zzg, (GoogleApiClient.ConnectionCallbacks) zzac.zzw(connectionCallbacks), (GoogleApiClient.OnConnectionFailedListener) zzac.zzw(onConnectionFailedListener));
    }

    protected zzl(Context context, Looper looper, zzn zzn, GoogleApiAvailability googleApiAvailability, int i, zzg zzg, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, zzn, googleApiAvailability, i, zza(connectionCallbacks), zza(onConnectionFailedListener), zzg.zzxP());
        this.zzaAL = zzg;
        this.zzahh = zzg.getAccount();
        this.zzakq = zzb(zzg.zzxM());
    }

    @Nullable
    private static zzf.zzb zza(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        if (connectionCallbacks == null) {
            return null;
        }
        return new zzf.zzb() {
            public void onConnected(@Nullable Bundle bundle) {
                GoogleApiClient.ConnectionCallbacks.this.onConnected(bundle);
            }

            public void onConnectionSuspended(int i) {
                GoogleApiClient.ConnectionCallbacks.this.onConnectionSuspended(i);
            }
        };
    }

    @Nullable
    private static zzf.zzc zza(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (onConnectionFailedListener == null) {
            return null;
        }
        return new zzf.zzc() {
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                GoogleApiClient.OnConnectionFailedListener.this.onConnectionFailed(connectionResult);
            }
        };
    }

    private Set<Scope> zzb(@NonNull Set<Scope> set) {
        Set<Scope> zzc = zzc(set);
        for (Scope contains : zzc) {
            if (!set.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return zzc;
    }

    public final Account getAccount() {
        return this.zzahh;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public Set<Scope> zzc(@NonNull Set<Scope> set) {
        return set;
    }

    public zzc[] zzxA() {
        return new zzc[0];
    }

    /* access modifiers changed from: protected */
    public final Set<Scope> zzxF() {
        return this.zzakq;
    }

    /* access modifiers changed from: protected */
    public final zzg zzxW() {
        return this.zzaAL;
    }
}
