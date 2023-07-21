package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzgl;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzqw;

@zzme
public class zzo extends zzk {
    @Nullable
    public zzj zza(Context context, zzqw zzqw, int i, boolean z, zzgl zzgl) {
        if (!zzs(context)) {
            return null;
        }
        return new zzd(context, z, zzh(zzqw), new zzz(context, zzqw.zzly(), zzqw.getRequestId(), zzgl, zzqw.zzlE()));
    }
}
