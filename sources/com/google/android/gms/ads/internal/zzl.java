package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.p000v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzeq;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzhc;
import com.google.android.gms.internal.zzhp;
import com.google.android.gms.internal.zzhq;
import com.google.android.gms.internal.zzhr;
import com.google.android.gms.internal.zzhs;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzqh;

@zzme
public class zzl extends zzer.zza {
    private final Context mContext;
    private final zze zzsv;
    private final zzka zzsz;
    private SimpleArrayMap<String, zzhs> zztA = new SimpleArrayMap<>();
    private zzep zztk;
    private zzhc zztp;
    private zzex zztr;
    private final String zzts;
    private final zzqh zztt;
    private zzhp zztx;
    private zzhq zzty;
    private SimpleArrayMap<String, zzhr> zztz = new SimpleArrayMap<>();

    public zzl(Context context, String str, zzka zzka, zzqh zzqh, zze zze) {
        this.mContext = context;
        this.zzts = str;
        this.zzsz = zzka;
        this.zztt = zzqh;
        this.zzsv = zze;
    }

    public void zza(zzhc zzhc) {
        this.zztp = zzhc;
    }

    public void zza(zzhp zzhp) {
        this.zztx = zzhp;
    }

    public void zza(zzhq zzhq) {
        this.zzty = zzhq;
    }

    public void zza(String str, zzhs zzhs, zzhr zzhr) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
        }
        this.zztA.put(str, zzhs);
        this.zztz.put(str, zzhr);
    }

    public void zzb(zzep zzep) {
        this.zztk = zzep;
    }

    public void zzb(zzex zzex) {
        this.zztr = zzex;
    }

    public zzeq zzck() {
        return new zzk(this.mContext, this.zzts, this.zzsz, this.zztt, this.zztk, this.zztx, this.zzty, this.zztA, this.zztz, this.zztp, this.zztr, this.zzsv);
    }
}
