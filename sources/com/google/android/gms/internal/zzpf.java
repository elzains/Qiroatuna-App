package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.ads.AdActivity;

@zzme
public class zzpf {
    final String zzWD;
    int zzWW = -1;
    long zzWY = -1;
    long zzWZ = -1;
    int zzXa = -1;
    long zzXb = 0;
    int zzXc = 0;
    int zzXd = 0;
    private final Object zzrJ = new Object();

    public zzpf(String str) {
        this.zzWD = str;
    }

    public static boolean zzI(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", "android");
        if (identifier == 0) {
            zzpk.zzbg("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            zzpk.zzbg("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            zzpk.zzbh("Fail to fetch AdActivity theme");
            zzpk.zzbg("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }

    public void zzai(int i) {
        this.zzWW = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzb(com.google.android.gms.internal.zzec r11, long r12) {
        /*
            r10 = this;
            java.lang.Object r1 = r10.zzrJ
            monitor-enter(r1)
            com.google.android.gms.internal.zzpe r0 = com.google.android.gms.ads.internal.zzw.zzcQ()     // Catch:{ all -> 0x0055 }
            long r2 = r0.zzks()     // Catch:{ all -> 0x0055 }
            com.google.android.gms.common.util.zze r0 = com.google.android.gms.ads.internal.zzw.zzcS()     // Catch:{ all -> 0x0055 }
            long r4 = r0.currentTimeMillis()     // Catch:{ all -> 0x0055 }
            long r6 = r10.zzWZ     // Catch:{ all -> 0x0055 }
            r8 = -1
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 != 0) goto L_0x0058
            long r2 = r4 - r2
            com.google.android.gms.internal.zzfz<java.lang.Long> r0 = com.google.android.gms.internal.zzgd.zzCR     // Catch:{ all -> 0x0055 }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0055 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ all -> 0x0055 }
            long r6 = r0.longValue()     // Catch:{ all -> 0x0055 }
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x0049
            r0 = -1
            r10.zzai(r0)     // Catch:{ all -> 0x0055 }
        L_0x0031:
            r10.zzWZ = r12     // Catch:{ all -> 0x0055 }
            long r2 = r10.zzWZ     // Catch:{ all -> 0x0055 }
            r10.zzWY = r2     // Catch:{ all -> 0x0055 }
        L_0x0037:
            android.os.Bundle r0 = r11.extras     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x005b
            android.os.Bundle r0 = r11.extras     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = "gw"
            r3 = 2
            int r0 = r0.getInt(r2, r3)     // Catch:{ all -> 0x0055 }
            r2 = 1
            if (r0 != r2) goto L_0x005b
            monitor-exit(r1)     // Catch:{ all -> 0x0055 }
        L_0x0048:
            return
        L_0x0049:
            com.google.android.gms.internal.zzpe r0 = com.google.android.gms.ads.internal.zzw.zzcQ()     // Catch:{ all -> 0x0055 }
            int r0 = r0.zzku()     // Catch:{ all -> 0x0055 }
            r10.zzai(r0)     // Catch:{ all -> 0x0055 }
            goto L_0x0031
        L_0x0055:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0055 }
            throw r0
        L_0x0058:
            r10.zzWY = r12     // Catch:{ all -> 0x0055 }
            goto L_0x0037
        L_0x005b:
            int r0 = r10.zzXa     // Catch:{ all -> 0x0055 }
            int r0 = r0 + 1
            r10.zzXa = r0     // Catch:{ all -> 0x0055 }
            int r0 = r10.zzWW     // Catch:{ all -> 0x0055 }
            int r0 = r0 + 1
            r10.zzWW = r0     // Catch:{ all -> 0x0055 }
            int r0 = r10.zzWW     // Catch:{ all -> 0x0055 }
            if (r0 != 0) goto L_0x0078
            r2 = 0
            r10.zzXb = r2     // Catch:{ all -> 0x0055 }
            com.google.android.gms.internal.zzpe r0 = com.google.android.gms.ads.internal.zzw.zzcQ()     // Catch:{ all -> 0x0055 }
            r0.zzp(r4)     // Catch:{ all -> 0x0055 }
        L_0x0076:
            monitor-exit(r1)     // Catch:{ all -> 0x0055 }
            goto L_0x0048
        L_0x0078:
            com.google.android.gms.internal.zzpe r0 = com.google.android.gms.ads.internal.zzw.zzcQ()     // Catch:{ all -> 0x0055 }
            long r2 = r0.zzkt()     // Catch:{ all -> 0x0055 }
            long r2 = r4 - r2
            r10.zzXb = r2     // Catch:{ all -> 0x0055 }
            goto L_0x0076
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpf.zzb(com.google.android.gms.internal.zzec, long):void");
    }

    public void zzjV() {
        synchronized (this.zzrJ) {
            this.zzXd++;
        }
    }

    public void zzjW() {
        synchronized (this.zzrJ) {
            this.zzXc++;
        }
    }

    public long zzkE() {
        return this.zzWZ;
    }

    public int zzku() {
        return this.zzWW;
    }

    public Bundle zzo(Context context, String str) {
        Bundle bundle;
        synchronized (this.zzrJ) {
            bundle = new Bundle();
            bundle.putString("session_id", this.zzWD);
            bundle.putLong("basets", this.zzWZ);
            bundle.putLong("currts", this.zzWY);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzXa);
            bundle.putInt("preqs_in_session", this.zzWW);
            bundle.putLong("time_in_session", this.zzXb);
            bundle.putInt("pclick", this.zzXc);
            bundle.putInt("pimp", this.zzXd);
            bundle.putBoolean("support_transparent_background", zzI(context));
        }
        return bundle;
    }
}
