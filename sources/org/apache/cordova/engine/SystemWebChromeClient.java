package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import java.util.Arrays;
import org.apache.cordova.CordovaDialogsHelper;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

public class SystemWebChromeClient extends WebChromeClient {
    private static final int FILECHOOSER_RESULTCODE = 5173;
    private static final String LOG_TAG = "SystemWebChromeClient";
    private long MAX_QUOTA = 104857600;
    private Context appContext;
    private CordovaDialogsHelper dialogsHelper;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private View mVideoProgressView;
    protected final SystemWebViewEngine parentEngine;

    public SystemWebChromeClient(SystemWebViewEngine parentEngine2) {
        this.parentEngine = parentEngine2;
        this.appContext = parentEngine2.webView.getContext();
        this.dialogsHelper = new CordovaDialogsHelper(this.appContext);
    }

    public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
        this.dialogsHelper.showAlert(message, new CordovaDialogsHelper.Result() {
            public void gotResult(boolean success, String value) {
                if (success) {
                    result.confirm();
                } else {
                    result.cancel();
                }
            }
        });
        return true;
    }

    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        this.dialogsHelper.showConfirm(message, new CordovaDialogsHelper.Result() {
            public void gotResult(boolean success, String value) {
                if (success) {
                    result.confirm();
                } else {
                    result.cancel();
                }
            }
        });
        return true;
    }

    public boolean onJsPrompt(WebView view, String origin, String message, String defaultValue, final JsPromptResult result) {
        String handledRet = this.parentEngine.bridge.promptOnJsPrompt(origin, message, defaultValue);
        if (handledRet != null) {
            result.confirm(handledRet);
            return true;
        }
        this.dialogsHelper.showPrompt(message, defaultValue, new CordovaDialogsHelper.Result() {
            public void gotResult(boolean success, String value) {
                if (success) {
                    result.confirm(value);
                } else {
                    result.cancel();
                }
            }
        });
        return true;
    }

    public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota, long estimatedSize, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
        LOG.m23d(LOG_TAG, "onExceededDatabaseQuota estimatedSize: %d  currentQuota: %d  totalUsedQuota: %d", Long.valueOf(estimatedSize), Long.valueOf(currentQuota), Long.valueOf(totalUsedQuota));
        quotaUpdater.updateQuota(this.MAX_QUOTA);
    }

    public void onConsoleMessage(String message, int lineNumber, String sourceID) {
        if (Build.VERSION.SDK_INT == 7) {
            LOG.m23d(LOG_TAG, "%s: Line %d : %s", sourceID, Integer.valueOf(lineNumber), message);
            super.onConsoleMessage(message, lineNumber, sourceID);
        }
    }

    @TargetApi(8)
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        if (consoleMessage.message() != null) {
            LOG.m23d(LOG_TAG, "%s: Line %d : %s", consoleMessage.sourceId(), Integer.valueOf(consoleMessage.lineNumber()), consoleMessage.message());
        }
        return super.onConsoleMessage(consoleMessage);
    }

    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        super.onGeolocationPermissionsShowPrompt(origin, callback);
        callback.invoke(origin, true, false);
        CordovaPlugin geolocation = this.parentEngine.pluginManager.getPlugin("Geolocation");
        if (geolocation != null && !geolocation.hasPermisssion()) {
            geolocation.requestPermissions(0);
        }
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
        this.parentEngine.getCordovaWebView().showCustomView(view, callback);
    }

    public void onHideCustomView() {
        this.parentEngine.getCordovaWebView().hideCustomView();
    }

    public View getVideoLoadingProgressView() {
        if (this.mVideoProgressView == null) {
            LinearLayout layout = new LinearLayout(this.parentEngine.getView().getContext());
            layout.setOrientation(1);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            layout.setLayoutParams(layoutParams);
            ProgressBar bar = new ProgressBar(this.parentEngine.getView().getContext());
            LinearLayout.LayoutParams barLayoutParams = new LinearLayout.LayoutParams(-2, -2);
            barLayoutParams.gravity = 17;
            bar.setLayoutParams(barLayoutParams);
            layout.addView(bar);
            this.mVideoProgressView = layout;
        }
        return this.mVideoProgressView;
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "*/*");
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        openFileChooser(uploadMsg, acceptType, (String) null);
    }

    public void openFileChooser(final ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        this.parentEngine.f27cordova.startActivityForResult(new CordovaPlugin() {
            public void onActivityResult(int requestCode, int resultCode, Intent intent) {
                Uri result = (intent == null || resultCode != -1) ? null : intent.getData();
                LOG.m21d(SystemWebChromeClient.LOG_TAG, "Receive file chooser URL: " + result);
                uploadMsg.onReceiveValue(result);
            }
        }, intent, FILECHOOSER_RESULTCODE);
    }

    @TargetApi(21)
    public boolean onShowFileChooser(WebView webView, final ValueCallback<Uri[]> filePathsCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        try {
            this.parentEngine.f27cordova.startActivityForResult(new CordovaPlugin() {
                public void onActivityResult(int requestCode, int resultCode, Intent intent) {
                    Uri[] result = WebChromeClient.FileChooserParams.parseResult(resultCode, intent);
                    LOG.m21d(SystemWebChromeClient.LOG_TAG, "Receive file chooser URL: " + result);
                    filePathsCallback.onReceiveValue(result);
                }
            }, fileChooserParams.createIntent(), FILECHOOSER_RESULTCODE);
            return true;
        } catch (ActivityNotFoundException e) {
            LOG.m36w("No activity found to handle file chooser intent.", (Throwable) e);
            filePathsCallback.onReceiveValue((Object) null);
            return true;
        }
    }

    @TargetApi(21)
    public void onPermissionRequest(PermissionRequest request) {
        LOG.m21d(LOG_TAG, "onPermissionRequest: " + Arrays.toString(request.getResources()));
        request.grant(request.getResources());
    }

    public void destroyLastDialog() {
        this.dialogsHelper.destroyLastDialog();
    }
}
