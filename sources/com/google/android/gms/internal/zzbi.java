package com.google.android.gms.internal;

import android.provider.Settings;
import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;

public class zzbi extends zzca {
    public zzbi(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        this.zzqV.zzbL = 2;
        try {
            this.zzqV.zzbL = Integer.valueOf(((Boolean) this.zzre.invoke((Object) null, new Object[]{this.zzpz.getApplicationContext()})).booleanValue() ? 1 : 0);
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof Settings.SettingNotFoundException)) {
                throw e;
            }
        }
    }
}
