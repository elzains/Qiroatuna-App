package org.apache.cordova;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import java.util.Collection;
import java.util.LinkedHashMap;
import org.apache.cordova.PluginResult;
import org.json.JSONException;

public class PluginManager {
    private static final int SLOW_EXEC_WARNING_THRESHOLD = (Debug.isDebuggerConnected() ? 60 : 16);
    private static String TAG = "PluginManager";
    private final CordovaWebView app;
    private final CordovaInterface ctx;
    private final LinkedHashMap<String, PluginEntry> entryMap = new LinkedHashMap<>();
    private boolean isInitialized;
    private CordovaPlugin permissionRequester;
    private final LinkedHashMap<String, CordovaPlugin> pluginMap = new LinkedHashMap<>();

    public PluginManager(CordovaWebView cordovaWebView, CordovaInterface cordova2, Collection<PluginEntry> pluginEntries) {
        this.ctx = cordova2;
        this.app = cordovaWebView;
        setPluginEntries(pluginEntries);
    }

    public Collection<PluginEntry> getPluginEntries() {
        return this.entryMap.values();
    }

    public void setPluginEntries(Collection<PluginEntry> pluginEntries) {
        if (this.isInitialized) {
            onPause(false);
            onDestroy();
            this.pluginMap.clear();
            this.entryMap.clear();
        }
        for (PluginEntry entry : pluginEntries) {
            addService(entry);
        }
        if (this.isInitialized) {
            startupPlugins();
        }
    }

    public void init() {
        LOG.m21d(TAG, "init()");
        this.isInitialized = true;
        onPause(false);
        onDestroy();
        this.pluginMap.clear();
        startupPlugins();
    }

    private void startupPlugins() {
        for (PluginEntry entry : this.entryMap.values()) {
            if (entry.onload) {
                getPlugin(entry.service);
            } else {
                this.pluginMap.put(entry.service, (Object) null);
            }
        }
    }

