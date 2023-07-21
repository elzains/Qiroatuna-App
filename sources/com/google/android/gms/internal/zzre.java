package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.util.Map;

@zzme
@TargetApi(11)
public class zzre extends zzrg {
    public zzre(zzqw zzqw, boolean z) {
        super(zzqw, z);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zza(webView, str, (Map<String, String>) null);
    }
}
