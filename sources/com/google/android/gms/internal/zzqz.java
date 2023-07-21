package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.internal.zzcy;
import java.util.Map;
import org.json.JSONObject;

@zzme
class zzqz extends FrameLayout implements zzqw {
    private static final int zzNf = Color.argb(0, 0, 0, 0);
    private final zzqw zzZX;
    private final zzqv zzZY;

    public zzqz(zzqw zzqw) {
        super(zzqw.getContext());
        this.zzZX = zzqw;
        this.zzZY = new zzqv(zzqw.zzls(), this, this);
        zzqx zzlv = this.zzZX.zzlv();
        if (zzlv != null) {
            zzlv.zzo(this);
        }
        addView(this.zzZX.getView());
    }

    public void destroy() {
        this.zzZX.destroy();
    }

    public String getRequestId() {
        return this.zzZX.getRequestId();
    }

    public int getRequestedOrientation() {
        return this.zzZX.getRequestedOrientation();
    }

    public View getView() {
        return this;
    }

    public WebView getWebView() {
        return this.zzZX.getWebView();
    }

    public boolean isDestroyed() {
        return this.zzZX.isDestroyed();
    }

    public void loadData(String str, String str2, String str3) {
        this.zzZX.loadData(str, str2, str3);
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.zzZX.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public void loadUrl(String str) {
        this.zzZX.loadUrl(str);
    }

    public void onPause() {
        this.zzZY.onPause();
        this.zzZX.onPause();
    }

    public void onResume() {
        this.zzZX.onResume();
    }

    public void setContext(Context context) {
        this.zzZX.setContext(context);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzZX.setOnClickListener(onClickListener);
    }

    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.zzZX.setOnTouchListener(onTouchListener);
    }

    public void setRequestedOrientation(int i) {
        this.zzZX.setRequestedOrientation(i);
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        this.zzZX.setWebChromeClient(webChromeClient);
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        this.zzZX.setWebViewClient(webViewClient);
    }

    public void stopLoading() {
        this.zzZX.stopLoading();
    }

    public void zzK(boolean z) {
        this.zzZX.zzK(z);
    }

    public void zzL(boolean z) {
        this.zzZX.zzL(z);
    }

    public void zzM(int i) {
        this.zzZX.zzM(i);
    }

    public void zzM(boolean z) {
        this.zzZX.zzM(z);
    }

    public void zzN(boolean z) {
        this.zzZX.zzN(z);
    }

    public void zza(Context context, zzeg zzeg, zzgl zzgl) {
        this.zzZY.onDestroy();
        this.zzZX.zza(context, zzeg, zzgl);
    }

    public void zza(zzcy.zza zza) {
        this.zzZX.zza(zza);
    }

    public void zza(zzeg zzeg) {
        this.zzZX.zza(zzeg);
    }

    public void zza(zzrb zzrb) {
        this.zzZX.zza(zzrb);
    }

    public void zza(String str, zzid zzid) {
        this.zzZX.zza(str, zzid);
    }

    public void zza(String str, Map<String, ?> map) {
        this.zzZX.zza(str, map);
    }

    public void zza(String str, JSONObject jSONObject) {
        this.zzZX.zza(str, jSONObject);
    }

    public void zzb(zze zze) {
        this.zzZX.zzb(zze);
    }

    public void zzb(@Nullable zzgy zzgy) {
        this.zzZX.zzb(zzgy);
    }

    public void zzb(String str, zzid zzid) {
        this.zzZX.zzb(str, zzid);
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzZX.zzb(str, jSONObject);
    }

    public zzeg zzbC() {
        return this.zzZX.zzbC();
    }

    public void zzbV() {
        this.zzZX.zzbV();
    }

    public void zzbW() {
        this.zzZX.zzbW();
    }

    public void zzbi(String str) {
        this.zzZX.zzbi(str);
    }

    public void zzbj(String str) {
        this.zzZX.zzbj(str);
    }

    public com.google.android.gms.ads.internal.zze zzby() {
        return this.zzZX.zzby();
    }

    public void zzc(zze zze) {
        this.zzZX.zzc(zze);
    }

    public void zzhK() {
        this.zzZX.zzhK();
    }

    public void zzj(String str, String str2) {
        this.zzZX.zzj(str, str2);
    }

    public void zzlA() {
        this.zzZY.onDestroy();
        this.zzZX.zzlA();
    }

    public boolean zzlB() {
        return this.zzZX.zzlB();
    }

    public boolean zzlC() {
        return this.zzZX.zzlC();
    }

    public zzqv zzlD() {
        return this.zzZY;
    }

    public zzgj zzlE() {
        return this.zzZX.zzlE();
    }

    public zzgk zzlF() {
        return this.zzZX.zzlF();
    }

    public zzrb zzlG() {
        return this.zzZX.zzlG();
    }

    public boolean zzlH() {
        return this.zzZX.zzlH();
    }

    public void zzlI() {
        this.zzZX.zzlI();
    }

    public void zzlJ() {
        this.zzZX.zzlJ();
    }

    public View.OnClickListener zzlK() {
        return this.zzZX.zzlK();
    }

    @Nullable
    public zzgy zzlL() {
        return this.zzZX.zzlL();
    }

    public void zzlM() {
        setBackgroundColor(zzNf);
        this.zzZX.setBackgroundColor(zzNf);
    }

    public void zzlp() {
        this.zzZX.zzlp();
    }

    public void zzlq() {
        this.zzZX.zzlq();
    }

    public Activity zzlr() {
        return this.zzZX.zzlr();
    }

    public Context zzls() {
        return this.zzZX.zzls();
    }

    public zze zzlt() {
        return this.zzZX.zzlt();
    }

    public zze zzlu() {
        return this.zzZX.zzlu();
    }

    public zzqx zzlv() {
        return this.zzZX.zzlv();
    }

    public boolean zzlw() {
        return this.zzZX.zzlw();
    }

    public zzaw zzlx() {
        return this.zzZX.zzlx();
    }

    public zzqh zzly() {
        return this.zzZX.zzly();
    }

    public boolean zzlz() {
        return this.zzZX.zzlz();
    }
}
