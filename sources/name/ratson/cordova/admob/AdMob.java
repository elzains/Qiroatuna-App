package name.ratson.cordova.admob;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rjfun.cordova.plugin.nativeaudio.NativeAudio;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import name.ratson.cordova.admob.banner.BannerExecutor;
import name.ratson.cordova.admob.interstitial.InterstitialExecutor;
import name.ratson.cordova.admob.rewardvideo.RewardVideoExecutor;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdMob extends CordovaPlugin {
    private static final String TAG = "AdMob";
    private BannerExecutor bannerExecutor = null;
    public final AdMobConfig config = new AdMobConfig();
    private InterstitialExecutor interstitialExecutor = null;
    private boolean isGpsAvailable = false;
    private RewardVideoExecutor rewardVideoExecutor = null;

    public void initialize(CordovaInterface cordova2, CordovaWebView webView) {
        boolean z;
        super.initialize(cordova2, webView);
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(cordova2.getActivity()) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.isGpsAvailable = z;
        Object[] objArr = new Object[1];
        objArr[0] = this.isGpsAvailable ? "true" : "false";
        Log.w(TAG, String.format("isGooglePlayServicesAvailable: %s", objArr));
    }

    public boolean execute(String action, JSONArray inputs, CallbackContext callbackContext) throws JSONException {
        PluginResult result;
        if (this.bannerExecutor == null) {
            this.bannerExecutor = new BannerExecutor(this);
        }
        if (this.interstitialExecutor == null) {
            this.interstitialExecutor = new InterstitialExecutor(this);
        }
        if (this.rewardVideoExecutor == null) {
            this.rewardVideoExecutor = new RewardVideoExecutor(this);
        }
        if (NativeAudio.SET_OPTIONS.equals(action)) {
            result = executeSetOptions(inputs.optJSONObject(0), callbackContext);
        } else if ("createBannerView".equals(action)) {
            result = this.bannerExecutor.prepareAd(inputs.optJSONObject(0), callbackContext);
        } else if ("destroyBannerView".equals(action)) {
            result = this.bannerExecutor.removeAd(callbackContext);
        } else if ("requestAd".equals(action)) {
            result = this.bannerExecutor.requestAd(inputs.optJSONObject(0), callbackContext);
        } else if ("showAd".equals(action)) {
            result = this.bannerExecutor.showAd(inputs.optBoolean(0), callbackContext);
        } else if ("prepareInterstitial".equals(action)) {
            result = this.interstitialExecutor.prepareAd(inputs.optJSONObject(0), callbackContext);
        } else if ("createInterstitialView".equals(action)) {
            result = this.interstitialExecutor.createAd(inputs.optJSONObject(0), callbackContext);
        } else if ("requestInterstitialAd".equals(action)) {
            result = this.interstitialExecutor.requestAd(inputs.optJSONObject(0), callbackContext);
        } else if ("showInterstitialAd".equals(action)) {
            result = this.interstitialExecutor.showAd(inputs.optBoolean(0), callbackContext);
        } else if ("isInterstitialReady".equals(action)) {
            result = this.interstitialExecutor.isReady(callbackContext);
        } else if ("createRewardVideo".equals(action)) {
            result = this.rewardVideoExecutor.prepareAd(inputs.optJSONObject(0), callbackContext);
        } else if ("showRewardVideo".equals(action)) {
            result = this.rewardVideoExecutor.showAd(inputs.optBoolean(0), callbackContext);
        } else if ("isRewardVideoReady".equals(action)) {
            result = this.rewardVideoExecutor.isReady(callbackContext);
        } else {
            Log.d(TAG, String.format("Invalid action passed: %s", new Object[]{action}));
            result = new PluginResult(PluginResult.Status.INVALID_ACTION);
        }
        if (result != null) {
            callbackContext.sendPluginResult(result);
        }
        return true;
    }

    private PluginResult executeSetOptions(JSONObject options, CallbackContext callbackContext) {
        Log.w(TAG, "executeSetOptions");
        this.config.setOptions(options);
        callbackContext.success();
        return null;
    }

    public AdRequest buildAdRequest() {
        AdRequest.Builder builder = new AdRequest.Builder();
        if (this.config.isTesting) {
            builder = builder.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB").addTestDevice(getDeviceId());
        }
        if (this.config.testDeviceList != null) {
            for (String addTestDevice : this.config.testDeviceList) {
                builder = builder.addTestDevice(addTestDevice);
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("cordova", 1);
        if (this.config.adExtras != null) {
            Iterator<String> it = this.config.adExtras.keys();
            while (it.hasNext()) {
                String key = it.next();
                try {
                    bundle.putString(key, this.config.adExtras.get(key).toString());
                } catch (JSONException exception) {
                    Log.w(TAG, String.format("Caught JSON Exception: %s", new Object[]{exception.getMessage()}));
                }
            }
        }
        AdRequest.Builder builder2 = builder.addNetworkExtrasBundle(AdMobAdapter.class, bundle);
        if (this.config.gender != null) {
            if ("male".compareToIgnoreCase(this.config.gender) != 0) {
                builder2.setGender(1);
            } else if ("female".compareToIgnoreCase(this.config.gender) != 0) {
                builder2.setGender(2);
            } else {
                builder2.setGender(0);
            }
        }
        if (this.config.location != null) {
            builder2.setLocation(this.config.location);
        }
        if ("yes".equals(this.config.forFamily)) {
            builder2.setIsDesignedForFamilies(true);
        } else if ("no".equals(this.config.forFamily)) {
            builder2.setIsDesignedForFamilies(false);
        }
        if ("yes".equals(this.config.forChild)) {
            builder2.tagForChildDirectedTreatment(true);
        } else if ("no".equals(this.config.forChild)) {
            builder2.tagForChildDirectedTreatment(false);
        }
        if (this.config.contentURL != null) {
            builder2.setContentUrl(this.config.contentURL);
        }
        return builder2.build();
    }

    public void onPause(boolean multitasking) {
        if (this.bannerExecutor != null) {
            this.bannerExecutor.pauseAd();
        }
        super.onPause(multitasking);
    }

    public void onResume(boolean multitasking) {
        super.onResume(multitasking);
        this.isGpsAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this.f21cordova.getActivity()) == 0;
        if (this.bannerExecutor != null) {
            this.bannerExecutor.resumeAd();
        }
    }

    public void onDestroy() {
        if (this.bannerExecutor != null) {
            this.bannerExecutor.destroy();
            this.bannerExecutor = null;
        }
        if (this.interstitialExecutor != null) {
            this.interstitialExecutor.destroy();
            this.interstitialExecutor = null;
        }
        if (this.rewardVideoExecutor != null) {
            this.rewardVideoExecutor.destroy();
            this.rewardVideoExecutor = null;
        }
        super.onDestroy();
    }

    @NonNull
    private String getDeviceId() {
        return md5(Settings.Secure.getString(this.f21cordova.getActivity().getContentResolver(), "android_id")).toUpperCase();
    }

    private static String md5(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte[] messageDigest = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (byte b : messageDigest) {
                String h = Integer.toHexString(b & 255);
                while (h.length() < 2) {
                    h = "0" + h;
                }
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
