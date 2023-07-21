package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.internal.zzhr;

@zzme
public class zzhw extends zzhr.zza {
    private final NativeCustomTemplateAd.OnCustomClickListener zzHA;

    public zzhw(NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
        this.zzHA = onCustomClickListener;
    }

    public void zza(zzhn zzhn, String str) {
        this.zzHA.onCustomClick(new zzho(zzhn), str);
    }
}
