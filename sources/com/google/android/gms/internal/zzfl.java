package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzet;

public class zzfl extends zzet.zza {
    /* access modifiers changed from: private */
    public zzep zztk;

    public void destroy() {
    }

    public String getMediationAdapterClassName() {
        return null;
    }

    public boolean isLoading() {
        return false;
    }

    public boolean isReady() {
        return false;
    }

    public void pause() {
    }

    public void resume() {
    }

    public void setManualImpressionsEnabled(boolean z) {
    }

    public void setUserId(String str) {
    }

    public void showInterstitial() {
    }

    public void stopLoading() {
    }

    public void zza(zzeg zzeg) {
    }

    public void zza(zzeo zzeo) {
    }

    public void zza(zzep zzep) {
        this.zztk = zzep;
    }

    public void zza(zzev zzev) {
    }

    public void zza(zzex zzex) {
    }

    public void zza(zzfc zzfc) {
    }

    public void zza(zzft zzft) {
    }

    public void zza(zzgp zzgp) {
    }

    public void zza(zzle zzle) {
    }

    public void zza(zzli zzli, String str) {
    }

    public void zza(zznw zznw) {
    }

    public boolean zzb(zzec zzec) {
        zzqf.m20e("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                if (zzfl.this.zztk != null) {
                    try {
                        zzfl.this.zztk.onAdFailedToLoad(1);
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not notify onAdFailedToLoad event.", e);
                    }
                }
            }
        });
        return false;
    }

    public IObjectWrapper zzbB() {
        return null;
    }

    public zzeg zzbC() {
        return null;
    }

    public void zzbE() {
    }

    public zzfa zzbF() {
        return null;
    }
}
