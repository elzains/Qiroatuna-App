package com.google.android.gms.internal;

import java.util.Map;

@zzme
public final class zzhy implements zzid {
    private final zzhz zzHC;

    public zzhy(zzhz zzhz) {
        this.zzHC = zzhz;
    }

    public void zza(zzqw zzqw, Map<String, String> map) {
        String str = map.get("name");
        if (str == null) {
            zzpk.zzbh("App event with no name parameter.");
        } else {
            this.zzHC.onAppEvent(str, map.get("info"));
        }
    }
}
