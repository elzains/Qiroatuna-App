package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p000v4.media.TransportMediator;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewTreeObserver;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.AdSize;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzh;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.zzf;
import com.google.android.gms.ads.internal.zzw;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@zzme
public class zzqx extends WebViewClient {
    private static final String[] zzZv = {"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzZw = {"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    private zzhz zzHC;
    private zzih zzIm;
    private zzf zzIo;
    private zzkr zzIp;
    private zzif zzIr;
    protected zzqw zzIs;
    private zzkx zzMh;
    private zzb zzZA;
    /* access modifiers changed from: private */
    public zzc zzZB;
    private boolean zzZC;
    private boolean zzZD;
    private ViewTreeObserver.OnGlobalLayoutListener zzZE;
    private ViewTreeObserver.OnScrollChangedListener zzZF;
    private boolean zzZG;
    private zzq zzZH;
    private final zzkv zzZI;
    private zze zzZJ;
    @Nullable
    protected zzot zzZK;
    private boolean zzZL;
    private boolean zzZM;
    private boolean zzZN;
    private int zzZO;
    private final HashMap<String, List<zzid>> zzZx;
    private zzh zzZy;
    private zza zzZz;
    private final Object zzrJ;
    private boolean zzwe;
    private zzdx zzyR;

    public interface zza {
        void zza(zzqw zzqw, boolean z);
    }

    public interface zzb {
        void zzk(zzqw zzqw);
    }

    public interface zzc {
        void zzcf();
    }

    private static class zzd implements zzh {
        private zzqw zzZQ;
        private zzh zzZy;

        public zzd(zzqw zzqw, zzh zzh) {
            this.zzZQ = zzqw;
            this.zzZy = zzh;
        }

        public void onPause() {
        }

        public void onResume() {
        }

        public void zzbN() {
            this.zzZy.zzbN();
            this.zzZQ.zzlp();
        }

        public void zzbO() {
            this.zzZy.zzbO();
            this.zzZQ.zzhK();
        }
    }

    public interface zze {
        void zzce();
    }

    public zzqx(zzqw zzqw, boolean z) {
        this(zzqw, z, new zzkv(zzqw, zzqw.zzls(), new zzfv(zzqw.getContext())), (zzkr) null);
    }

    zzqx(zzqw zzqw, boolean z, zzkv zzkv, zzkr zzkr) {
        this.zzZx = new HashMap<>();
        this.zzrJ = new Object();
        this.zzZC = false;
        this.zzIs = zzqw;
        this.zzwe = z;
        this.zzZI = zzkv;
        this.zzIp = zzkr;
    }

    private String zzbk(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        Uri parse = Uri.parse(str);
        return parse.getHost() != null ? parse.getHost() : "";
    }

    private void zzc(Context context, String str, String str2, String str3) {
        if (zzgd.zzDy.get().booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString("err", str);
            bundle.putString("code", str2);
            bundle.putString("host", zzbk(str3));
            zzw.zzcM().zza(context, this.zzIs.zzly().zzba, "gmob-apps", bundle, true);
        }
    }

    private static boolean zzi(Uri uri) {
        String scheme = uri.getScheme();
        return "http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme);
    }

    private void zzlX() {
        if (this.zzZA != null) {
            this.zzZA.zzk(this.zzIs);
            this.zzZA = null;
        }
    }

    public final void onLoadResource(WebView webView, String str) {
        String valueOf = String.valueOf(str);
        zzpk.m19v(valueOf.length() != 0 ? "Loading resource: ".concat(valueOf) : new String("Loading resource: "));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzj(parse);
        }
    }

