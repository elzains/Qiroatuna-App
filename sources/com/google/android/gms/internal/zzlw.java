package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.zzf;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzqp;
import java.lang.ref.WeakReference;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzme
public class zzlw {
    private static final long zzQl = TimeUnit.SECONDS.toMillis(60);
    private static boolean zzQm = false;
    private static zzji zzQn = null;
    private static final Object zztX = new Object();
    private final Context mContext;
    /* access modifiers changed from: private */
    public final zzs zzGJ;
    private final zzaw zzGP;
    private zzjg zzQo;
    private zzji.zze zzQp;
    private zzjf zzQq;
    private boolean zzQr;
    private final zzqh zzuc;

    public static abstract class zza {
        public abstract void zze(zzjj zzjj);

        public void zzjd() {
        }
    }

    public zzlw(Context context, zzs zzs, zzaw zzaw, zzqh zzqh) {
        this.zzQr = false;
        this.mContext = context;
        this.zzGJ = zzs;
        this.zzGP = zzaw;
        this.zzuc = zzqh;
        this.zzQr = zzgd.zzEm.get().booleanValue();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzlw(Context context, zzpb.zza zza2, zzs zzs, zzaw zzaw) {
        this(context, zzs, zzaw, (zza2 == null || zza2.zzTi == null) ? null : zza2.zzTi.zzvn);
    }

    private void zziV() {
        synchronized (zztX) {
            if (!zzQm) {
                zzQn = new zzji(this.mContext.getApplicationContext() != null ? this.mContext.getApplicationContext() : this.mContext, this.zzuc, zzgd.zzEj.get(), new zzpt<zzjf>() {
                    /* renamed from: zza */
                    public void zzd(zzjf zzjf) {
                        zzs zzs = (zzs) new WeakReference(zzlw.this.zzGJ).get();
                        zzjf.zza(zzs, zzs, zzs, zzs, false, (zzif) null, (zzih) null, (zzf) null, (zzkx) null);
                    }
                }, new zzji.zzb());
                zzQm = true;
            }
        }
    }

    private void zziW() {
        this.zzQp = new zzji.zze(zzjb().zzc(this.zzGP));
    }

    private void zziX() {
        this.zzQo = new zzjg();
    }

    private void zziY() throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        this.zzQq = zziZ().zza(this.mContext, this.zzuc, zzgd.zzEj.get(), this.zzGP, this.zzGJ.zzby()).get(zzQl, TimeUnit.MILLISECONDS);
        this.zzQq.zza(this.zzGJ, this.zzGJ, this.zzGJ, this.zzGJ, false, (zzif) null, (zzih) null, (zzf) null, (zzkx) null);
    }

    public void zza(final zza zza2) {
        if (this.zzQr) {
            zzji.zze zzjc = zzjc();
            if (zzjc == null) {
                zzpk.zzbh("SharedJavascriptEngine not initialized");
            } else {
                zzjc.zza(new zzqp.zzc<zzjj>(this) {
                    /* renamed from: zzb */
                    public void zzd(zzjj zzjj) {
                        zza2.zze(zzjj);
                    }
                }, new zzqp.zza(this) {
                    public void run() {
                        zza2.zzjd();
                    }
                });
            }
        } else {
            zzjf zzja = zzja();
            if (zzja == null) {
                zzpk.zzbh("JavascriptEngine not initialized");
            } else {
                zza2.zze(zzja);
            }
        }
    }

    public void zziT() {
        if (this.zzQr) {
            zziV();
        } else {
            zziX();
        }
    }

    public void zziU() throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        if (this.zzQr) {
            zziW();
        } else {
            zziY();
        }
    }

    /* access modifiers changed from: protected */
    public zzjg zziZ() {
        return this.zzQo;
    }

    /* access modifiers changed from: protected */
    public zzjf zzja() {
        return this.zzQq;
    }

    /* access modifiers changed from: protected */
    public zzji zzjb() {
        return zzQn;
    }

    /* access modifiers changed from: protected */
    public zzji.zze zzjc() {
        return this.zzQp;
    }
}
