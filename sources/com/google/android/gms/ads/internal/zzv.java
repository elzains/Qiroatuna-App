package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzck;
import com.google.android.gms.internal.zzcl;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzeo;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzet;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.internal.zzfa;
import com.google.android.gms.internal.zzfc;
import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zzle;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zznw;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpn;
import com.google.android.gms.internal.zzqh;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzme
public class zzv extends zzet.zza {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    @Nullable
    public zzep zztk;
    /* access modifiers changed from: private */
    public final zzqh zztt;
    private final zzeg zzus;
    /* access modifiers changed from: private */
    public final Future<zzck> zzut = zzcC();
    private final zzb zzuu;
    /* access modifiers changed from: private */
    @Nullable
    public WebView zzuv = new WebView(this.mContext);
    /* access modifiers changed from: private */
    @Nullable
    public zzck zzuw;
    private AsyncTask<Void, Void, String> zzux;

    private class zza extends AsyncTask<Void, Void, String> {
        private zza() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: zzD */
        public void onPostExecute(String str) {
            if (zzv.this.zzuv != null && str != null) {
                zzv.this.zzuv.loadUrl(str);
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public String doInBackground(Void... voidArr) {
            try {
                zzck unused = zzv.this.zzuw = (zzck) zzv.this.zzut.get(zzgd.zzET.get().longValue(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException e) {
                zzpk.zzc("Failed to load ad data", e);
            } catch (TimeoutException e2) {
                zzpk.zzbh("Timed out waiting for ad data");
            }
            return zzv.this.zzcA();
        }
    }

    private static class zzb {
        private final Map<String, String> zzuA = new TreeMap();
        private String zzuB;
        private String zzuC;
        private final String zzuz;

        public zzb(String str) {
            this.zzuz = str;
        }

        public String getQuery() {
            return this.zzuB;
        }

        public void zza(zzec zzec, zzqh zzqh) {
            this.zzuB = zzec.zzza.zzAT;
            Bundle bundle = zzec.zzzd != null ? zzec.zzzd.getBundle(AdMobAdapter.class.getName()) : null;
            if (bundle != null) {
                String str = zzgd.zzES.get();
                for (String str2 : bundle.keySet()) {
                    if (str.equals(str2)) {
                        this.zzuC = bundle.getString(str2);
                    } else if (str2.startsWith("csa_")) {
                        this.zzuA.put(str2.substring("csa_".length()), bundle.getString(str2));
                    }
                }
                this.zzuA.put("SDKVersion", zzqh.zzba);
            }
        }

        public String zzcE() {
            return this.zzuC;
        }

        public String zzcF() {
            return this.zzuz;
        }

        public Map<String, String> zzcG() {
            return this.zzuA;
        }
    }

    public zzv(Context context, zzeg zzeg, String str, zzqh zzqh) {
        this.mContext = context;
        this.zztt = zzqh;
        this.zzus = zzeg;
        this.zzuu = new zzb(str);
        zzcz();
    }

    /* access modifiers changed from: private */
    public String zzB(String str) {
        if (this.zzuw == null) {
            return str;
        }
        Uri parse = Uri.parse(str);
        try {
            parse = this.zzuw.zzd(parse, this.mContext);
        } catch (RemoteException e) {
            zzpk.zzc("Unable to process ad data", e);
        } catch (zzcl e2) {
            zzpk.zzc("Unable to parse ad click url", e2);
        }
        return parse.toString();
    }

    /* access modifiers changed from: private */
    public void zzC(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        this.mContext.startActivity(intent);
    }

    private Future<zzck> zzcC() {
        return zzpn.zza(new Callable<zzck>() {
            /* renamed from: zzcD */
            public zzck call() throws Exception {
                return new zzck(zzv.this.zztt.zzba, zzv.this.mContext, false);
            }
        });
    }

    private void zzcz() {
        zzj(0);
        this.zzuv.setVerticalScrollBarEnabled(false);
        this.zzuv.getSettings().setJavaScriptEnabled(true);
        this.zzuv.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                if (zzv.this.zztk != null) {
                    try {
                        zzv.this.zztk.onAdFailedToLoad(0);
                    } catch (RemoteException e) {
                        zzpk.zzc("Could not call AdListener.onAdFailedToLoad().", e);
                    }
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith(zzv.this.zzcB())) {
                    return false;
                }
                if (str.startsWith(zzgd.zzEO.get())) {
                    if (zzv.this.zztk != null) {
                        try {
                            zzv.this.zztk.onAdFailedToLoad(3);
                        } catch (RemoteException e) {
                            zzpk.zzc("Could not call AdListener.onAdFailedToLoad().", e);
                        }
                    }
                    zzv.this.zzj(0);
                    return true;
                } else if (str.startsWith(zzgd.zzEP.get())) {
                    if (zzv.this.zztk != null) {
                        try {
                            zzv.this.zztk.onAdFailedToLoad(0);
                        } catch (RemoteException e2) {
                            zzpk.zzc("Could not call AdListener.onAdFailedToLoad().", e2);
                        }
                    }
                    zzv.this.zzj(0);
                    return true;
                } else if (str.startsWith(zzgd.zzEQ.get())) {
                    if (zzv.this.zztk != null) {
                        try {
                            zzv.this.zztk.onAdLoaded();
                        } catch (RemoteException e3) {
                            zzpk.zzc("Could not call AdListener.onAdLoaded().", e3);
                        }
                    }
                    zzv.this.zzj(zzv.this.zzA(str));
                    return true;
                } else if (str.startsWith("gmsg://")) {
                    return true;
                } else {
                    if (zzv.this.zztk != null) {
                        try {
                            zzv.this.zztk.onAdLeftApplication();
                        } catch (RemoteException e4) {
                            zzpk.zzc("Could not call AdListener.onAdLeftApplication().", e4);
                        }
                    }
                    zzv.this.zzC(zzv.this.zzB(str));
                    return true;
                }
            }
        });
        this.zzuv.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (zzv.this.zzuw == null) {
                    return false;
                }
                try {
                    zzv.this.zzuw.zza(motionEvent);
                    return false;
                } catch (RemoteException e) {
                    zzpk.zzc("Unable to process ad data", e);
                    return false;
                }
            }
        });
    }

    public void destroy() throws RemoteException {
        zzac.zzdj("destroy must be called on the main UI thread.");
        this.zzux.cancel(true);
        this.zzut.cancel(true);
        this.zzuv.destroy();
        this.zzuv = null;
    }

    @Nullable
    public String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public boolean isLoading() throws RemoteException {
        return false;
    }

    public boolean isReady() throws RemoteException {
        return false;
    }

    public void pause() throws RemoteException {
        zzac.zzdj("pause must be called on the main UI thread.");
    }

    public void resume() throws RemoteException {
        zzac.zzdj("resume must be called on the main UI thread.");
    }

    public void setManualImpressionsEnabled(boolean z) throws RemoteException {
    }

    public void setUserId(String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void stopLoading() throws RemoteException {
    }

    /* access modifiers changed from: package-private */
    public int zzA(String str) {
        String queryParameter = Uri.parse(str).getQueryParameter("height");
        if (TextUtils.isEmpty(queryParameter)) {
            return 0;
        }
        try {
            return zzel.zzeT().zzb(this.mContext, Integer.parseInt(queryParameter));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void zza(zzeg zzeg) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }

    public void zza(zzeo zzeo) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzep zzep) throws RemoteException {
        this.zztk = zzep;
    }

    public void zza(zzev zzev) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzex zzex) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzfc zzfc) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzft zzft) {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzgp zzgp) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzle zzle) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zzli zzli, String str) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public void zza(zznw zznw) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    public boolean zzb(zzec zzec) throws RemoteException {
        zzac.zzb(this.zzuv, (Object) "This Search Ad has already been torn down");
        this.zzuu.zza(zzec, this.zztt);
        this.zzux = new zza().execute(new Void[0]);
        return true;
    }

    public IObjectWrapper zzbB() throws RemoteException {
        zzac.zzdj("getAdFrame must be called on the main UI thread.");
        return zzd.zzA(this.zzuv);
    }

    public zzeg zzbC() throws RemoteException {
        return this.zzus;
    }

    public void zzbE() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }

    @Nullable
    public zzfa zzbF() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public String zzcA() {
        Uri uri;
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https://").appendEncodedPath(zzgd.zzER.get());
        builder.appendQueryParameter(SearchIntents.EXTRA_QUERY, this.zzuu.getQuery());
        builder.appendQueryParameter("pubId", this.zzuu.zzcF());
        Map<String, String> zzcG = this.zzuu.zzcG();
        for (String next : zzcG.keySet()) {
            builder.appendQueryParameter(next, zzcG.get(next));
        }
        Uri build = builder.build();
        if (this.zzuw != null) {
            try {
                uri = this.zzuw.zzc(build, this.mContext);
            } catch (RemoteException | zzcl e) {
                zzpk.zzc("Unable to process ad data", e);
            }
            String valueOf = String.valueOf(zzcB());
            String valueOf2 = String.valueOf(uri.getEncodedQuery());
            return new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length()).append(valueOf).append("#").append(valueOf2).toString();
        }
        uri = build;
        String valueOf3 = String.valueOf(zzcB());
        String valueOf22 = String.valueOf(uri.getEncodedQuery());
        return new StringBuilder(String.valueOf(valueOf3).length() + 1 + String.valueOf(valueOf22).length()).append(valueOf3).append("#").append(valueOf22).toString();
    }

    /* access modifiers changed from: package-private */
    public String zzcB() {
        String zzcE = this.zzuu.zzcE();
        String str = TextUtils.isEmpty(zzcE) ? "www.google.com" : zzcE;
        String valueOf = String.valueOf("https://");
        String str2 = zzgd.zzER.get();
        return new StringBuilder(String.valueOf(valueOf).length() + String.valueOf(str).length() + String.valueOf(str2).length()).append(valueOf).append(str).append(str2).toString();
    }

    /* access modifiers changed from: package-private */
    public void zzj(int i) {
        if (this.zzuv != null) {
            this.zzuv.setLayoutParams(new ViewGroup.LayoutParams(-1, i));
        }
    }
}
