package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import com.google.android.gms.ads.internal.zzw;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;

@zzme
@TargetApi(8)
public class zzpp {

    @TargetApi(9)
    public static class zza extends zzpp {
        public zza() {
            super();
        }

        public boolean zza(DownloadManager.Request request) {
            request.setShowRunningNotification(true);
            return true;
        }

        public int zzkQ() {
            return 6;
        }

        public int zzkR() {
            return 7;
        }
    }

    @TargetApi(11)
    public static class zzb extends zza {
        public boolean zza(DownloadManager.Request request) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(1);
            return true;
        }

        public boolean zza(final Context context, final WebSettings webSettings) {
            super.zza(context, webSettings);
            return ((Boolean) zzqb.zzb(new Callable<Boolean>(this) {
                /* renamed from: zzbX */
                public Boolean call() {
                    if (context.getCacheDir() != null) {
                        webSettings.setAppCachePath(context.getCacheDir().getAbsolutePath());
                        webSettings.setAppCacheMaxSize(0);
                        webSettings.setAppCacheEnabled(true);
                    }
                    webSettings.setDatabasePath(context.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
                    webSettings.setDatabaseEnabled(true);
                    webSettings.setDomStorageEnabled(true);
                    webSettings.setDisplayZoomControls(false);
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setSupportZoom(true);
                    webSettings.setAllowContentAccess(false);
                    return true;
                }
            })).booleanValue();
        }

        public boolean zza(Window window) {
            window.setFlags(16777216, 16777216);
            return true;
        }

        public zzqx zzb(zzqw zzqw, boolean z) {
            return new zzre(zzqw, z);
        }

        public Set<String> zzh(Uri uri) {
            return uri.getQueryParameterNames();
        }

        public WebChromeClient zzn(zzqw zzqw) {
            return new zzrd(zzqw);
        }

        public boolean zzt(View view) {
            view.setLayerType(0, (Paint) null);
            return true;
        }

        public boolean zzu(View view) {
            view.setLayerType(1, (Paint) null);
            return true;
        }
    }

    @TargetApi(14)
    public static class zzc extends zzb {
        public String zza(SslError sslError) {
            return sslError.getUrl();
        }

        public WebChromeClient zzn(zzqw zzqw) {
            return new zzrf(zzqw);
        }
    }

    @TargetApi(17)
    public static class zzd extends zzf {
        public String getDefaultUserAgent(Context context) {
            return zzqc.zzld().zzac(context);
        }

        public void zzW(Context context) {
            zzqc.zzld().zzab(context);
            zzw.zzcQ().zzkB();
        }

