package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzw;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzct implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener {
    protected final Object zzrJ = new Object();
    private boolean zzuo = false;
    private zzpz zzvT;
    private final Context zzwi;
    private final WeakReference<zzpb> zzwk;
    private WeakReference<ViewTreeObserver> zzwl;
    private final zzda zzwm;
    protected final zzcr zzwn;
    private final WindowManager zzwo;
    private final PowerManager zzwp;
    private final KeyguardManager zzwq;
    @Nullable
    private zzcu zzwr;
    private boolean zzws;
    private boolean zzwt = false;
    private boolean zzwu;
    private boolean zzwv;
    private boolean zzww;
    @Nullable
    BroadcastReceiver zzwx;
    private final HashSet<Object> zzwy = new HashSet<>();
    private final HashSet<zzcx> zzwz = new HashSet<>();

    public static class zza implements zzda {
        private WeakReference<zzha> zzwB;

        public zza(zzha zzha) {
            this.zzwB = new WeakReference<>(zzha);
        }

        @Nullable
        public View zzdS() {
            zzha zzha = (zzha) this.zzwB.get();
            if (zzha != null) {
                return zzha.zzgc();
            }
            return null;
        }

        public boolean zzdT() {
            return this.zzwB.get() == null;
        }

        public zzda zzdU() {
            return new zzb((zzha) this.zzwB.get());
        }
    }

    public static class zzb implements zzda {
        private zzha zzwC;

        public zzb(zzha zzha) {
            this.zzwC = zzha;
        }

        public View zzdS() {
            if (this.zzwC != null) {
                return this.zzwC.zzgc();
            }
            return null;
        }

        public boolean zzdT() {
            return this.zzwC == null;
        }

        public zzda zzdU() {
            return this;
        }
    }

    public static class zzc implements zzda {
        @Nullable
        private final View mView;
        @Nullable
        private final zzpb zzwD;

        public zzc(View view, zzpb zzpb) {
            this.mView = view;
            this.zzwD = zzpb;
        }

        public View zzdS() {
            return this.mView;
        }

        public boolean zzdT() {
            return this.zzwD == null || this.mView == null;
        }

        public zzda zzdU() {
            return this;
        }
    }

    public static class zzd implements zzda {
        private final WeakReference<View> zzwE;
        private final WeakReference<zzpb> zzwF;

        public zzd(View view, zzpb zzpb) {
            this.zzwE = new WeakReference<>(view);
            this.zzwF = new WeakReference<>(zzpb);
        }

        public View zzdS() {
            return (View) this.zzwE.get();
        }

        public boolean zzdT() {
            return this.zzwE.get() == null || this.zzwF.get() == null;
        }

        public zzda zzdU() {
            return new zzc((View) this.zzwE.get(), (zzpb) this.zzwF.get());
        }
    }

    public zzct(Context context, zzeg zzeg, zzpb zzpb, zzqh zzqh, zzda zzda) {
        this.zzwk = new WeakReference<>(zzpb);
        this.zzwm = zzda;
        this.zzwl = new WeakReference<>((Object) null);
        this.zzwu = true;
        this.zzww = false;
        this.zzvT = new zzpz(200);
        this.zzwn = new zzcr(UUID.randomUUID().toString(), zzqh, zzeg.zzzy, zzpb.zzWa, zzpb.zzdD(), zzeg.zzzB);
        this.zzwo = (WindowManager) context.getSystemService("window");
        this.zzwp = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzwq = (KeyguardManager) context.getSystemService("keyguard");
        this.zzwi = context;
    }

    /* access modifiers changed from: protected */
    public void destroy() {
        synchronized (this.zzrJ) {
            zzdL();
            zzdG();
            this.zzwu = false;
            zzdI();
            zzdN();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isScreenOn() {
        return this.zzwp.isScreenOn();
    }

    public void onGlobalLayout() {
        zzk(2);
    }

    public void onScrollChanged() {
        zzk(1);
    }

    public void pause() {
        synchronized (this.zzrJ) {
            this.zzuo = true;
            zzk(3);
        }
    }

    public void resume() {
        synchronized (this.zzrJ) {
            this.zzuo = false;
            zzk(3);
        }
    }

    public void stop() {
        synchronized (this.zzrJ) {
            this.zzwt = true;
            zzk(3);
        }
    }

    /* access modifiers changed from: protected */
    public int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    /* access modifiers changed from: package-private */
    public JSONObject zza(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }

    /* access modifiers changed from: protected */
    public void zza(View view, Map<String, String> map) {
        zzk(3);
    }

    public void zza(zzcu zzcu) {
        synchronized (this.zzrJ) {
            this.zzwr = zzcu;
        }
    }

    public void zza(zzcx zzcx) {
        if (this.zzwz.isEmpty()) {
            zzdF();
            zzk(3);
        }
        this.zzwz.add(zzcx);
        try {
            zzcx.zzc(zza(zzd(this.zzwm.zzdS())), false);
        } catch (JSONException e) {
            zzpk.zzb("Skipping measurement update for new client.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public void zza(zzcx zzcx, Map<String, String> map) {
        String valueOf = String.valueOf(this.zzwn.zzdC());
        zzpk.zzbf(valueOf.length() != 0 ? "Received request to untrack: ".concat(valueOf) : new String("Received request to untrack: "));
        zzb(zzcx);
    }

    /* access modifiers changed from: protected */
    public void zza(JSONObject jSONObject, boolean z) {
        try {
            zzb(zza(jSONObject), z);
        } catch (Throwable th) {
            zzpk.zzb("Skipping active view message.", th);
        }
    }

    public void zzb(zzcx zzcx) {
        this.zzwz.remove(zzcx);
        zzcx.zzdW();
        if (this.zzwz.isEmpty()) {
            destroy();
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzqw zzqw, Map<String, String> map) {
        zza(zzqw.getView(), map);
    }

    /* access modifiers changed from: protected */
    public void zzb(JSONObject jSONObject, boolean z) {
        Iterator it = new ArrayList(this.zzwz).iterator();
        while (it.hasNext()) {
            ((zzcx) it.next()).zzc(jSONObject, z);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzb(@Nullable Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.zzwn.zzdC());
    }

    /* access modifiers changed from: package-private */
    public void zzc(Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            zzj("1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible")));
        }
    }

    /* access modifiers changed from: protected */
    public JSONObject zzd(@Nullable View view) throws JSONException {
        if (view == null) {
            return zzdP();
        }
        boolean isAttachedToWindow = zzw.zzcO().isAttachedToWindow(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Exception e) {
            zzpk.zzb("Failure getting view location.", e);
        }
        DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        rect2.right = this.zzwo.getDefaultDisplay().getWidth();
        rect2.bottom = this.zzwo.getDefaultDisplay().getHeight();
        Rect rect3 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect3, (Point) null);
        Rect rect4 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect4);
        Rect rect5 = new Rect();
        view.getHitRect(rect5);
        JSONObject zzdM = zzdM();
        zzdM.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", isAttachedToWindow).put("viewBox", new JSONObject().put("top", zza(rect2.top, displayMetrics)).put("bottom", zza(rect2.bottom, displayMetrics)).put("left", zza(rect2.left, displayMetrics)).put("right", zza(rect2.right, displayMetrics))).put("adBox", new JSONObject().put("top", zza(rect.top, displayMetrics)).put("bottom", zza(rect.bottom, displayMetrics)).put("left", zza(rect.left, displayMetrics)).put("right", zza(rect.right, displayMetrics))).put("globalVisibleBox", new JSONObject().put("top", zza(rect3.top, displayMetrics)).put("bottom", zza(rect3.bottom, displayMetrics)).put("left", zza(rect3.left, displayMetrics)).put("right", zza(rect3.right, displayMetrics))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect4.top, displayMetrics)).put("bottom", zza(rect4.bottom, displayMetrics)).put("left", zza(rect4.left, displayMetrics)).put("right", zza(rect4.right, displayMetrics))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect5.top, displayMetrics)).put("bottom", zza(rect5.bottom, displayMetrics)).put("left", zza(rect5.left, displayMetrics)).put("right", zza(rect5.right, displayMetrics))).put("screenDensity", (double) displayMetrics.density).put("isVisible", zzw.zzcM().zza(view, this.zzwp, this.zzwq));
        return zzdM;
    }

    /* access modifiers changed from: protected */
    public void zzdF() {
        synchronized (this.zzrJ) {
            if (this.zzwx == null) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                this.zzwx = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        zzct.this.zzk(3);
                    }
                };
                this.zzwi.registerReceiver(this.zzwx, intentFilter);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzdG() {
        synchronized (this.zzrJ) {
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
        return;
    }

    public void zzdH() {
        synchronized (this.zzrJ) {
            if (this.zzwu) {
                this.zzwv = true;
                try {
                    zza(zzdQ(), true);
                } catch (JSONException e) {
                    zzpk.zzb("JSON failure while processing active view data.", e);
                } catch (RuntimeException e2) {
                    zzpk.zzb("Failure while processing active view data.", e2);
                }
                String valueOf = String.valueOf(this.zzwn.zzdC());
                zzpk.zzbf(valueOf.length() != 0 ? "Untracking ad unit: ".concat(valueOf) : new String("Untracking ad unit: "));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzdI() {
        if (this.zzwr != null) {
            this.zzwr.zza(this);
        }
    }

    public boolean zzdJ() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzwu;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r0 = (android.view.ViewTreeObserver) r3.zzwl.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzdK() {
        /*
            r3 = this;
            com.google.android.gms.internal.zzda r0 = r3.zzwm
            com.google.android.gms.internal.zzda r0 = r0.zzdU()
            android.view.View r1 = r0.zzdS()
            if (r1 != 0) goto L_0x000d
        L_0x000c:
            return
        L_0x000d:
            java.lang.ref.WeakReference<android.view.ViewTreeObserver> r0 = r3.zzwl
            java.lang.Object r0 = r0.get()
            android.view.ViewTreeObserver r0 = (android.view.ViewTreeObserver) r0
            android.view.ViewTreeObserver r1 = r1.getViewTreeObserver()
            if (r1 == r0) goto L_0x000c
            r3.zzdL()
            boolean r2 = r3.zzws
            if (r2 == 0) goto L_0x002a
            if (r0 == 0) goto L_0x0033
            boolean r0 = r0.isAlive()
            if (r0 == 0) goto L_0x0033
        L_0x002a:
            r0 = 1
            r3.zzws = r0
            r1.addOnScrollChangedListener(r3)
            r1.addOnGlobalLayoutListener(r3)
        L_0x0033:
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
            r0.<init>(r1)
            r3.zzwl = r0
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzct.zzdK():void");
    }

    /* access modifiers changed from: protected */
    public void zzdL() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzwl.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public JSONObject zzdM() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zzwn.zzdA()).put("activeViewJSON", this.zzwn.zzdB()).put("timestamp", zzw.zzcS().elapsedRealtime()).put("adFormat", this.zzwn.zzdz()).put("hashCode", this.zzwn.zzdC()).put("isMraid", this.zzwn.zzdD()).put("isStopped", this.zzwt).put("isPaused", this.zzuo).put("isScreenOn", isScreenOn()).put("isNative", this.zzwn.zzdE()).put("appMuted", zzw.zzcM().zzcs()).put("appVolume", (double) zzw.zzcM().zzcq()).put("deviceVolume", (double) zzw.zzcM().zzS(this.zzwi));
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public void zzdN() {
        Iterator it = new ArrayList(this.zzwz).iterator();
        while (it.hasNext()) {
            zzb((zzcx) it.next());
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzdO() {
        Iterator<zzcx> it = this.zzwz.iterator();
        while (it.hasNext()) {
            if (it.next().zzdV()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public JSONObject zzdP() throws JSONException {
        return zzdM().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
    }

    /* access modifiers changed from: protected */
    public JSONObject zzdQ() throws JSONException {
        JSONObject zzdM = zzdM();
        zzdM.put("doneReasonCode", "u");
        return zzdM;
    }

    public zzcr zzdR() {
        return this.zzwn;
    }

    /* access modifiers changed from: protected */
    public void zzj(boolean z) {
        Iterator<Object> it = this.zzwy.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0075, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0076, code lost:
        com.google.android.gms.internal.zzpk.zza("Active view update failed.", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzk(int r8) {
        /*
            r7 = this;
            r0 = 0
            r1 = 1
            java.lang.Object r3 = r7.zzrJ
            monitor-enter(r3)
            boolean r2 = r7.zzdO()     // Catch:{ all -> 0x0041 }
            if (r2 == 0) goto L_0x000f
            boolean r2 = r7.zzwu     // Catch:{ all -> 0x0041 }
            if (r2 != 0) goto L_0x0011
        L_0x000f:
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
        L_0x0010:
            return
        L_0x0011:
            com.google.android.gms.internal.zzda r2 = r7.zzwm     // Catch:{ all -> 0x0041 }
            android.view.View r4 = r2.zzdS()     // Catch:{ all -> 0x0041 }
            if (r4 == 0) goto L_0x0044
            com.google.android.gms.internal.zzpo r2 = com.google.android.gms.ads.internal.zzw.zzcM()     // Catch:{ all -> 0x0041 }
            android.os.PowerManager r5 = r7.zzwp     // Catch:{ all -> 0x0041 }
            android.app.KeyguardManager r6 = r7.zzwq     // Catch:{ all -> 0x0041 }
            boolean r2 = r2.zza((android.view.View) r4, (android.os.PowerManager) r5, (android.app.KeyguardManager) r6)     // Catch:{ all -> 0x0041 }
            if (r2 == 0) goto L_0x0044
            android.graphics.Rect r2 = new android.graphics.Rect     // Catch:{ all -> 0x0041 }
            r2.<init>()     // Catch:{ all -> 0x0041 }
            r5 = 0
            boolean r2 = r4.getGlobalVisibleRect(r2, r5)     // Catch:{ all -> 0x0041 }
            if (r2 == 0) goto L_0x0044
            r2 = r1
        L_0x0034:
            com.google.android.gms.internal.zzda r5 = r7.zzwm     // Catch:{ all -> 0x0041 }
            boolean r5 = r5.zzdT()     // Catch:{ all -> 0x0041 }
            if (r5 == 0) goto L_0x0046
            r7.zzdH()     // Catch:{ all -> 0x0041 }
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            goto L_0x0010
        L_0x0041:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            throw r0
        L_0x0044:
            r2 = r0
            goto L_0x0034
        L_0x0046:
            if (r8 != r1) goto L_0x0049
            r0 = r1
        L_0x0049:
            if (r0 == 0) goto L_0x0059
            com.google.android.gms.internal.zzpz r0 = r7.zzvT     // Catch:{ all -> 0x0041 }
            boolean r0 = r0.tryAcquire()     // Catch:{ all -> 0x0041 }
            if (r0 != 0) goto L_0x0059
            boolean r0 = r7.zzww     // Catch:{ all -> 0x0041 }
            if (r2 != r0) goto L_0x0059
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            goto L_0x0010
        L_0x0059:
            if (r2 != 0) goto L_0x0063
            boolean r0 = r7.zzww     // Catch:{ all -> 0x0041 }
            if (r0 != 0) goto L_0x0063
            if (r8 != r1) goto L_0x0063
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            goto L_0x0010
        L_0x0063:
            org.json.JSONObject r0 = r7.zzd(r4)     // Catch:{ JSONException -> 0x007c, RuntimeException -> 0x0075 }
            r1 = 0
            r7.zza((org.json.JSONObject) r0, (boolean) r1)     // Catch:{ JSONException -> 0x007c, RuntimeException -> 0x0075 }
            r7.zzww = r2     // Catch:{ JSONException -> 0x007c, RuntimeException -> 0x0075 }
        L_0x006d:
            r7.zzdK()     // Catch:{ all -> 0x0041 }
            r7.zzdI()     // Catch:{ all -> 0x0041 }
            monitor-exit(r3)     // Catch:{ all -> 0x0041 }
            goto L_0x0010
        L_0x0075:
            r0 = move-exception
        L_0x0076:
            java.lang.String r1 = "Active view update failed."
            com.google.android.gms.internal.zzpk.zza(r1, r0)     // Catch:{ all -> 0x0041 }
            goto L_0x006d
        L_0x007c:
            r0 = move-exception
            goto L_0x0076
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzct.zzk(int):void");
    }
}
