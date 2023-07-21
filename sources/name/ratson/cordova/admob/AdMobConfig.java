package name.ratson.cordova.admob;

import android.location.Location;
import android.util.Log;
import com.google.android.gms.ads.AdSize;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdMobConfig {
    private static final String OPT_AD_EXTRAS = "adExtras";
    private static final String OPT_AD_SIZE = "adSize";
    public static final String OPT_AUTO_SHOW = "autoShow";
    private static final String OPT_AUTO_SHOW_BANNER = "autoShowBanner";
    public static final String OPT_AUTO_SHOW_INTERSTITIAL = "autoShowInterstitial";
    private static final String OPT_BANNER_AT_TOP = "bannerAtTop";
    private static final String OPT_CONTENTURL = "contentUrl";
    private static final String OPT_EXCLUDE = "exclude";
    private static final String OPT_FORCHILD = "forChild";
    private static final String OPT_FORFAMILY = "forFamily";
    private static final String OPT_GENDER = "gender";
    private static final String OPT_INTERSTITIAL_AD_ID = "interstitialAdId";
    private static final String OPT_IS_TESTING = "isTesting";
    private static final String OPT_LOCATION = "location";
    private static final String OPT_OFFSET_TOPBAR = "offsetTopBar";
    private static final String OPT_OVERLAP = "overlap";
    private static final String OPT_PUBLISHER_ID = "publisherId";
    private static final String OPT_REWARD_VIDEO_ID = "rewardVideoId";
    public static final String OPT_TEST_DEVICES = "testDevices";
    private static final String TEST_BANNER_ID = "ca-app-pub-3940256099942544/6300978111";
    private static final String TEST_INTERSTITIAL_ID = "ca-app-pub-3940256099942544/1033173712";
    private static final String TEST_REWARDED_VIDEO_ID = "ca-app-pub-3940256099942544/1033173712";
    public JSONObject adExtras = null;
    public AdSize adSize = AdSize.SMART_BANNER;
    public boolean autoShow = true;
    public boolean autoShowBanner = true;
    public boolean autoShowInterstitial = true;
    public boolean autoShowRewardVideo = false;
    private String bannerAdUnitId = "";
    public boolean bannerAtTop = false;
    public boolean bannerOverlap = false;
    public String contentURL = null;
    public JSONArray exclude = null;
    public String forChild = null;
    public String forFamily = null;
    public String gender = null;
    private String interstitialAdUnitId = "";
    public boolean isTesting = false;
    public Location location = null;
    public boolean offsetTopBar = false;
    private String rewardVideoId = "";
    public List<String> testDeviceList = null;

    public void setOptions(JSONObject options) {
        JSONArray testDevices;
        JSONArray location2;
        if (options != null) {
            if (options.has(OPT_PUBLISHER_ID)) {
                this.bannerAdUnitId = options.optString(OPT_PUBLISHER_ID);
            }
            if (options.has(OPT_INTERSTITIAL_AD_ID)) {
                this.interstitialAdUnitId = options.optString(OPT_INTERSTITIAL_AD_ID);
            }
            if (options.has(OPT_REWARD_VIDEO_ID)) {
                this.rewardVideoId = options.optString(OPT_REWARD_VIDEO_ID);
            }
            if (options.has(OPT_AD_SIZE)) {
                this.adSize = adSizeFromString(options.optString(OPT_AD_SIZE));
            }
            if (options.has(OPT_BANNER_AT_TOP)) {
                this.bannerAtTop = options.optBoolean(OPT_BANNER_AT_TOP);
            }
            if (options.has(OPT_OVERLAP)) {
                this.bannerOverlap = options.optBoolean(OPT_OVERLAP);
            }
            if (options.has(OPT_OFFSET_TOPBAR)) {
                this.offsetTopBar = options.optBoolean(OPT_OFFSET_TOPBAR);
            }
            if (options.has(OPT_IS_TESTING)) {
                this.isTesting = options.optBoolean(OPT_IS_TESTING);
            }
            if (options.has(OPT_AD_EXTRAS)) {
                this.adExtras = options.optJSONObject(OPT_AD_EXTRAS);
            }
            if (options.has(OPT_AUTO_SHOW)) {
                this.autoShow = options.optBoolean(OPT_AUTO_SHOW);
            }
            if (options.has(OPT_AUTO_SHOW_BANNER)) {
                this.autoShowBanner = options.optBoolean(OPT_AUTO_SHOW_BANNER);
            }
            if (options.has(OPT_AUTO_SHOW_INTERSTITIAL)) {
                this.autoShowInterstitial = options.optBoolean(OPT_AUTO_SHOW_INTERSTITIAL);
            }
            if (options.has(OPT_LOCATION) && (location2 = options.optJSONArray(OPT_LOCATION)) != null) {
                this.location = new Location("dummyprovider");
                this.location.setLatitude(location2.optDouble(0, 0.0d));
                this.location.setLongitude(location2.optDouble(1, 0.0d));
            }
            if (options.has(OPT_GENDER)) {
                this.gender = options.optString(OPT_GENDER);
            }
            if (options.has(OPT_FORCHILD)) {
                this.forChild = options.optString(OPT_FORCHILD);
            }
            if (options.has(OPT_FORFAMILY)) {
                this.forFamily = options.optString(OPT_FORFAMILY);
            }
            if (options.has(OPT_CONTENTURL)) {
                this.contentURL = options.optString(OPT_CONTENTURL);
            }
            if (options.has(OPT_EXCLUDE)) {
                this.exclude = options.optJSONArray(OPT_EXCLUDE);
            }
            if (options.has(OPT_TEST_DEVICES) && (testDevices = options.optJSONArray(OPT_TEST_DEVICES)) != null) {
                this.testDeviceList = new ArrayList();
                for (int i = 0; i < testDevices.length(); i++) {
                    this.testDeviceList.add(testDevices.optString(i));
                }
            }
        }
    }

    public void setBannerOptions(JSONObject options) {
        try {
            this.autoShowBanner = ((Boolean) options.remove(OPT_AUTO_SHOW)).booleanValue();
        } catch (NullPointerException e) {
        }
        setOptions(options);
    }

    public void setInterstitialOptions(JSONObject options) {
        try {
            this.autoShowInterstitial = ((Boolean) options.remove(OPT_AUTO_SHOW)).booleanValue();
        } catch (NullPointerException e) {
        }
        setOptions(options);
    }

    public void setRewardVideoOptions(JSONObject options) {
        try {
            this.autoShowRewardVideo = ((Boolean) options.remove(OPT_AUTO_SHOW)).booleanValue();
        } catch (NullPointerException e) {
        }
        setOptions(options);
    }

    private static AdSize adSizeFromString(String size) {
        if ("BANNER".equals(size)) {
            return AdSize.BANNER;
        }
        if ("FULL_BANNER".equals(size)) {
            return AdSize.FULL_BANNER;
        }
        if ("LARGE_BANNER".equals(size)) {
            return AdSize.LARGE_BANNER;
        }
        if ("LEADERBOARD".equals(size)) {
            return AdSize.LEADERBOARD;
        }
        if ("MEDIUM_RECTANGLE".equals(size)) {
            return AdSize.MEDIUM_RECTANGLE;
        }
        if ("WIDE_SKYSCRAPER".equals(size)) {
            return AdSize.WIDE_SKYSCRAPER;
        }
        if ("SMART_BANNER".equals(size)) {
            return AdSize.SMART_BANNER;
        }
        if ("FLUID".equals(size)) {
            return AdSize.FLUID;
        }
        if ("SEARCH".equals(size)) {
            return AdSize.SEARCH;
        }
        if ("IAB_BANNER".equals(size)) {
            return AdSize.FULL_BANNER;
        }
        if ("IAB_MRECT".equals(size)) {
            return AdSize.MEDIUM_RECTANGLE;
        }
        if ("IAB_LEADERBOARD".equals(size)) {
            return AdSize.LEADERBOARD;
        }
        return null;
    }

    public String getBannerAdUnitId() {
        if (!isEmptyAdUnitId(this.bannerAdUnitId)) {
            return this.bannerAdUnitId;
        }
        Log.e("banner", "Please put your AdMob id into the javascript code. Test ad is used.");
        return TEST_BANNER_ID;
    }

    public String getInterstitialAdUnitId() {
        if (!isEmptyAdUnitId(this.interstitialAdUnitId)) {
            return this.interstitialAdUnitId;
        }
        Log.e("interstitial", "Please put your AdMob id into the javascript code. Test ad is used.");
        return "ca-app-pub-3940256099942544/1033173712";
    }

    public String getRewardedVideoAdUnitId() {
        if (!isEmptyAdUnitId(this.rewardVideoId)) {
            return this.rewardVideoId;
        }
        Log.e("rewardedvideo", "Please put your AdMob id into the javascript code. Test ad is used.");
        return "ca-app-pub-3940256099942544/1033173712";
    }

    private static boolean isEmptyAdUnitId(String adId) {
        return adId.length() == 0 || adId.indexOf("xxxx") > 0;
    }
}
