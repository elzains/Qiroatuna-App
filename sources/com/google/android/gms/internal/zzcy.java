package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzw;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

@zzme
@TargetApi(14)
public class zzcy implements Application.ActivityLifecycleCallbacks, View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    private static final long zzwR = zzgd.zzDu.get().longValue();
    private zzpz zzvT = new zzpz(zzwR);
    private Application zzwS;
    private WeakReference<ViewTreeObserver> zzwT;
    WeakReference<View> zzwU;
    private zzcz zzwV;
    private int zzwW = -1;
    private HashSet<zzb> zzwX = new HashSet<>();
    private DisplayMetrics zzwY;
    private final Context zzwi;
    private final WindowManager zzwo;
    private final PowerManager zzwp;
    private final KeyguardManager zzwq;
    private boolean zzww = false;
    @Nullable
    BroadcastReceiver zzwx;

    public static class zza {
        public final long timestamp;
        public final boolean zzxa;
        public final boolean zzxb;
        public final int zzxc;
        public final Rect zzxd;
        public final Rect zzxe;
        public final Rect zzxf;
        public final boolean zzxg;
        public final Rect zzxh;
        public final boolean zzxi;
        public final Rect zzxj;
        public final float zzxk;
        public final boolean zzxl;

        public zza(long j, boolean z, boolean z2, int i, Rect rect, Rect rect2, Rect rect3, boolean z3, Rect rect4, boolean z4, Rect rect5, float f, boolean z5) {
            this.timestamp = j;
            this.zzxa = z;
            this.zzxb = z2;
            this.zzxc = i;
            this.zzxd = rect;
            this.zzxe = rect2;
            this.zzxf = rect3;
            this.zzxg = z3;
            this.zzxh = rect4;
            this.zzxi = z4;
            this.zzxj = rect5;
            this.zzxk = f;
            this.zzxl = z5;
        }
    }

    public interface zzb {
        void zza(zza zza);
    }

    public zzcy(Context context, View view) {
        this.zzwi = context.getApplicationContext();
        this.zzwo = (WindowManager) context.getSystemService("window");
        this.zzwp = (PowerManager) this.zzwi.getSystemService("power");
        this.zzwq = (KeyguardManager) context.getSystemService("keyguard");
        if (this.zzwi instanceof Application) {
            this.zzwS = (Application) this.zzwi;
            this.zzwV = new zzcz((Application) this.zzwi, this);
        }
        this.zzwY = context.getResources().getDisplayMetrics();
        zze(view);
    }

    private void zza(Activity activity, int i) {
        Window window;
        if (this.zzwU != null && (window = activity.getWindow()) != null) {
            View peekDecorView = window.peekDecorView();
            View view = (View) this.zzwU.get();
            if (view != null && peekDecorView != null && view.getRootView() == peekDecorView.getRootView()) {
                this.zzwW = i;
            }
        }
    }

    private void zzdF() {
        if (this.zzwx == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zzwx = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    zzcy.this.zzl(3);
                }
            };
            this.zzwi.registerReceiver(this.zzwx, intentFilter);
        }
    }

    private void zzdG() {
        if (this.zzwx != null) {
            try {
                this.zzwi.unregisterReceiver(this.zzwx);
            } catch (IllegalStateException e) {
                zzpk.zzb("Failed trying to unregister the receiver", e);
            } catch (Exception e2) {
                zzw.zzcQ().zza((Throwable) e2, "ActiveViewUnit.stopScreenStatusMonitoring");
            }
            this.zzwx = null;
        }
    }

    private void zzdX() {
        zzw.zzcM();
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                zzcy.this.zzl(3);
            }
        });
    }

    private void zzf(View view) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.zzwT = new WeakReference<>(viewTreeObserver);
            viewTreeObserver.addOnScrollChangedListener(this);
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        zzdF();
        if (this.zzwS != null) {
            try {
                this.zzwS.registerActivityLifecycleCallbacks(this.zzwV);
            } catch (Exception e) {
                zzpk.zzb("Error registering activity lifecycle callbacks.", e);
            }
        }
    }

    private void zzg(View view) {
        try {
            if (this.zzwT != null) {
                ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzwT.get();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnScrollChangedListener(this);
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                this.zzwT = null;
            }
        } catch (Exception e) {
            zzpk.zzb("Error while unregistering listeners from the last ViewTreeObserver.", e);
        }
        try {
            ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            if (viewTreeObserver2.isAlive()) {
                viewTreeObserver2.removeOnScrollChangedListener(this);
                viewTreeObserver2.removeGlobalOnLayoutListener(this);
            }
        } catch (Exception e2) {
            zzpk.zzb("Error while unregistering listeners from the ViewTreeObserver.", e2);
        }
        zzdG();
        if (this.zzwS != null) {
            try {
                this.zzwS.unregisterActivityLifecycleCallbacks(this.zzwV);
            } catch (Exception e3) {
                zzpk.zzb("Error registering activity lifecycle callbacks.", e3);
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzl(int i) {
        if (this.zzwX.size() != 0 && this.zzwU != null) {
            View view = (View) this.zzwU.get();
            boolean z = i == 1;
            boolean z2 = view == null;
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            boolean z3 = false;
            Rect rect3 = new Rect();
            boolean z4 = false;
            Rect rect4 = new Rect();
            Rect rect5 = new Rect();
            rect5.right = this.zzwo.getDefaultDisplay().getWidth();
            rect5.bottom = this.zzwo.getDefaultDisplay().getHeight();
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            if (view != null) {
                z3 = view.getGlobalVisibleRect(rect2);
                z4 = view.getLocalVisibleRect(rect3);
                view.getHitRect(rect4);
                try {
                    view.getLocationOnScreen(iArr);
                    view.getLocationInWindow(iArr2);
                } catch (Exception e) {
                    zzpk.zzb("Failure getting view location.", e);
                }
                rect.left = iArr[0];
                rect.top = iArr[1];
                rect.right = rect.left + view.getWidth();
                rect.bottom = rect.top + view.getHeight();
            }
            int windowVisibility = view != null ? view.getWindowVisibility() : 8;
            if (this.zzwW != -1) {
                windowVisibility = this.zzwW;
            }
            boolean z5 = !z2 && zzw.zzcM().zza(view, this.zzwp, this.zzwq) && z3 && z4 && windowVisibility == 0;
            if (z && !this.zzvT.tryAcquire() && z5 == this.zzww) {
                return;
            }
            if (z5 || this.zzww || i != 1) {
                zza zza2 = new zza(zzw.zzcS().elapsedRealtime(), this.zzwp.isScreenOn(), view != null ? zzw.zzcO().isAttachedToWindow(view) : false, view != null ? view.getWindowVisibility() : 8, zza(rect5), zza(rect), zza(rect2), z3, zza(rect3), z4, zza(rect4), this.zzwY.density, z5);
                Iterator<zzb> it = this.zzwX.iterator();
                while (it.hasNext()) {
                    it.next().zza(zza2);
                }
                this.zzww = z5;
            }
        }
    }

    private int zzm(int i) {
        return (int) (((float) i) / this.zzwY.density);
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        zza(activity, 0);
        zzl(3);
        zzdX();
    }

    public void onActivityDestroyed(Activity activity) {
        zzl(3);
        zzdX();
    }

    public void onActivityPaused(Activity activity) {
        zza(activity, 4);
        zzl(3);
        zzdX();
    }

    public void onActivityResumed(Activity activity) {
        zza(activity, 0);
        zzl(3);
        zzdX();
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zzl(3);
        zzdX();
    }

    public void onActivityStarted(Activity activity) {
        zza(activity, 0);
        zzl(3);
        zzdX();
    }

    public void onActivityStopped(Activity activity) {
        zzl(3);
        zzdX();
    }

    public void onGlobalLayout() {
        zzl(2);
        zzdX();
    }

    public void onScrollChanged() {
        zzl(1);
    }

    public void onViewAttachedToWindow(View view) {
        this.zzwW = -1;
        zzf(view);
        zzl(3);
    }

    public void onViewDetachedFromWindow(View view) {
        this.zzwW = -1;
        zzl(3);
        zzdX();
        zzg(view);
    }

    /* access modifiers changed from: package-private */
    public Rect zza(Rect rect) {
        return new Rect(zzm(rect.left), zzm(rect.top), zzm(rect.right), zzm(rect.bottom));
    }

    public void zza(zzb zzb2) {
        this.zzwX.add(zzb2);
        zzl(3);
    }

    public void zzb(zzb zzb2) {
        this.zzwX.remove(zzb2);
    }

    public void zzdY() {
        zzl(4);
    }

    public void zze(View view) {
        View view2 = this.zzwU != null ? (View) this.zzwU.get() : null;
        if (view2 != null) {
            view2.removeOnAttachStateChangeListener(this);
            zzg(view2);
        }
        this.zzwU = new WeakReference<>(view);
        if (view != null) {
            if (zzw.zzcO().isAttachedToWindow(view)) {
                zzf(view);
            }
            view.addOnAttachStateChangeListener(this);
        }
    }
}
