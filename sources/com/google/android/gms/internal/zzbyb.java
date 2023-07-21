package com.google.android.gms.internal;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public class zzbyb extends CustomTabsServiceConnection {
    private WeakReference<zzbyc> zzcvV;

    public zzbyb(zzbyc zzbyc) {
        this.zzcvV = new WeakReference<>(zzbyc);
    }

    public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzbyc zzbyc = (zzbyc) this.zzcvV.get();
        if (zzbyc != null) {
            zzbyc.zza(customTabsClient);
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzbyc zzbyc = (zzbyc) this.zzcvV.get();
        if (zzbyc != null) {
            zzbyc.zzfI();
        }
    }
}