    public final void onPageFinished(WebView webView, String str) {
        synchronized (this.zzrJ) {
            if (this.zzZL) {
                zzpk.m19v("Blank page loaded, 1...");
                this.zzIs.zzlA();
                return;
            }
            this.zzZM = true;
            zzlX();
            zzlY();
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        zzc(this.zzIs.getContext(), "http_err", (i >= 0 || (-i) + -1 >= zzZv.length) ? String.valueOf(i) : zzZv[(-i) - 1], str2);
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            zzc(this.zzIs.getContext(), "ssl_err", (primaryError < 0 || primaryError >= zzZw.length) ? String.valueOf(primaryError) : zzZw[primaryError], zzw.zzcO().zza(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public final void reset() {
        if (this.zzZK != null) {
            this.zzZK = null;
        }
        synchronized (this.zzrJ) {
            this.zzZx.clear();
            this.zzyR = null;
            this.zzZy = null;
            this.zzZz = null;
            this.zzZA = null;
            this.zzHC = null;
            this.zzZC = false;
            this.zzwe = false;
            this.zzZD = false;
            this.zzZG = false;
            this.zzIr = null;
            this.zzZH = null;
            this.zzZB = null;
            if (this.zzIp != null) {
                this.zzIp.zzs(true);
                this.zzIp = null;
            }
        }
    }

    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        zzdp zza2;
        try {
            zzds zzJ = zzds.zzJ(str);
            if (zzJ == null || (zza2 = zzw.zzcR().zza(zzJ)) == null || !zza2.zzew()) {
                return null;
            }
            return new WebResourceResponse("", "", zza2.zzex());
        } catch (Throwable th) {
            zzw.zzcQ().zza(th, "AdWebViewClient.shouldInterceptRequest");
            return null;
        }
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 79:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case AdSize.LARGE_AD_HEIGHT:
            case 91:
            case TransportMediator.KEYCODE_MEDIA_PLAY:
            case TransportMediator.KEYCODE_MEDIA_PAUSE:
            case 128:
            case 129:
            case TransportMediator.KEYCODE_MEDIA_RECORD:
            case 222:
                return true;
            default:
                return false;
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Uri uri;
        String valueOf = String.valueOf(str);
        zzpk.m19v(valueOf.length() != 0 ? "AdWebView shouldOverrideUrlLoading: ".concat(valueOf) : new String("AdWebView shouldOverrideUrlLoading: "));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzj(parse);
        } else if (this.zzZC && webView == this.zzIs.getWebView() && zzi(parse)) {
            if (this.zzyR != null && zzgd.zzCw.get().booleanValue()) {
                this.zzyR.onAdClicked();
                this.zzyR = null;
            }
            return super.shouldOverrideUrlLoading(webView, str);
        } else if (!this.zzIs.getWebView().willNotDraw()) {
            try {
                zzaw zzlx = this.zzIs.zzlx();
                if (zzlx != null && zzlx.zzc(parse)) {
                    parse = zzlx.zza(parse, this.zzIs.getContext(), this.zzIs.getView());
                }
                uri = parse;
            } catch (zzax e) {
                String valueOf2 = String.valueOf(str);
                zzpk.zzbh(valueOf2.length() != 0 ? "Unable to append parameter to URL: ".concat(valueOf2) : new String("Unable to append parameter to URL: "));
                uri = parse;
            }
            if (this.zzIo == null || this.zzIo.zzcd()) {
                zza(new com.google.android.gms.ads.internal.overlay.zzc("android.intent.action.VIEW", uri.toString(), (String) null, (String) null, (String) null, (String) null, (String) null));
            } else {
                this.zzIo.zzx(str);
            }
        } else {
            String valueOf3 = String.valueOf(str);
            zzpk.zzbh(valueOf3.length() != 0 ? "AdWebView unable to handle URL: ".concat(valueOf3) : new String("AdWebView unable to handle URL: "));
        }
        return true;
    }

    public void zzO(boolean z) {
        this.zzZC = z;
    }

    public void zza(int i, int i2, boolean z) {
        this.zzZI.zzf(i, i2);
        if (this.zzIp != null) {
            this.zzIp.zza(i, i2, z);
        }
    }

    public final void zza(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        synchronized (this.zzrJ) {
            this.zzZD = true;
            this.zzIs.zzlI();
            this.zzZE = onGlobalLayoutListener;
            this.zzZF = onScrollChangedListener;
        }
    }

    public void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean z = false;
        boolean zzhm = this.zzIp != null ? this.zzIp.zzhm() : false;
        com.google.android.gms.ads.internal.overlay.zzf zzcK = zzw.zzcK();
        Context context = this.zzIs.getContext();
        if (!zzhm) {
            z = true;
        }
        zzcK.zza(context, adOverlayInfoParcel, z);
        if (this.zzZK != null && adOverlayInfoParcel.url == null && adOverlayInfoParcel.zzNE != null) {
            String str = adOverlayInfoParcel.zzNE.url;
        }
    }

    public final void zza(com.google.android.gms.ads.internal.overlay.zzc zzc2) {
        zzh zzh = null;
        boolean zzlz = this.zzIs.zzlz();
        zzdx zzdx = (!zzlz || this.zzIs.zzbC().zzzz) ? this.zzyR : null;
        if (!zzlz) {
            zzh = this.zzZy;
        }
        zza(new AdOverlayInfoParcel(zzc2, zzdx, zzh, this.zzZH, this.zzIs.zzly()));
    }

    public void zza(zzdx zzdx, zzh zzh, zzhz zzhz, zzq zzq, boolean z, zzif zzif, @Nullable zzih zzih, zzf zzf, zzkx zzkx, @Nullable zzot zzot) {
        if (zzf == null) {
            zzf = new zzf(this.zzIs.getContext());
        }
        this.zzIp = new zzkr(this.zzIs, zzkx);
        this.zzZK = zzot;
        zza("/appEvent", (zzid) new zzhy(zzhz));
        zza("/backButton", zzic.zzHO);
        zza("/refresh", zzic.zzHP);
        zza("/canOpenURLs", zzic.zzHE);
        zza("/canOpenIntents", zzic.zzHF);
        zza("/click", zzic.zzHG);
        zza("/close", zzic.zzHH);
        zza("/customClose", zzic.zzHJ);
        zza("/instrument", zzic.zzHU);
        zza("/delayPageLoaded", zzic.zzHW);
        zza("/delayPageClosed", zzic.zzHX);
        zza("/getLocationInfo", zzic.zzHY);
        zza("/httpTrack", zzic.zzHK);
        zza("/log", zzic.zzHL);
        zza("/mraid", (zzid) new zzik(zzf, this.zzIp));
        zza("/mraidLoaded", (zzid) this.zzZI);
        zza("/open", (zzid) new zzil(zzif, zzf, this.zzIp));
        zza("/precache", zzic.zzHT);
        zza("/touch", zzic.zzHN);
        zza("/video", zzic.zzHQ);
        zza("/videoMeta", zzic.zzHR);
        zza("/appStreaming", zzic.zzHI);
        if (zzw.zzdl().zzjQ()) {
            zza("/logScionEvent", zzic.zzHS);
        }
        if (zzih != null) {
            zza("/setInterstitialProperties", (zzid) new zzig(zzih));
        }
        this.zzyR = zzdx;
        this.zzZy = zzh;
        this.zzHC = zzhz;
        this.zzIr = zzif;
        this.zzZH = zzq;
        this.zzIo = zzf;
        this.zzMh = zzkx;
        this.zzIm = zzih;
        zzO(z);
    }

    public void zza(zza zza2) {
        this.zzZz = zza2;
    }

    public void zza(zzb zzb2) {
        this.zzZA = zzb2;
    }

    public void zza(zzc zzc2) {
        this.zzZB = zzc2;
    }

    public void zza(zze zze2) {
        this.zzZJ = zze2;
    }

    public void zza(String str, zzid zzid) {
        synchronized (this.zzrJ) {
            List list = this.zzZx.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zzZx.put(str, list);
            }
            list.add(zzid);
        }
    }

