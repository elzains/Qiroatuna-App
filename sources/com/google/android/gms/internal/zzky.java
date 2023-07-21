package com.google.android.gms.internal;

import android.app.Activity;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.internal.zzkz;
import com.google.android.gms.internal.zzla;

@zzme
public final class zzky extends zzf<zzla> {
    public zzky() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzS */
    public zzla zzc(IBinder iBinder) {
        return zzla.zza.zzU(iBinder);
    }

    public zzkz zzf(Activity activity) {
        try {
            return zzkz.zza.zzT(((zzla) zzbl(activity)).zzp(zzd.zzA(activity)));
        } catch (RemoteException e) {
            zzqf.zzc("Could not create remote AdOverlay.", e);
            return null;
        } catch (zzf.zza e2) {
            zzqf.zzc("Could not create remote AdOverlay.", e2);
            return null;
        }
    }
}
