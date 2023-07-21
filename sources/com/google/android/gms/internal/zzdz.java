package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.internal.zzep;

@zzme
public final class zzdz extends zzep.zza {
    private final AdListener zzyS;

    public zzdz(AdListener adListener) {
        this.zzyS = adListener;
    }

    public void onAdClosed() {
        this.zzyS.onAdClosed();
    }

    public void onAdFailedToLoad(int i) {
        this.zzyS.onAdFailedToLoad(i);
    }

    public void onAdLeftApplication() {
        this.zzyS.onAdLeftApplication();
    }

    public void onAdLoaded() {
        this.zzyS.onAdLoaded();
    }

    public void onAdOpened() {
        this.zzyS.onAdOpened();
    }
}
