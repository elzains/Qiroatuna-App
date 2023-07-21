package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzes;

@zzme
public final class zzea extends zzf<zzes> {
    public zzea() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    public zzer zza(Context context, String str, zzka zzka) {
        try {
            return zzer.zza.zzo(((zzes) zzbl(context)).zza(zzd.zzA(context), str, zzka, 10298000));
        } catch (RemoteException e) {
            zzqf.zzc("Could not create remote builder for AdLoader.", e);
        } catch (zzf.zza e2) {
            zzqf.zzc("Could not create remote builder for AdLoader.", e2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzj */
    public zzes zzc(IBinder iBinder) {
        return zzes.zza.zzp(iBinder);
    }
}