    public final void zza(boolean z, int i) {
        zza(new AdOverlayInfoParcel((!this.zzIs.zzlz() || this.zzIs.zzbC().zzzz) ? this.zzyR : null, this.zzZy, this.zzZH, this.zzIs, z, i, this.zzIs.zzly()));
    }

    public final void zza(boolean z, int i, String str) {
        zzd zzd2 = null;
        boolean zzlz = this.zzIs.zzlz();
        zzdx zzdx = (!zzlz || this.zzIs.zzbC().zzzz) ? this.zzyR : null;
        if (!zzlz) {
            zzd2 = new zzd(this.zzIs, this.zzZy);
        }
        zza(new AdOverlayInfoParcel(zzdx, zzd2, this.zzHC, this.zzZH, this.zzIs, z, i, str, this.zzIs.zzly(), this.zzIr));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zzlz = this.zzIs.zzlz();
        zza(new AdOverlayInfoParcel((!zzlz || this.zzIs.zzbC().zzzz) ? this.zzyR : null, zzlz ? null : new zzd(this.zzIs, this.zzZy), this.zzHC, this.zzZH, this.zzIs, z, i, str, str2, this.zzIs.zzly(), this.zzIr));
    }

    public void zzb(String str, zzid zzid) {
        synchronized (this.zzrJ) {
            List list = this.zzZx.get(str);
            if (list != null) {
                list.remove(zzid);
            }
        }
    }

