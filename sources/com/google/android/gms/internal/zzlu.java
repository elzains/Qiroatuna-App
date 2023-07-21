package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzlp;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzpb;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@zzme
public class zzlu extends zzlp {
    /* access modifiers changed from: private */
    public final zzqw zzIs;
    private zzjr zzKY;
    zzjp zzQe;
    protected zzjv zzQf;
    /* access modifiers changed from: private */
    public boolean zzQg;
    private final zzgl zzsn;
    private zzka zzsz;

    zzlu(Context context, zzpb.zza zza, zzka zzka, zzlq.zza zza2, zzgl zzgl, zzqw zzqw) {
        super(context, zza, zza2);
        this.zzsz = zzka;
        this.zzKY = zza.zzWc;
        this.zzsn = zzgl;
        this.zzIs = zzqw;
    }

    private static int zzT(int i) {
        switch (i) {
            case -1:
                return 4;
            case 0:
                return 0;
            case 1:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 5;
            default:
                return 6;
        }
    }

    private static String zza(zzjv zzjv) {
        String str = zzjv.zzLi.zzKq;
        int zzT = zzT(zzjv.zzLh);
        return new StringBuilder(String.valueOf(str).length() + 33).append(str).append(".").append(zzT).append(".").append(zzjv.zzLn).toString();
    }

