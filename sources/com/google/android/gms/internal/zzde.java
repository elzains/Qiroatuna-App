package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.zzt;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
@TargetApi(14)
public class zzde extends Thread {
    private boolean mStarted = false;
    private boolean zzal = false;
    private final Object zzrJ;
    private boolean zzxO = false;
    private final zzdc zzxP;
    private final zzmd zzxQ;
    private final int zzxR;
    private final int zzxS;
    private final int zzxT;
    private final int zzxU;
    private final int zzxV;
    private final int zzxW;
    private final String zzxX;
    private final int zzxp;
    private final int zzxr;

    @zzme
    class zza {
        final int zzyf;
        final int zzyg;

        zza(zzde zzde, int i, int i2) {
            this.zzyf = i;
            this.zzyg = i2;
        }
    }

    public zzde(zzdc zzdc, zzmd zzmd) {
        this.zzxP = zzdc;
        this.zzxQ = zzmd;
        this.zzrJ = new Object();
        this.zzxp = zzgd.zzCd.get().intValue();
        this.zzxS = zzgd.zzCe.get().intValue();
        this.zzxr = zzgd.zzCf.get().intValue();
        this.zzxT = zzgd.zzCg.get().intValue();
        this.zzxU = zzgd.zzCj.get().intValue();
        this.zzxV = zzgd.zzCl.get().intValue();
        this.zzxW = zzgd.zzCm.get().intValue();
        this.zzxR = zzgd.zzCh.get().intValue();
        this.zzxX = zzgd.zzCo.get();
        setName("ContentFetchTask");
    }

    public void run() {
        while (true) {
            try {
                if (zzek()) {
                    Activity activity = zzw.zzcP().getActivity();
                    if (activity == null) {
                        zzpk.zzbf("ContentFetchThread: no activity. Sleeping.");
                        zzem();
                    } else {
                        zza(activity);
                    }
                } else {
                    zzpk.zzbf("ContentFetchTask: sleeping");
                    zzem();
                }
                Thread.sleep((long) (this.zzxR * 1000));
            } catch (InterruptedException e) {
                zzpk.zzb("Error in ContentFetchTask", e);
            } catch (Throwable th) {
                zzpk.zzb("Error in ContentFetchTask", th);
                this.zzxQ.zza(th, "ContentFetchTask.run");
            }
            synchronized (this.zzrJ) {
                while (this.zzxO) {
                    try {
                        zzpk.zzbf("ContentFetchTask: waiting");
                        this.zzrJ.wait();
                    } catch (InterruptedException e2) {
                    }
                }
            }
        }
    }

    public void wakeup() {
        synchronized (this.zzrJ) {
            this.zzxO = false;
            this.zzrJ.notifyAll();
            zzpk.zzbf("ContentFetchThread: wakeup");
        }
    }

