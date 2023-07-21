package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;

public class zzbm extends zzca {
    private static volatile Long zzbI = null;
    private static final Object zzqS = new Object();

    public zzbm(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        if (zzbI == null) {
            synchronized (zzqS) {
                if (zzbI == null) {
                    zzbI = (Long) this.zzre.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zzqV) {
            this.zzqV.zzbI = zzbI;
        }
    }
}
