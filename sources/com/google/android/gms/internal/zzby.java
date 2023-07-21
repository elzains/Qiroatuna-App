package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;

public class zzby extends zzca {
    private final View zzrb;

    public zzby(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2, View view) {
        super(zzbd, str, str2, zza, i, i2);
        this.zzrb = view;
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        if (this.zzrb != null) {
            zzbg zzbg = new zzbg((String) this.zzre.invoke((Object) null, new Object[]{this.zzrb}));
            synchronized (this.zzqV) {
                this.zzqV.zzbU = new zzag.zza.zzb();
                this.zzqV.zzbU.zzcn = zzbg.zzqQ;
            }
        }
    }
}
