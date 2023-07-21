package com.mesmotronic.plugins;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.view.View;
import android.view.Window;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class FullScreenPlugin extends CordovaPlugin {
    public static final String ACTION_IMMERSIVE_HEIGHT = "immersiveHeight";
    public static final String ACTION_IMMERSIVE_MODE = "immersiveMode";
    public static final String ACTION_IMMERSIVE_WIDTH = "immersiveWidth";
    public static final String ACTION_IS_IMMERSIVE_MODE_SUPPORTED = "isImmersiveModeSupported";
    public static final String ACTION_IS_SUPPORTED = "isSupported";
    public static final String ACTION_LEAN_MODE = "leanMode";
    public static final String ACTION_SHOW_SYSTEM_UI = "showSystemUI";
    public static final String ACTION_SHOW_UNDER_STATUS_BAR = "showUnderStatusBar";
    public static final String ACTION_SHOW_UNDER_SYSTEM_UI = "showUnderSystemUI";
    private Activity activity;
    /* access modifiers changed from: private */
    public CallbackContext context;
    /* access modifiers changed from: private */
    public View decorView;
    /* access modifiers changed from: private */
    public Window window;

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.context = callbackContext;
        this.activity = this.f21cordova.getActivity();
        this.window = this.activity.getWindow();
        this.decorView = this.window.getDecorView();
        if (ACTION_IS_SUPPORTED.equals(action)) {
            return isSupported();
        }
        if (ACTION_IS_IMMERSIVE_MODE_SUPPORTED.equals(action)) {
            return isImmersiveModeSupported();
        }
        if (ACTION_IMMERSIVE_WIDTH.equals(action)) {
            return immersiveWidth();
        }
        if (ACTION_IMMERSIVE_HEIGHT.equals(action)) {
            return immersiveHeight();
        }
        if (ACTION_LEAN_MODE.equals(action)) {
            return leanMode();
        }
        if (ACTION_SHOW_SYSTEM_UI.equals(action)) {
            return showSystemUI();
        }
        if (ACTION_SHOW_UNDER_STATUS_BAR.equals(action)) {
            return showUnderStatusBar();
        }
        if (ACTION_SHOW_UNDER_SYSTEM_UI.equals(action)) {
            return showUnderSystemUI();
        }
        if (ACTION_IMMERSIVE_MODE.equals(action)) {
            return immersiveMode();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void resetWindow() {
        this.decorView.setOnFocusChangeListener((View.OnFocusChangeListener) null);
        this.decorView.setOnSystemUiVisibilityChangeListener((View.OnSystemUiVisibilityChangeListener) null);
        this.window.clearFlags(2048);
    }

    /* access modifiers changed from: protected */
    public boolean isSupported() {
        this.context.sendPluginResult(new PluginResult(PluginResult.Status.OK, Build.VERSION.SDK_INT >= 14));
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isImmersiveModeSupported() {
        this.context.sendPluginResult(new PluginResult(PluginResult.Status.OK, Build.VERSION.SDK_INT >= 19));
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean immersiveWidth() {
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    Point outSize = new Point();
                    FullScreenPlugin.this.decorView.getDisplay().getRealSize(outSize);
                    FullScreenPlugin.this.context.sendPluginResult(new PluginResult(PluginResult.Status.OK, outSize.x));
                } catch (Exception e) {
                    FullScreenPlugin.this.context.error(e.getMessage());
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean immersiveHeight() {
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    Point outSize = new Point();
                    FullScreenPlugin.this.decorView.getDisplay().getRealSize(outSize);
                    FullScreenPlugin.this.context.sendPluginResult(new PluginResult(PluginResult.Status.OK, outSize.y));
                } catch (Exception e) {
                    FullScreenPlugin.this.context.error(e.getMessage());
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean leanMode() {
        if (!isSupported()) {
            this.context.error("Not supported");
            return false;
        }
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    FullScreenPlugin.this.resetWindow();
                    FullScreenPlugin.this.decorView.setOnSystemUiVisibilityChangeListener((View.OnSystemUiVisibilityChangeListener) null);
                    FullScreenPlugin.this.decorView.setSystemUiVisibility(6);
                    FullScreenPlugin.this.context.success();
                } catch (Exception e) {
                    FullScreenPlugin.this.context.error(e.getMessage());
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean showSystemUI() {
        if (!isSupported()) {
            this.context.error("Not supported");
            return false;
        }
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    FullScreenPlugin.this.resetWindow();
                    FullScreenPlugin.this.window.clearFlags(201327616);
                    FullScreenPlugin.this.decorView.setOnSystemUiVisibilityChangeListener((View.OnSystemUiVisibilityChangeListener) null);
                    FullScreenPlugin.this.decorView.setSystemUiVisibility(0);
                    FullScreenPlugin.this.context.sendPluginResult(new PluginResult(PluginResult.Status.OK, true));
                    FullScreenPlugin.this.context.success();
                } catch (Exception e) {
                    FullScreenPlugin.this.context.error(e.getMessage());
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean showUnderStatusBar() {
        if (!isImmersiveModeSupported()) {
            this.context.error("Not supported");
            return false;
        }
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    FullScreenPlugin.this.resetWindow();
                    FullScreenPlugin.this.window.setFlags(67108864, 67108864);
                    FullScreenPlugin.this.decorView.setSystemUiVisibility(1280);
                    FullScreenPlugin.this.context.success();
                } catch (Exception e) {
                    FullScreenPlugin.this.context.error(e.getMessage());
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean showUnderSystemUI() {
        if (!isImmersiveModeSupported()) {
            this.context.error("Not supported");
            return false;
        }
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    FullScreenPlugin.this.resetWindow();
                    FullScreenPlugin.this.window.setFlags(134217728, 134217728);
                    FullScreenPlugin.this.window.setFlags(67108864, 67108864);
                    FullScreenPlugin.this.decorView.setSystemUiVisibility(768);
                    FullScreenPlugin.this.context.success();
                } catch (Exception e) {
                    FullScreenPlugin.this.context.error(e.getMessage());
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean immersiveMode() {
        if (!isImmersiveModeSupported()) {
            this.context.error("Not supported");
            return false;
        }
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    FullScreenPlugin.this.resetWindow();
                    FullScreenPlugin.this.window.addFlags(1024);
                    FullScreenPlugin.this.decorView.setSystemUiVisibility(5894);
                    FullScreenPlugin.this.decorView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        public void onFocusChange(View v, boolean hasFocus) {
                            if (hasFocus) {
                                FullScreenPlugin.this.decorView.setSystemUiVisibility(5894);
                            }
                        }
                    });
                    FullScreenPlugin.this.decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                        public void onSystemUiVisibilityChange(int visibility) {
                            FullScreenPlugin.this.decorView.setSystemUiVisibility(5894);
                        }
                    });
                    FullScreenPlugin.this.context.success();
                } catch (Exception e) {
                    FullScreenPlugin.this.context.error(e.getMessage());
                }
            }
        });
        return true;
    }
}
