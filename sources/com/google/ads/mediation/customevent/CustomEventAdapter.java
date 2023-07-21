package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.internal.zzqf;

@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter<CustomEventExtras, CustomEventServerParameters>, MediationInterstitialAdapter<CustomEventExtras, CustomEventServerParameters> {
    private View zzcW;
    CustomEventBanner zzcX;
    CustomEventInterstitial zzcY;

    static final class zza implements CustomEventBannerListener {
        private final CustomEventAdapter zzcZ;
        private final MediationBannerListener zzda;

        public zza(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.zzcZ = customEventAdapter;
            this.zzda = mediationBannerListener;
        }

        public void onClick() {
            zzqf.zzbf("Custom event adapter called onFailedToReceiveAd.");
            this.zzda.onClick(this.zzcZ);
        }

        public void onDismissScreen() {
            zzqf.zzbf("Custom event adapter called onFailedToReceiveAd.");
            this.zzda.onDismissScreen(this.zzcZ);
        }

        public void onFailedToReceiveAd() {
            zzqf.zzbf("Custom event adapter called onFailedToReceiveAd.");
            this.zzda.onFailedToReceiveAd(this.zzcZ, AdRequest.ErrorCode.NO_FILL);
        }

        public void onLeaveApplication() {
            zzqf.zzbf("Custom event adapter called onFailedToReceiveAd.");
            this.zzda.onLeaveApplication(this.zzcZ);
        }

        public void onPresentScreen() {
            zzqf.zzbf("Custom event adapter called onFailedToReceiveAd.");
            this.zzda.onPresentScreen(this.zzcZ);
        }

        public void onReceivedAd(View view) {
            zzqf.zzbf("Custom event adapter called onReceivedAd.");
            this.zzcZ.zza(view);
            this.zzda.onReceivedAd(this.zzcZ);
        }
    }

    class zzb implements CustomEventInterstitialListener {
        private final CustomEventAdapter zzcZ;
        private final MediationInterstitialListener zzdb;

        public zzb(CustomEventAdapter customEventAdapter, MediationInterstitialListener mediationInterstitialListener) {
            this.zzcZ = customEventAdapter;
            this.zzdb = mediationInterstitialListener;
        }

        public void onDismissScreen() {
            zzqf.zzbf("Custom event adapter called onDismissScreen.");
            this.zzdb.onDismissScreen(this.zzcZ);
        }

        public void onFailedToReceiveAd() {
            zzqf.zzbf("Custom event adapter called onFailedToReceiveAd.");
            this.zzdb.onFailedToReceiveAd(this.zzcZ, AdRequest.ErrorCode.NO_FILL);
        }

        public void onLeaveApplication() {
            zzqf.zzbf("Custom event adapter called onLeaveApplication.");
            this.zzdb.onLeaveApplication(this.zzcZ);
        }

        public void onPresentScreen() {
            zzqf.zzbf("Custom event adapter called onPresentScreen.");
            this.zzdb.onPresentScreen(this.zzcZ);
        }

        public void onReceivedAd() {
            zzqf.zzbf("Custom event adapter called onReceivedAd.");
            this.zzdb.onReceivedAd(CustomEventAdapter.this);
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

    public void destroy() {
        if (this.zzcX != null) {
            this.zzcX.destroy();
        }
        if (this.zzcY != null) {
            this.zzcY.destroy();
        }
    }

    public Class<CustomEventExtras> getAdditionalParametersType() {
        return CustomEventExtras.class;
    }

    public View getBannerView() {
        return this.zzcW;
    }

    public Class<CustomEventServerParameters> getServerParametersType() {
        return CustomEventServerParameters.class;
    }

    public void requestBannerAd(MediationBannerListener mediationBannerListener, Activity activity, CustomEventServerParameters customEventServerParameters, AdSize adSize, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        this.zzcX = (CustomEventBanner) zzj(customEventServerParameters.className);
        if (this.zzcX == null) {
            mediationBannerListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
        } else {
            this.zzcX.requestBannerAd(new zza(this, mediationBannerListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, adSize, mediationAdRequest, customEventExtras == null ? null : customEventExtras.getExtra(customEventServerParameters.label));
        }
    }

    public void requestInterstitialAd(MediationInterstitialListener mediationInterstitialListener, Activity activity, CustomEventServerParameters customEventServerParameters, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        this.zzcY = (CustomEventInterstitial) zzj(customEventServerParameters.className);
        if (this.zzcY == null) {
            mediationInterstitialListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
        } else {
            this.zzcY.requestInterstitialAd(zza(mediationInterstitialListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, mediationAdRequest, customEventExtras == null ? null : customEventExtras.getExtra(customEventServerParameters.label));
        }
    }

    public void showInterstitial() {
        this.zzcY.showInterstitial();
    }

    /* access modifiers changed from: package-private */
    public zzb zza(MediationInterstitialListener mediationInterstitialListener) {
        return new zzb(this, mediationInterstitialListener);
    }
}
