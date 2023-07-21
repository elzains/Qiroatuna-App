package org.apache.cordova.inappbrowser;

import android.app.Dialog;
import android.content.Context;

public class InAppBrowserDialog extends Dialog {
    Context context;
    InAppBrowser inAppBrowser = null;

    public InAppBrowserDialog(Context context2, int theme) {
        super(context2, theme);
        this.context = context2;
    }

    public void setInAppBroswer(InAppBrowser browser) {
        this.inAppBrowser = browser;
    }

    public void onBackPressed() {
        if (this.inAppBrowser == null) {
            dismiss();
        } else if (!this.inAppBrowser.hardwareBack() || !this.inAppBrowser.canGoBack()) {
            this.inAppBrowser.closeDialog();
        } else {
            this.inAppBrowser.goBack();
        }
    }
}
