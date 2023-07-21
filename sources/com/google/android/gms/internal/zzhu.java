package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.internal.zzhp;

@zzme
public class zzhu extends zzhp.zza {
    private final NativeAppInstallAd.OnAppInstallAdLoadedListener zzHy;

    public zzhu(NativeAppInstallAd.OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
        this.zzHy = onAppInstallAdLoadedListener;
    }

    public void zza(zzhj zzhj) {
        this.zzHy.onAppInstallAdLoaded(zzb(zzhj));
    }

    /* access modifiers changed from: package-private */
    public zzhk zzb(zzhj zzhj) {
        return new zzhk(zzhj);
    }
}
