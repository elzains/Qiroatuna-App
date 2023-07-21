package name.ratson.cordova.admob.interstitial;

import android.util.Log;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import name.ratson.cordova.admob.AbstractExecutor;
import name.ratson.cordova.admob.AdMob;
import name.ratson.cordova.admob.AdMobConfig;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;

public class InterstitialExecutor extends AbstractExecutor {
    /* access modifiers changed from: private */
    public InterstitialAd interstitialAd;

    public InterstitialExecutor(AdMob plugin) {
        super(plugin);
    }

    public String getAdType() {
        return "interstitial";
    }

    public PluginResult prepareAd(JSONObject options, CallbackContext callbackContext) {
        AdMobConfig config = this.plugin.config;
        CordovaInterface cordova2 = this.plugin.f21cordova;
        config.setInterstitialOptions(options);
        final CallbackContext delayCallback = callbackContext;
        cordova2.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                AdMobConfig config = InterstitialExecutor.this.plugin.config;
                CordovaInterface cordova2 = InterstitialExecutor.this.plugin.f21cordova;
                InterstitialExecutor.this.destroy();
                InterstitialAd unused = InterstitialExecutor.this.interstitialAd = new InterstitialAd(cordova2.getActivity());
                InterstitialExecutor.this.interstitialAd.setAdUnitId(config.getInterstitialAdUnitId());
                InterstitialExecutor.this.interstitialAd.setAdListener(new InterstitialListener(InterstitialExecutor.this));
                Log.i("interstitial", config.getInterstitialAdUnitId());
                InterstitialExecutor.this.interstitialAd.loadAd(InterstitialExecutor.this.plugin.buildAdRequest());
                delayCallback.success();
            }
        });
        return null;
    }

    public PluginResult createAd(JSONObject options, CallbackContext callbackContext) {
        AdMobConfig config = this.plugin.config;
        CordovaInterface cordova2 = this.plugin.f21cordova;
        config.setInterstitialOptions(options);
        final CallbackContext delayCallback = callbackContext;
        cordova2.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                AdMobConfig config = InterstitialExecutor.this.plugin.config;
                CordovaInterface cordova2 = InterstitialExecutor.this.plugin.f21cordova;
                InterstitialExecutor.this.destroy();
                InterstitialAd unused = InterstitialExecutor.this.interstitialAd = new InterstitialAd(cordova2.getActivity());
                InterstitialExecutor.this.interstitialAd.setAdUnitId(config.getInterstitialAdUnitId());
                InterstitialExecutor.this.interstitialAd.setAdListener(new InterstitialListener(InterstitialExecutor.this));
                Log.w("interstitial", config.getInterstitialAdUnitId());
                InterstitialExecutor.this.interstitialAd.loadAd(InterstitialExecutor.this.plugin.buildAdRequest());
                delayCallback.success();
            }
        });
        return null;
    }

    public void destroy() {
        if (this.interstitialAd != null) {
            this.interstitialAd.setAdListener((AdListener) null);
            this.interstitialAd = null;
        }
    }

    public PluginResult requestAd(JSONObject options, CallbackContext callbackContext) {
        CordovaInterface cordova2 = this.plugin.f21cordova;
        this.plugin.config.setInterstitialOptions(options);
        if (this.interstitialAd == null) {
            callbackContext.error("interstitialAd is null, call createInterstitialView first");
        } else {
            final CallbackContext delayCallback = callbackContext;
            cordova2.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    InterstitialExecutor.this.interstitialAd.loadAd(InterstitialExecutor.this.plugin.buildAdRequest());
                    delayCallback.success();
                }
            });
        }
        return null;
    }

    public PluginResult showAd(boolean show, final CallbackContext callbackContext) {
        if (this.interstitialAd == null) {
            return new PluginResult(PluginResult.Status.ERROR, "interstitialAd is null, call createInterstitialView first.");
        }
        this.plugin.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                AdMobConfig config = InterstitialExecutor.this.plugin.config;
                if (InterstitialExecutor.this.interstitialAd.isLoaded()) {
                    InterstitialExecutor.this.interstitialAd.show();
                    if (callbackContext != null) {
                        callbackContext.success();
                    }
                } else if (!config.autoShowInterstitial && callbackContext != null) {
                    callbackContext.error("Interstital not ready yet");
                }
            }
        });
        return null;
    }

    public PluginResult isReady(final CallbackContext callbackContext) {
        this.plugin.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (InterstitialExecutor.this.interstitialAd == null || !InterstitialExecutor.this.interstitialAd.isLoaded()) {
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
        return this.plugin.config.autoShowInterstitial;
    }
}
