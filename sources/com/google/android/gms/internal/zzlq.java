package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.internal.zzpb;

@zzme
public class zzlq {

    public interface zza {
        void zzb(zzpb zzpb);
    }

    public zzpq zza(Context context, com.google.android.gms.ads.internal.zza zza2, zzpb.zza zza3, zzaw zzaw, @Nullable zzqw zzqw, zzka zzka, zza zza4, zzgl zzgl) {
        zzmn zzmn = zza3.zzWm;
        zzpq zzlu = zzmn.zzSn ? new zzlu(context, zza3, zzka, zza4, zzgl, zzqw) : (zzmn.zzzB || (zza2 instanceof zzs)) ? (!zzmn.zzzB || !(zza2 instanceof zzs)) ? new zzls(zza3, zza4) : new zzlv(context, (zzs) zza2, zza3, zzaw, zza4, zzgl) : (!zzgd.zzCu.get().booleanValue() || !zzt.zzzl() || zzt.zzzn() || zzqw == null || !zzqw.zzbC().zzzz) ? new zzlr(context, zza3, zzqw, zza4) : new zzlt(context, zza3, zzqw, zza4);
        String valueOf = String.valueOf(zzlu.getClass().getName());
        zzpk.zzbf(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
        zzlu.zziP();
        return zzlu;
    }

    public zzpq zza(Context context, zzpb.zza zza2, zzns zzns) {
        zzok zzok = new zzok(context, zza2, zzns);
        String valueOf = String.valueOf(zzok.getClass().getName());
        zzpk.zzbf(valueOf.length() != 0 ? "AdRenderer: ".concat(valueOf) : new String("AdRenderer: "));
        zzok.zziP();
        return zzok;
    }
}
