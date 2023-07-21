package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@zzme
public class zzgk {
    private final Map<String, zzgj> zzFN = new HashMap();
    @Nullable
    private final zzgl zzsn;

    public zzgk(@Nullable zzgl zzgl) {
        this.zzsn = zzgl;
    }

    public void zza(String str, zzgj zzgj) {
        this.zzFN.put(str, zzgj);
    }

    public void zza(String str, String str2, long j) {
        zzgh.zza(this.zzsn, this.zzFN.get(str2), j, str);
        this.zzFN.put(str, zzgh.zza(this.zzsn, j));
    }

    @Nullable
    public zzgl zzfA() {
        return this.zzsn;
    }
}
