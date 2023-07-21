package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.internal.zznu;
import com.google.android.gms.internal.zznv;

@zzme
public class zzny extends zzf<zznv> {
    public zzny() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzak */
    public zznv zzc(IBinder iBinder) {
        return zznv.zza.zzai(iBinder);
    }

    public zznu zzb(Context context, zzka zzka) {
        try {
            return zznu.zza.zzah(((zznv) zzbl(context)).zza(zzd.zzA(context), zzka, 10298000));
        } catch (RemoteException | zzf.zza e) {
            zzqf.zzc("Could not get remote RewardedVideoAd.", e);
            return null;
        }
    }
}
