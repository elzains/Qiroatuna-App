package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

@zzme
public final class zzkn<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    /* access modifiers changed from: private */
    public final zzkc zzLE;

    public zzkn(zzkc zzkc) {
        this.zzLE = zzkc;
    }

    public void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzqf.zzbf("Adapter called onClick.");
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onClick must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdClicked();
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdClicked.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdClicked();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdClicked.", e);
        }
    }

    public void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzqf.zzbf("Adapter called onDismissScreen.");
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onDismissScreen must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdClosed();
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdClosed.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdClosed();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdClosed.", e);
        }
    }

    public void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzqf.zzbf("Adapter called onDismissScreen.");
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onDismissScreen must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdClosed();
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdClosed.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdClosed();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdClosed.", e);
        }
    }

    public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, final AdRequest.ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzqf.zzbf(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error. ").append(valueOf).toString());
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onFailedToReceiveAd must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdFailedToLoad(zzko.zza(errorCode));
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdFailedToLoad.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdFailedToLoad(zzko.zza(errorCode));
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, final AdRequest.ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        zzqf.zzbf(new StringBuilder(String.valueOf(valueOf).length() + 47).append("Adapter called onFailedToReceiveAd with error ").append(valueOf).append(".").toString());
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onFailedToReceiveAd must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdFailedToLoad(zzko.zza(errorCode));
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdFailedToLoad.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdFailedToLoad(zzko.zza(errorCode));
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzqf.zzbf("Adapter called onLeaveApplication.");
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onLeaveApplication must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdLeftApplication();
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdLeftApplication.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdLeftApplication();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    public void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzqf.zzbf("Adapter called onLeaveApplication.");
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onLeaveApplication must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdLeftApplication();
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdLeftApplication.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdLeftApplication();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    public void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzqf.zzbf("Adapter called onPresentScreen.");
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onPresentScreen must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdOpened();
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdOpened.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdOpened();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdOpened.", e);
        }
    }

    public void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzqf.zzbf("Adapter called onPresentScreen.");
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onPresentScreen must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdOpened();
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdOpened.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdOpened();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdOpened.", e);
        }
    }

    public void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzqf.zzbf("Adapter called onReceivedAd.");
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onReceivedAd must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdLoaded();
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdLoaded.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdLoaded();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLoaded.", e);
        }
    }

    public void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzqf.zzbf("Adapter called onReceivedAd.");
        if (!zzel.zzeT().zzlj()) {
            zzqf.zzbh("onReceivedAd must be called on the main UI thread.");
            zzqe.zzYP.post(new Runnable() {
                public void run() {
                    try {
                        zzkn.this.zzLE.onAdLoaded();
                    } catch (RemoteException e) {
                        zzqf.zzc("Could not call onAdLoaded.", e);
                    }
                }
            });
            return;
        }
        try {
            this.zzLE.onAdLoaded();
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLoaded.", e);
        }
    }
}
