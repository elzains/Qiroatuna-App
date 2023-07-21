package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;

public class zzbj extends zzca {
    private static volatile String zzqR = null;
    private static final Object zzqS = new Object();

    public zzbj(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        this.zzqV.zzbw = "E";
        if (zzqR == null) {
            synchronized (zzqS) {
                if (zzqR == null) {
                    zzqR = (String) this.zzre.invoke((Object) null, new Object[]{this.zzpz.getContext()});
                }
            }
        }
        synchronized (this.zzqV) {
            this.zzqV.zzbw = zzam.zza(zzqR.getBytes(), true);
        }
    }
}
