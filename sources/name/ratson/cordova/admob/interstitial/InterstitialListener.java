package name.ratson.cordova.admob.interstitial;

import android.util.Log;
import com.google.android.gms.ads.AdListener;
import name.ratson.cordova.admob.AbstractExecutor;
import org.apache.cordova.CallbackContext;
import org.json.JSONException;
import org.json.JSONObject;

class InterstitialListener extends AdListener {
    private final InterstitialExecutor executor;

    InterstitialListener(InterstitialExecutor executor2) {
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
        this.executor.fireAdEvent("admob.interstitial.events.LOAD_FAIL", data);
        this.executor.fireAdEvent("onFailedToReceiveAd", data);
    }

    public void onAdLeftApplication() {
        JSONObject data = new JSONObject();
        try {
            data.put("adType", this.executor.getAdType());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.executor.fireAdEvent("admob.interstitial.events.EXIT_APP", data);
        this.executor.fireAdEvent("onLeaveToAd", data);
    }

    public void onAdLoaded() {
        Log.w("AdMob", "InterstitialAdLoaded");
        this.executor.fireAdEvent("admob.interstitial.events.LOAD");
        this.executor.fireAdEvent("onReceiveInterstitialAd");
        if (this.executor.shouldAutoShow()) {
            this.executor.showAd(true, (CallbackContext) null);
        }
    }

    public void onAdOpened() {
        this.executor.fireAdEvent("admob.interstitial.events.OPEN");
        this.executor.fireAdEvent("onPresentInterstitialAd");
    }

    public void onAdClosed() {
        this.executor.fireAdEvent("admob.interstitial.events.CLOSE");
        this.executor.fireAdEvent("onDismissInterstitialAd");
        this.executor.destroy();
    }
}
