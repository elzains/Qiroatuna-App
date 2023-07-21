package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;

public class zzbq extends zzca {
    private static final Object zzqS = new Object();
    private static volatile Long zzqW = null;

    public zzbq(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        if (zzqW == null) {
            synchronized (zzqS) {
                if (zzqW == null) {
                    zzqW = (Long) this.zzre.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zzqV) {
            this.zzqV.zzbu = zzqW;
        }
    }
}
