package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.internal.zzli;

@zzme
public final class zzln extends zzli.zza {
    private final PlayStorePurchaseListener zzAr;

    public zzln(PlayStorePurchaseListener playStorePurchaseListener) {
        this.zzAr = playStorePurchaseListener;
    }

    public boolean isValidPurchase(String str) {
        return this.zzAr.isValidPurchase(str);
    }

    public void zza(zzlh zzlh) {
        this.zzAr.onInAppPurchaseFinished(new zzll(zzlh));
    }
}
