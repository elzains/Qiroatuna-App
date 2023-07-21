package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzmd;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.WeakHashMap;

@zzme
public class zzmc implements zzmd {
    private static zzmd zzRe = null;
    private static final Object zztX = new Object();
    private final Object zzRf = new Object();
    private final String zzRg;
    private final WeakHashMap<Thread, Boolean> zzRh = new WeakHashMap<>();
    private final zzqh zzuc;

    zzmc(String str, zzqh zzqh) {
        this.zzRg = str;
        this.zzuc = zzqh;
        zzjl();
        zzjk();
    }

    public static zzmd zzb(Context context, zzqh zzqh) {
        String packageName;
        synchronized (zztX) {
            if (zzRe == null) {
                if (zzgd.zzBm.get().booleanValue()) {
                    try {
                        packageName = context.getApplicationContext().getPackageName();
                    } catch (Throwable th) {
                        zzpk.zzbh("Cannot obtain package name, proceeding.");
                    }
                    zzRe = new zzmc(packageName, zzqh);
                } else {
                    zzRe = new zzmd.zza();
                }
            }
        }
        return zzRe;
    }

    private Throwable zzd(Throwable th) {
        Throwable th2;
        if (zzgd.zzBn.get().booleanValue()) {
            return th;
        }
        LinkedList linkedList = new LinkedList();
        while (th != null) {
            linkedList.push(th);
            th = th.getCause();
        }
        Throwable th3 = null;
        while (!linkedList.isEmpty()) {
            Throwable th4 = (Throwable) linkedList.pop();
            StackTraceElement[] stackTrace = th4.getStackTrace();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StackTraceElement(th4.getClass().getName(), "<filtered>", "<filtered>", 1));
            boolean z = false;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (zzaI(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                    z = true;
                } else if (zzaJ(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                } else {
                    arrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                }
            }
            if (z) {
                th2 = th3 == null ? new Throwable(th4.getMessage()) : new Throwable(th4.getMessage(), th3);
                th2.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
            } else {
                th2 = th3;
            }
            th3 = th2;
        }
        return th3;
    }

    private void zzjk() {
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                try {
                    zzmc.this.zza(thread, th);
                    if (defaultUncaughtExceptionHandler != null) {
                        defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                    }
                } catch (Throwable th2) {
                    if (defaultUncaughtExceptionHandler != null) {
                        defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                    }
                    throw th2;
                }
            }
        });
    }

    private void zzjl() {
        zza(Looper.getMainLooper().getThread());
    }

    /* access modifiers changed from: package-private */
    public String zza(Class cls, Throwable th, String str) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT)).appendQueryParameter("device", zzw.zzcM().zzkN()).appendQueryParameter("js", this.zzuc.zzba).appendQueryParameter("appid", this.zzRg).appendQueryParameter("exceptiontype", cls.getName()).appendQueryParameter("stacktrace", stringWriter.toString()).appendQueryParameter("eids", TextUtils.join(",", zzgd.zzfs())).appendQueryParameter("exceptionkey", str).appendQueryParameter("cl", "146496160").appendQueryParameter("rc", "dev").appendQueryParameter("session_id", zzw.zzcQ().getSessionId()).toString();
    }

    public void zza(Thread thread) {
        if (thread != null) {
            synchronized (this.zzRf) {
                this.zzRh.put(thread, true);
            }
            final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = thread.getUncaughtExceptionHandler();
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    try {
                        zzmc.this.zza(thread, th);
                        if (uncaughtExceptionHandler != null) {
                            uncaughtExceptionHandler.uncaughtException(thread, th);
                        }
                    } catch (Throwable th2) {
                        if (uncaughtExceptionHandler != null) {
                            uncaughtExceptionHandler.uncaughtException(thread, th);
                        }
                        throw th2;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void zza(Thread thread, Throwable th) {
        if (zzb(th)) {
            zzc(th);
        }
    }

    public void zza(Throwable th, String str) {
        Throwable zzd = zzd(th);
        if (zzd != null) {
            Class<?> cls = th.getClass();
            ArrayList arrayList = new ArrayList();
            arrayList.add(zza(cls, zzd, str));
            zzw.zzcM().zza((List<String>) arrayList, zzw.zzcQ().zzkn());
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzaI(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith(zzgd.zzBo.get())) {
            return true;
        }
        try {
            return Class.forName(str).isAnnotationPresent(zzme.class);
        } catch (Exception e) {
            Exception exc = e;
            String valueOf = String.valueOf(str);
            zzpk.zza(valueOf.length() != 0 ? "Fail to check class type for class ".concat(valueOf) : new String("Fail to check class type for class "), exc);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzaJ(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("android.") || str.startsWith("java.");
    }

    /* access modifiers changed from: protected */
    public boolean zzb(Throwable th) {
        boolean z = true;
        if (th == null) {
            return false;
        }
        boolean z2 = false;
        boolean z3 = false;
        while (th != null) {
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                if (zzaI(stackTraceElement.getClassName())) {
                    z3 = true;
                }
                if (getClass().getName().equals(stackTraceElement.getClassName())) {
                    z2 = true;
                }
            }
            th = th.getCause();
        }
        if (!z3 || z2) {
            z = false;
        }
        return z;
    }

    public void zzc(Throwable th) {
        zza(th, "");
    }
}
