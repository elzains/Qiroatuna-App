package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.internal.zzlf;
import com.google.android.gms.internal.zzlg;

@zzme
public final class zzlk extends zzf<zzlg> {
    public zzlk() {
        super("com.google.android.gms.ads.InAppPurchaseManagerCreatorImpl");
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzac */
    public zzlg zzc(IBinder iBinder) {
        return zzlg.zza.zzZ(iBinder);
    }

    public zzlf zzg(Activity activity) {
        try {
            return zzlf.zza.zzY(((zzlg) zzbl(activity)).zzq(zzd.zzA(activity)));
        } catch (RemoteException e) {
            zzqf.zzc("Could not create remote InAppPurchaseManager.", e);
            return null;
        } catch (zzf.zza e2) {
            zzqf.zzc("Could not create remote InAppPurchaseManager.", e2);
            return null;
        }
    }
}
