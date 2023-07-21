package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.internal.zzhq;

@zzme
public class zzhv extends zzhq.zza {
    private final NativeContentAd.OnContentAdLoadedListener zzHz;

    public zzhv(NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zzHz = onContentAdLoadedListener;
    }

    public void zza(zzhl zzhl) {
        this.zzHz.onContentAdLoaded(zzb(zzhl));
    }

    /* access modifiers changed from: package-private */
    public zzhm zzb(zzhl zzhl) {
        return new zzhm(zzhl);
    }
}
