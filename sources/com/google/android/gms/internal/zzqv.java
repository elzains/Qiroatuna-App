package com.google.android.gms.internal;

import android.content.Context;
import android.view.ViewGroup;
import com.google.android.gms.ads.internal.overlay.zzl;
import com.google.android.gms.common.internal.zzac;

@zzme
public class zzqv {
    private final Context mContext;
    private final zzqw zzIs;
    private zzl zzPi;
    private final ViewGroup zzZu;

    public zzqv(Context context, ViewGroup viewGroup, zzqw zzqw) {
        this(context, viewGroup, zzqw, (zzl) null);
    }

    zzqv(Context context, ViewGroup viewGroup, zzqw zzqw, zzl zzl) {
        this.mContext = context;
        this.zzZu = viewGroup;
        this.zzIs = zzqw;
        this.zzPi = zzl;
    }

    public void onDestroy() {
        zzac.zzdj("onDestroy must be called from the UI thread.");
        if (this.zzPi != null) {
            this.zzPi.destroy();
            this.zzZu.removeView(this.zzPi);
            this.zzPi = null;
        }
    }

    public void onPause() {
        zzac.zzdj("onPause must be called from the UI thread.");
        if (this.zzPi != null) {
            this.zzPi.pause();
        }
    }

    public void zza(int i, int i2, int i3, int i4, int i5, boolean z) {
        if (this.zzPi == null) {
            zzgh.zza(this.zzIs.zzlF().zzfA(), this.zzIs.zzlE(), "vpr2");
            this.zzPi = new zzl(this.mContext, this.zzIs, i5, z, this.zzIs.zzlF().zzfA());
            this.zzZu.addView(this.zzPi, 0, new ViewGroup.LayoutParams(-1, -1));
            this.zzPi.zzd(i, i2, i3, i4);
            this.zzIs.zzlv().zzO(false);
        }
    }

    public void zze(int i, int i2, int i3, int i4) {
        zzac.zzdj("The underlay may only be modified from the UI thread.");
        if (this.zzPi != null) {
            this.zzPi.zzd(i, i2, i3, i4);
        }
    }

    public zzl zzlo() {
        zzac.zzdj("getAdVideoUnderlay must be called from the UI thread.");
        return this.zzPi;
    }
}
