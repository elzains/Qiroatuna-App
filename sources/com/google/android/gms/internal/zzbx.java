package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;

public class zzbx extends zzca {
    public zzbx(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        this.zzqV.zzbK = 2;
        boolean booleanValue = ((Boolean) this.zzre.invoke((Object) null, new Object[]{this.zzpz.getApplicationContext()})).booleanValue();
        synchronized (this.zzqV) {
            if (booleanValue) {
                this.zzqV.zzbK = 1;
            } else {
                this.zzqV.zzbK = 0;
            }
        }
    }
}
