package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;

public class zzbv extends zzca {
    public zzbv(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzqV) {
            zzbc zzbc = new zzbc((String) this.zzre.invoke((Object) null, new Object[0]));
            this.zzqV.zzbM = zzbc.zzql;
            this.zzqV.zzbN = zzbc.zzqm;
        }
    }
}
