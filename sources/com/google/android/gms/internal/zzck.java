package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.view.MotionEvent;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;

@zzme
public final class zzck {
    private final zzco zzrN;

    public zzck(String str, Context context, boolean z) {
        this.zzrN = zzcn.zzb(str, context, z);
    }

    public void zza(MotionEvent motionEvent) throws RemoteException {
        this.zzrN.zzd(zzd.zzA(motionEvent));
    }

    public Uri zzc(Uri uri, Context context) throws zzcl, RemoteException {
        IObjectWrapper zza = this.zzrN.zza(zzd.zzA(uri), zzd.zzA(context));
        if (zza != null) {
            return (Uri) zzd.zzF(zza);
        }
        throw new zzcl();
    }

    public Uri zzd(Uri uri, Context context) throws zzcl, RemoteException {
        IObjectWrapper zzb = this.zzrN.zzb(zzd.zzA(uri), zzd.zzA(context));
        if (zzb != null) {
            return (Uri) zzd.zzF(zzb);
        }
        throw new zzcl();
    }
}