    public void exec(String service, String action, String callbackId, String rawArgs) {
        CordovaPlugin plugin = getPlugin(service);
        if (plugin == null) {
            LOG.m21d(TAG, "exec() call to unknown plugin: " + service);
            this.app.sendPluginResult(new PluginResult(PluginResult.Status.CLASS_NOT_FOUND_EXCEPTION), callbackId);
            return;
        }
        CallbackContext callbackContext = new CallbackContext(callbackId, this.app);
        try {
            long pluginStartTime = System.currentTimeMillis();
            boolean wasValidAction = plugin.execute(action, rawArgs, callbackContext);
            long duration = System.currentTimeMillis() - pluginStartTime;
            if (duration > ((long) SLOW_EXEC_WARNING_THRESHOLD)) {
                LOG.m33w(TAG, "THREAD WARNING: exec() call to " + service + "." + action + " blocked the main thread for " + duration + "ms. Plugin should use CordovaInterface.getThreadPool().");
            }
            if (!wasValidAction) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.INVALID_ACTION));
            }
        } catch (JSONException e) {
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
        } catch (Exception e2) {
            LOG.m25e(TAG, "Uncaught exception from plugin", (Throwable) e2);
            callbackContext.error(e2.getMessage());
        }
    }

    public CordovaPlugin getPlugin(String service) {
        CordovaPlugin ret = this.pluginMap.get(service);
        if (ret == null) {
            PluginEntry pe = this.entryMap.get(service);
            if (pe == null) {
                return null;
            }
            if (pe.plugin != null) {
                ret = pe.plugin;
            } else {
                ret = instantiatePlugin(pe.pluginClass);
            }
            ret.privateInitialize(service, this.ctx, this.app, this.app.getPreferences());
            this.pluginMap.put(service, ret);
        }
        return ret;
    }

    public void addService(String service, String className) {
        addService(new PluginEntry(service, className, false));
    }

    public void addService(PluginEntry entry) {
        this.entryMap.put(entry.service, entry);
        if (entry.plugin != null) {
            entry.plugin.privateInitialize(entry.service, this.ctx, this.app, this.app.getPreferences());
            this.pluginMap.put(entry.service, entry.plugin);
        }
    }

    public void onPause(boolean multitasking) {
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null) {
                plugin.onPause(multitasking);
            }
        }
    }

    public boolean onReceivedHttpAuthRequest(CordovaWebView view, ICordovaHttpAuthHandler handler, String host, String realm) {
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null && plugin.onReceivedHttpAuthRequest(this.app, handler, host, realm)) {
                return true;
            }
        }
        return false;
    }

    public boolean onReceivedClientCertRequest(CordovaWebView view, ICordovaClientCertRequest request) {
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null && plugin.onReceivedClientCertRequest(this.app, request)) {
                return true;
            }
        }
        return false;
    }

    public void onResume(boolean multitasking) {
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null) {
                plugin.onResume(multitasking);
            }
        }
    }

    public void onStart() {
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null) {
                plugin.onStart();
            }
        }
    }

    public void onStop() {
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null) {
                plugin.onStop();
            }
        }
    }

    public void onDestroy() {
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null) {
                plugin.onDestroy();
            }
        }
    }

    public Object postMessage(String id, Object data) {
        Object obj;
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null && (obj = plugin.onMessage(id, data)) != null) {
                return obj;
            }
        }
        return this.ctx.onMessage(id, data);
    }

    public void onNewIntent(Intent intent) {
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null) {
                plugin.onNewIntent(intent);
            }
        }
    }

    public boolean shouldAllowRequest(String url) {
        Boolean result;
        for (PluginEntry entry : this.entryMap.values()) {
            CordovaPlugin plugin = this.pluginMap.get(entry.service);
            if (plugin != null && (result = plugin.shouldAllowRequest(url)) != null) {
                return result.booleanValue();
            }
        }
        if (url.startsWith("blob:") || url.startsWith("data:") || url.startsWith("about:blank") || url.startsWith("https://ssl.gstatic.com/accessibility/javascript/android/")) {
            return true;
        }
        if (!url.startsWith("file://")) {
            return false;
        }
        if (url.contains("/app_webview/")) {
            return false;
        }
        return true;
    }

    public boolean shouldAllowNavigation(String url) {
        Boolean result;
        for (PluginEntry entry : this.entryMap.values()) {
            CordovaPlugin plugin = this.pluginMap.get(entry.service);
            if (plugin != null && (result = plugin.shouldAllowNavigation(url)) != null) {
                return result.booleanValue();
            }
        }
        return url.startsWith("file://") || url.startsWith("about:blank");
    }

    public boolean shouldAllowBridgeAccess(String url) {
        Boolean result;
        for (PluginEntry entry : this.entryMap.values()) {
            CordovaPlugin plugin = this.pluginMap.get(entry.service);
            if (plugin != null && (result = plugin.shouldAllowBridgeAccess(url)) != null) {
                return result.booleanValue();
            }
        }
        return url.startsWith("file://");
    }

    public Boolean shouldOpenExternalUrl(String url) {
        Boolean result;
        for (PluginEntry entry : this.entryMap.values()) {
            CordovaPlugin plugin = this.pluginMap.get(entry.service);
            if (plugin != null && (result = plugin.shouldOpenExternalUrl(url)) != null) {
                return result;
            }
        }
        return false;
    }

    public boolean onOverrideUrlLoading(String url) {
        for (PluginEntry entry : this.entryMap.values()) {
            CordovaPlugin plugin = this.pluginMap.get(entry.service);
            if (plugin != null && plugin.onOverrideUrlLoading(url)) {
                return true;
            }
        }
        return false;
    }

    public void onReset() {
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null) {
                plugin.onReset();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Uri remapUri(Uri uri) {
        Uri ret;
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null && (ret = plugin.remapUri(uri)) != null) {
                return ret;
            }
        }
        return null;
    }

    private CordovaPlugin instantiatePlugin(String className) {
        Class<?> c = null;
        if (className != null) {
            try {
                if (!"".equals(className)) {
                    c = Class.forName(className);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error adding plugin " + className + ".");
                return null;
            }
        }
        if ((c != null) && CordovaPlugin.class.isAssignableFrom(c)) {
            return (CordovaPlugin) c.newInstance();
        }
        return null;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (plugin != null) {
                plugin.onConfigurationChanged(newConfig);
            }
        }
    }

    public Bundle onSaveInstanceState() {
        Bundle pluginState;
        Bundle state = new Bundle();
        for (CordovaPlugin plugin : this.pluginMap.values()) {
            if (!(plugin == null || (pluginState = plugin.onSaveInstanceState()) == null)) {
                state.putBundle(plugin.getServiceName(), pluginState);
            }
        }
        return state;
    }
}
