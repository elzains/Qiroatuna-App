package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzm;

@zzme
public class zziw {
    private final Context mContext;
    private final zze zzsv;
    private final zzka zzsz;
    private final zzqh zztt;

    zziw(Context context, zzka zzka, zzqh zzqh, zze zze) {
        this.mContext = context;
        this.zzsz = zzka;
        this.zztt = zzqh;
        this.zzsv = zze;
    }

    public Context getApplicationContext() {
        return this.mContext.getApplicationContext();
    }

    public zzm zzag(String str) {
        return new zzm(this.mContext, new zzeg(), str, this.zzsz, this.zztt, this.zzsv);
    }

    public zzm zzah(String str) {
        return new zzm(this.mContext.getApplicationContext(), new zzeg(), str, this.zzsz, this.zztt, this.zzsv);
    }

    public zziw zzgu() {
        return new zziw(getApplicationContext(), this.zzsz, this.zztt, this.zzsv);
    }
}
