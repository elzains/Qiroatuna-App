package name.ratson.cordova.admob.banner;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.AdView;
import name.ratson.cordova.admob.AbstractExecutor;
import name.ratson.cordova.admob.AdMob;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;

public class BannerExecutor extends AbstractExecutor {
    private static final String TAG = "BannerExecutor";
    /* access modifiers changed from: private */
    public AdView adView;
    /* access modifiers changed from: private */
    public RelativeLayout adViewLayout = null;
    /* access modifiers changed from: private */
    public boolean bannerShow = true;
    boolean bannerVisible = false;
    /* access modifiers changed from: private */
    public ViewGroup parentView;

    public BannerExecutor(AdMob plugin) {
        super(plugin);
    }

    public String getAdType() {
        return "banner";
    }

    public PluginResult prepareAd(JSONObject options, final CallbackContext callbackContext) {
        CordovaInterface cordova2 = this.plugin.f21cordova;
        this.plugin.config.setBannerOptions(options);
        cordova2.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                CordovaInterface cordova2 = BannerExecutor.this.plugin.f21cordova;
                if (BannerExecutor.this.adView == null) {
                    AdView unused = BannerExecutor.this.adView = new AdView(cordova2.getActivity());
                    BannerExecutor.this.adView.setAdUnitId(BannerExecutor.this.plugin.config.getBannerAdUnitId());
                    BannerExecutor.this.adView.setAdSize(BannerExecutor.this.plugin.config.adSize);
                    BannerExecutor.this.adView.setAdListener(new BannerListener(BannerExecutor.this));
                }
                if (BannerExecutor.this.adView.getParent() != null) {
                    ((ViewGroup) BannerExecutor.this.adView.getParent()).removeView(BannerExecutor.this.adView);
                }
                BannerExecutor.this.bannerVisible = false;
                BannerExecutor.this.adView.loadAd(BannerExecutor.this.plugin.buildAdRequest());
                Log.w("banner", BannerExecutor.this.plugin.config.getBannerAdUnitId());
                callbackContext.success();
            }
        });
        return null;
    }

    public PluginResult requestAd(JSONObject options, CallbackContext callbackContext) {
        CordovaInterface cordova2 = this.plugin.f21cordova;
        this.plugin.config.setBannerOptions(options);
        if (this.adView == null) {
            callbackContext.error("adView is null, call createBannerView first");
        } else {
            final CallbackContext delayCallback = callbackContext;
            cordova2.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    BannerExecutor.this.adView.loadAd(BannerExecutor.this.plugin.buildAdRequest());
                    delayCallback.success();
                }
            });
        }
        return null;
    }

    public PluginResult removeAd(CallbackContext callbackContext) {
        CordovaInterface cordova2 = this.plugin.f21cordova;
        Log.w(TAG, "executeDestroyBannerView");
        final CallbackContext delayCallback = callbackContext;
        cordova2.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (BannerExecutor.this.adView != null) {
                    ViewGroup parentView = (ViewGroup) BannerExecutor.this.adView.getParent();
                    if (parentView != null) {
                        parentView.removeView(BannerExecutor.this.adView);
                    }
                    BannerExecutor.this.adView.destroy();
                    AdView unused = BannerExecutor.this.adView = null;
                }
                BannerExecutor.this.bannerVisible = false;
                delayCallback.success();
            }
        });
        return null;
    }

    public PluginResult showAd(boolean show, final CallbackContext callbackContext) {
        CordovaInterface cordova2 = this.plugin.f21cordova;
        this.bannerShow = show;
        if (this.adView == null) {
            return new PluginResult(PluginResult.Status.ERROR, "adView is null, call createBannerView first.");
        }
        cordova2.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                CordovaInterface cordova2 = BannerExecutor.this.plugin.f21cordova;
                if (BannerExecutor.this.bannerVisible != BannerExecutor.this.bannerShow) {
                    if (BannerExecutor.this.bannerShow) {
                        CordovaWebView webView = BannerExecutor.this.plugin.webView;
                        if (BannerExecutor.this.adView.getParent() != null) {
                            ((ViewGroup) BannerExecutor.this.adView.getParent()).removeView(BannerExecutor.this.adView);
                        }
                        if (BannerExecutor.this.plugin.config.bannerOverlap) {
                            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(-1, -2);
                            params2.addRule(BannerExecutor.this.plugin.config.bannerAtTop ? 10 : 12);
                            if (BannerExecutor.this.adViewLayout == null) {
                                RelativeLayout unused = BannerExecutor.this.adViewLayout = new RelativeLayout(cordova2.getActivity());
                                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(-1, -1);
                                try {
                                    ((ViewGroup) ((View) webView.getClass().getMethod("getView", new Class[0]).invoke(webView, new Object[0])).getParent()).addView(BannerExecutor.this.adViewLayout, params);
                                } catch (Exception e) {
                                    ((ViewGroup) webView).addView(BannerExecutor.this.adViewLayout, params);
                                }
                            }
                            BannerExecutor.this.adViewLayout.addView(BannerExecutor.this.adView, params2);
                            BannerExecutor.this.adViewLayout.bringToFront();
                        } else {
                            ViewGroup wvParentView = (ViewGroup) BannerExecutor.this.getWebView().getParent();
                            if (BannerExecutor.this.parentView == null) {
                                ViewGroup unused2 = BannerExecutor.this.parentView = new LinearLayout(webView.getContext());
                            }
                            if (!(wvParentView == null || wvParentView == BannerExecutor.this.parentView)) {
                                wvParentView.removeView(BannerExecutor.this.getWebView());
                                ((LinearLayout) BannerExecutor.this.parentView).setOrientation(1);
                                BannerExecutor.this.parentView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 0.0f));
                                BannerExecutor.this.getWebView().setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0f));
                                BannerExecutor.this.parentView.addView(BannerExecutor.this.getWebView());
                                cordova2.getActivity().setContentView(BannerExecutor.this.parentView);
                            }
                            if (BannerExecutor.this.plugin.config.bannerAtTop) {
                                BannerExecutor.this.parentView.addView(BannerExecutor.this.adView, 0);
                            } else {
                                BannerExecutor.this.parentView.addView(BannerExecutor.this.adView);
                            }
                            BannerExecutor.this.parentView.bringToFront();
                            BannerExecutor.this.parentView.requestLayout();
                            BannerExecutor.this.parentView.requestFocus();
                        }
                        BannerExecutor.this.adView.setVisibility(0);
                        BannerExecutor.this.bannerVisible = true;
                    } else {
                        BannerExecutor.this.adView.setVisibility(8);
                        BannerExecutor.this.bannerVisible = false;
                    }
                }
                if (callbackContext != null) {
                    callbackContext.success();
                }
            }
        });
        return null;
    }

    public void pauseAd() {
        if (this.adView != null) {
            this.adView.pause();
        }
    }

    public void resumeAd() {
        if (this.adView != null) {
            this.adView.resume();
        }
    }

    public void destroy() {
        if (this.adView != null) {
            this.adView.destroy();
            this.adView = null;
        }
        if (this.adViewLayout != null) {
            ViewGroup parentView2 = (ViewGroup) this.adViewLayout.getParent();
            if (parentView2 != null) {
                parentView2.removeView(this.adViewLayout);
            }
            this.adViewLayout = null;
        }
    }

    /* access modifiers changed from: private */
    public View getWebView() {
        CordovaWebView webView = this.plugin.webView;
        try {
            return (View) webView.getClass().getMethod("getView", new Class[0]).invoke(webView, new Object[0]);
        } catch (Exception e) {
            return (View) webView;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldAutoShow() {
        return this.plugin.config.autoShowBanner;
    }
}
