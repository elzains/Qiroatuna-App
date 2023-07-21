package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.zzev;

@zzme
public final class zzei extends zzev.zza {
    private final AppEventListener zzzE;

    public zzei(AppEventListener appEventListener) {
        this.zzzE = appEventListener;
    }

    public void onAppEvent(String str, String str2) {
        this.zzzE.onAppEvent(str, str2);
    }
}
