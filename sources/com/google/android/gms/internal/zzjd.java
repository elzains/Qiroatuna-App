package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzm;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzet;
import com.google.android.gms.internal.zzjb;

@zzme
public class zzjd extends zzet.zza {
    private final zziw zzJc;
    @Nullable
    private zzm zzJh;
    private final zziy zzJu;
    @Nullable
    private zzli zzJv;
    private String zzJw;
    private final String zzts;

    public zzjd(Context context, String str, zzka zzka, zzqh zzqh, zze zze) {
        this(str, new zziw(context, zzka, zzqh, zze));
    }

    zzjd(String str, zziw zziw) {
        this.zzts = str;
        this.zzJc = zziw;
        this.zzJu = new zziy();
        zzw.zzdb().zza(zziw);
    }

    private void zzgK() {
        if (this.zzJh != null && this.zzJv != null) {
            this.zzJh.zza(this.zzJv, this.zzJw);
        }
    }

    static boolean zzp(zzec zzec) {
        return zziz.zzj(zzec).contains("gw");
    }

    static boolean zzq(zzec zzec) {
        return zziz.zzj(zzec).contains("_ad");
    }

    /* access modifiers changed from: package-private */
    public void abort() {
        if (this.zzJh == null) {
            this.zzJh = this.zzJc.zzag(this.zzts);
            this.zzJu.zzc(this.zzJh);
            zzgK();
        }
    }

    public void destroy() throws RemoteException {
        if (this.zzJh != null) {
            this.zzJh.destroy();
        }
    }

    @Nullable
    public String getMediationAdapterClassName() throws RemoteException {
        if (this.zzJh != null) {
            return this.zzJh.getMediationAdapterClassName();
        }
        return null;
    }

    public boolean isLoading() throws RemoteException {
        return this.zzJh != null && this.zzJh.isLoading();
    }

    public boolean isReady() throws RemoteException {
        return this.zzJh != null && this.zzJh.isReady();
    }

    public void pause() throws RemoteException {
        if (this.zzJh != null) {
            this.zzJh.pause();
        }
    }

    public void resume() throws RemoteException {
        if (this.zzJh != null) {
            this.zzJh.resume();
        }
    }

    public void setManualImpressionsEnabled(boolean z) throws RemoteException {
        abort();
        if (this.zzJh != null) {
            this.zzJh.setManualImpressionsEnabled(z);
        }
    }

    public void setUserId(String str) {
    }

    public void showInterstitial() throws RemoteException {
        if (this.zzJh != null) {
            this.zzJh.showInterstitial();
        } else {
            zzpk.zzbh("Interstitial ad must be loaded before showInterstitial().");
        }
    }

    public void stopLoading() throws RemoteException {
        if (this.zzJh != null) {
            this.zzJh.stopLoading();
        }
    }

    public void zza(zzeg zzeg) throws RemoteException {
        if (this.zzJh != null) {
            this.zzJh.zza(zzeg);
        }
    }

    public void zza(zzeo zzeo) throws RemoteException {
        this.zzJu.zzIY = zzeo;
        if (this.zzJh != null) {
            this.zzJu.zzc(this.zzJh);
        }
    }

    public void zza(zzep zzep) throws RemoteException {
        this.zzJu.zztk = zzep;
        if (this.zzJh != null) {
            this.zzJu.zzc(this.zzJh);
        }
    }

    public void zza(zzev zzev) throws RemoteException {
        this.zzJu.zzIV = zzev;
        if (this.zzJh != null) {
            this.zzJu.zzc(this.zzJh);
        }
    }

    public void zza(zzex zzex) throws RemoteException {
        abort();
        if (this.zzJh != null) {
            this.zzJh.zza(zzex);
        }
    }

    public void zza(zzfc zzfc) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzft zzft) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public void zza(zzgp zzgp) throws RemoteException {
        this.zzJu.zzIX = zzgp;
        if (this.zzJh != null) {
            this.zzJu.zzc(this.zzJh);
        }
    }

    public void zza(zzle zzle) throws RemoteException {
        this.zzJu.zzIW = zzle;
        if (this.zzJh != null) {
            this.zzJu.zzc(this.zzJh);
        }
    }

    public void zza(zzli zzli, String str) throws RemoteException {
        this.zzJv = zzli;
        this.zzJw = str;
        zzgK();
    }

    public void zza(zznw zznw) {
        this.zzJu.zzIZ = zznw;
        if (this.zzJh != null) {
            this.zzJu.zzc(this.zzJh);
        }
    }

    public boolean zzb(zzec zzec) throws RemoteException {
        if (!zzp(zzec)) {
            abort();
        }
        if (zziz.zzl(zzec)) {
            abort();
        }
        if (zzec.zzza != null) {
            abort();
        }
        if (this.zzJh != null) {
            return this.zzJh.zzb(zzec);
        }
        zziz zzdb = zzw.zzdb();
        if (zzq(zzec)) {
            zzdb.zzb(zzec, this.zzts);
        }
        zzjb.zza zza = zzdb.zza(zzec, this.zzts);
        if (zza != null) {
            if (!zza.zzJl) {
                zza.load();
                zzjc.zzgC().zzgG();
            } else {
                zzjc.zzgC().zzgF();
            }
            this.zzJh = zza.zzJh;
            zza.zzJj.zza(this.zzJu);
            this.zzJu.zzc(this.zzJh);
            zzgK();
            return zza.zzJm;
        }
        abort();
        zzjc.zzgC().zzgG();
        return this.zzJh.zzb(zzec);
    }

    @Nullable
    public IObjectWrapper zzbB() throws RemoteException {
        if (this.zzJh != null) {
            return this.zzJh.zzbB();
        }
        return null;
    }

    @Nullable
    public zzeg zzbC() throws RemoteException {
        if (this.zzJh != null) {
            return this.zzJh.zzbC();
        }
        return null;
    }

    public void zzbE() throws RemoteException {
        if (this.zzJh != null) {
            this.zzJh.zzbE();
        } else {
            zzpk.zzbh("Interstitial ad must be loaded before pingManualTrackingUrl().");
        }
    }

    public zzfa zzbF() {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }
}
