package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzcy;

@zzme
public class zzov implements zzcy.zzb {
    private final Context mContext;
    boolean zzVV = false;
    private final Object zzrJ = new Object();
    private final String zzts;

    public zzov(Context context, String str) {
        this.mContext = context;
        this.zzts = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzC(boolean r5) {
        /*
            r4 = this;
            com.google.android.gms.internal.zzow r0 = com.google.android.gms.ads.internal.zzw.zzdl()
            boolean r0 = r0.zzjQ()
            if (r0 != 0) goto L_0x000b
        L_0x000a:
            return
        L_0x000b:
            java.lang.Object r1 = r4.zzrJ
            monitor-enter(r1)
            boolean r0 = r4.zzVV     // Catch:{ all -> 0x0014 }
            if (r0 != r5) goto L_0x0017
            monitor-exit(r1)     // Catch:{ all -> 0x0014 }
            goto L_0x000a
        L_0x0014:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0014 }
            throw r0
        L_0x0017:
            r4.zzVV = r5     // Catch:{ all -> 0x0014 }
            boolean r0 = r4.zzVV     // Catch:{ all -> 0x0014 }
            if (r0 == 0) goto L_0x002a
            com.google.android.gms.internal.zzow r0 = com.google.android.gms.ads.internal.zzw.zzdl()     // Catch:{ all -> 0x0014 }
            android.content.Context r2 = r4.mContext     // Catch:{ all -> 0x0014 }
            java.lang.String r3 = r4.zzts     // Catch:{ all -> 0x0014 }
            r0.zzd(r2, r3)     // Catch:{ all -> 0x0014 }
        L_0x0028:
            monitor-exit(r1)     // Catch:{ all -> 0x0014 }
            goto L_0x000a
        L_0x002a:
            com.google.android.gms.internal.zzow r0 = com.google.android.gms.ads.internal.zzw.zzdl()     // Catch:{ all -> 0x0014 }
            android.content.Context r2 = r4.mContext     // Catch:{ all -> 0x0014 }
            java.lang.String r3 = r4.zzts     // Catch:{ all -> 0x0014 }
            r0.zze(r2, r3)     // Catch:{ all -> 0x0014 }
            goto L_0x0028
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzov.zzC(boolean):void");
    }

    public void zza(zzcy.zza zza) {
        zzC(zza.zzxl);
    }
}
