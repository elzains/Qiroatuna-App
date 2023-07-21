package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zznu;

@zzme
public class zznr extends zznu.zza {
    private final Context mContext;
    private final zzns zzVj;
    private final Object zzrJ;
    private final zzqh zztt;

    public zznr(Context context, zze zze, zzka zzka, zzqh zzqh) {
        this(context, zzqh, new zzns(context, zze, zzeg.zzeE(), zzka, zzqh));
    }

    zznr(Context context, zzqh zzqh, zzns zzns) {
        this.zzrJ = new Object();
        this.mContext = context;
        this.zztt = zzqh;
        this.zzVj = zzns;
    }

    public void destroy() {
        zzh((IObjectWrapper) null);
    }

    public boolean isLoaded() {
        boolean isLoaded;
        synchronized (this.zzrJ) {
            isLoaded = this.zzVj.isLoaded();
        }
        return isLoaded;
    }

    public void pause() {
        zzf((IObjectWrapper) null);
    }

    public void resume() {
        zzg((IObjectWrapper) null);
    }

    public void setUserId(String str) {
        zzpk.zzbh("RewardedVideoAd.setUserId() is deprecated. Please do not call this method.");
    }

    public void show() {
        synchronized (this.zzrJ) {
            this.zzVj.zzjF();
        }
    }

    public void zza(zznw zznw) {
        synchronized (this.zzrJ) {
            this.zzVj.zza(zznw);
        }
    }

    public void zza(zzoa zzoa) {
        synchronized (this.zzrJ) {
            this.zzVj.zza(zzoa);
        }
    }

    public void zzf(IObjectWrapper iObjectWrapper) {
        synchronized (this.zzrJ) {
            this.zzVj.pause();
        }
    }

    public void zzg(IObjectWrapper iObjectWrapper) {
        synchronized (this.zzrJ) {
            Context context = iObjectWrapper == null ? null : (Context) zzd.zzF(iObjectWrapper);
            if (context != null) {
                try {
                    this.zzVj.onContextChanged(context);
                } catch (Exception e) {
                    zzpk.zzc("Unable to extract updated context.", e);
                }
            }
            this.zzVj.resume();
        }
    }

    public void zzh(IObjectWrapper iObjectWrapper) {
        synchronized (this.zzrJ) {
            this.zzVj.destroy();
        }
    }
}
