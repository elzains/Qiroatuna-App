package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;

public class zzbs extends zzca {
    private static volatile String zzbb = null;
    private static final Object zzqS = new Object();

    public zzbs(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        this.zzqV.zzbb = "E";
        if (zzbb == null) {
            synchronized (zzqS) {
                if (zzbb == null) {
                    zzbb = (String) this.zzre.invoke((Object) null, new Object[0]);
                }
            }
        }
        synchronized (this.zzqV) {
            this.zzqV.zzbb = zzbb;
        }
    }
}
