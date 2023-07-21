package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.api.Releasable;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.apache.cordova.globalization.Globalization;

@zzme
public abstract class zzis implements Releasable {
    protected Context mContext;
    protected String zzIA;
    protected WeakReference<zzqw> zzIB;

    public zzis(zzqw zzqw) {
        this.mContext = zzqw.getContext();
        this.zzIA = zzw.zzcM().zzu(this.mContext, zzqw.zzly().zzba);
        this.zzIB = new WeakReference<>(zzqw);
    }

    /* access modifiers changed from: private */
    public void zza(String str, Map<String, String> map) {
        zzqw zzqw = (zzqw) this.zzIB.get();
        if (zzqw != null) {
            zzqw.zza(str, (Map<String, ?>) map);
        }
    }

    /* access modifiers changed from: private */
    public String zzaf(String str) {
        char c = 65535;
        switch (str.hashCode()) {
            case -1396664534:
                if (str.equals("badUrl")) {
                    c = 6;
                    break;
                }
                break;
            case -1347010958:
                if (str.equals("inProgress")) {
                    c = 2;
                    break;
                }
                break;
            case -918817863:
                if (str.equals("downloadTimeout")) {
                    c = 7;
                    break;
                }
                break;
            case -659376217:
                if (str.equals("contentLengthMissing")) {
                    c = 3;
                    break;
                }
                break;
            case -642208130:
                if (str.equals("playerFailed")) {
                    c = 1;
                    break;
                }
                break;
            case -354048396:
                if (str.equals("sizeExceeded")) {
                    c = 8;
                    break;
                }
                break;
            case -32082395:
                if (str.equals("externalAbort")) {
                    c = 9;
                    break;
                }
                break;
            case 96784904:
                if (str.equals("error")) {
                    c = 0;
                    break;
                }
                break;
            case 580119100:
                if (str.equals("expireFailed")) {
                    c = 5;
                    break;
                }
                break;
            case 725497484:
                if (str.equals("noCacheDir")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
                return "internal";
            case 4:
            case 5:
                return "io";
            case 6:
            case 7:
                return "network";
            case 8:
            case 9:
                return "policy";
            default:
                return "internal";
        }
    }

    public abstract void abort();

    public void release() {
    }

    /* access modifiers changed from: protected */
    public void zza(final String str, final String str2, final int i) {
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("event", "precacheComplete");
                hashMap.put("src", str);
                hashMap.put("cachedSrc", str2);
                hashMap.put("totalBytes", Integer.toString(i));
                zzis.this.zza("onPrecacheEvent", (Map<String, String>) hashMap);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void zza(String str, String str2, int i, int i2, boolean z) {
        final String str3 = str;
        final String str4 = str2;
        final int i3 = i;
        final int i4 = i2;
        final boolean z2 = z;
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("event", "precacheProgress");
                hashMap.put("src", str3);
                hashMap.put("cachedSrc", str4);
                hashMap.put("bytesLoaded", Integer.toString(i3));
                hashMap.put("totalBytes", Integer.toString(i4));
                hashMap.put("cacheReady", z2 ? "1" : "0");
                zzis.this.zza("onPrecacheEvent", (Map<String, String>) hashMap);
            }
        });
    }

    public void zza(String str, String str2, String str3, @Nullable String str4) {
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final String str8 = str4;
        zzqe.zzYP.post(new Runnable() {
            public void run() {
                HashMap hashMap = new HashMap();
                hashMap.put("event", "precacheCanceled");
                hashMap.put("src", str5);
                if (!TextUtils.isEmpty(str6)) {
                    hashMap.put("cachedSrc", str6);
                }
                hashMap.put(Globalization.TYPE, zzis.this.zzaf(str7));
                hashMap.put("reason", str7);
                if (!TextUtils.isEmpty(str8)) {
                    hashMap.put("message", str8);
                }
                zzis.this.zza("onPrecacheEvent", (Map<String, String>) hashMap);
            }
        });
    }

    public abstract boolean zzad(String str);

    /* access modifiers changed from: protected */
    public String zzae(String str) {
        return zzel.zzeT().zzbe(str);
    }
}
