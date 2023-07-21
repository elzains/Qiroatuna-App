package com.google.android.gms.internal;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.dynamic.zzd;

@zzme
public class zzgn implements CustomRenderedAd {
    private final zzgo zzFW;

    public zzgn(zzgo zzgo) {
        this.zzFW = zzgo;
    }

    public String getBaseUrl() {
        try {
            return this.zzFW.zzfG();
        } catch (RemoteException e) {
            zzqf.zzc("Could not delegate getBaseURL to CustomRenderedAd", e);
            return null;
        }
    }

    public String getContent() {
        try {
            return this.zzFW.getContent();
        } catch (RemoteException e) {
            zzqf.zzc("Could not delegate getContent to CustomRenderedAd", e);
            return null;
        }
    }

    public void onAdRendered(View view) {
        try {
            this.zzFW.zzi(view != null ? zzd.zzA(view) : null);
        } catch (RemoteException e) {
            zzqf.zzc("Could not delegate onAdRendered to CustomRenderedAd", e);
        }
    }

    public void recordClick() {
        try {
            this.zzFW.recordClick();
        } catch (RemoteException e) {
            zzqf.zzc("Could not delegate recordClick to CustomRenderedAd", e);
        }
    }

    public void recordImpression() {
        try {
            this.zzFW.recordImpression();
        } catch (RemoteException e) {
            zzqf.zzc("Could not delegate recordImpression to CustomRenderedAd", e);
        }
    }
}
