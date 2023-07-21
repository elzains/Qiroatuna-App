package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;

@zzme
public class zzgg {
    @Nullable
    public zzgf zza(@Nullable zzge zzge) {
        if (zzge == null) {
            throw new IllegalArgumentException("CSI configuration can't be null. ");
        } else if (!zzge.zzfu()) {
            zzpk.m19v("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
            return null;
        } else if (zzge.getContext() == null) {
            throw new IllegalArgumentException("Context can't be null. Please set up context in CsiConfiguration.");
        } else if (!TextUtils.isEmpty(zzge.zzdA())) {
            return new zzgf(zzge.getContext(), zzge.zzdA(), zzge.zzfv(), zzge.zzfw());
        } else {
            throw new IllegalArgumentException("AfmaVersion can't be null or empty. Please set up afmaVersion in CsiConfiguration.");
        }
    }
}