    public boolean zzdD() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzwe;
        }
        return z;
    }

    public void zze(int i, int i2) {
        if (this.zzIp != null) {
            this.zzIp.zze(i, i2);
        }
    }

    public final void zzhG() {
        synchronized (this.zzrJ) {
            this.zzZC = false;
            this.zzwe = true;
            zzw.zzcM().runOnUiThread(new Runnable() {
                public void run() {
                    zzqx.this.zzIs.zzlI();
                    com.google.android.gms.ads.internal.overlay.zze zzlt = zzqx.this.zzIs.zzlt();
                    if (zzlt != null) {
                        zzlt.zzhG();
                    }
                    if (zzqx.this.zzZB != null) {
                        zzqx.this.zzZB.zzcf();
                        zzc unused = zzqx.this.zzZB = null;
                    }
                }
            });
        }
    }

    public void zzj(Uri uri) {
        String path = uri.getPath();
        List<zzid> list = this.zzZx.get(path);
        if (list != null) {
            Map<String, String> zzg = zzw.zzcM().zzg(uri);
            if (zzpk.zzak(2)) {
                String valueOf = String.valueOf(path);
                zzpk.m19v(valueOf.length() != 0 ? "Received GMSG: ".concat(valueOf) : new String("Received GMSG: "));
                for (String next : zzg.keySet()) {
                    String str = zzg.get(next);
                    zzpk.m19v(new StringBuilder(String.valueOf(next).length() + 4 + String.valueOf(str).length()).append("  ").append(next).append(": ").append(str).toString());
                }
            }
            for (zzid zza2 : list) {
                zza2.zza(this.zzIs, zzg);
            }
            return;
        }
        String valueOf2 = String.valueOf(uri);
        zzpk.m19v(new StringBuilder(String.valueOf(valueOf2).length() + 32).append("No GMSG handler found for GMSG: ").append(valueOf2).toString());
    }

    public zzf zzlN() {
        return this.zzIo;
    }

    public boolean zzlO() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzZD;
        }
        return z;
    }

    public ViewTreeObserver.OnGlobalLayoutListener zzlP() {
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
        synchronized (this.zzrJ) {
            onGlobalLayoutListener = this.zzZE;
        }
        return onGlobalLayoutListener;
    }

    public ViewTreeObserver.OnScrollChangedListener zzlQ() {
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
        synchronized (this.zzrJ) {
            onScrollChangedListener = this.zzZF;
        }
        return onScrollChangedListener;
    }

    public boolean zzlR() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzZG;
        }
        return z;
    }

    public void zzlS() {
        synchronized (this.zzrJ) {
            zzpk.m19v("Loading blank page in WebView, 2...");
            this.zzZL = true;
            this.zzIs.zzbi("about:blank");
        }
    }

    public void zzlT() {
        if (this.zzZK != null) {
            zzpo.zzXC.post(new Runnable() {
                public void run() {
                    if (zzqx.this.zzZK != null) {
                        zzot zzot = zzqx.this.zzZK;
                        zzqw zzqw = zzqx.this.zzIs;
                    }
                }
            });
        }
    }

    public void zzlU() {
        synchronized (this.zzrJ) {
            this.zzZG = true;
        }
        this.zzZO++;
        zzlY();
    }

    public void zzlV() {
        this.zzZO--;
        zzlY();
    }

    public void zzlW() {
        this.zzZN = true;
        zzlY();
    }

    public final void zzlY() {
        if (this.zzZz != null && ((this.zzZM && this.zzZO <= 0) || this.zzZN)) {
            this.zzZz.zza(this.zzIs, !this.zzZN);
            this.zzZz = null;
        }
        this.zzIs.zzlJ();
    }

    public zze zzlZ() {
        return this.zzZJ;
    }

    public void zzo(zzqw zzqw) {
        this.zzIs = zzqw;
    }
}
