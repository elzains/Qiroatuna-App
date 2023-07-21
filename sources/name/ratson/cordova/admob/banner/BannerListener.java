package name.ratson.cordova.admob.banner;

import android.util.Log;
import com.google.android.gms.ads.AdListener;
import name.ratson.cordova.admob.AbstractExecutor;
import org.apache.cordova.CallbackContext;
import org.json.JSONException;
import org.json.JSONObject;

class BannerListener extends AdListener {
    private final BannerExecutor executor;

    BannerListener(BannerExecutor executor2) {
        this.executor = executor2;
    }

    public void onAdFailedToLoad(int errorCode) {
        JSONObject data = new JSONObject();
        try {
            data.put("error", errorCode);
            data.put("reason", AbstractExecutor.getErrorReason(errorCode));
            data.put("adType", this.executor.getAdType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.executor.fireAdEvent("admob.banner.events.LOAD_FAIL", data);
        this.executor.fireAdEvent("onFailedToReceiveAd", data);
    }

    public void onAdLeftApplication() {
        JSONObject data = new JSONObject();
        try {
            data.put("adType", this.executor.getAdType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.executor.fireAdEvent("admob.banner.events.EXIT_APP", data);
        this.executor.fireAdEvent("onLeaveToAd", data);
    }

    public void onAdLoaded() {
        Log.w("AdMob", "BannerAdLoaded");
        if (this.executor.shouldAutoShow() && !this.executor.bannerVisible) {
            this.executor.showAd(true, (CallbackContext) null);
        }
        this.executor.fireAdEvent("admob.banner.events.LOAD");
        this.executor.fireAdEvent("onReceiveAd");
    }

    public void onAdOpened() {
        this.executor.fireAdEvent("admob.banner.events.OPEN");
        this.executor.fireAdEvent("onPresentAd");
    }

    public void onAdClosed() {
        this.executor.fireAdEvent("admob.banner.events.CLOSE");
        this.executor.fireAdEvent("onDismissAd");
    }
}
