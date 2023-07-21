package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.common.internal.zzac;

@zzme
public final class zzki implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener {
    private final zzkc zzLE;
    private NativeAdMapper zzLF;

    public zzki(zzkc zzkc) {
        this.zzLE = zzkc;
    }

    public void onAdClicked(MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzdj("onAdClicked must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdClicked.");
        try {
            this.zzLE.onAdClicked();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdClicked.", e);
        }
    }

    public void onAdClicked(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzdj("onAdClicked must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdClicked.");
        try {
            this.zzLE.onAdClicked();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdClicked.", e);
        }
    }

    public void onAdClicked(MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzdj("onAdClicked must be called on the main UI thread.");
        NativeAdMapper zzhi = zzhi();
        if (zzhi == null) {
            zzqf.zzbh("Could not call onAdClicked since NativeAdMapper is null.");
        } else if (!zzhi.getOverrideClickHandling()) {
            zzqf.zzbf("Could not call onAdClicked since setOverrideClickHandling is not set to true");
        } else {
            zzqf.zzbf("Adapter called onAdClicked.");
            try {
                this.zzLE.onAdClicked();
            } catch (RemoteException e) {
                zzqf.zzc("Could not call onAdClicked.", e);
            }
        }
    }

    public void onAdClosed(MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzdj("onAdClosed must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdClosed.");
        try {
            this.zzLE.onAdClosed();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdClosed.", e);
        }
    }

    public void onAdClosed(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzdj("onAdClosed must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdClosed.");
        try {
            this.zzLE.onAdClosed();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdClosed.", e);
        }
    }

    public void onAdClosed(MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzdj("onAdClosed must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdClosed.");
        try {
            this.zzLE.onAdClosed();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdClosed.", e);
        }
    }

    public void onAdFailedToLoad(MediationBannerAdapter mediationBannerAdapter, int i) {
        zzac.zzdj("onAdFailedToLoad must be called on the main UI thread.");
        zzqf.zzbf(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error. ").append(i).toString());
        try {
            this.zzLE.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdFailedToLoad(MediationInterstitialAdapter mediationInterstitialAdapter, int i) {
        zzac.zzdj("onAdFailedToLoad must be called on the main UI thread.");
        zzqf.zzbf(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error ").append(i).append(".").toString());
        try {
            this.zzLE.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdFailedToLoad(MediationNativeAdapter mediationNativeAdapter, int i) {
        zzac.zzdj("onAdFailedToLoad must be called on the main UI thread.");
        zzqf.zzbf(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error ").append(i).append(".").toString());
        try {
            this.zzLE.onAdFailedToLoad(i);
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdImpression(MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzdj("onAdImpression must be called on the main UI thread.");
        NativeAdMapper zzhi = zzhi();
        if (zzhi == null) {
            zzqf.zzbh("Could not call onAdImpression since NativeAdMapper is null. ");
        } else if (!zzhi.getOverrideImpressionRecording()) {
            zzqf.zzbf("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
        } else {
            zzqf.zzbf("Adapter called onAdImpression.");
            try {
                this.zzLE.onAdImpression();
            } catch (RemoteException e) {
                zzqf.zzc("Could not call onAdImpression.", e);
            }
        }
    }

    public void onAdLeftApplication(MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzdj("onAdLeftApplication must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdLeftApplication.");
        try {
            this.zzLE.onAdLeftApplication();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLeftApplication(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzdj("onAdLeftApplication must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdLeftApplication.");
        try {
            this.zzLE.onAdLeftApplication();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLeftApplication(MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzdj("onAdLeftApplication must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdLeftApplication.");
        try {
            this.zzLE.onAdLeftApplication();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLoaded(MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzdj("onAdLoaded must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdLoaded.");
        try {
            this.zzLE.onAdLoaded();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLoaded.", e);
        }
    }

    public void onAdLoaded(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzdj("onAdLoaded must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdLoaded.");
        try {
            this.zzLE.onAdLoaded();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLoaded.", e);
        }
    }

    public void onAdLoaded(MediationNativeAdapter mediationNativeAdapter, NativeAdMapper nativeAdMapper) {
        zzac.zzdj("onAdLoaded must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdLoaded.");
        this.zzLF = nativeAdMapper;
        try {
            this.zzLE.onAdLoaded();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLoaded.", e);
        }
    }

    public void onAdOpened(MediationBannerAdapter mediationBannerAdapter) {
        zzac.zzdj("onAdOpened must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdOpened.");
        try {
            this.zzLE.onAdOpened();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdOpened.", e);
        }
    }

    public void onAdOpened(MediationInterstitialAdapter mediationInterstitialAdapter) {
        zzac.zzdj("onAdOpened must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdOpened.");
        try {
            this.zzLE.onAdOpened();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdOpened.", e);
        }
    }

    public void onAdOpened(MediationNativeAdapter mediationNativeAdapter) {
        zzac.zzdj("onAdOpened must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdOpened.");
        try {
            this.zzLE.onAdOpened();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdOpened.", e);
        }
    }

    public NativeAdMapper zzhi() {
        return this.zzLF;
    }
}