    private static String zzg(List<zzjv> list) {
        if (list == null) {
            return "".toString();
        }
        String str = "";
        for (zzjv next : list) {
            if (!(next == null || next.zzLi == null || TextUtils.isEmpty(next.zzLi.zzKq))) {
                String valueOf = String.valueOf(str);
                String valueOf2 = String.valueOf(zza(next));
                str = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length()).append(valueOf).append(valueOf2).append("_").toString();
            }
        }
        return str.substring(0, Math.max(0, str.length() - 1));
    }

    private void zziS() throws zzlp.zza {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                synchronized (zzlu.this.zzPU) {
                    boolean unused = zzlu.this.zzQg = zzp.zza(zzlu.this.zzIs, zzlu.this.zzQf, countDownLatch);
                }
            }
        });
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
            synchronized (this.zzPU) {
                if (!this.zzQg) {
                    throw new zzlp.zza("View could not be prepared", 0);
                } else if (this.zzIs.isDestroyed()) {
                    throw new zzlp.zza("Assets not loaded, web view is destroyed", 0);
                }
            }
        } catch (InterruptedException e) {
            String valueOf = String.valueOf(e);
            throw new zzlp.zza(new StringBuilder(String.valueOf(valueOf).length() + 38).append("Interrupted while waiting for latch : ").append(valueOf).toString(), 0);
        }
    }

    public void onStop() {
        synchronized (this.zzPU) {
            super.onStop();
            if (this.zzQe != null) {
                this.zzQe.cancel();
            }
        }
    }

    /* access modifiers changed from: protected */
    public zzpb zzR(int i) {
        zzmk zzmk = this.zzPR.zzTi;
        return new zzpb(zzmk.zzRy, this.zzIs, this.zzPS.zzKF, i, this.zzPS.zzKG, this.zzPS.zzSp, this.zzPS.orientation, this.zzPS.zzKL, zzmk.zzRB, this.zzPS.zzSn, this.zzQf != null ? this.zzQf.zzLi : null, this.zzQf != null ? this.zzQf.zzLj : null, this.zzQf != null ? this.zzQf.zzLk : AdMobAdapter.class.getName(), this.zzKY, this.zzQf != null ? this.zzQf.zzLl : null, this.zzPS.zzSo, this.zzPR.zzvr, this.zzPS.zzSm, this.zzPR.zzWg, this.zzPS.zzSr, this.zzPS.zzSs, this.zzPR.zzWa, (zzha.zza) null, this.zzPS.zzSC, this.zzPS.zzSD, this.zzPS.zzSE, this.zzKY != null ? this.zzKY.zzKQ : false, this.zzPS.zzSG, this.zzQe != null ? zzg(this.zzQe.zzgU()) : null, this.zzPS.zzKI, this.zzPS.zzSJ);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0020, code lost:
        r0 = r0.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzh(long r6) throws com.google.android.gms.internal.zzlp.zza {
        /*
            r5 = this;
            r1 = 0
            java.lang.Object r2 = r5.zzPU
            monitor-enter(r2)
            com.google.android.gms.internal.zzjp r0 = r5.zzi(r6)     // Catch:{ all -> 0x004a }
            r5.zzQe = r0     // Catch:{ all -> 0x004a }
            monitor-exit(r2)     // Catch:{ all -> 0x004a }
            java.util.ArrayList r2 = new java.util.ArrayList
            com.google.android.gms.internal.zzjr r0 = r5.zzKY
            java.util.List<com.google.android.gms.internal.zzjq> r0 = r0.zzKD
            r2.<init>(r0)
            com.google.android.gms.internal.zzpb$zza r0 = r5.zzPR
            com.google.android.gms.internal.zzmk r0 = r0.zzTi
            com.google.android.gms.internal.zzec r0 = r0.zzRy
            android.os.Bundle r0 = r0.zzzd
            java.lang.String r3 = "com.google.ads.mediation.admob.AdMobAdapter"
            if (r0 == 0) goto L_0x0096
            android.os.Bundle r0 = r0.getBundle(r3)
            if (r0 == 0) goto L_0x0096
            java.lang.String r4 = "_skipMediation"
            boolean r0 = r0.getBoolean(r4)
        L_0x002c:
            if (r0 == 0) goto L_0x004d
            java.util.ListIterator r4 = r2.listIterator()
        L_0x0032:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x004d
            java.lang.Object r0 = r4.next()
            com.google.android.gms.internal.zzjq r0 = (com.google.android.gms.internal.zzjq) r0
            java.util.List<java.lang.String> r0 = r0.zzKp
            boolean r0 = r0.contains(r3)
            if (r0 != 0) goto L_0x0032
            r4.remove()
            goto L_0x0032
        L_0x004a:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x004a }
            throw r0
        L_0x004d:
            com.google.android.gms.internal.zzjp r0 = r5.zzQe
            com.google.android.gms.internal.zzjv r0 = r0.zzd(r2)
            r5.zzQf = r0
            com.google.android.gms.internal.zzjv r0 = r5.zzQf
            int r0 = r0.zzLh
            switch(r0) {
                case 0: goto L_0x0084;
                case 1: goto L_0x007b;
                default: goto L_0x005c;
            }
        L_0x005c:
            com.google.android.gms.internal.zzlp$zza r0 = new com.google.android.gms.internal.zzlp$zza
            com.google.android.gms.internal.zzjv r2 = r5.zzQf
            int r2 = r2.zzLh
            r3 = 40
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Unexpected mediation result: "
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2, r1)
            throw r0
        L_0x007b:
            com.google.android.gms.internal.zzlp$zza r0 = new com.google.android.gms.internal.zzlp$zza
            java.lang.String r1 = "No fill from any mediation ad networks."
            r2 = 3
            r0.<init>(r1, r2)
            throw r0
        L_0x0084:
            com.google.android.gms.internal.zzjv r0 = r5.zzQf
            com.google.android.gms.internal.zzjq r0 = r0.zzLi
            if (r0 == 0) goto L_0x0095
            com.google.android.gms.internal.zzjv r0 = r5.zzQf
            com.google.android.gms.internal.zzjq r0 = r0.zzLi
            java.lang.String r0 = r0.zzKy
            if (r0 == 0) goto L_0x0095
            r5.zziS()
        L_0x0095:
            return
        L_0x0096:
            r0 = r1
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzlu.zzh(long):void");
    }

    /* access modifiers changed from: package-private */
    public zzjp zzi(long j) {
        if (this.zzKY.zzKO != -1) {
            return new zzjx(this.mContext, this.zzPR.zzTi, this.zzsz, this.zzKY, this.zzPS.zzzB, this.zzPS.zzzD, j, zzgd.zzDM.get().longValue(), 2);
        }
        return new zzjy(this.mContext, this.zzPR.zzTi, this.zzsz, this.zzKY, this.zzPS.zzzB, this.zzPS.zzzD, j, zzgd.zzDM.get().longValue(), this.zzsn);
    }
}
