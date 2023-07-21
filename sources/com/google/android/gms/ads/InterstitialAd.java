package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zzdx;
import com.google.android.gms.internal.zzfg;

public final class InterstitialAd {
    private final zzfg zzrH;

    public InterstitialAd(Context context) {
        this.zzrH = new zzfg(context);
    }

    public AdListener getAdListener() {
        return this.zzrH.getAdListener();
    }

    public String getAdUnitId() {
        return this.zzrH.getAdUnitId();
    }

    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.zzrH.getInAppPurchaseListener();
    }

    public String getMediationAdapterClassName() {
        return this.zzrH.getMediationAdapterClassName();
    }

    public boolean isLoaded() {
        return this.zzrH.isLoaded();
    }

    public boolean isLoading() {
        return this.zzrH.isLoading();
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(AdRequest adRequest) {
        this.zzrH.zza(adRequest.zzbp());
    }

    public void setAdListener(AdListener adListener) {
        this.zzrH.setAdListener(adListener);
        if (adListener != null && (adListener instanceof zzdx)) {
            this.zzrH.zza((zzdx) adListener);
        } else if (adListener == null) {
            this.zzrH.zza((zzdx) null);
        }
    }

    public void setAdUnitId(String str) {
        this.zzrH.setAdUnitId(str);
    }

    public void setInAppPurchaseListener(InAppPurchaseListener inAppPurchaseListener) {
        this.zzrH.setInAppPurchaseListener(inAppPurchaseListener);
    }

    public void setPlayStorePurchaseParams(PlayStorePurchaseListener playStorePurchaseListener, String str) {
        this.zzrH.setPlayStorePurchaseParams(playStorePurchaseListener, str);
    }

    public void setRewardedVideoAdListener(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzrH.setRewardedVideoAdListener(rewardedVideoAdListener);
    }

    public void show() {
        this.zzrH.show();
    }

    public void zzd(boolean z) {
        this.zzrH.zzd(z);
    }
}
