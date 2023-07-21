package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.internal.zzacl;

public class zzacj extends zzl<zzacl> {
    public zzacj(Context context, Looper looper, zzg zzg, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 39, zzg, connectionCallbacks, onConnectionFailedListener);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzbz */
    public zzacl zzh(IBinder iBinder) {
        return zzacl.zza.zzbB(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.common.internal.service.ICommonService";
    }

    public String zzez() {
        return "com.google.android.gms.common.service.START";
    }
}
