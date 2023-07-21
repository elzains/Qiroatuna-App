package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;

public class zzbn extends zzca {
    private long startTime;

    public zzbn(zzbd zzbd, String str, String str2, zzag.zza zza, long j, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
        this.startTime = j;
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zzre.invoke((Object) null, new Object[0])).longValue();
        synchronized (this.zzqV) {
            this.zzqV.zzca = Long.valueOf(longValue);
            if (this.startTime != 0) {
                this.zzqV.zzbq = Long.valueOf(longValue - this.startTime);
                this.zzqV.zzbv = Long.valueOf(this.startTime);
            }
        }
    }
}
