package com.google.android.gms.internal;

import com.google.android.gms.ads.VideoController;
import com.google.android.gms.internal.zzfb;

public final class zzfs extends zzfb.zza {
    private final VideoController.VideoLifecycleCallbacks zzrL;

    public zzfs(VideoController.VideoLifecycleCallbacks videoLifecycleCallbacks) {
        this.zzrL = videoLifecycleCallbacks;
    }

    public void onVideoEnd() {
        this.zzrL.onVideoEnd();
    }

    public void zzeY() {
    }

    public void zzeZ() {
    }

    public void zzfa() {
    }
}
