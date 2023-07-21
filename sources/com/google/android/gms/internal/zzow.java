package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import java.util.concurrent.atomic.AtomicBoolean;

@zzme
public class zzow {
    private final Object zzVW = new Object();
    @Nullable
    private String zzVX = null;
    final AtomicBoolean zzVY = new AtomicBoolean(false);

    private Object zza(Class cls, Context context) {
        try {
            return cls.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
        } catch (Exception e) {
            zza(e, "getInstance");
            return null;
        }
    }

    private Object zza(String str, Context context) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement");
            return loadClass.getDeclaredMethod(str, new Class[0]).invoke(zza((Class) loadClass, context), new Object[0]);
        } catch (Exception e) {
            zza(e, str);
            return null;
        }
    }

    private void zza(Context context, String str, Bundle bundle) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement");
            Object zza = zza((Class) loadClass, context);
            loadClass.getDeclaredMethod("logEventInternal", new Class[]{String.class, String.class, Bundle.class}).invoke(zza, new Object[]{"am", str, bundle});
        } catch (Exception e) {
            zza(e, "logEventInternal");
        }
    }

    private void zza(Exception exc, String str) {
        if (!this.zzVY.get()) {
            zzpk.zzb(new StringBuilder(String.valueOf(str).length() + 190).append("Invoke Scion method ").append(str).append(" error, the Google Mobile Ads SDK will not integrate with Scion. Admob/Scion integration requires the latest Scion SDK jar, but Scion SDK is either missing or out of date").toString(), exc);
            zzw.zzcQ().zza((Throwable) exc, "ScionApiAdapter.logInvokeScionApiError");
            this.zzVY.set(true);
        }
    }

    private void zzb(Context context, String str, String str2) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement");
            Object zza = zza((Class) loadClass, context);
            loadClass.getDeclaredMethod(str2, new Class[]{String.class}).invoke(zza, new Object[]{str});
        } catch (Exception e) {
            zza(e, str2);
        }
    }

    private Bundle zzj(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("_aeid", str);
        return bundle;
    }

    @Nullable
    public String zzC(Context context) {
        if (!zzjQ()) {
            return null;
        }
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement");
            return (String) loadClass.getDeclaredMethod("getCurrentScreenName", new Class[0]).invoke(zza((Class) loadClass, context), new Object[0]);
        } catch (Exception e) {
            zza(e, "getCurrentScreenName");
            return null;
        }
    }

    @Nullable
    public String zzD(Context context) {
        if (!zzjQ()) {
            return null;
        }
        synchronized (this.zzVW) {
            if (this.zzVX != null) {
                String str = this.zzVX;
                return str;
            }
            this.zzVX = (String) zza("getGmpAppId", context);
            String str2 = this.zzVX;
            return str2;
        }
    }

    @Nullable
    public String zzE(Context context) {
        if (!zzjQ()) {
            return null;
        }
        return (String) zza("getAppInstanceId", context);
    }

    @Nullable
    public String zzF(Context context) {
        Object zza;
        if (zzjQ() && (zza = zza("generateEventId", context)) != null) {
            return zza.toString();
        }
        return null;
    }

    public void zza(Context context, String str, String str2) {
        if (zzjQ() && (context instanceof Activity)) {
            if (!TextUtils.isEmpty(str2)) {
                str = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length()).append(str).append("_").append(str2).toString();
            }
            try {
                Class<?> loadClass = context.getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                Object invoke = loadClass.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
                loadClass.getDeclaredMethod("setCurrentScreen", new Class[]{Activity.class, String.class, String.class}).invoke(invoke, new Object[]{(Activity) context, str, context.getPackageName()});
            } catch (Exception e) {
                zza(e, "setCurrentScreen");
            }
        }
    }

    public void zzd(Context context, String str) {
        if (zzjQ()) {
            zzb(context, str, "beginAdUnitExposure");
        }
    }

    public void zze(Context context, String str) {
        if (zzjQ()) {
            zzb(context, str, "endAdUnitExposure");
        }
    }

    public void zzf(Context context, String str) {
        if (zzjQ()) {
            Bundle zzj = zzj(context, str);
            zzj.putString("_r", "1");
            zza(context, "_ac", zzj);
        }
    }

    public void zzg(Context context, String str) {
        if (zzjQ()) {
            zza(context, "_ai", zzj(context, str));
        }
    }

    public void zzh(Context context, String str) {
        if (zzjQ()) {
            zza(context, "_aq", zzj(context, str));
        }
    }

    public void zzi(Context context, String str) {
        if (zzjQ()) {
            zza(context, "_aa", zzj(context, str));
        }
    }

    public boolean zzjQ() {
        return zzgd.zzCA.get().booleanValue() && !this.zzVY.get();
    }

    public boolean zzjR() {
        return zzgd.zzCB.get().booleanValue() && zzjQ();
    }

    public boolean zzjS() {
        return zzgd.zzCC.get().booleanValue() && zzjQ();
    }

    public boolean zzjT() {
        return zzgd.zzCD.get().booleanValue() && zzjQ();
    }

    public boolean zzjU() {
        return zzgd.zzCG.get().booleanValue() && zzjQ();
    }
}
