package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.internal.zzqf;

@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    CustomEventBanner zzaaR;
    CustomEventInterstitial zzaaS;
    CustomEventNative zzaaT;
    private View zzcW;

    static final class zza implements CustomEventBannerListener {
        private final CustomEventAdapter zzaaU;
        private final MediationBannerListener zzcO;

        public zza(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.zzaaU = customEventAdapter;
            this.zzcO = mediationBannerListener;
        }

        public void onAdClicked() {
            zzqf.zzbf("Custom event adapter called onAdClicked.");
            this.zzcO.onAdClicked(this.zzaaU);
        }

        public void onAdClosed() {
            zzqf.zzbf("Custom event adapter called onAdClosed.");
            this.zzcO.onAdClosed(this.zzaaU);
        }

        public void onAdFailedToLoad(int i) {
            zzqf.zzbf("Custom event adapter called onAdFailedToLoad.");
            this.zzcO.onAdFailedToLoad(this.zzaaU, i);
        }

        public void onAdLeftApplication() {
            zzqf.zzbf("Custom event adapter called onAdLeftApplication.");
            this.zzcO.onAdLeftApplication(this.zzaaU);
        }

        public void onAdLoaded(View view) {
            zzqf.zzbf("Custom event adapter called onAdLoaded.");
            this.zzaaU.zza(view);
            this.zzcO.onAdLoaded(this.zzaaU);
        }

        public void onAdOpened() {
            zzqf.zzbf("Custom event adapter called onAdOpened.");
            this.zzcO.onAdOpened(this.zzaaU);
        }
    }

    class zzb implements CustomEventInterstitialListener {
        private final CustomEventAdapter zzaaU;
        private final MediationInterstitialListener zzcP;

        public zzb(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.zzaaU = customEventAdapter;
            this.zzcP = mediationInterstitialListener;
        }

        public void onAdClicked() {
            zzqf.zzbf("Custom event adapter called onAdClicked.");
            this.zzcP.onAdClicked(this.zzaaU);
        }

        public void onAdClosed() {
            zzqf.zzbf("Custom event adapter called onAdClosed.");
            this.zzcP.onAdClosed(this.zzaaU);
        }

        public void onAdFailedToLoad(int i) {
            zzqf.zzbf("Custom event adapter called onFailedToReceiveAd.");
            this.zzcP.onAdFailedToLoad(this.zzaaU, i);
        }

        public void onAdLeftApplication() {
            zzqf.zzbf("Custom event adapter called onAdLeftApplication.");
            this.zzcP.onAdLeftApplication(this.zzaaU);
        }

        public void onAdLoaded() {
            zzqf.zzbf("Custom event adapter called onReceivedAd.");
            this.zzcP.onAdLoaded(CustomEventAdapter.this);
        }

        public void onAdOpened() {
            zzqf.zzbf("Custom event adapter called onAdOpened.");
            this.zzcP.onAdOpened(this.zzaaU);
        }
    }

    static class zzc implements CustomEventNativeListener {
        private final CustomEventAdapter zzaaU;
        private final MediationNativeListener zzcQ;

        public zzc(CustomEventAdapter customEventAdapter, MediationNativeListener mediationNativeListener) {
            this.zzaaU = customEventAdapter;
            this.zzcQ = mediationNativeListener;
        }

        public void onAdClicked() {
            zzqf.zzbf("Custom event adapter called onAdClicked.");
            this.zzcQ.onAdClicked(this.zzaaU);
        }

        public void onAdClosed() {
            zzqf.zzbf("Custom event adapter called onAdClosed.");
            this.zzcQ.onAdClosed(this.zzaaU);
        }

        public void onAdFailedToLoad(int i) {
            zzqf.zzbf("Custom event adapter called onAdFailedToLoad.");
            this.zzcQ.onAdFailedToLoad(this.zzaaU, i);
        }

        public void onAdImpression() {
            zzqf.zzbf("Custom event adapter called onAdImpression.");
            this.zzcQ.onAdImpression(this.zzaaU);
        }

        public void onAdLeftApplication() {
            zzqf.zzbf("Custom event adapter called onAdLeftApplication.");
            this.zzcQ.onAdLeftApplication(this.zzaaU);
        }

        public void onAdLoaded(NativeAdMapper nativeAdMapper) {
            zzqf.zzbf("Custom event adapter called onAdLoaded.");
            this.zzcQ.onAdLoaded(this.zzaaU, nativeAdMapper);
        }

        public void onAdOpened() {
            zzqf.zzbf("Custom event adapter called onAdOpened.");
            this.zzcQ.onAdOpened(this.zzaaU);
        }
    }

    /* access modifiers changed from: private */
    public void zza(View view) {
        this.zzcW = view;
    }

    private static <T> T zzj(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (Throwable th) {
            String valueOf = String.valueOf(th.getMessage());
            zzqf.zzbh(new StringBuilder(String.valueOf(str).length() + 46 + String.valueOf(valueOf).length()).append("Could not instantiate custom event adapter: ").append(str).append(". ").append(valueOf).toString());
            return null;
        }
    }

    public View getBannerView() {
        return this.zzcW;
    }

    public void onDestroy() {
        if (this.zzaaR != null) {
            this.zzaaR.onDestroy();
        }
        if (this.zzaaS != null) {
            this.zzaaS.onDestroy();
        }
        if (this.zzaaT != null) {
            this.zzaaT.onDestroy();
        }
    }

    public void onPause() {
        if (this.zzaaR != null) {
            this.zzaaR.onPause();
        }
        if (this.zzaaS != null) {
            this.zzaaS.onPause();
        }
        if (this.zzaaT != null) {
            this.zzaaT.onPause();
        }
    }

    public void onResume() {
        if (this.zzaaR != null) {
            this.zzaaR.onResume();
        }
        if (this.zzaaS != null) {
            this.zzaaS.onResume();
        }
        if (this.zzaaT != null) {
            this.zzaaT.onResume();
        }
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzaaR = (CustomEventBanner) zzj(bundle.getString("class_name"));
        if (this.zzaaR == null) {
            mediationBannerListener.onAdFailedToLoad(this, 0);
        } else {
            this.zzaaR.requestBannerAd(context, new zza(this, mediationBannerListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), adSize, mediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzaaS = (CustomEventInterstitial) zzj(bundle.getString("class_name"));
        if (this.zzaaS == null) {
            mediationInterstitialListener.onAdFailedToLoad(this, 0);
        } else {
            this.zzaaS.requestInterstitialAd(context, zza(mediationInterstitialListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), mediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    public void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        this.zzaaT = (CustomEventNative) zzj(bundle.getString("class_name"));
        if (this.zzaaT == null) {
            mediationNativeListener.onAdFailedToLoad(this, 0);
        } else {
            this.zzaaT.requestNativeAd(context, new zzc(this, mediationNativeListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), nativeMediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    public void showInterstitial() {
        this.zzaaS.showInterstitial();
    }

    /* access modifiers changed from: package-private */
    public zzb zza(MediationInterstitialListener mediationInterstitialListener) {
        return new zzb(this, mediationInterstitialListener);
    }
}
