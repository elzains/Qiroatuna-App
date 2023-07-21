package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzco;

@zzme
public final class zzcm extends zzco.zza {
    private final zzav zzrO;
    private final zzaw zzrP;
    private final zzat zzrQ;
    private boolean zzrR = false;

    public zzcm(String str, Context context, boolean z) {
        this.zzrO = zzav.zza(str, context, z);
        this.zzrP = new zzaw(this.zzrO);
        this.zzrQ = z ? null : zzat.zzc(context);
    }

    private IObjectWrapper zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, boolean z) {
        try {
            Uri uri = (Uri) zzd.zzF(iObjectWrapper);
            Context context = (Context) zzd.zzF(iObjectWrapper2);
            return zzd.zzA(z ? this.zzrP.zza(uri, context) : this.zzrP.zzb(uri, context));
        } catch (zzax e) {
            return null;
        }
    }

    public IObjectWrapper zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return zza(iObjectWrapper, iObjectWrapper2, true);
    }

    public String zza(IObjectWrapper iObjectWrapper, String str) {
        return this.zzrO.zzb((Context) zzd.zzF(iObjectWrapper), str);
    }

    public String zza(IObjectWrapper iObjectWrapper, byte[] bArr) {
        Context context = (Context) zzd.zzF(iObjectWrapper);
        String zza = this.zzrO.zza(context, bArr);
        if (this.zzrQ == null || !this.zzrR) {
            return zza;
        }
        String zza2 = this.zzrQ.zza(zza, this.zzrQ.zza(context, bArr));
        this.zzrR = false;
        return zza2;
    }

    public boolean zza(IObjectWrapper iObjectWrapper) {
        return this.zzrP.zza((Uri) zzd.zzF(iObjectWrapper));
    }

    public IObjectWrapper zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return zza(iObjectWrapper, iObjectWrapper2, false);
    }

    public void zzb(String str, String str2) {
        this.zzrP.zzb(str, str2);
    }

    public boolean zzb(IObjectWrapper iObjectWrapper) {
        return this.zzrP.zzc((Uri) zzd.zzF(iObjectWrapper));
    }

    public boolean zzb(String str, boolean z) {
        if (this.zzrQ == null) {
            return false;
        }
        this.zzrQ.zza(new AdvertisingIdClient.Info(str, z));
        this.zzrR = true;
        return true;
    }

    public String zzbt() {
        return "ms";
    }

    public String zzc(IObjectWrapper iObjectWrapper) {
        return zza(iObjectWrapper, (byte[]) null);
    }

    public void zzd(IObjectWrapper iObjectWrapper) {
        this.zzrP.zza((MotionEvent) zzd.zzF(iObjectWrapper));
    }

    public void zzm(String str) {
        this.zzrP.zzm(str);
    }
}
