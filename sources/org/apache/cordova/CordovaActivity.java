package org.apache.cordova;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.p000v4.view.ViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaActivity extends Activity {
    private static int ACTIVITY_EXITING = 2;
    private static int ACTIVITY_RUNNING = 1;
    private static int ACTIVITY_STARTING = 0;
    public static String TAG = "CordovaActivity";
    protected CordovaWebView appView;
    protected CordovaInterfaceImpl cordovaInterface;
    protected boolean immersiveMode;
    protected boolean keepRunning = true;
    protected String launchUrl;
    protected ArrayList<PluginEntry> pluginEntries;
    protected CordovaPreferences preferences;

    public void onCreate(Bundle savedInstanceState) {
        loadConfig();
        LOG.setLogLevel(this.preferences.getString("loglevel", "ERROR"));
        LOG.m27i(TAG, "Apache Cordova native platform version 6.1.2 is starting");
        LOG.m21d(TAG, "CordovaActivity.onCreate()");
        if (!this.preferences.getBoolean("ShowTitle", false)) {
            getWindow().requestFeature(1);
        }
        if (this.preferences.getBoolean("SetFullscreen", false)) {
            LOG.m21d(TAG, "The SetFullscreen configuration is deprecated in favor of Fullscreen, and will be removed in a future version.");
            this.preferences.set("Fullscreen", true);
        }
        if (!this.preferences.getBoolean("Fullscreen", false)) {
            getWindow().setFlags(2048, 2048);
        } else if (Build.VERSION.SDK_INT < 19 || this.preferences.getBoolean("FullscreenNotImmersive", false)) {
            getWindow().setFlags(1024, 1024);
        } else {
            this.immersiveMode = true;
        }
        super.onCreate(savedInstanceState);
        this.cordovaInterface = makeCordovaInterface();
        if (savedInstanceState != null) {
            this.cordovaInterface.restoreInstanceState(savedInstanceState);
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.appView = makeWebView();
        createViews();
        if (!this.appView.isInitialized()) {
            this.appView.init(this.cordovaInterface, this.pluginEntries, this.preferences);
        }
        this.cordovaInterface.onCordovaInit(this.appView.getPluginManager());
        if ("media".equals(this.preferences.getString("DefaultVolumeStream", "").toLowerCase(Locale.ENGLISH))) {
            setVolumeControlStream(3);
        }
    }

    /* access modifiers changed from: protected */
    public void loadConfig() {
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse((Context) this);
        this.preferences = parser.getPreferences();
        this.preferences.setPreferencesBundle(getIntent().getExtras());
        this.launchUrl = parser.getLaunchUrl();
        this.pluginEntries = parser.getPluginEntries();
        Config.parser = parser;
    }

    /* access modifiers changed from: protected */
    public void createViews() {
        this.appView.getView().setId(100);
        this.appView.getView().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        setContentView(this.appView.getView());
        if (this.preferences.contains("BackgroundColor")) {
            try {
                this.appView.getView().setBackgroundColor(this.preferences.getInteger("BackgroundColor", ViewCompat.MEASURED_STATE_MASK));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        this.appView.getView().requestFocusFromTouch();
    }

    /* access modifiers changed from: protected */
    public CordovaWebView makeWebView() {
        return new CordovaWebViewImpl(makeWebViewEngine());
    }

    /* access modifiers changed from: protected */
    public CordovaWebViewEngine makeWebViewEngine() {
        return CordovaWebViewImpl.createEngine(this, this.preferences);
    }

    /* access modifiers changed from: protected */
    public CordovaInterfaceImpl makeCordovaInterface() {
        return new CordovaInterfaceImpl(this) {
            public Object onMessage(String id, Object data) {
                return CordovaActivity.this.onMessage(id, data);
            }
        };
    }

    public void loadUrl(String url) {
        if (this.appView == null) {
            init();
        }
        this.keepRunning = this.preferences.getBoolean("KeepRunning", true);
        this.appView.loadUrlIntoView(url, true);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LOG.m21d(TAG, "Paused the activity.");
        if (this.appView != null) {
            this.appView.handlePause(this.keepRunning || this.cordovaInterface.activityResultCallback != null);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.appView != null) {
            this.appView.onNewIntent(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        LOG.m21d(TAG, "Resumed the activity.");
        if (this.appView != null) {
            getWindow().getDecorView().requestFocus();
            this.appView.handleResume(this.keepRunning);
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        LOG.m21d(TAG, "Stopped the activity.");
        if (this.appView != null) {
            this.appView.handleStop();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        LOG.m21d(TAG, "Started the activity.");
        if (this.appView != null) {
            this.appView.handleStart();
        }
    }

    public void onDestroy() {
        LOG.m21d(TAG, "CordovaActivity.onDestroy()");
        super.onDestroy();
        if (this.appView != null) {
            this.appView.handleDestroy();
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && this.immersiveMode) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    @SuppressLint({"NewApi"})
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        this.cordovaInterface.setActivityResultRequestCode(requestCode);
        super.startActivityForResult(intent, requestCode, options);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        LOG.m21d(TAG, "Incoming Result. Request code = " + requestCode);
        super.onActivityResult(requestCode, resultCode, intent);
        this.cordovaInterface.onActivityResult(requestCode, resultCode, intent);
    }

    public void onReceivedError(int errorCode, String description, String failingUrl) {
        final String errorUrl = this.preferences.getString("errorUrl", (String) null);
        if (errorUrl == null || failingUrl.equals(errorUrl) || this.appView == null) {
            final boolean exit = errorCode != -2;
            final String str = description;
            final String str2 = failingUrl;
            runOnUiThread(new Runnable() {
                public void run() {
                    if (exit) {
                        this.appView.getView().setVisibility(8);
                        this.displayError("Application Error", str + " (" + str2 + ")", "OK", exit);
                    }
                }
            });
            return;
        }
        runOnUiThread(new Runnable() {
            public void run() {
                this.appView.showWebPage(errorUrl, false, true, (Map<String, Object>) null);
            }
        });
    }

    public void displayError(String title, String message, String button, boolean exit) {
        final String str = message;
        final String str2 = title;
        final String str3 = button;
        final boolean z = exit;
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setMessage(str);
                    dlg.setTitle(str2);
                    dlg.setCancelable(false);
                    dlg.setPositiveButton(str3, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if (z) {
                                CordovaActivity.this.finish();
                            }
                        }
                    });
                    dlg.create();
                    dlg.show();
                } catch (Exception e) {
                    CordovaActivity.this.finish();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.appView != null) {
            this.appView.getPluginManager().postMessage("onCreateOptionsMenu", menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.appView == null) {
            return true;
        }
        this.appView.getPluginManager().postMessage("onPrepareOptionsMenu", menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.appView == null) {
            return true;
        }
        this.appView.getPluginManager().postMessage("onOptionsItemSelected", item);
        return true;
    }

    public Object onMessage(String id, Object data) {
        if ("onReceivedError".equals(id)) {
            JSONObject d = (JSONObject) data;
            try {
                onReceivedError(d.getInt("errorCode"), d.getString("description"), d.getString("url"));
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else if (!"exit".equals(id)) {
            return null;
        } else {
            finish();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        this.cordovaInterface.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        PluginManager pm;
        super.onConfigurationChanged(newConfig);
        if (this.appView != null && (pm = this.appView.getPluginManager()) != null) {
            pm.onConfigurationChanged(newConfig);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        try {
            this.cordovaInterface.onRequestPermissionResult(requestCode, permissions, grantResults);
        } catch (JSONException e) {
            LOG.m21d(TAG, "JSONException: Parameters fed into the method are not valid");
            e.printStackTrace();
        }
    }
}
