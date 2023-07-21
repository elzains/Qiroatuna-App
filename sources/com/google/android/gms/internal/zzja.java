package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzd;
import java.util.Arrays;

@zzme
class zzja {
    private final Object[] mParams;

    zzja(zzec zzec, String str, int i) {
        this.mParams = zzd.zza(zzgd.zzDe.get(), zzec, str, i, (zzeg) null);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzja)) {
            return false;
        }
        return Arrays.equals(this.mParams, ((zzja) obj).mParams);
    }

    public int hashCode() {
        return Arrays.hashCode(this.mParams);
    }

    public String toString() {
        String valueOf = String.valueOf(Arrays.toString(this.mParams));
        return new StringBuilder(String.valueOf(valueOf).length() + 24).append("[InterstitialAdPoolKey ").append(valueOf).append("]").toString();
    }
}
