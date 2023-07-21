package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzaqm;

public class zzaqh extends zzf<zzaqm> {
    public zzaqh(Context context, Looper looper, zzf.zzb zzb, zzf.zzc zzc) {
        super(context, looper, 116, zzb, zzc, (String) null);
    }

    public zzaqm zzGK() throws DeadObjectException {
        return (zzaqm) super.zzxD();
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzcT */
    public zzaqm zzh(IBinder iBinder) {
        return zzaqm.zza.zzcU(iBinder);
    }

    /* access modifiers changed from: protected */
    public String zzeA() {
        return "com.google.android.gms.gass.internal.IGassService";
    }

    /* access modifiers changed from: protected */
    public String zzez() {
        return "com.google.android.gms.gass.START";
    }
}
