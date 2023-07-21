package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@zzme
public class zzod {
    public static void zza(Context context, Runnable runnable) {
        zzac.zzdj("Adapters must be initialized on the main thread.");
        Map<String, zzjr> zzkf = zzw.zzcQ().zzkw().zzkf();
        if (zzkf != null && !zzkf.isEmpty()) {
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    zzpk.zzc("Could not initialize rewarded ads.", th);
                    return;
                }
            }
            zzns zzjE = zzns.zzjE();
            if (zzjE != null) {
                zza(context, zzkf.values(), zzjE);
            }
        }
    }

    static void zza(Context context, Collection<zzjr> collection, zzns zzns) {
        HashMap hashMap = new HashMap();
        IObjectWrapper zzA = zzd.zzA(context);
        for (zzjr zzjr : collection) {
            for (zzjq next : zzjr.zzKD) {
                String str = next.zzKv;
                for (String next2 : next.zzKp) {
                    if (!hashMap.containsKey(next2)) {
                        hashMap.put(next2, new ArrayList());
                    }
                    if (str != null) {
                        ((Collection) hashMap.get(next2)).add(str);
                    }
                }
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            String str2 = (String) entry.getKey();
            try {
                zzol zzaN = zzns.zzaN(str2);
                if (zzaN != null) {
                    zzkb zzjN = zzaN.zzjN();
                    if (!zzjN.isInitialized() && zzjN.zzhg()) {
                        zzjN.zza(zzA, (zzom) zzaN.zzjO(), (List<String>) (List) entry.getValue());
                        String valueOf = String.valueOf(str2);
                        zzpk.zzbf(valueOf.length() != 0 ? "Initialized rewarded video mediation adapter ".concat(valueOf) : new String("Initialized rewarded video mediation adapter "));
                    }
                }
            } catch (Throwable th) {
                zzpk.zzc(new StringBuilder(String.valueOf(str2).length() + 56).append("Failed to initialize rewarded video mediation adapter \"").append(str2).append("\"").toString(), th);
            }
        }
    }
}
