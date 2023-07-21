package com.google.android.gms.internal;

import android.util.Log;
import com.google.ads.AdRequest;

@zzme
public class zzqf {
    /* renamed from: e */
    public static void m20e(String str) {
        if (zzak(6)) {
            Log.e(AdRequest.LOGTAG, str);
        }
    }

    public static void zza(String str, Throwable th) {
        if (zzak(3)) {
            Log.d(AdRequest.LOGTAG, str, th);
        }
    }

    public static boolean zzak(int i) {
        return i >= 5 || Log.isLoggable(AdRequest.LOGTAG, i);
    }

    public static void zzb(String str, Throwable th) {
        if (zzak(6)) {
            Log.e(AdRequest.LOGTAG, str, th);
        }
    }

    public static void zzbf(String str) {
        if (zzak(3)) {
            Log.d(AdRequest.LOGTAG, str);
        }
    }

    public static void zzbg(String str) {
        if (zzak(4)) {
            Log.i(AdRequest.LOGTAG, str);
        }
    }

    public static void zzbh(String str) {
        if (zzak(5)) {
            Log.w(AdRequest.LOGTAG, str);
        }
    }

    public static void zzc(String str, Throwable th) {
        if (zzak(5)) {
            Log.w(AdRequest.LOGTAG, str, th);
        }
    }
}
