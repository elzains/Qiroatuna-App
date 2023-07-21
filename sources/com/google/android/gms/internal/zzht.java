package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.FrameLayout;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.internal.zzhh;
import com.google.android.gms.internal.zzhi;

@zzme
public class zzht extends zzf<zzhi> {
    public zzht() {
        super("com.google.android.gms.ads.NativeAdViewDelegateCreatorImpl");
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzL */
    public zzhi zzc(IBinder iBinder) {
        return zzhi.zza.zzD(iBinder);
    }

    public zzhh zzb(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        try {
            return zzhh.zza.zzC(((zzhi) zzbl(context)).zza(zzd.zzA(context), zzd.zzA(frameLayout), zzd.zzA(frameLayout2), 10298000));
        } catch (RemoteException | zzf.zza e) {
            zzqf.zzc("Could not create remote NativeAdViewDelegate.", e);
            return null;
        }
    }
}
