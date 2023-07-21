package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzqx;

@zzme
public class zzlr extends zzlo implements zzqx.zza {
    zzlr(Context context, zzpb.zza zza, zzqw zzqw, zzlq.zza zza2) {
        super(context, zza, zzqw, zza2);
    }

    /* access modifiers changed from: protected */
    public void zziO() {
        if (this.zzPS.errorCode == -2) {
            this.zzIs.zzlv().zza((zzqx.zza) this);
            zziQ();
            zzpk.zzbf("Loading HTML in WebView.");
            this.zzIs.loadDataWithBaseURL(this.zzPS.zzNJ, this.zzPS.body, "text/html", "UTF-8", (String) null);
        }
    }

    /* access modifiers changed from: protected */
    public void zziQ() {
    }
}
