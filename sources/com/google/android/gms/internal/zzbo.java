package com.google.android.gms.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.internal.zzag;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class zzbo extends zzca {
    public zzbo(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2) {
        super(zzbd, str, str2, zza, i, i2);
    }

    private void zzbi() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zzqV) {
            this.zzqV.zzbW = (String) this.zzre.invoke((Object) null, new Object[]{this.zzpz.getApplicationContext()});
        }
    }

    private void zzbj() {
        AdvertisingIdClient zzaY = this.zzpz.zzaY();
        if (zzaY != null) {
            try {
                AdvertisingIdClient.Info info = zzaY.getInfo();
                String zzr = zzbf.zzr(info.getId());
                if (zzr != null) {
                    synchronized (this.zzqV) {
                        this.zzqV.zzbW = zzr;
                        this.zzqV.zzbY = Boolean.valueOf(info.isLimitAdTrackingEnabled());
                        this.zzqV.zzbX = 5;
                    }
                }
            } catch (IOException e) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzbd() throws IllegalAccessException, InvocationTargetException {
        if (this.zzpz.zzaN()) {
            zzbj();
        } else {
            zzbi();
        }
    }
}
