package org.apache.cordova.splashscreen;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.p000v4.view.ViewCompat;
import android.view.Display;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.rjfun.cordova.plugin.nativeaudio.NativeAudio;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class SplashScreen extends CordovaPlugin {
    private static final int DEFAULT_SPLASHSCREEN_DURATION = 3000;
    private static final boolean HAS_BUILT_IN_SPLASH_SCREEN;
    private static final String LOG_TAG = "SplashScreen";
    private static boolean firstShow = true;
    /* access modifiers changed from: private */
    public static boolean lastHideAfterDelay;
    /* access modifiers changed from: private */
    public static ProgressDialog spinnerDialog;
    /* access modifiers changed from: private */
    public static Dialog splashDialog;
    private int orientation;
    /* access modifiers changed from: private */
    public ImageView splashImageView;

    static {
        boolean z = false;
        if (Integer.valueOf(CordovaWebView.CORDOVA_VERSION.split("\\.")[0]).intValue() < 4) {
            z = true;
        }
        HAS_BUILT_IN_SPLASH_SCREEN = z;
    }

    private View getView() {
        try {
            return (View) this.webView.getClass().getMethod("getView", new Class[0]).invoke(this.webView, new Object[0]);
        } catch (Exception e) {
            return (View) this.webView;
        }
    }

    /* access modifiers changed from: protected */
    public void pluginInitialize() {
        String splashResource;
        if (!HAS_BUILT_IN_SPLASH_SCREEN) {
            getView().setVisibility(4);
            if (this.preferences.getInteger("SplashDrawableId", 0) == 0 && (splashResource = this.preferences.getString(LOG_TAG, "screen")) != null) {
                int drawableId = this.f21cordova.getActivity().getResources().getIdentifier(splashResource, "drawable", this.f21cordova.getActivity().getClass().getPackage().getName());
                if (drawableId == 0) {
                    drawableId = this.f21cordova.getActivity().getResources().getIdentifier(splashResource, "drawable", this.f21cordova.getActivity().getPackageName());
                }
                this.preferences.set("SplashDrawableId", drawableId);
            }
            this.orientation = this.f21cordova.getActivity().getResources().getConfiguration().orientation;
            if (firstShow) {
                showSplashScreen(this.preferences.getBoolean("AutoHideSplashScreen", true));
            }
            if (this.preferences.getBoolean("SplashShowOnlyFirstTime", true)) {
                firstShow = false;
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isMaintainAspectRatio() {
        return this.preferences.getBoolean("SplashMaintainAspectRatio", false);
    }

    /* access modifiers changed from: private */
    public int getFadeDuration() {
        int fadeSplashScreenDuration = this.preferences.getBoolean("FadeSplashScreen", true) ? this.preferences.getInteger("FadeSplashScreenDuration", DEFAULT_SPLASHSCREEN_DURATION) : 0;
        if (fadeSplashScreenDuration < 30) {
            return fadeSplashScreenDuration * 1000;
        }
        return fadeSplashScreenDuration;
    }

    public void onPause(boolean multitasking) {
        if (!HAS_BUILT_IN_SPLASH_SCREEN) {
            removeSplashScreen(true);
        }
    }

    public void onDestroy() {
        if (!HAS_BUILT_IN_SPLASH_SCREEN) {
            removeSplashScreen(true);
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("hide")) {
            this.f21cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    SplashScreen.this.webView.postMessage("splashscreen", "hide");
                }
            });
        } else if (!action.equals("show")) {
            return false;
        } else {
            this.f21cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    SplashScreen.this.webView.postMessage("splashscreen", "show");
                }
            });
        }
        callbackContext.success();
        return true;
    }

    public Object onMessage(String id, Object data) {
        if (!HAS_BUILT_IN_SPLASH_SCREEN) {
            if ("splashscreen".equals(id)) {
                if ("hide".equals(data.toString())) {
                    removeSplashScreen(false);
                } else {
                    showSplashScreen(false);
                }
            } else if ("spinner".equals(id)) {
                if (NativeAudio.STOP.equals(data.toString())) {
                    getView().setVisibility(0);
                }
            } else if ("onReceivedError".equals(id)) {
                spinnerStop();
            }
        }
        return null;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        int drawableId;
        if (newConfig.orientation != this.orientation) {
            this.orientation = newConfig.orientation;
            if (this.splashImageView != null && (drawableId = this.preferences.getInteger("SplashDrawableId", 0)) != 0) {
                this.splashImageView.setImageDrawable(this.f21cordova.getActivity().getResources().getDrawable(drawableId));
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeSplashScreen(final boolean forceHideImmediately) {
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (SplashScreen.splashDialog != null && SplashScreen.splashDialog.isShowing()) {
                    int fadeSplashScreenDuration = SplashScreen.this.getFadeDuration();
                    if (fadeSplashScreenDuration <= 0 || forceHideImmediately) {
                        SplashScreen.this.spinnerStop();
                        SplashScreen.splashDialog.dismiss();
                        Dialog unused = SplashScreen.splashDialog = null;
                        ImageView unused2 = SplashScreen.this.splashImageView = null;
                        return;
                    }
                    AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                    fadeOut.setInterpolator(new DecelerateInterpolator());
                    fadeOut.setDuration((long) fadeSplashScreenDuration);
                    SplashScreen.this.splashImageView.setAnimation(fadeOut);
                    SplashScreen.this.splashImageView.startAnimation(fadeOut);
                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationStart(Animation animation) {
                            SplashScreen.this.spinnerStop();
                        }

                        public void onAnimationEnd(Animation animation) {
                            if (SplashScreen.splashDialog != null && SplashScreen.splashDialog.isShowing()) {
                                SplashScreen.splashDialog.dismiss();
                                Dialog unused = SplashScreen.splashDialog = null;
                                ImageView unused2 = SplashScreen.this.splashImageView = null;
                            }
                        }

                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                }
            }
        });
    }

    private void showSplashScreen(final boolean hideAfterDelay) {
        int splashscreenTime = this.preferences.getInteger("SplashScreenDelay", DEFAULT_SPLASHSCREEN_DURATION);
        final int drawableId = this.preferences.getInteger("SplashDrawableId", 0);
        final int effectiveSplashDuration = Math.max(0, splashscreenTime - getFadeDuration());
        lastHideAfterDelay = hideAfterDelay;
        if ((splashDialog != null && splashDialog.isShowing()) || drawableId == 0) {
            return;
        }
        if (splashscreenTime > 0 || !hideAfterDelay) {
            this.f21cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Display display = SplashScreen.this.f21cordova.getActivity().getWindowManager().getDefaultDisplay();
                    Context context = SplashScreen.this.webView.getContext();
                    ImageView unused = SplashScreen.this.splashImageView = new ImageView(context);
                    SplashScreen.this.splashImageView.setImageResource(drawableId);
                    SplashScreen.this.splashImageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                    SplashScreen.this.splashImageView.setMinimumHeight(display.getHeight());
                    SplashScreen.this.splashImageView.setMinimumWidth(display.getWidth());
                    SplashScreen.this.splashImageView.setBackgroundColor(SplashScreen.this.preferences.getInteger("backgroundColor", ViewCompat.MEASURED_STATE_MASK));
                    if (SplashScreen.this.isMaintainAspectRatio()) {
                        SplashScreen.this.splashImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    } else {
                        SplashScreen.this.splashImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                    Dialog unused2 = SplashScreen.splashDialog = new Dialog(context, 16973840);
                    if ((SplashScreen.this.f21cordova.getActivity().getWindow().getAttributes().flags & 1024) == 1024) {
                        SplashScreen.splashDialog.getWindow().setFlags(1024, 1024);
                    }
                    SplashScreen.splashDialog.setContentView(SplashScreen.this.splashImageView);
                    SplashScreen.splashDialog.setCancelable(false);
                    SplashScreen.splashDialog.show();
                    if (SplashScreen.this.preferences.getBoolean("ShowSplashScreenSpinner", true)) {
                        SplashScreen.this.spinnerStart();
                    }
                    if (hideAfterDelay) {
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                if (SplashScreen.lastHideAfterDelay) {
                                    SplashScreen.this.removeSplashScreen(false);
                                }
                            }
                        }, (long) effectiveSplashDuration);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void spinnerStart() {
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                SplashScreen.this.spinnerStop();
                ProgressDialog unused = SplashScreen.spinnerDialog = new ProgressDialog(SplashScreen.this.webView.getContext());
                SplashScreen.spinnerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        ProgressDialog unused = SplashScreen.spinnerDialog = null;
                    }
                });
                SplashScreen.spinnerDialog.setCancelable(false);
                SplashScreen.spinnerDialog.setIndeterminate(true);
                RelativeLayout centeredLayout = new RelativeLayout(SplashScreen.this.f21cordova.getActivity());
                centeredLayout.setGravity(17);
                centeredLayout.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
                ProgressBar progressBar = new ProgressBar(SplashScreen.this.webView.getContext());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(13, -1);
                progressBar.setLayoutParams(layoutParams);
                centeredLayout.addView(progressBar);
                SplashScreen.spinnerDialog.getWindow().clearFlags(2);
                SplashScreen.spinnerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                SplashScreen.spinnerDialog.show();
                SplashScreen.spinnerDialog.setContentView(centeredLayout);
            }
        });
    }

    /* access modifiers changed from: private */
    public void spinnerStop() {
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                if (SplashScreen.spinnerDialog != null && SplashScreen.spinnerDialog.isShowing()) {
                    SplashScreen.spinnerDialog.dismiss();
                    ProgressDialog unused = SplashScreen.spinnerDialog = null;
                }
            }
        });
    }
}