        public Drawable zza(Context context, Bitmap bitmap, boolean z, float f) {
            if (!z || f <= 0.0f || f > 25.0f) {
                return new BitmapDrawable(context.getResources(), bitmap);
            }
            try {
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), false);
                Bitmap createBitmap = Bitmap.createBitmap(createScaledBitmap);
                RenderScript create = RenderScript.create(context);
                ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
                Allocation createFromBitmap = Allocation.createFromBitmap(create, createScaledBitmap);
                Allocation createFromBitmap2 = Allocation.createFromBitmap(create, createBitmap);
                create2.setRadius(f);
                create2.setInput(createFromBitmap);
                create2.forEach(createFromBitmap2);
                createFromBitmap2.copyTo(createBitmap);
                return new BitmapDrawable(context.getResources(), createBitmap);
            } catch (RuntimeException e) {
                return new BitmapDrawable(context.getResources(), bitmap);
            }
        }

        public boolean zza(Context context, WebSettings webSettings) {
            super.zza(context, webSettings);
            webSettings.setMediaPlaybackRequiresUserGesture(false);
            return true;
        }
    }

    @TargetApi(18)
    public static class zze extends zzd {
        public boolean isAttachedToWindow(View view) {
            return super.isAttachedToWindow(view) || view.getWindowId() != null;
        }

        public int zzkS() {
            return 14;
        }
    }

    @TargetApi(16)
    public static class zzf extends zzc {
        public void zza(View view, Drawable drawable) {
            view.setBackground(drawable);
        }

        public void zza(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }

        public boolean zza(Context context, WebSettings webSettings) {
            super.zza(context, webSettings);
            webSettings.setAllowFileAccessFromFileURLs(false);
            webSettings.setAllowUniversalAccessFromFileURLs(false);
            return true;
        }

        public void zzb(Activity activity, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
            Window window = activity.getWindow();
            if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
                zza(window.getDecorView().getViewTreeObserver(), onGlobalLayoutListener);
            }
        }
    }

    @TargetApi(19)
    public static class zzg extends zze {
        public boolean isAttachedToWindow(View view) {
            return view.isAttachedToWindow();
        }

        public ViewGroup.LayoutParams zzkT() {
            return new ViewGroup.LayoutParams(-1, -1);
        }
    }

    @TargetApi(21)
    public static class zzh extends zzg {
        public CookieManager zzX(Context context) {
            try {
                return CookieManager.getInstance();
            } catch (Exception e) {
                zzpk.zzb("Failed to obtain CookieManager.", e);
                zzw.zzcQ().zza((Throwable) e, "ApiLevelUtil.getCookieManager");
                return null;
            }
        }

        public zzqx zzb(zzqw zzqw, boolean z) {
            return new zzrh(zzqw, z);
        }
    }

    private zzpp() {
    }

    public static zzpp zzaj(int i) {
        return i >= 21 ? new zzh() : i >= 19 ? new zzg() : i >= 18 ? new zze() : i >= 17 ? new zzd() : i >= 16 ? new zzf() : i >= 14 ? new zzc() : i >= 11 ? new zzb() : i >= 9 ? new zza() : new zzpp();
    }

    public String getDefaultUserAgent(Context context) {
        return "";
    }

    public boolean isAttachedToWindow(View view) {
        return (view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true;
    }

    public void zzW(Context context) {
        zzw.zzcQ().zzkB();
    }

    public CookieManager zzX(Context context) {
        try {
            CookieSyncManager.createInstance(context);
            return CookieManager.getInstance();
        } catch (Exception e) {
            zzpk.zzb("Failed to obtain CookieManager.", e);
            zzw.zzcQ().zza((Throwable) e, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }

    public Drawable zza(Context context, Bitmap bitmap, boolean z, float f) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public String zza(SslError sslError) {
        return "";
    }

    public void zza(View view, Drawable drawable) {
        view.setBackgroundDrawable(drawable);
    }

    public void zza(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
    }

    public boolean zza(DownloadManager.Request request) {
        return false;
    }

    public boolean zza(Context context, WebSettings webSettings) {
        return false;
    }

    public boolean zza(Window window) {
        return false;
    }

    public zzqx zzb(zzqw zzqw, boolean z) {
        return new zzqx(zzqw, z);
    }

    public void zzb(Activity activity, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            zza(window.getDecorView().getViewTreeObserver(), onGlobalLayoutListener);
        }
    }

    public Set<String> zzh(Uri uri) {
        if (uri.isOpaque()) {
            return Collections.emptySet();
        }
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null) {
            return Collections.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = 0;
        do {
            int indexOf = encodedQuery.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = encodedQuery.length();
            }
            int indexOf2 = encodedQuery.indexOf(61, i);
            if (indexOf2 > indexOf || indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            linkedHashSet.add(Uri.decode(encodedQuery.substring(i, indexOf2)));
            i = indexOf + 1;
        } while (i < encodedQuery.length());
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public int zzkQ() {
        return 0;
    }

    public int zzkR() {
        return 1;
    }

    public int zzkS() {
        return 5;
    }

    public ViewGroup.LayoutParams zzkT() {
        return new ViewGroup.LayoutParams(-2, -2);
    }

    public boolean zzl(zzqw zzqw) {
        if (zzqw == null) {
            return false;
        }
        zzqw.onPause();
        return true;
    }

    public boolean zzm(zzqw zzqw) {
        if (zzqw == null) {
            return false;
        }
        zzqw.onResume();
        return true;
    }

    public WebChromeClient zzn(zzqw zzqw) {
        return null;
    }

    public boolean zzt(View view) {
        return false;
    }

    public boolean zzu(View view) {
        return false;
    }
}
