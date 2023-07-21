package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.zzd;

@zzme
public class zzon implements MediationRewardedVideoAdListener {
    private final zzom zzVO;

    public zzon(zzom zzom) {
        this.zzVO = zzom;
    }

    public void onAdClicked(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzdj("onAdClicked must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdClicked.");
        try {
            this.zzVO.zzw(zzd.zzA(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdClicked.", e);
        }
    }

    public void onAdClosed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzdj("onAdClosed must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdClosed.");
        try {
            this.zzVO.zzv(zzd.zzA(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdClosed.", e);
        }
    }

    public void onAdFailedToLoad(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        zzac.zzdj("onAdFailedToLoad must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdFailedToLoad.");
        try {
            this.zzVO.zzd(zzd.zzA(mediationRewardedVideoAdAdapter), i);
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdFailedToLoad.", e);
        }
    }

    public void onAdLeftApplication(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzdj("onAdLeftApplication must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdLeftApplication.");
        try {
            this.zzVO.zzx(zzd.zzA(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLeftApplication.", e);
        }
    }

    public void onAdLoaded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzdj("onAdLoaded must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdLoaded.");
        try {
            this.zzVO.zzs(zzd.zzA(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdLoaded.", e);
        }
    }

    public void onAdOpened(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzdj("onAdOpened must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onAdOpened.");
        try {
            this.zzVO.zzt(zzd.zzA(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onAdOpened.", e);
        }
    }

    public void onInitializationFailed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        zzac.zzdj("onInitializationFailed must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onInitializationFailed.");
        try {
            this.zzVO.zzc(zzd.zzA(mediationRewardedVideoAdAdapter), i);
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onInitializationFailed.", e);
        }
    }

    public void onInitializationSucceeded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzdj("onInitializationSucceeded must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onInitializationSucceeded.");
        try {
            this.zzVO.zzr(zzd.zzA(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onInitializationSucceeded.", e);
        }
    }

    public void onRewarded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, RewardItem rewardItem) {
        zzac.zzdj("onRewarded must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onRewarded.");
        if (rewardItem != null) {
            try {
                this.zzVO.zza(zzd.zzA(mediationRewardedVideoAdAdapter), new zzoo(rewardItem));
            } catch (RemoteException e) {
                zzqf.zzc("Could not call onRewarded.", e);
            }
        } else {
            this.zzVO.zza(zzd.zzA(mediationRewardedVideoAdAdapter), new zzoo("", 1));
        }
    }

    public void onVideoStarted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        zzac.zzdj("onVideoStarted must be called on the main UI thread.");
        zzqf.zzbf("Adapter called onVideoStarted.");
        try {
            this.zzVO.zzu(zzd.zzA(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzqf.zzc("Could not call onVideoStarted.", e);
        }
    }
}
