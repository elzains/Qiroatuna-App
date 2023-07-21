package com.google.android.gms.internal;

import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.internal.zznw;

@zzme
public class zznz extends zznw.zza {
    private final RewardedVideoAdListener zzcJ;

    public zznz(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzcJ = rewardedVideoAdListener;
    }

    public void onRewardedVideoAdClosed() {
        if (this.zzcJ != null) {
            this.zzcJ.onRewardedVideoAdClosed();
        }
    }

    public void onRewardedVideoAdFailedToLoad(int i) {
        if (this.zzcJ != null) {
            this.zzcJ.onRewardedVideoAdFailedToLoad(i);
        }
    }

    public void onRewardedVideoAdLeftApplication() {
        if (this.zzcJ != null) {
            this.zzcJ.onRewardedVideoAdLeftApplication();
        }
    }

    public void onRewardedVideoAdLoaded() {
        if (this.zzcJ != null) {
            this.zzcJ.onRewardedVideoAdLoaded();
        }
    }

    public void onRewardedVideoAdOpened() {
        if (this.zzcJ != null) {
            this.zzcJ.onRewardedVideoAdOpened();
        }
    }

    public void onRewardedVideoStarted() {
        if (this.zzcJ != null) {
            this.zzcJ.onRewardedVideoStarted();
        }
    }

    public void zza(zznt zznt) {
        if (this.zzcJ != null) {
            this.zzcJ.onRewarded(new zznx(zznt));
        }
    }
}
