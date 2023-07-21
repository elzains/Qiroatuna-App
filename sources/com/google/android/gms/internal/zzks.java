package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzw;
import java.util.Map;

@zzme
public class zzks {
    private final zzqw zzIs;
    private final boolean zzMm;
    private final String zzMn;

    public zzks(zzqw zzqw, Map<String, String> map) {
        this.zzIs = zzqw;
        this.zzMn = map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzMm = Boolean.parseBoolean(map.get("allowOrientationChange"));
        } else {
            this.zzMm = true;
        }
    }

    public void execute() {
        if (this.zzIs == null) {
            zzpk.zzbh("AdWebView is null");
        } else {
            this.zzIs.setRequestedOrientation("portrait".equalsIgnoreCase(this.zzMn) ? zzw.zzcO().zzkR() : "landscape".equalsIgnoreCase(this.zzMn) ? zzw.zzcO().zzkQ() : this.zzMm ? -1 : zzw.zzcO().zzkS());
        }
    }
}
