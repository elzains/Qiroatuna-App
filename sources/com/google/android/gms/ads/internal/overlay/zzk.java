package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.internal.zzgl;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzqw;

@zzme
public abstract class zzk {
    @Nullable
    public abstract zzj zza(Context context, zzqw zzqw, int i, boolean z, zzgl zzgl);

    /* access modifiers changed from: protected */
    public boolean zzh(zzqw zzqw) {
        return zzqw.zzbC().zzzz;
    }

    /* access modifiers changed from: protected */
    public boolean zzs(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        zzt.zzzg();
        return applicationInfo == null || applicationInfo.targetSdkVersion >= 11;
    }
}