    /* access modifiers changed from: package-private */
    public zza zza(@Nullable View view, zzdb zzdb) {
        int i = 0;
        if (view == null) {
            return new zza(this, 0, 0);
        }
        Context context = zzw.zzcP().getContext();
        if (context != null) {
            String str = (String) view.getTag(context.getResources().getIdentifier(zzgd.zzCn.get(), "id", context.getPackageName()));
            if (!TextUtils.isEmpty(this.zzxX) && str != null && str.equals(this.zzxX)) {
                return new zza(this, 0, 0);
            }
        }
        boolean globalVisibleRect = view.getGlobalVisibleRect(new Rect());
        if ((view instanceof TextView) && !(view instanceof EditText)) {
            CharSequence text = ((TextView) view).getText();
            if (TextUtils.isEmpty(text)) {
                return new zza(this, 0, 0);
            }
            zzdb.zzb(text.toString(), globalVisibleRect, view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
            return new zza(this, 1, 0);
        } else if ((view instanceof WebView) && !(view instanceof zzqw)) {
            zzdb.zzef();
            return zza((WebView) view, zzdb, globalVisibleRect) ? new zza(this, 0, 1) : new zza(this, 0, 0);
        } else if (!(view instanceof ViewGroup)) {
            return new zza(this, 0, 0);
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            int i2 = 0;
            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                zza zza2 = zza(viewGroup.getChildAt(i3), zzdb);
                i2 += zza2.zzyf;
                i += zza2.zzyg;
            }
            return new zza(this, i2, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(@Nullable Activity activity) {
        if (activity != null) {
            View view = null;
            try {
                if (!(activity.getWindow() == null || activity.getWindow().getDecorView() == null)) {
                    view = activity.getWindow().getDecorView().findViewById(16908290);
                }
            } catch (Throwable th) {
                zzw.zzcQ().zza(th, "ContentFetchTask.extractContent");
                zzpk.zzbf("Failed getting root view of activity. Content not extracted.");
            }
            if (view != null) {
                zzh(view);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzdb zzdb, WebView webView, String str, boolean z) {
        zzdb.zzee();
        try {
            if (!TextUtils.isEmpty(str)) {
                String optString = new JSONObject(str).optString("text");
                if (!TextUtils.isEmpty(webView.getTitle())) {
                    String valueOf = String.valueOf(webView.getTitle());
                    zzdb.zza(new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(optString).length()).append(valueOf).append("\n").append(optString).toString(), z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                } else {
                    zzdb.zza(optString, z, webView.getX(), webView.getY(), (float) webView.getWidth(), (float) webView.getHeight());
                }
            }
            if (zzdb.zzdZ()) {
                this.zzxP.zzb(zzdb);
            }
        } catch (JSONException e) {
            zzpk.zzbf("Json string may be malformed.");
        } catch (Throwable th) {
            zzpk.zza("Failed to get webview content.", th);
            this.zzxQ.zza(th, "ContentFetchTask.processWebViewContent");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zza(ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
        return runningAppProcessInfo.importance == 100;
    }

    /* access modifiers changed from: package-private */
    @TargetApi(19)
    public boolean zza(final WebView webView, final zzdb zzdb, final boolean z) {
        if (!zzt.zzzl()) {
            return false;
        }
        zzdb.zzef();
        webView.post(new Runnable() {
            ValueCallback<String> zzya = new ValueCallback<String>() {
                /* renamed from: zzE */
                public void onReceiveValue(String str) {
                    zzde.this.zza(zzdb, webView, str, z);
                }
            };

            public void run() {
                if (webView.getSettings().getJavaScriptEnabled()) {
                    try {
                        webView.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzya);
                    } catch (Throwable th) {
                        this.zzya.onReceiveValue("");
                    }
                }
            }
        });
        return true;
    }

    public void zzej() {
        synchronized (this.zzrJ) {
            if (this.mStarted) {
                zzpk.zzbf("Content hash thread already started, quiting...");
                return;
            }
            this.mStarted = true;
            start();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzek() {
        try {
            Context context = zzw.zzcP().getContext();
            if (context == null) {
                return false;
            }
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (Process.myPid() == next.pid) {
                    if (zza(next) && !keyguardManager.inKeyguardRestrictedInputMode() && zzi(context)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Throwable th) {
            zzw.zzcQ().zza(th, "ContentFetchTask.isInForeground");
            return false;
        }
    }

    public zzdb zzel() {
        return this.zzxP.zzei();
    }

    public void zzem() {
        synchronized (this.zzrJ) {
            this.zzxO = true;
            zzpk.zzbf(new StringBuilder(42).append("ContentFetchThread: paused, mPause = ").append(this.zzxO).toString());
        }
    }

    public boolean zzen() {
        return this.zzxO;
    }

    /* access modifiers changed from: package-private */
    public boolean zzh(@Nullable final View view) {
        if (view == null) {
            return false;
        }
        view.post(new Runnable() {
            public void run() {
                zzde.this.zzi(view);
            }
        });
        return true;
    }

    /* access modifiers changed from: package-private */
    public void zzi(View view) {
        try {
            zzdb zzdb = new zzdb(this.zzxp, this.zzxS, this.zzxr, this.zzxT, this.zzxU, this.zzxV, this.zzxW);
            zza zza2 = zza(view, zzdb);
            zzdb.zzeg();
            if (zza2.zzyf != 0 || zza2.zzyg != 0) {
                if (zza2.zzyg != 0 || zzdb.zzeh() != 0) {
                    if (zza2.zzyg != 0 || !this.zzxP.zza(zzdb)) {
                        this.zzxP.zzc(zzdb);
                    }
                }
            }
        } catch (Exception e) {
            zzpk.zzb("Exception in fetchContentOnUIThread", e);
            this.zzxQ.zza(e, "ContentFetchTask.fetchContent");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzi(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return false;
        }
        return powerManager.isScreenOn();
    }
}
