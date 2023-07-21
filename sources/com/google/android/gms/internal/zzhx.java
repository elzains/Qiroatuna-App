package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.internal.zzhs;

@zzme
public class zzhx extends zzhs.zza {
    private final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzHB;

    public zzhx(NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener) {
        this.zzHB = onCustomTemplateAdLoadedListener;
    }

    public void zza(zzhn zzhn) {
        this.zzHB.onCustomTemplateAdLoaded(new zzho(zzhn));
    }
}
