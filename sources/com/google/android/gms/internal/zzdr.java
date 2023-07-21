package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzdv;

@zzme
public class zzdr extends zzf<zzdv> {
    zzdr(Context context, Looper looper, zzf.zzb zzb, zzf.zzc zzc) {
        super(context, looper, 123, zzb, zzc, (String) null);
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.ads.internal.cache.ICacheService";
    }

    public zzdv zzeB() throws DeadObjectException {
        return (zzdv) super.zzxD();
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.gms.ads.service.CACHE";
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzg */
    public zzdv zzh(IBinder iBinder) {
        return zzdv.zza.zzi(iBinder);
    }
}
