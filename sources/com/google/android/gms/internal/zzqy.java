package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.zzw;
import java.util.concurrent.Callable;

@zzme
public class zzqy {
    public zzqw zza(Context context, zzeg zzeg, boolean z, boolean z2, @Nullable zzaw zzaw, zzqh zzqh) {
        final Context context2 = context;
        final zzeg zzeg2 = zzeg;
        final boolean z3 = z;
        final boolean z4 = z2;
        final zzaw zzaw2 = zzaw;
        final zzqh zzqh2 = zzqh;
        return (zzqw) zzqb.zzb(new Callable<zzqw>() {
            /* renamed from: zzma */
            public zzqw call() {
                return zzqy.this.zza(context2, zzeg2, z3, z4, zzaw2, zzqh2, (zzgl) null, (zzu) null, (zze) null);
            }
        });
    }

    public zzqw zza(Context context, zzeg zzeg, boolean z, boolean z2, @Nullable zzaw zzaw, zzqh zzqh, zzgl zzgl, zzu zzu, zze zze) {
        final Context context2 = context;
        final zzeg zzeg2 = zzeg;
        final boolean z3 = z;
        final boolean z4 = z2;
        final zzaw zzaw2 = zzaw;
        final zzqh zzqh2 = zzqh;
        final zzgl zzgl2 = zzgl;
        final zzu zzu2 = zzu;
        final zze zze2 = zze;
        return (zzqw) zzqb.zzb(new Callable<zzqw>(this) {
            /* renamed from: zzma */
            public zzqw call() {
                zzqz zzqz = new zzqz(zzra.zzb(context2, zzeg2, z3, z4, zzaw2, zzqh2, zzgl2, zzu2, zze2));
                zzqz.setWebViewClient(zzw.zzcO().zzb((zzqw) zzqz, z4));
                zzqz.setWebChromeClient(zzw.zzcO().zzn(zzqz));
                return zzqz;
            }
        });
    }
}
