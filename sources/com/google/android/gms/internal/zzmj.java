package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzmt;

@zzme
public class zzmj extends zzf<zzmt> {
    final int zzRw;

    public zzmj(Context context, Looper looper, zzf.zzb zzb, zzf.zzc zzc, int i) {
        super(context, looper, 8, zzb, zzc, (String) null);
        this.zzRw = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzad */
    public zzmt zzh(IBinder iBinder) {
        return zzmt.zza.zzae(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.gms.ads.service.START";
    }

    public zzmt zzjq() throws DeadObjectException {
        return (zzmt) super.zzxD();
    }
}
