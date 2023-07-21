package org.apache.cordova;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import java.util.HashMap;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CoreAndroid extends CordovaPlugin {
    public static final String PLUGIN_NAME = "CoreAndroid";
    protected static final String TAG = "CordovaApp";
    private CallbackContext messageChannel;
    private final Object messageChannelLock = new Object();
    private PluginResult pendingResume;
    private BroadcastReceiver telephonyReceiver;

    public void fireJavascriptEvent(String action) {
        sendEventMessage(action);
    }

    public void pluginInitialize() {
        initTelephonyReceiver();
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean execute(java.lang.String r8, org.json.JSONArray r9, org.apache.cordova.CallbackContext r10) throws org.json.JSONException {
        /*
            r7 = this;
            r3 = 1
            r4 = 0
            org.apache.cordova.PluginResult$Status r2 = org.apache.cordova.PluginResult.Status.OK
            java.lang.String r1 = ""
            java.lang.String r5 = "clearCache"
            boolean r5 = r8.equals(r5)     // Catch:{ JSONException -> 0x0031 }
            if (r5 == 0) goto L_0x001a
            r7.clearCache()     // Catch:{ JSONException -> 0x0031 }
        L_0x0011:
            org.apache.cordova.PluginResult r5 = new org.apache.cordova.PluginResult     // Catch:{ JSONException -> 0x0031 }
            r5.<init>((org.apache.cordova.PluginResult.Status) r2, (java.lang.String) r1)     // Catch:{ JSONException -> 0x0031 }
            r10.sendPluginResult(r5)     // Catch:{ JSONException -> 0x0031 }
        L_0x0019:
            return r3
        L_0x001a:
            java.lang.String r5 = "show"
            boolean r5 = r8.equals(r5)     // Catch:{ JSONException -> 0x0031 }
            if (r5 == 0) goto L_0x003e
            org.apache.cordova.CordovaInterface r5 = r7.f21cordova     // Catch:{ JSONException -> 0x0031 }
            android.app.Activity r5 = r5.getActivity()     // Catch:{ JSONException -> 0x0031 }
            org.apache.cordova.CoreAndroid$1 r6 = new org.apache.cordova.CoreAndroid$1     // Catch:{ JSONException -> 0x0031 }
            r6.<init>()     // Catch:{ JSONException -> 0x0031 }
            r5.runOnUiThread(r6)     // Catch:{ JSONException -> 0x0031 }
            goto L_0x0011
        L_0x0031:
            r0 = move-exception
            org.apache.cordova.PluginResult r3 = new org.apache.cordova.PluginResult
            org.apache.cordova.PluginResult$Status r5 = org.apache.cordova.PluginResult.Status.JSON_EXCEPTION
            r3.<init>(r5)
            r10.sendPluginResult(r3)
            r3 = r4
            goto L_0x0019
        L_0x003e:
            java.lang.String r5 = "loadUrl"
            boolean r5 = r8.equals(r5)     // Catch:{ JSONException -> 0x0031 }
            if (r5 == 0) goto L_0x0054
            r5 = 0
            java.lang.String r5 = r9.getString(r5)     // Catch:{ JSONException -> 0x0031 }
            r6 = 1
            org.json.JSONObject r6 = r9.optJSONObject(r6)     // Catch:{ JSONException -> 0x0031 }
            r7.loadUrl(r5, r6)     // Catch:{ JSONException -> 0x0031 }
            goto L_0x0011
        L_0x0054:
            java.lang.String r5 = "cancelLoadUrl"
            boolean r5 = r8.equals(r5)     // Catch:{ JSONException -> 0x0031 }
            if (r5 != 0) goto L_0x0011
            java.lang.String r5 = "clearHistory"
            boolean r5 = r8.equals(r5)     // Catch:{ JSONException -> 0x0031 }
            if (r5 == 0) goto L_0x0068
            r7.clearHistory()     // Catch:{ JSONException -> 0x0031 }
            goto L_0x0011
        L_0x0068:
            java.lang.String r5 = "backHistory"
            boolean r5 = r8.equals(r5)     // Catch:{ JSONException -> 0x0031 }
            if (r5 == 0) goto L_0x0074
            r7.backHistory()     // Catch:{ JSONException -> 0x0031 }
            goto L_0x0011
        L_0x0074:
            java.lang.String r5 = "overrideButton"
            boolean r5 = r8.equals(r5)     // Catch:{ JSONException -> 0x0031 }
            if (r5 == 0) goto L_0x008a
            r5 = 0
            java.lang.String r5 = r9.getString(r5)     // Catch:{ JSONException -> 0x0031 }
            r6 = 1
            boolean r6 = r9.getBoolean(r6)     // Catch:{ JSONException -> 0x0031 }
            r7.overrideButton(r5, r6)     // Catch:{ JSONException -> 0x0031 }
            goto L_0x0011
        L_0x008a:
            java.lang.String r5 = "overrideBackbutton"
            boolean r5 = r8.equals(r5)     // Catch:{ JSONException -> 0x0031 }
            if (r5 == 0) goto L_0x009c
            r5 = 0
            boolean r5 = r9.getBoolean(r5)     // Catch:{ JSONException -> 0x0031 }
            r7.overrideBackbutton(r5)     // Catch:{ JSONException -> 0x0031 }
            goto L_0x0011
        L_0x009c:
            java.lang.String r5 = "exitApp"
            boolean r5 = r8.equals(r5)     // Catch:{ JSONException -> 0x0031 }
            if (r5 == 0) goto L_0x00a9
            r7.exitApp()     // Catch:{ JSONException -> 0x0031 }
            goto L_0x0011
        L_0x00a9:
            java.lang.String r5 = "messageChannel"
            boolean r5 = r8.equals(r5)     // Catch:{ JSONException -> 0x0031 }
            if (r5 == 0) goto L_0x0011
            java.lang.Object r5 = r7.messageChannelLock     // Catch:{ JSONException -> 0x0031 }
            monitor-enter(r5)     // Catch:{ JSONException -> 0x0031 }
            r7.messageChannel = r10     // Catch:{ all -> 0x00c5 }
            org.apache.cordova.PluginResult r6 = r7.pendingResume     // Catch:{ all -> 0x00c5 }
            if (r6 == 0) goto L_0x00c2
            org.apache.cordova.PluginResult r6 = r7.pendingResume     // Catch:{ all -> 0x00c5 }
            r7.sendEventMessage((org.apache.cordova.PluginResult) r6)     // Catch:{ all -> 0x00c5 }
            r6 = 0
            r7.pendingResume = r6     // Catch:{ all -> 0x00c5 }
        L_0x00c2:
            monitor-exit(r5)     // Catch:{ all -> 0x00c5 }
            goto L_0x0019
        L_0x00c5:
            r3 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x00c5 }
            throw r3     // Catch:{ JSONException -> 0x0031 }
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.CoreAndroid.execute(java.lang.String, org.json.JSONArray, org.apache.cordova.CallbackContext):boolean");
    }

    public void clearCache() {
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                CoreAndroid.this.webView.clearCache(true);
            }
        });
    }

    public void loadUrl(String url, JSONObject props) throws JSONException {
        LOG.m21d("App", "App.loadUrl(" + url + "," + props + ")");
        int wait = 0;
        boolean openExternal = false;
        boolean clearHistory = false;
        HashMap<String, Object> params = new HashMap<>();
        if (props != null) {
            JSONArray keys = props.names();
            for (int i = 0; i < keys.length(); i++) {
                String key = keys.getString(i);
                if (key.equals("wait")) {
                    wait = props.getInt(key);
                } else if (key.equalsIgnoreCase("openexternal")) {
                    openExternal = props.getBoolean(key);
                } else if (key.equalsIgnoreCase("clearhistory")) {
                    clearHistory = props.getBoolean(key);
                } else {
                    Object value = props.get(key);
                    if (value != null) {
                        if (value.getClass().equals(String.class)) {
                            params.put(key, (String) value);
                        } else if (value.getClass().equals(Boolean.class)) {
                            params.put(key, (Boolean) value);
                        } else if (value.getClass().equals(Integer.class)) {
                            params.put(key, (Integer) value);
                        }
                    }
                }
            }
        }
        if (wait > 0) {
            try {
                synchronized (this) {
                    wait((long) wait);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.webView.showWebPage(url, openExternal, clearHistory, params);
    }

    public void clearHistory() {
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                CoreAndroid.this.webView.clearHistory();
            }
        });
    }

    public void backHistory() {
        this.f21cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                CoreAndroid.this.webView.backHistory();
            }
        });
    }

    public void overrideBackbutton(boolean override) {
        LOG.m27i("App", "WARNING: Back Button Default Behavior will be overridden.  The backbutton event will be fired!");
        this.webView.setButtonPlumbedToJs(4, override);
    }

    public void overrideButton(String button, boolean override) {
        LOG.m27i("App", "WARNING: Volume Button Default Behavior will be overridden.  The volume event will be fired!");
        if (button.equals("volumeup")) {
            this.webView.setButtonPlumbedToJs(24, override);
        } else if (button.equals("volumedown")) {
            this.webView.setButtonPlumbedToJs(25, override);
        } else if (button.equals("menubutton")) {
            this.webView.setButtonPlumbedToJs(82, override);
        }
    }

    public boolean isBackbuttonOverridden() {
        return this.webView.isButtonPlumbedToJs(4);
    }

    public void exitApp() {
        this.webView.getPluginManager().postMessage("exit", (Object) null);
    }

    private void initTelephonyReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        this.telephonyReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (intent != null && intent.getAction().equals("android.intent.action.PHONE_STATE") && intent.hasExtra("state")) {
                    String extraData = intent.getStringExtra("state");
                    if (extraData.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                        LOG.m27i(CoreAndroid.TAG, "Telephone RINGING");
                        CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "ringing");
                    } else if (extraData.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                        LOG.m27i(CoreAndroid.TAG, "Telephone OFFHOOK");
                        CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "offhook");
                    } else if (extraData.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                        LOG.m27i(CoreAndroid.TAG, "Telephone IDLE");
                        CoreAndroid.this.webView.getPluginManager().postMessage("telephone", "idle");
                    }
                }
            }
        };
        this.webView.getContext().registerReceiver(this.telephonyReceiver, intentFilter);
    }

    private void sendEventMessage(String action) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("action", action);
        } catch (JSONException e) {
            LOG.m25e(TAG, "Failed to create event message", (Throwable) e);
        }
        sendEventMessage(new PluginResult(PluginResult.Status.OK, obj));
    }

    private void sendEventMessage(PluginResult payload) {
        payload.setKeepCallback(true);
        if (this.messageChannel != null) {
            this.messageChannel.sendPluginResult(payload);
        }
    }

    public void onDestroy() {
        this.webView.getContext().unregisterReceiver(this.telephonyReceiver);
    }

    public void sendResumeEvent(PluginResult resumeEvent) {
        synchronized (this.messageChannelLock) {
            if (this.messageChannel != null) {
                sendEventMessage(resumeEvent);
            } else {
                this.pendingResume = resumeEvent;
            }
        }
    }

    public static Object getBuildConfigValue(Context ctx, String key) {
        try {
            return Class.forName(ctx.getPackageName() + ".BuildConfig").getField(key).get((Object) null);
        } catch (ClassNotFoundException e) {
            LOG.m21d(TAG, "Unable to get the BuildConfig, is this built with ANT?");
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException e2) {
            LOG.m21d(TAG, key + " is not a valid field. Check your build.gradle");
            return null;
        } catch (IllegalAccessException e3) {
            LOG.m21d(TAG, "Illegal Access Exception: Let's print a stack trace.");
            e3.printStackTrace();
            return null;
        }
    }
}
