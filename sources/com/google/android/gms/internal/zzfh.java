package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zzd;

@zzme
public class zzfh {
    private static zzfh zzAy;
    private static final Object zztX = new Object();
    private RewardedVideoAd zzAA;
    private zzey zzAz;

    private zzfh() {
    }

    public static zzfh zzfk() {
        zzfh zzfh;
        synchronized (zztX) {
            if (zzAy == null) {
                zzAy = new zzfh();
            }
            zzfh = zzAy;
        }
        return zzfh;
    }

    public RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        RewardedVideoAd rewardedVideoAd;
        synchronized (zztX) {
            if (this.zzAA != null) {
                rewardedVideoAd = this.zzAA;
            } else {
                this.zzAA = new zzoc(context, zzel.zzeU().zza(context, (zzka) new zzjz()));
                rewardedVideoAd = this.zzAA;
            }
        }
        return rewardedVideoAd;
    }

    public void openDebugMenu(Context context, String str) {
        zzac.zza(this.zzAz != null, (Object) "MobileAds.initialize() must be called prior to opening debug menu.");
        try {
            this.zzAz.zzb(zzd.zzA(context), str);
        } catch (RemoteException e) {
            zzqf.zzb("Unable to open debug menu.", e);
        }
    }

    public void setAppMuted(boolean z) {
        zzac.zza(this.zzAz != null, (Object) "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzAz.setAppMuted(z);
        } catch (RemoteException e) {
            zzqf.zzb("Unable to set app mute state.", e);
        }
    }

    public void setAppVolume(float f) {
        boolean z = true;
        zzac.zzb(0.0f <= f && f <= 1.0f, (Object) "The app volume must be a value between 0 and 1 inclusive.");
        if (this.zzAz == null) {
            z = false;
        }
        zzac.zza(z, (Object) "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzAz.setAppVolume(f);
        } catch (RemoteException e) {
            zzqf.zzb("Unable to set app volume.", e);
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(final android.content.Context r4, java.lang.String r5, com.google.android.gms.internal.zzfi r6) {
        /*
            r3 = this;
            java.lang.Object r1 = zztX
            monitor-enter(r1)
            com.google.android.gms.internal.zzey r0 = r3.zzAz     // Catch:{ all -> 0x0013 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r1)     // Catch:{ all -> 0x0013 }
        L_0x0008:
            return
        L_0x0009:
            if (r4 != 0) goto L_0x0016
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0013 }
            java.lang.String r2 = "Context cannot be null."
            r0.<init>(r2)     // Catch:{ all -> 0x0013 }
            throw r0     // Catch:{ all -> 0x0013 }
        L_0x0013:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0013 }
            throw r0
        L_0x0016:
            com.google.android.gms.internal.zzek r0 = com.google.android.gms.internal.zzel.zzeU()     // Catch:{ RemoteException -> 0x0037 }
            com.google.android.gms.internal.zzey r0 = r0.zzl(r4)     // Catch:{ RemoteException -> 0x0037 }
            r3.zzAz = r0     // Catch:{ RemoteException -> 0x0037 }
            com.google.android.gms.internal.zzey r0 = r3.zzAz     // Catch:{ RemoteException -> 0x0037 }
            r0.initialize()     // Catch:{ RemoteException -> 0x0037 }
            if (r5 == 0) goto L_0x0035
            com.google.android.gms.internal.zzey r0 = r3.zzAz     // Catch:{ RemoteException -> 0x0037 }
            com.google.android.gms.internal.zzfh$1 r2 = new com.google.android.gms.internal.zzfh$1     // Catch:{ RemoteException -> 0x0037 }
            r2.<init>(r4)     // Catch:{ RemoteException -> 0x0037 }
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.zzd.zzA(r2)     // Catch:{ RemoteException -> 0x0037 }
            r0.zzc(r5, r2)     // Catch:{ RemoteException -> 0x0037 }
        L_0x0035:
            monitor-exit(r1)     // Catch:{ all -> 0x0013 }
            goto L_0x0008
        L_0x0037:
            r0 = move-exception
            java.lang.String r2 = "MobileAdsSettingManager initialization failed"
            com.google.android.gms.internal.zzqf.zzc(r2, r0)     // Catch:{ all -> 0x0013 }
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfh.zza(android.content.Context, java.lang.String, com.google.android.gms.internal.zzfi):void");
    }
}
