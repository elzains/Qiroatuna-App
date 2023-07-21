package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class zzbt extends zzca {
    private List<Long> zzqY = null;

    public zzbt(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        this.zzqV.zzbx = -1L;
        this.zzqV.zzby = -1L;
        if (this.zzqY == null) {
            this.zzqY = (List) this.zzre.invoke((Object) null, new Object[]{this.zzpz.getContext()});
        }
        if (this.zzqY != null && this.zzqY.size() == 2) {
            synchronized (this.zzqV) {
                this.zzqV.zzbx = Long.valueOf(this.zzqY.get(0).longValue());
                this.zzqV.zzby = Long.valueOf(this.zzqY.get(1).longValue());
            }
        }
    }
}
