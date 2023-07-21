package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zznu;

public class zzfo extends zznu.zza {
    /* access modifiers changed from: private */
    public zznw zzAF;

    public void destroy() throws RemoteException {
    }

    public boolean isLoaded() throws RemoteException {
        return false;
    }

    public void pause() throws RemoteException {
    }

    public void resume() throws RemoteException {
    }

    public void setUserId(String str) throws RemoteException {
    }

    public void show() throws RemoteException {
    }

    public void zza(zznw zznw) throws RemoteException {
        this.zzAF = zznw;
    }

    public void zza(zzoa zzoa) throws RemoteException {
        zzqf.m20e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                if (zzfo.this.zzAF != null) {
                    try {
                        zzfo.this.zzAF.onRewardedVideoAdFailedToLoad(1);
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not notify onRewardedVideoAdFailedToLoad event.", e);
                    }
                }
            }
        });
    }

    public void zzf(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public void zzg(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public void zzh(IObjectWrapper iObjectWrapper) throws RemoteException {
    }
}
