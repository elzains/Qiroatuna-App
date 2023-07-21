package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;
import com.google.android.gms.internal.zzgp;

@zzme
public final class zzgq extends zzgp.zza {
    private final OnCustomRenderedAdLoadedListener zzAq;

    public zzgq(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzAq = onCustomRenderedAdLoadedListener;
    }

    public void zza(zzgo zzgo) {
        this.zzAq.onCustomRenderedAdLoaded(new zzgn(zzgo));
    }
}
