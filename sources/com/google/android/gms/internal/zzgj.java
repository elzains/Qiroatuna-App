package com.google.android.gms.internal;

import android.support.annotation.Nullable;

@zzme
public class zzgj {
    private final long zzFK;
    @Nullable
    private final String zzFL;
    @Nullable
    private final zzgj zzFM;

    public zzgj(long j, @Nullable String str, @Nullable zzgj zzgj) {
        this.zzFK = j;
        this.zzFL = str;
        this.zzFM = zzgj;
    }

    /* access modifiers changed from: package-private */
    public long getTime() {
        return this.zzFK;
    }

    /* access modifiers changed from: package-private */
    public String zzfy() {
        return this.zzFL;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public zzgj zzfz() {
        return this.zzFM;
    }
}
