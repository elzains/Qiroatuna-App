package name.ratson.cordova.admob.rewardvideo;

import android.util.Log;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import name.ratson.cordova.admob.AbstractExecutor;
import org.apache.cordova.CallbackContext;
import org.json.JSONException;
import org.json.JSONObject;

class RewardVideoListener implements RewardedVideoAdListener {
    private final RewardVideoExecutor executor;

    RewardVideoListener(RewardVideoExecutor executor2) {
        this.executor = executor2;
    }

    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        synchronized (this.executor.rewardedVideoLock) {
            this.executor.isRewardedVideoLoading = false;
        }
        JSONObject data = new JSONObject();
        try {
            data.put("error", errorCode);
            data.put("reason", AbstractExecutor.getErrorReason(errorCode));
            data.put("adType", this.executor.getAdType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.executor.fireAdEvent("admob.rewardvideo.events.LOAD_FAIL", data);
    }

    public void onRewardedVideoAdLeftApplication() {
        JSONObject data = new JSONObject();
        try {
            data.put("adType", this.executor.getAdType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.executor.fireAdEvent("admob.rewardvideo.events.EXIT_APP", data);
    }

    public void onRewardedVideoAdLoaded() {
        synchronized (this.executor.rewardedVideoLock) {
            this.executor.isRewardedVideoLoading = false;
        }
        Log.w("AdMob", "RewardedVideoAdLoaded");
        this.executor.fireAdEvent("admob.rewardvideo.events.LOAD");
        if (this.executor.shouldAutoShow()) {
            this.executor.showAd(true, (CallbackContext) null);
        }
    }

    public void onRewardedVideoAdOpened() {
        this.executor.fireAdEvent("admob.rewardvideo.events.OPEN");
    }

    public void onRewardedVideoStarted() {
        this.executor.fireAdEvent("admob.rewardvideo.events.START");
    }

    public void onRewardedVideoAdClosed() {
        this.executor.fireAdEvent("admob.rewardvideo.events.CLOSE");
        this.executor.clearAd();
    }

    public void onRewarded(RewardItem reward) {
        JSONObject data = new JSONObject();
        try {
            data.put("adType", this.executor.getAdType());
            data.put("rewardType", reward.getType());
            data.put("rewardAmount", reward.getAmount());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.executor.fireAdEvent("admob.rewardvideo.events.REWARD", data);
    }
}
