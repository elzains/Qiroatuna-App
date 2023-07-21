package name.ratson.cordova.admob.rewardvideo;

import android.os.Bundle;
import android.util.Log;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import name.ratson.cordova.admob.AbstractExecutor;
import name.ratson.cordova.admob.AdMob;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;

public class RewardVideoExecutor extends AbstractExecutor {
    boolean isRewardedVideoLoading = false;
    /* access modifiers changed from: private */
    public RewardedVideoAd rewardedVideoAd;
    final Object rewardedVideoLock = new Object();

    public RewardVideoExecutor(AdMob plugin) {
        super(plugin);
    }

    public String getAdType() {
        return "rewardvideo";
    }

    public PluginResult prepareAd(JSONObject options, CallbackContext callbackContext) {
        CordovaInterface cordova2 = this.plugin.f21cordova;
        this.plugin.config.setRewardVideoOptions(options);
        final CallbackContext delayCallback = callbackContext;
        cordova2.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                CordovaInterface cordova2 = RewardVideoExecutor.this.plugin.f21cordova;
                RewardVideoExecutor.this.clearAd();
                RewardedVideoAd unused = RewardVideoExecutor.this.rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(cordova2.getActivity());
                RewardVideoExecutor.this.rewardedVideoAd.setRewardedVideoAdListener(new RewardVideoListener(RewardVideoExecutor.this));
                Log.w("rewardedvideo", RewardVideoExecutor.this.plugin.config.getRewardedVideoAdUnitId());
                synchronized (RewardVideoExecutor.this.rewardedVideoLock) {
                    if (!RewardVideoExecutor.this.isRewardedVideoLoading) {
                        RewardVideoExecutor.this.isRewardedVideoLoading = true;
                        Bundle extras = new Bundle();
                        extras.putBoolean("_noRefresh", true);
                        RewardVideoExecutor.this.rewardedVideoAd.loadAd(RewardVideoExecutor.this.plugin.config.getRewardedVideoAdUnitId(), new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build());
                        delayCallback.success();
                    }
                }
            }
        });
        return null;
    }

    public void clearAd() {
        if (this.rewardedVideoAd != null) {
            this.rewardedVideoAd.setRewardedVideoAdListener((RewardedVideoAdListener) null);
            this.rewardedVideoAd = null;
        }
    }

    public PluginResult showAd(boolean show, final CallbackContext callbackContext) {
        if (this.rewardedVideoAd == null) {
            return new PluginResult(PluginResult.Status.ERROR, "rewardedVideoAd is null, call createRewardVideo first.");
        }
        this.plugin.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (RewardVideoExecutor.this.rewardedVideoAd instanceof RewardedVideoAd) {
                    RewardedVideoAd rvad = RewardVideoExecutor.this.rewardedVideoAd;
                    if (rvad.isLoaded()) {
                        rvad.show();
                    }
                }
                if (callbackContext != null) {
                    callbackContext.success();
                }
            }
        });
        return null;
    }

    public void destroy() {
        clearAd();
    }

    public PluginResult isReady(final CallbackContext callbackContext) {
        this.plugin.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (RewardVideoExecutor.this.rewardedVideoAd == null || !RewardVideoExecutor.this.rewardedVideoAd.isLoaded()) {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, false));
                } else {
                    callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
                }
            }
        });
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldAutoShow() {
        return this.plugin.config.autoShowRewardVideo;
    }
}
