package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzl;
import com.google.android.gms.internal.zzzs;

public class zzzp extends zzl<zzzs> {
    public zzzp(Context context, Looper looper, zzg zzg, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 40, zzg, connectionCallbacks, onConnectionFailedListener);
    }

    public void zza(zzzr zzzr, zzzm zzzm) throws RemoteException {
        ((zzzs) zzxD()).zza(zzzr, zzzm);
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzbm */
    public zzzs zzh(IBinder iBinder) {
        return zzzs.zza.zzbo(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.gms.clearcut.service.START";
    }
}
