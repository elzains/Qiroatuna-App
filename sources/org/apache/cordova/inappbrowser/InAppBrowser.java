package org.apache.cordova.inappbrowser;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.globalization.Globalization;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class InAppBrowser extends CordovaPlugin {
    private static final String CLEAR_ALL_CACHE = "clearcache";
    private static final String CLEAR_SESSION_CACHE = "clearsessioncache";
    private static final String EXIT_EVENT = "exit";
    private static final String HARDWARE_BACK_BUTTON = "hardwareback";
    private static final String HIDDEN = "hidden";
    private static final String LOAD_ERROR_EVENT = "loaderror";
    private static final String LOAD_START_EVENT = "loadstart";
    private static final String LOAD_STOP_EVENT = "loadstop";
    private static final String LOCATION = "location";
    protected static final String LOG_TAG = "InAppBrowser";
    private static final String MEDIA_PLAYBACK_REQUIRES_USER_ACTION = "mediaPlaybackRequiresUserAction";
    private static final String NULL = "null";
    private static final String SELF = "_self";
    private static final String SYSTEM = "_system";
    private static final String ZOOM = "zoom";
    private CallbackContext callbackContext;
    /* access modifiers changed from: private */
    public boolean clearAllCache = false;
    /* access modifiers changed from: private */
    public boolean clearSessionCache = false;
    /* access modifiers changed from: private */
    public InAppBrowserDialog dialog;
    /* access modifiers changed from: private */
    public EditText edittext;
    private boolean hadwareBackButton = true;
    /* access modifiers changed from: private */
    public WebView inAppWebView;
    /* access modifiers changed from: private */
    public boolean mediaPlaybackRequiresUserGesture = false;
    /* access modifiers changed from: private */
    public boolean openWindowHidden = false;
    private boolean showLocationBar = true;
    /* access modifiers changed from: private */
    public boolean showZoomControls = true;

    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext2) throws JSONException {
        String jsWrapper;
        String jsWrapper2;
        String jsWrapper3;
        if (action.equals("open")) {
            this.callbackContext = callbackContext2;
            final String url = args.getString(0);
            String t = args.optString(1);
            if (t == null || t.equals("") || t.equals(NULL)) {
                t = SELF;
            }
            final String target = t;
            final HashMap<String, Boolean> features = parseFeature(args.optString(2));
            Log.d(LOG_TAG, "target = " + target);
            final CallbackContext callbackContext3 = callbackContext2;
            this.f21cordova.getActivity().runOnUiThread(new Runnable() {
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v39, resolved type: java.lang.Object} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: java.lang.Boolean} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v43, resolved type: java.lang.Object} */
                /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: java.lang.Boolean} */
                /* JADX WARNING: Multi-variable type inference failed */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r16 = this;
                        r15 = 1
                        java.lang.String r7 = ""
                        java.lang.String r10 = "_self"
                        r0 = r16
                        java.lang.String r11 = r2
                        boolean r10 = r10.equals(r11)
                        if (r10 == 0) goto L_0x013a
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "in self"
                        android.util.Log.d(r10, r11)
                        r9 = 0
                        r0 = r16
                        java.lang.String r10 = r3
                        java.lang.String r11 = "javascript:"
                        boolean r10 = r10.startsWith(r11)
                        if (r10 == 0) goto L_0x0027
                        java.lang.Boolean r9 = java.lang.Boolean.valueOf(r15)
                    L_0x0027:
                        if (r9 != 0) goto L_0x004c
                        java.lang.Class<org.apache.cordova.Config> r10 = org.apache.cordova.Config.class
                        java.lang.String r11 = "isUrlWhiteListed"
                        r12 = 1
                        java.lang.Class[] r12 = new java.lang.Class[r12]     // Catch:{ NoSuchMethodException -> 0x0183, IllegalAccessException -> 0x0180, InvocationTargetException -> 0x017d }
                        r13 = 0
                        java.lang.Class<java.lang.String> r14 = java.lang.String.class
                        r12[r13] = r14     // Catch:{ NoSuchMethodException -> 0x0183, IllegalAccessException -> 0x0180, InvocationTargetException -> 0x017d }
                        java.lang.reflect.Method r4 = r10.getMethod(r11, r12)     // Catch:{ NoSuchMethodException -> 0x0183, IllegalAccessException -> 0x0180, InvocationTargetException -> 0x017d }
                        r10 = 0
                        r11 = 1
                        java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ NoSuchMethodException -> 0x0183, IllegalAccessException -> 0x0180, InvocationTargetException -> 0x017d }
                        r12 = 0
                        r0 = r16
                        java.lang.String r13 = r3     // Catch:{ NoSuchMethodException -> 0x0183, IllegalAccessException -> 0x0180, InvocationTargetException -> 0x017d }
                        r11[r12] = r13     // Catch:{ NoSuchMethodException -> 0x0183, IllegalAccessException -> 0x0180, InvocationTargetException -> 0x017d }
                        java.lang.Object r10 = r4.invoke(r10, r11)     // Catch:{ NoSuchMethodException -> 0x0183, IllegalAccessException -> 0x0180, InvocationTargetException -> 0x017d }
                        r0 = r10
                        java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ NoSuchMethodException -> 0x0183, IllegalAccessException -> 0x0180, InvocationTargetException -> 0x017d }
                        r9 = r0
                    L_0x004c:
                        if (r9 != 0) goto L_0x0094
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        org.apache.cordova.CordovaWebView r10 = r10.webView     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        java.lang.Class r10 = r10.getClass()     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        java.lang.String r11 = "getPluginManager"
                        r12 = 0
                        java.lang.Class[] r12 = new java.lang.Class[r12]     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        java.lang.reflect.Method r2 = r10.getMethod(r11, r12)     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        org.apache.cordova.CordovaWebView r10 = r10.webView     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        r11 = 0
                        java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        java.lang.Object r6 = r2.invoke(r10, r11)     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        org.apache.cordova.PluginManager r6 = (org.apache.cordova.PluginManager) r6     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        java.lang.Class r10 = r6.getClass()     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        java.lang.String r11 = "shouldAllowNavigation"
                        r12 = 1
                        java.lang.Class[] r12 = new java.lang.Class[r12]     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        r13 = 0
                        java.lang.Class<java.lang.String> r14 = java.lang.String.class
                        r12[r13] = r14     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        java.lang.reflect.Method r8 = r10.getMethod(r11, r12)     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        r10 = 1
                        java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        r11 = 0
                        r0 = r16
                        java.lang.String r12 = r3     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        r10[r11] = r12     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        java.lang.Object r10 = r8.invoke(r6, r10)     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        r0 = r10
                        java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ NoSuchMethodException -> 0x017a, IllegalAccessException -> 0x0177, InvocationTargetException -> 0x0174 }
                        r9 = r0
                    L_0x0094:
                        java.lang.Boolean r10 = java.lang.Boolean.TRUE
                        boolean r10 = r10.equals(r9)
                        if (r10 == 0) goto L_0x00c2
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "loading in webview"
                        android.util.Log.d(r10, r11)
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this
                        org.apache.cordova.CordovaWebView r10 = r10.webView
                        r0 = r16
                        java.lang.String r11 = r3
                        r10.loadUrl(r11)
                    L_0x00b0:
                        org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult
                        org.apache.cordova.PluginResult$Status r10 = org.apache.cordova.PluginResult.Status.OK
                        r5.<init>((org.apache.cordova.PluginResult.Status) r10, (java.lang.String) r7)
                        r5.setKeepCallback(r15)
                        r0 = r16
                        org.apache.cordova.CallbackContext r10 = r5
                        r10.sendPluginResult(r5)
                        return
                    L_0x00c2:
                        r0 = r16
                        java.lang.String r10 = r3
                        java.lang.String r11 = "tel:"
                        boolean r10 = r10.startsWith(r11)
                        if (r10 == 0) goto L_0x0121
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "loading in dialer"
                        android.util.Log.d(r10, r11)     // Catch:{ ActivityNotFoundException -> 0x00f5 }
                        android.content.Intent r3 = new android.content.Intent     // Catch:{ ActivityNotFoundException -> 0x00f5 }
                        java.lang.String r10 = "android.intent.action.DIAL"
                        r3.<init>(r10)     // Catch:{ ActivityNotFoundException -> 0x00f5 }
                        r0 = r16
                        java.lang.String r10 = r3     // Catch:{ ActivityNotFoundException -> 0x00f5 }
                        android.net.Uri r10 = android.net.Uri.parse(r10)     // Catch:{ ActivityNotFoundException -> 0x00f5 }
                        r3.setData(r10)     // Catch:{ ActivityNotFoundException -> 0x00f5 }
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this     // Catch:{ ActivityNotFoundException -> 0x00f5 }
                        org.apache.cordova.CordovaInterface r10 = r10.f21cordova     // Catch:{ ActivityNotFoundException -> 0x00f5 }
                        android.app.Activity r10 = r10.getActivity()     // Catch:{ ActivityNotFoundException -> 0x00f5 }
                        r10.startActivity(r3)     // Catch:{ ActivityNotFoundException -> 0x00f5 }
                        goto L_0x00b0
                    L_0x00f5:
                        r1 = move-exception
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.StringBuilder r11 = new java.lang.StringBuilder
                        r11.<init>()
                        java.lang.String r12 = "Error dialing "
                        java.lang.StringBuilder r11 = r11.append(r12)
                        r0 = r16
                        java.lang.String r12 = r3
                        java.lang.StringBuilder r11 = r11.append(r12)
                        java.lang.String r12 = ": "
                        java.lang.StringBuilder r11 = r11.append(r12)
                        java.lang.String r12 = r1.toString()
                        java.lang.StringBuilder r11 = r11.append(r12)
                        java.lang.String r11 = r11.toString()
                        org.apache.cordova.LOG.m24e(r10, r11)
                        goto L_0x00b0
                    L_0x0121:
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "loading in InAppBrowser"
                        android.util.Log.d(r10, r11)
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this
                        r0 = r16
                        java.lang.String r11 = r3
                        r0 = r16
                        java.util.HashMap r12 = r4
                        java.lang.String r7 = r10.showWebPage(r11, r12)
                        goto L_0x00b0
                    L_0x013a:
                        java.lang.String r10 = "_system"
                        r0 = r16
                        java.lang.String r11 = r2
                        boolean r10 = r10.equals(r11)
                        if (r10 == 0) goto L_0x015b
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "in system"
                        android.util.Log.d(r10, r11)
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this
                        r0 = r16
                        java.lang.String r11 = r3
                        java.lang.String r7 = r10.openExternal(r11)
                        goto L_0x00b0
                    L_0x015b:
                        java.lang.String r10 = "InAppBrowser"
                        java.lang.String r11 = "in blank"
                        android.util.Log.d(r10, r11)
                        r0 = r16
                        org.apache.cordova.inappbrowser.InAppBrowser r10 = org.apache.cordova.inappbrowser.InAppBrowser.this
                        r0 = r16
                        java.lang.String r11 = r3
                        r0 = r16
                        java.util.HashMap r12 = r4
                        java.lang.String r7 = r10.showWebPage(r11, r12)
                        goto L_0x00b0
                    L_0x0174:
                        r10 = move-exception
                        goto L_0x0094
                    L_0x0177:
                        r10 = move-exception
                        goto L_0x0094
                    L_0x017a:
                        r10 = move-exception
                        goto L_0x0094
                    L_0x017d:
                        r10 = move-exception
                        goto L_0x004c
                    L_0x0180:
                        r10 = move-exception
                        goto L_0x004c
                    L_0x0183:
                        r10 = move-exception
                        goto L_0x004c
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.inappbrowser.InAppBrowser.C07371.run():void");
                }
            });
        } else if (action.equals("close")) {
            closeDialog();
        } else if (action.equals("injectScriptCode")) {
            String jsWrapper4 = null;
            if (args.getBoolean(1)) {
                jsWrapper4 = String.format("(function(){prompt(JSON.stringify([eval(%%s)]), 'gap-iab://%s')})()", new Object[]{callbackContext2.getCallbackId()});
            }
            injectDeferredObject(args.getString(0), jsWrapper4);
        } else if (action.equals("injectScriptFile")) {
            if (args.getBoolean(1)) {
                jsWrapper3 = String.format("(function(d) { var c = d.createElement('script'); c.src = %%s; c.onload = function() { prompt('', 'gap-iab://%s'); }; d.body.appendChild(c); })(document)", new Object[]{callbackContext2.getCallbackId()});
            } else {
                jsWrapper3 = "(function(d) { var c = d.createElement('script'); c.src = %s; d.body.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper3);
        } else if (action.equals("injectStyleCode")) {
            if (args.getBoolean(1)) {
                jsWrapper2 = String.format("(function(d) { var c = d.createElement('style'); c.innerHTML = %%s; d.body.appendChild(c); prompt('', 'gap-iab://%s');})(document)", new Object[]{callbackContext2.getCallbackId()});
            } else {
                jsWrapper2 = "(function(d) { var c = d.createElement('style'); c.innerHTML = %s; d.body.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper2);
        } else if (action.equals("injectStyleFile")) {
            if (args.getBoolean(1)) {
                jsWrapper = String.format("(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %%s; d.head.appendChild(c); prompt('', 'gap-iab://%s');})(document)", new Object[]{callbackContext2.getCallbackId()});
            } else {
                jsWrapper = "(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %s; d.head.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper);
        } else if (!action.equals("show")) {
            return false;
        } else {
            this.f21cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    InAppBrowser.this.dialog.show();
                }
            });
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
            pluginResult.setKeepCallback(true);
            this.callbackContext.sendPluginResult(pluginResult);
        }
        return true;
    }

    public void onReset() {
        closeDialog();
    }

    public void onDestroy() {
        closeDialog();
    }

    private void injectDeferredObject(String source, String jsWrapper) {
        String scriptToInject;
        if (jsWrapper != null) {
            JSONArray jsonEsc = new JSONArray();
            jsonEsc.put(source);
            String jsonRepr = jsonEsc.toString();
            scriptToInject = String.format(jsWrapper, new Object[]{jsonRepr.substring(1, jsonRepr.length() - 1)});
        } else {
            scriptToInject = source;
        }
        final String finalScriptToInject = scriptToInject;
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                if (Build.VERSION.SDK_INT < 19) {
                    InAppBrowser.this.inAppWebView.loadUrl("javascript:" + finalScriptToInject);
                } else {
                    InAppBrowser.this.inAppWebView.evaluateJavascript(finalScriptToInject, (ValueCallback) null);
                }
            }
        });
    }

    private HashMap<String, Boolean> parseFeature(String optString) {
        if (optString.equals(NULL)) {
            return null;
        }
        HashMap<String, Boolean> map = new HashMap<>();
        StringTokenizer features = new StringTokenizer(optString, ",");
        while (features.hasMoreElements()) {
            StringTokenizer option = new StringTokenizer(features.nextToken(), "=");
            if (option.hasMoreElements()) {
                map.put(option.nextToken(), option.nextToken().equals("no") ? Boolean.FALSE : Boolean.TRUE);
            }
        }
        return map;
    }

    public String openExternal(String url) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            try {
                Uri uri = Uri.parse(url);
                if ("file".equals(uri.getScheme())) {
                    intent.setDataAndType(uri, this.webView.getResourceApi().getMimeType(uri));
                } else {
                    intent.setData(uri);
                }
                intent.putExtra("com.android.browser.application_id", this.f21cordova.getActivity().getPackageName());
                this.f21cordova.getActivity().startActivity(intent);
                return "";
            } catch (ActivityNotFoundException e) {
                e = e;
                Intent intent2 = intent;
                Log.d(LOG_TAG, "InAppBrowser: Error loading url " + url + ":" + e.toString());
                return e.toString();
            }
        } catch (ActivityNotFoundException e2) {
            e = e2;
            Log.d(LOG_TAG, "InAppBrowser: Error loading url " + url + ":" + e.toString());
            return e.toString();
        }
    }

    public void closeDialog() {
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                WebView childView = InAppBrowser.this.inAppWebView;
                if (childView != null) {
                    childView.setWebViewClient(new WebViewClient() {
                        public void onPageFinished(WebView view, String url) {
                            if (InAppBrowser.this.dialog != null) {
                                InAppBrowser.this.dialog.dismiss();
                                InAppBrowserDialog unused = InAppBrowser.this.dialog = null;
                            }
                        }
                    });
                    childView.loadUrl("about:blank");
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put(Globalization.TYPE, InAppBrowser.EXIT_EVENT);
                        InAppBrowser.this.sendUpdate(obj, false);
                    } catch (JSONException e) {
                        Log.d(InAppBrowser.LOG_TAG, "Should never happen");
                    }
                }
            }
        });
    }

    public void goBack() {
        if (this.inAppWebView.canGoBack()) {
            this.inAppWebView.goBack();
        }
    }

    public boolean canGoBack() {
        return this.inAppWebView.canGoBack();
    }

    public boolean hardwareBack() {
        return this.hadwareBackButton;
    }

    /* access modifiers changed from: private */
    public void goForward() {
        if (this.inAppWebView.canGoForward()) {
            this.inAppWebView.goForward();
        }
    }

    /* access modifiers changed from: private */
    public void navigate(String url) {
        ((InputMethodManager) this.f21cordova.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.edittext.getWindowToken(), 0);
        if (url.startsWith("http") || url.startsWith("file:")) {
            this.inAppWebView.loadUrl(url);
        } else {
            this.inAppWebView.loadUrl("http://" + url);
        }
        this.inAppWebView.requestFocus();
    }

    /* access modifiers changed from: private */
    public boolean getShowLocationBar() {
        return this.showLocationBar;
    }

    /* access modifiers changed from: private */
    public InAppBrowser getInAppBrowser() {
        return this;
    }

    public String showWebPage(final String url, HashMap<String, Boolean> features) {
        this.showLocationBar = true;
        this.showZoomControls = true;
        this.openWindowHidden = false;
        this.mediaPlaybackRequiresUserGesture = false;
        if (features != null) {
            Boolean show = features.get(LOCATION);
            if (show != null) {
                this.showLocationBar = show.booleanValue();
            }
            Boolean zoom = features.get(ZOOM);
            if (zoom != null) {
                this.showZoomControls = zoom.booleanValue();
            }
            Boolean hidden = features.get(HIDDEN);
            if (hidden != null) {
                this.openWindowHidden = hidden.booleanValue();
            }
            Boolean hardwareBack = features.get(HARDWARE_BACK_BUTTON);
            if (hardwareBack != null) {
                this.hadwareBackButton = hardwareBack.booleanValue();
            }
            Boolean mediaPlayback = features.get(MEDIA_PLAYBACK_REQUIRES_USER_ACTION);
            if (mediaPlayback != null) {
                this.mediaPlaybackRequiresUserGesture = mediaPlayback.booleanValue();
            }
            Boolean cache = features.get(CLEAR_ALL_CACHE);
            if (cache != null) {
                this.clearAllCache = cache.booleanValue();
            } else {
                Boolean cache2 = features.get(CLEAR_SESSION_CACHE);
                if (cache2 != null) {
                    this.clearSessionCache = cache2.booleanValue();
                }
            }
        }
        final CordovaWebView thatWebView = this.webView;
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            private int dpToPixels(int dipValue) {
                return (int) TypedValue.applyDimension(1, (float) dipValue, InAppBrowser.this.f21cordova.getActivity().getResources().getDisplayMetrics());
            }

            @SuppressLint({"NewApi"})
            public void run() {
                if (InAppBrowser.this.dialog != null) {
                    InAppBrowser.this.dialog.dismiss();
                }
                InAppBrowserDialog unused = InAppBrowser.this.dialog = new InAppBrowserDialog(InAppBrowser.this.f21cordova.getActivity(), 16973830);
                InAppBrowser.this.dialog.getWindow().getAttributes().windowAnimations = 16973826;
                InAppBrowser.this.dialog.requestWindowFeature(1);
                InAppBrowser.this.dialog.setCancelable(true);
                InAppBrowser.this.dialog.setInAppBroswer(InAppBrowser.this.getInAppBrowser());
                LinearLayout linearLayout = new LinearLayout(InAppBrowser.this.f21cordova.getActivity());
                linearLayout.setOrientation(1);
                RelativeLayout toolbar = new RelativeLayout(InAppBrowser.this.f21cordova.getActivity());
                toolbar.setBackgroundColor(-3355444);
                toolbar.setLayoutParams(new RelativeLayout.LayoutParams(-1, dpToPixels(44)));
                toolbar.setPadding(dpToPixels(2), dpToPixels(2), dpToPixels(2), dpToPixels(2));
                toolbar.setHorizontalGravity(3);
                toolbar.setVerticalGravity(48);
                RelativeLayout actionButtonContainer = new RelativeLayout(InAppBrowser.this.f21cordova.getActivity());
                actionButtonContainer.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
                actionButtonContainer.setHorizontalGravity(3);
                actionButtonContainer.setVerticalGravity(16);
                Integer num = 1;
                actionButtonContainer.setId(num.intValue());
                Button back = new Button(InAppBrowser.this.f21cordova.getActivity());
                RelativeLayout.LayoutParams backLayoutParams = new RelativeLayout.LayoutParams(-2, -1);
                backLayoutParams.addRule(5);
                back.setLayoutParams(backLayoutParams);
                back.setContentDescription("Back Button");
                Integer num2 = 2;
                back.setId(num2.intValue());
                Resources activityRes = InAppBrowser.this.f21cordova.getActivity().getResources();
                Drawable backIcon = activityRes.getDrawable(activityRes.getIdentifier("ic_action_previous_item", "drawable", InAppBrowser.this.f21cordova.getActivity().getPackageName()));
                if (Build.VERSION.SDK_INT < 16) {
                    back.setBackgroundDrawable(backIcon);
                } else {
                    back.setBackground(backIcon);
                }
                back.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        InAppBrowser.this.goBack();
                    }
                });
                Button button = new Button(InAppBrowser.this.f21cordova.getActivity());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
                layoutParams.addRule(1, 2);
                button.setLayoutParams(layoutParams);
                button.setContentDescription("Forward Button");
                Integer num3 = 3;
                button.setId(num3.intValue());
                Drawable fwdIcon = activityRes.getDrawable(activityRes.getIdentifier("ic_action_next_item", "drawable", InAppBrowser.this.f21cordova.getActivity().getPackageName()));
                if (Build.VERSION.SDK_INT < 16) {
                    button.setBackgroundDrawable(fwdIcon);
                } else {
                    button.setBackground(fwdIcon);
                }
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        InAppBrowser.this.goForward();
                    }
                });
                EditText unused2 = InAppBrowser.this.edittext = new EditText(InAppBrowser.this.f21cordova.getActivity());
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams2.addRule(1, 1);
                layoutParams2.addRule(0, 5);
                InAppBrowser.this.edittext.setLayoutParams(layoutParams2);
                Integer num4 = 4;
                InAppBrowser.this.edittext.setId(num4.intValue());
                InAppBrowser.this.edittext.setSingleLine(true);
                InAppBrowser.this.edittext.setText(url);
                InAppBrowser.this.edittext.setInputType(16);
                InAppBrowser.this.edittext.setImeOptions(2);
                InAppBrowser.this.edittext.setInputType(0);
                InAppBrowser.this.edittext.setOnKeyListener(new View.OnKeyListener() {
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() != 0 || keyCode != 66) {
                            return false;
                        }
                        InAppBrowser.this.navigate(InAppBrowser.this.edittext.getText().toString());
                        return true;
                    }
                });
                Button close = new Button(InAppBrowser.this.f21cordova.getActivity());
                RelativeLayout.LayoutParams closeLayoutParams = new RelativeLayout.LayoutParams(-2, -1);
                closeLayoutParams.addRule(11);
                close.setLayoutParams(closeLayoutParams);
                button.setContentDescription("Close Button");
                Integer num5 = 5;
                close.setId(num5.intValue());
                Drawable closeIcon = activityRes.getDrawable(activityRes.getIdentifier("ic_action_remove", "drawable", InAppBrowser.this.f21cordova.getActivity().getPackageName()));
                if (Build.VERSION.SDK_INT < 16) {
                    close.setBackgroundDrawable(closeIcon);
                } else {
                    close.setBackground(closeIcon);
                }
                close.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        InAppBrowser.this.closeDialog();
                    }
                });
                WebView unused3 = InAppBrowser.this.inAppWebView = new WebView(InAppBrowser.this.f21cordova.getActivity());
                InAppBrowser.this.inAppWebView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                Integer num6 = 6;
                InAppBrowser.this.inAppWebView.setId(num6.intValue());
                InAppBrowser.this.inAppWebView.setWebChromeClient(new InAppChromeClient(thatWebView));
                InAppBrowser.this.inAppWebView.setWebViewClient(new InAppBrowserClient(thatWebView, InAppBrowser.this.edittext));
                WebSettings settings = InAppBrowser.this.inAppWebView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setBuiltInZoomControls(InAppBrowser.this.showZoomControls);
                settings.setPluginState(WebSettings.PluginState.ON);
                if (Build.VERSION.SDK_INT >= 17) {
                    settings.setMediaPlaybackRequiresUserGesture(InAppBrowser.this.mediaPlaybackRequiresUserGesture);
                }
                String overrideUserAgent = InAppBrowser.this.preferences.getString("OverrideUserAgent", (String) null);
                String appendUserAgent = InAppBrowser.this.preferences.getString("AppendUserAgent", (String) null);
                if (overrideUserAgent != null) {
                    settings.setUserAgentString(overrideUserAgent);
                }
                if (appendUserAgent != null) {
                    settings.setUserAgentString(settings.getUserAgentString() + appendUserAgent);
                }
                Bundle appSettings = InAppBrowser.this.f21cordova.getActivity().getIntent().getExtras();
                if (appSettings == null ? true : appSettings.getBoolean("InAppBrowserStorageEnabled", true)) {
                    settings.setDatabasePath(InAppBrowser.this.f21cordova.getActivity().getApplicationContext().getDir("inAppBrowserDB", 0).getPath());
                    settings.setDatabaseEnabled(true);
                }
                settings.setDomStorageEnabled(true);
                if (InAppBrowser.this.clearAllCache) {
                    CookieManager.getInstance().removeAllCookie();
                } else if (InAppBrowser.this.clearSessionCache) {
                    CookieManager.getInstance().removeSessionCookie();
                }
                InAppBrowser.this.inAppWebView.loadUrl(url);
                Integer num7 = 6;
                InAppBrowser.this.inAppWebView.setId(num7.intValue());
                InAppBrowser.this.inAppWebView.getSettings().setLoadWithOverviewMode(true);
                InAppBrowser.this.inAppWebView.getSettings().setUseWideViewPort(true);
                InAppBrowser.this.inAppWebView.requestFocus();
                InAppBrowser.this.inAppWebView.requestFocusFromTouch();
                actionButtonContainer.addView(back);
                actionButtonContainer.addView(button);
                toolbar.addView(actionButtonContainer);
                toolbar.addView(InAppBrowser.this.edittext);
                toolbar.addView(close);
                if (InAppBrowser.this.getShowLocationBar()) {
                    linearLayout.addView(toolbar);
                }
                linearLayout.addView(InAppBrowser.this.inAppWebView);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(InAppBrowser.this.dialog.getWindow().getAttributes());
                lp.width = -1;
                lp.height = -1;
                InAppBrowser.this.dialog.setContentView(linearLayout);
                InAppBrowser.this.dialog.show();
                InAppBrowser.this.dialog.getWindow().setAttributes(lp);
                if (InAppBrowser.this.openWindowHidden) {
                    InAppBrowser.this.dialog.hide();
                }
            }
        });
        return "";
    }

    /* access modifiers changed from: private */
    public void sendUpdate(JSONObject obj, boolean keepCallback) {
        sendUpdate(obj, keepCallback, PluginResult.Status.OK);
    }

    /* access modifiers changed from: private */
    public void sendUpdate(JSONObject obj, boolean keepCallback, PluginResult.Status status) {
        if (this.callbackContext != null) {
            PluginResult result = new PluginResult(status, obj);
            result.setKeepCallback(keepCallback);
            this.callbackContext.sendPluginResult(result);
            if (!keepCallback) {
                this.callbackContext = null;
            }
        }
    }

    public class InAppBrowserClient extends WebViewClient {
        EditText edittext;
        CordovaWebView webView;

        public InAppBrowserClient(CordovaWebView webView2, EditText mEditText) {
            this.webView = webView2;
            this.edittext = mEditText;
        }

        public boolean shouldOverrideUrlLoading(WebView webView2, String url) {
            String address;
            if (url.startsWith("tel:")) {
                try {
                    Intent intent = new Intent("android.intent.action.DIAL");
                    intent.setData(Uri.parse(url));
                    InAppBrowser.this.f21cordova.getActivity().startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException e) {
                    LOG.m24e(InAppBrowser.LOG_TAG, "Error dialing " + url + ": " + e.toString());
                }
            } else if (url.startsWith("geo:") || url.startsWith("mailto:") || url.startsWith("market:")) {
                try {
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setData(Uri.parse(url));
                    InAppBrowser.this.f21cordova.getActivity().startActivity(intent2);
                    return true;
                } catch (ActivityNotFoundException e2) {
                    LOG.m24e(InAppBrowser.LOG_TAG, "Error with " + url + ": " + e2.toString());
                }
            } else {
                if (url.startsWith("sms:")) {
                    try {
                        Intent intent3 = new Intent("android.intent.action.VIEW");
                        int parmIndex = url.indexOf(63);
                        if (parmIndex == -1) {
                            address = url.substring(4);
                        } else {
                            address = url.substring(4, parmIndex);
                            String query = Uri.parse(url).getQuery();
                            if (query != null && query.startsWith("body=")) {
                                intent3.putExtra("sms_body", query.substring(5));
                            }
                        }
                        intent3.setData(Uri.parse("sms:" + address));
                        intent3.putExtra("address", address);
                        intent3.setType("vnd.android-dir/mms-sms");
                        InAppBrowser.this.f21cordova.getActivity().startActivity(intent3);
                        return true;
                    } catch (ActivityNotFoundException e3) {
                        LOG.m24e(InAppBrowser.LOG_TAG, "Error sending sms " + url + ":" + e3.toString());
                    }
                }
                return false;
            }
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            String newloc;
            super.onPageStarted(view, url, favicon);
            if (url.startsWith("http:") || url.startsWith("https:") || url.startsWith("file:")) {
                newloc = url;
            } else {
                LOG.m24e(InAppBrowser.LOG_TAG, "Possible Uncaught/Unknown URI");
                newloc = "http://" + url;
            }
            if (!newloc.equals(this.edittext.getText().toString())) {
                this.edittext.setText(newloc);
            }
            try {
                JSONObject obj = new JSONObject();
                obj.put(Globalization.TYPE, InAppBrowser.LOAD_START_EVENT);
                obj.put("url", newloc);
                InAppBrowser.this.sendUpdate(obj, true);
            } catch (JSONException e) {
                LOG.m24e(InAppBrowser.LOG_TAG, "URI passed in has caused a JSON error.");
            }
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (Build.VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().flush();
            } else {
                CookieSyncManager.getInstance().sync();
            }
            try {
                JSONObject obj = new JSONObject();
                obj.put(Globalization.TYPE, InAppBrowser.LOAD_STOP_EVENT);
                obj.put("url", url);
                InAppBrowser.this.sendUpdate(obj, true);
            } catch (JSONException e) {
                Log.d(InAppBrowser.LOG_TAG, "Should never happen");
            }
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            try {
                JSONObject obj = new JSONObject();
                obj.put(Globalization.TYPE, InAppBrowser.LOAD_ERROR_EVENT);
                obj.put("url", failingUrl);
                obj.put("code", errorCode);
                obj.put("message", description);
                InAppBrowser.this.sendUpdate(obj, true, PluginResult.Status.ERROR);
            } catch (JSONException e) {
                Log.d(InAppBrowser.LOG_TAG, "Should never happen");
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: org.apache.cordova.PluginManager} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: org.apache.cordova.PluginManager} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onReceivedHttpAuthRequest(android.webkit.WebView r8, android.webkit.HttpAuthHandler r9, java.lang.String r10, java.lang.String r11) {
            /*
                r7 = this;
                r2 = 0
                org.apache.cordova.CordovaWebView r4 = r7.webView     // Catch:{ NoSuchMethodException -> 0x0051, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x004d }
                java.lang.Class r4 = r4.getClass()     // Catch:{ NoSuchMethodException -> 0x0051, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x004d }
                java.lang.String r5 = "getPluginManager"
                r6 = 0
                java.lang.Class[] r6 = new java.lang.Class[r6]     // Catch:{ NoSuchMethodException -> 0x0051, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x004d }
                java.lang.reflect.Method r1 = r4.getMethod(r5, r6)     // Catch:{ NoSuchMethodException -> 0x0051, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x004d }
                org.apache.cordova.CordovaWebView r4 = r7.webView     // Catch:{ NoSuchMethodException -> 0x0051, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x004d }
                r5 = 0
                java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ NoSuchMethodException -> 0x0051, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x004d }
                java.lang.Object r4 = r1.invoke(r4, r5)     // Catch:{ NoSuchMethodException -> 0x0051, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x004d }
                r0 = r4
                org.apache.cordova.PluginManager r0 = (org.apache.cordova.PluginManager) r0     // Catch:{ NoSuchMethodException -> 0x0051, IllegalAccessException -> 0x004f, InvocationTargetException -> 0x004d }
                r2 = r0
            L_0x001d:
                if (r2 != 0) goto L_0x0035
                org.apache.cordova.CordovaWebView r4 = r7.webView     // Catch:{ NoSuchFieldException -> 0x004b, IllegalAccessException -> 0x0049 }
                java.lang.Class r4 = r4.getClass()     // Catch:{ NoSuchFieldException -> 0x004b, IllegalAccessException -> 0x0049 }
                java.lang.String r5 = "pluginManager"
                java.lang.reflect.Field r3 = r4.getField(r5)     // Catch:{ NoSuchFieldException -> 0x004b, IllegalAccessException -> 0x0049 }
                org.apache.cordova.CordovaWebView r4 = r7.webView     // Catch:{ NoSuchFieldException -> 0x004b, IllegalAccessException -> 0x0049 }
                java.lang.Object r4 = r3.get(r4)     // Catch:{ NoSuchFieldException -> 0x004b, IllegalAccessException -> 0x0049 }
                r0 = r4
                org.apache.cordova.PluginManager r0 = (org.apache.cordova.PluginManager) r0     // Catch:{ NoSuchFieldException -> 0x004b, IllegalAccessException -> 0x0049 }
                r2 = r0
            L_0x0035:
                if (r2 == 0) goto L_0x0045
                org.apache.cordova.CordovaWebView r4 = r7.webView
                org.apache.cordova.CordovaHttpAuthHandler r5 = new org.apache.cordova.CordovaHttpAuthHandler
                r5.<init>(r9)
                boolean r4 = r2.onReceivedHttpAuthRequest(r4, r5, r10, r11)
                if (r4 == 0) goto L_0x0045
            L_0x0044:
                return
            L_0x0045:
                super.onReceivedHttpAuthRequest(r8, r9, r10, r11)
                goto L_0x0044
            L_0x0049:
                r4 = move-exception
                goto L_0x0035
            L_0x004b:
                r4 = move-exception
                goto L_0x0035
            L_0x004d:
                r4 = move-exception
                goto L_0x001d
            L_0x004f:
                r4 = move-exception
                goto L_0x001d
            L_0x0051:
                r4 = move-exception
                goto L_0x001d
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.inappbrowser.InAppBrowser.InAppBrowserClient.onReceivedHttpAuthRequest(android.webkit.WebView, android.webkit.HttpAuthHandler, java.lang.String, java.lang.String):void");
        }
    }
}
