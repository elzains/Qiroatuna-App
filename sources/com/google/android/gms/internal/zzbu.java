package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;

public class zzbu extends zzca {
    private final StackTraceElement[] zzqZ;

    public zzbu(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2, StackTraceElement[] stackTraceElementArr) {
        super(zzbd, str, str2, zza, i, i2);
        this.zzqZ = stackTraceElementArr;
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        if (this.zzqZ != null) {
            zzbb zzbb = new zzbb((String) this.zzre.invoke((Object) null, new Object[]{this.zzqZ}));
            synchronized (this.zzqV) {
                this.zzqV.zzbJ = zzbb.zzqi;
                if (zzbb.zzqj.booleanValue()) {
                    this.zzqV.zzbR = Integer.valueOf(zzbb.zzqk.booleanValue() ? 0 : 1);
                }
            }
        }
    }
}
