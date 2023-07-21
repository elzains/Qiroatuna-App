package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzc;
import java.util.Map;

@zzme
public class zzit implements zzid {
    public void zza(zzqw zzqw, Map<String, String> map) {
        int i;
        zzir zzdj = zzw.zzdj();
        if (!map.containsKey("abort")) {
            String str = map.get("src");
            if (str == null) {
                zzpk.zzbh("Precache video action is missing the src parameter.");
                return;
            }
            try {
                i = Integer.parseInt(map.get("player"));
            } catch (NumberFormatException e) {
                i = 0;
            }
            String str2 = map.containsKey("mimetype") ? map.get("mimetype") : "";
            if (zzdj.zzf(zzqw)) {
                zzpk.zzbh("Precache task already running.");
                return;
            }
            zzc.zzt(zzqw.zzby());
            new zziq(zzqw, zzqw.zzby().zzsM.zza(zzqw, i, str2), str).zziP();
        } else if (!zzdj.zze(zzqw)) {
            zzpk.zzbh("Precache abort but no preload task running.");
        }
    }
}
