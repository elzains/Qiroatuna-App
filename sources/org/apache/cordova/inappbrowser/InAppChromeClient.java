package org.apache.cordova.inappbrowser;

import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class InAppChromeClient extends WebChromeClient {
    private String LOG_TAG = "InAppChromeClient";
    private long MAX_QUOTA = 104857600;
    private CordovaWebView webView;

    public InAppChromeClient(CordovaWebView webView2) {
        this.webView = webView2;
    }

    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota, long estimatedSize, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
        LOG.m23d(this.LOG_TAG, "onExceededDatabaseQuota estimatedSize: %d  currentQuota: %d  totalUsedQuota: %d", Long.valueOf(estimatedSize), Long.valueOf(currentQuota), Long.valueOf(totalUsedQuota));
        quotaUpdater.updateQuota(this.MAX_QUOTA);
    }

    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        super.onGeolocationPermissionsShowPrompt(origin, callback);
        callback.invoke(origin, true, false);
    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        PluginResult scriptResult;
        if (defaultValue != null && defaultValue.startsWith("gap")) {
            if (defaultValue.startsWith("gap-iab://")) {
                String scriptCallbackId = defaultValue.substring(10);
                if (scriptCallbackId.startsWith("InAppBrowser")) {
                    if (message == null || message.length() == 0) {
                        scriptResult = new PluginResult(PluginResult.Status.OK, new JSONArray());
                    } else {
                        try {
                            scriptResult = new PluginResult(PluginResult.Status.OK, new JSONArray(message));
                        } catch (JSONException e) {
                            scriptResult = new PluginResult(PluginResult.Status.JSON_EXCEPTION, e.getMessage());
                        }
                    }
                    this.webView.sendPluginResult(scriptResult, scriptCallbackId);
                    result.confirm("");
                    return true;
                }
            } else {
                LOG.m33w(this.LOG_TAG, "InAppBrowser does not support Cordova API calls: " + url + " " + defaultValue);
                result.cancel();
                return true;
            }
        }
        return false;
    }
}
