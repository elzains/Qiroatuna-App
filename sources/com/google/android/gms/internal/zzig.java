package com.google.android.gms.internal;

import java.util.Map;

@zzme
public class zzig implements zzid {
    private final zzih zzIm;

    public zzig(zzih zzih) {
        this.zzIm = zzih;
    }

    public void zza(zzqw zzqw, Map<String, String> map) {
        float f;
        boolean equals = "1".equals(map.get("transparentBackground"));
        boolean equals2 = "1".equals(map.get("blur"));
        try {
            if (map.get("blurRadius") != null) {
                f = Float.parseFloat(map.get("blurRadius"));
                this.zzIm.zzg(equals);
                this.zzIm.zza(equals2, f);
            }
        } catch (NumberFormatException e) {
            zzpk.zzb("Fail to parse float", e);
        }
        f = 0.0f;
        this.zzIm.zzg(equals);
        this.zzIm.zza(equals2, f);
    }
}
