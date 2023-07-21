package com.google.android.gms.internal;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzom;

@zzme
public class zzoi extends zzom.zza {
    private volatile zzog zzVE;
    private volatile zzoh zzVF;
    private volatile zzoj zzVs;

    public zzoi(zzoh zzoh) {
        this.zzVF = zzoh;
    }

    public void zza(IObjectWrapper iObjectWrapper, zzoo zzoo) {
        if (this.zzVF != null) {
            this.zzVF.zzc(zzoo);
        }
    }

    public void zza(zzog zzog) {
        this.zzVE = zzog;
    }

    public void zza(zzoj zzoj) {
        this.zzVs = zzoj;
    }

    public void zzc(IObjectWrapper iObjectWrapper, int i) {
        if (this.zzVE != null) {
            this.zzVE.zzad(i);
        }
    }

    public void zzd(IObjectWrapper iObjectWrapper, int i) {
        if (this.zzVs != null) {
            this.zzVs.zza(zzd.zzF(iObjectWrapper).getClass().getName(), i);
        }
    }

    public void zzr(IObjectWrapper iObjectWrapper) {
        if (this.zzVE != null) {
            this.zzVE.zzjJ();
        }
    }

    public void zzs(IObjectWrapper iObjectWrapper) {
        if (this.zzVs != null) {
            this.zzVs.zzaO(zzd.zzF(iObjectWrapper).getClass().getName());
        }
    }

    public void zzt(IObjectWrapper iObjectWrapper) {
        if (this.zzVF != null) {
            this.zzVF.onRewardedVideoAdOpened();
        }
    }

    public void zzu(IObjectWrapper iObjectWrapper) {
        if (this.zzVF != null) {
            this.zzVF.onRewardedVideoStarted();
        }
    }

    public void zzv(IObjectWrapper iObjectWrapper) {
        if (this.zzVF != null) {
            this.zzVF.onRewardedVideoAdClosed();
        }
    }

    public void zzw(IObjectWrapper iObjectWrapper) {
        if (this.zzVF != null) {
            this.zzVF.zzjG();
        }
    }

    public void zzx(IObjectWrapper iObjectWrapper) {
        if (this.zzVF != null) {
            this.zzVF.onRewardedVideoAdLeftApplication();
        }
    }
}
