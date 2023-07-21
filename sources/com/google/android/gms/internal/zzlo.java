package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzqx;
import java.util.concurrent.atomic.AtomicBoolean;

@zzme
public abstract class zzlo implements zzpq<Void>, zzqx.zza {
    protected final Context mContext;
    protected final zzqw zzIs;
    protected final zzlq.zza zzPQ;
    protected final zzpb.zza zzPR;
    protected zzmn zzPS;
    private Runnable zzPT;
    protected final Object zzPU = new Object();
    /* access modifiers changed from: private */
    public AtomicBoolean zzPV = new AtomicBoolean(true);

    protected zzlo(Context context, zzpb.zza zza, zzqw zzqw, zzlq.zza zza2) {
        this.mContext = context;
        this.zzPR = zza;
        this.zzPS = this.zzPR.zzWm;
        this.zzIs = zzqw;
        this.zzPQ = zza2;
    }

    private zzpb zzR(int i) {
        zzmk zzmk = this.zzPR.zzTi;
        return new zzpb(zzmk.zzRy, this.zzIs, this.zzPS.zzKF, i, this.zzPS.zzKG, this.zzPS.zzSp, this.zzPS.orientation, this.zzPS.zzKL, zzmk.zzRB, this.zzPS.zzSn, (zzjq) null, (zzkb) null, (String) null, (zzjr) null, (zzjt) null, this.zzPS.zzSo, this.zzPR.zzvr, this.zzPS.zzSm, this.zzPR.zzWg, this.zzPS.zzSr, this.zzPS.zzSs, this.zzPR.zzWa, (zzha.zza) null, this.zzPS.zzSC, this.zzPS.zzSD, this.zzPS.zzSE, this.zzPS.zzSF, this.zzPS.zzSG, (String) null, this.zzPS.zzKI, this.zzPS.zzSJ);
    }

    public void cancel() {
        if (this.zzPV.getAndSet(false)) {
            this.zzIs.stopLoading();
            zzw.zzcO().zzl(this.zzIs);
            zzQ(-1);
            zzpo.zzXC.removeCallbacks(this.zzPT);
        }
    }

    /* access modifiers changed from: protected */
    public void zzQ(int i) {
        if (i != -2) {
            this.zzPS = new zzmn(i, this.zzPS.zzKL);
        }
        this.zzIs.zzlq();
        this.zzPQ.zzb(zzR(i));
    }

    public void zza(zzqw zzqw, boolean z) {
        int i = 0;
        zzpk.zzbf("WebView finished loading.");
        if (this.zzPV.getAndSet(false)) {
            if (z) {
                i = -2;
            }
            zzQ(i);
            zzpo.zzXC.removeCallbacks(this.zzPT);
        }
    }

    /* renamed from: zziN */
    public final Void zziP() {
        zzac.zzdj("Webview render task needs to be called on UI thread.");
        this.zzPT = new Runnable() {
            public void run() {
                if (zzlo.this.zzPV.get()) {
                    zzpk.m20e("Timed out waiting for WebView to finish loading.");
                    zzlo.this.cancel();
                }
            }
        };
        zzpo.zzXC.postDelayed(this.zzPT, zzgd.zzDM.get().longValue());
        zziO();
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void zziO();
}
