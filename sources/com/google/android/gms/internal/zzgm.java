package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.zzi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzgo;

@zzme
public final class zzgm extends zzgo.zza {
    private final zzi zzFT;
    @Nullable
    private final String zzFU;
    private final String zzFV;

    public zzgm(zzi zzi, @Nullable String str, String str2) {
        this.zzFT = zzi;
        this.zzFU = str;
        this.zzFV = str2;
    }

    public String getContent() {
        return this.zzFV;
    }

    public void recordClick() {
        this.zzFT.zzbZ();
    }

    public void recordImpression() {
        this.zzFT.zzca();
    }

    public String zzfG() {
        return this.zzFU;
    }

    public void zzi(@Nullable IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper != null) {
            this.zzFT.zzc((View) zzd.zzF(iObjectWrapper));
        }
    }
}
