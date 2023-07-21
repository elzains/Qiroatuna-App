package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzw;
import java.util.Map;

@zzme
public class zzij implements zzid {
    public void zza(zzqw zzqw, Map<String, String> map) {
        if (zzw.zzdl().zzjQ()) {
            int i = -1;
            try {
                i = Integer.parseInt(map.get("eventType"));
            } catch (Exception e) {
                zzpk.zzb("Parse Scion log event type error", e);
            }
            String str = map.get("eventId");
            switch (i) {
                case 0:
                    zzw.zzdl().zzf(zzqw.getContext(), str);
                    return;
                case 1:
                    zzw.zzdl().zzg(zzqw.getContext(), str);
                    return;
                case 2:
                    zzw.zzdl().zzi(zzqw.getContext(), str);
                    return;
                default:
                    return;
            }
        }
    }
}
