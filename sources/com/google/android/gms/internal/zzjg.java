package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.internal.zzjf;
import java.util.concurrent.Future;

@zzme
public class zzjg {

    private static class zza<JavascriptEngine> extends zzqj<JavascriptEngine> {
        JavascriptEngine zzJD;

        private zza() {
        }
    }

    /* access modifiers changed from: private */
    public zzjf zza(Context context, zzqh zzqh, final zza<zzjf> zza2, zzaw zzaw, zze zze) {
        JavascriptEngine zzjh = new zzjh(context, zzqh, zzaw, zze);
        zza2.zzJD = zzjh;
        zzjh.zza(new zzjf.zza(this) {
            public void zzgN() {
                zza2.zzh((zzjf) zza2.zzJD);
            }
        });
        return zzjh;
    }

    public Future<zzjf> zza(Context context, zzqh zzqh, String str, zzaw zzaw, zze zze) {
        final zza zza2 = new zza();
        final Context context2 = context;
        final zzqh zzqh2 = zzqh;
        final zzaw zzaw2 = zzaw;
        final zze zze2 = zze;
        final String str2 = str;
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                zzjg.this.zza(context2, zzqh2, (zza<zzjf>) zza2, zzaw2, zze2).zzan(str2);
            }
        });
        return zza2;
    }
}
