package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzw;

@zzme
public final class zzpy extends zzpj {
    private final String zzE;
    private final zzqg zzYD;

    public zzpy(Context context, String str, String str2) {
        this(str2, zzw.zzcM().zzu(context, str));
    }

    public zzpy(String str, String str2) {
        this.zzYD = new zzqg(str2);
        this.zzE = str;
    }

    public void onStop() {
    }

    public void zzco() {
        this.zzYD.zzu(this.zzE);
    }
}
