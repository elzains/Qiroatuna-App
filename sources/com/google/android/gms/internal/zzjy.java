package com.google.android.gms.internal;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

@zzme
public class zzjy implements zzjp {
    private final Context mContext;
    private final zzjr zzKY;
    private final boolean zzLa;
    private final zzmk zzLo;
    private final long zzLp;
    private final long zzLq;
    private boolean zzLs = false;
    private List<zzjv> zzLu = new ArrayList();
    private zzju zzLy;
    private final Object zzrJ = new Object();
    private final zzgl zzsn;
    private final zzka zzsz;
    private final boolean zzwf;

    public zzjy(Context context, zzmk zzmk, zzka zzka, zzjr zzjr, boolean z, boolean z2, long j, long j2, zzgl zzgl) {
        this.mContext = context;
        this.zzLo = zzmk;
        this.zzsz = zzka;
        this.zzKY = zzjr;
        this.zzwf = z;
        this.zzLa = z2;
        this.zzLp = j;
        this.zzLq = j2;
        this.zzsn = zzgl;
    }

    public void cancel() {
        synchronized (this.zzrJ) {
            this.zzLs = true;
            if (this.zzLy != null) {
                this.zzLy.cancel();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00a8, code lost:
        r2 = r21.zzLy.zza(r21.zzLp, r21.zzLq);
        r21.zzLu.add(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c1, code lost:
        if (r2.zzLh != 0) goto L_0x010d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00c3, code lost:
        com.google.android.gms.internal.zzpk.zzbf("Adapter succeeded.");
        r21.zzsn.zzh("mediation_network_succeed", r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00d5, code lost:
        if (r15.isEmpty() != false) goto L_0x00e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00d7, code lost:
        r21.zzsn.zzh("mediation_networks_fail", android.text.TextUtils.join(",", r15));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00e6, code lost:
        r21.zzsn.zza(r19, "mls");
        r21.zzsn.zza(r16, "ttm");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x010d, code lost:
        r15.add(r4);
        r21.zzsn.zza(r19, "mlf");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0123, code lost:
        if (r2.zzLj == null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0125, code lost:
        com.google.android.gms.internal.zzpo.zzXC.post(new com.google.android.gms.internal.zzjy.C04511(r21));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzjv zzd(java.util.List<com.google.android.gms.internal.zzjq> r22) {
        /*
            r21 = this;
            java.lang.String r2 = "Starting mediation."
            com.google.android.gms.internal.zzpk.zzbf(r2)
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            r0 = r21
            com.google.android.gms.internal.zzgl r2 = r0.zzsn
            com.google.android.gms.internal.zzgj r16 = r2.zzfB()
            java.util.Iterator r17 = r22.iterator()
        L_0x0016:
            boolean r2 = r17.hasNext()
            if (r2 == 0) goto L_0x0133
            java.lang.Object r7 = r17.next()
            com.google.android.gms.internal.zzjq r7 = (com.google.android.gms.internal.zzjq) r7
            java.lang.String r3 = "Trying mediation network: "
            java.lang.String r2 = r7.zzKo
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r4 = r2.length()
            if (r4 == 0) goto L_0x0066
            java.lang.String r2 = r3.concat(r2)
        L_0x0034:
            com.google.android.gms.internal.zzpk.zzbg(r2)
            java.util.List<java.lang.String> r2 = r7.zzKp
            java.util.Iterator r18 = r2.iterator()
        L_0x003d:
            boolean r2 = r18.hasNext()
            if (r2 == 0) goto L_0x0016
            java.lang.Object r4 = r18.next()
            java.lang.String r4 = (java.lang.String) r4
            r0 = r21
            com.google.android.gms.internal.zzgl r2 = r0.zzsn
            com.google.android.gms.internal.zzgj r19 = r2.zzfB()
            r0 = r21
            java.lang.Object r0 = r0.zzrJ
            r20 = r0
            monitor-enter(r20)
            r0 = r21
            boolean r2 = r0.zzLs     // Catch:{ all -> 0x010a }
            if (r2 == 0) goto L_0x006c
            com.google.android.gms.internal.zzjv r2 = new com.google.android.gms.internal.zzjv     // Catch:{ all -> 0x010a }
            r3 = -1
            r2.<init>(r3)     // Catch:{ all -> 0x010a }
            monitor-exit(r20)     // Catch:{ all -> 0x010a }
        L_0x0065:
            return r2
        L_0x0066:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r3)
            goto L_0x0034
        L_0x006c:
            com.google.android.gms.internal.zzju r2 = new com.google.android.gms.internal.zzju     // Catch:{ all -> 0x010a }
            r0 = r21
            android.content.Context r3 = r0.mContext     // Catch:{ all -> 0x010a }
            r0 = r21
            com.google.android.gms.internal.zzka r5 = r0.zzsz     // Catch:{ all -> 0x010a }
            r0 = r21
            com.google.android.gms.internal.zzjr r6 = r0.zzKY     // Catch:{ all -> 0x010a }
            r0 = r21
            com.google.android.gms.internal.zzmk r8 = r0.zzLo     // Catch:{ all -> 0x010a }
            com.google.android.gms.internal.zzec r8 = r8.zzRy     // Catch:{ all -> 0x010a }
            r0 = r21
            com.google.android.gms.internal.zzmk r9 = r0.zzLo     // Catch:{ all -> 0x010a }
            com.google.android.gms.internal.zzeg r9 = r9.zzvr     // Catch:{ all -> 0x010a }
            r0 = r21
            com.google.android.gms.internal.zzmk r10 = r0.zzLo     // Catch:{ all -> 0x010a }
            com.google.android.gms.internal.zzqh r10 = r10.zzvn     // Catch:{ all -> 0x010a }
            r0 = r21
            boolean r11 = r0.zzwf     // Catch:{ all -> 0x010a }
            r0 = r21
            boolean r12 = r0.zzLa     // Catch:{ all -> 0x010a }
            r0 = r21
            com.google.android.gms.internal.zzmk r13 = r0.zzLo     // Catch:{ all -> 0x010a }
            com.google.android.gms.internal.zzhc r13 = r13.zzvF     // Catch:{ all -> 0x010a }
            r0 = r21
            com.google.android.gms.internal.zzmk r14 = r0.zzLo     // Catch:{ all -> 0x010a }
            java.util.List<java.lang.String> r14 = r14.zzvK     // Catch:{ all -> 0x010a }
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x010a }
            r0 = r21
            r0.zzLy = r2     // Catch:{ all -> 0x010a }
            monitor-exit(r20)     // Catch:{ all -> 0x010a }
            r0 = r21
            com.google.android.gms.internal.zzju r2 = r0.zzLy
            r0 = r21
            long r8 = r0.zzLp
            r0 = r21
            long r10 = r0.zzLq
            com.google.android.gms.internal.zzjv r2 = r2.zza((long) r8, (long) r10)
            r0 = r21
            java.util.List<com.google.android.gms.internal.zzjv> r3 = r0.zzLu
            r3.add(r2)
            int r3 = r2.zzLh
            if (r3 != 0) goto L_0x010d
            java.lang.String r3 = "Adapter succeeded."
            com.google.android.gms.internal.zzpk.zzbf(r3)
            r0 = r21
            com.google.android.gms.internal.zzgl r3 = r0.zzsn
            java.lang.String r5 = "mediation_network_succeed"
            r3.zzh(r5, r4)
            boolean r3 = r15.isEmpty()
            if (r3 != 0) goto L_0x00e6
            r0 = r21
            com.google.android.gms.internal.zzgl r3 = r0.zzsn
            java.lang.String r4 = "mediation_networks_fail"
            java.lang.String r5 = ","
            java.lang.String r5 = android.text.TextUtils.join(r5, r15)
            r3.zzh(r4, r5)
        L_0x00e6:
            r0 = r21
            com.google.android.gms.internal.zzgl r3 = r0.zzsn
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]
            r5 = 0
            java.lang.String r6 = "mls"
            r4[r5] = r6
            r0 = r19
            r3.zza(r0, r4)
            r0 = r21
            com.google.android.gms.internal.zzgl r3 = r0.zzsn
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]
            r5 = 0
            java.lang.String r6 = "ttm"
            r4[r5] = r6
            r0 = r16
            r3.zza(r0, r4)
            goto L_0x0065
        L_0x010a:
            r2 = move-exception
            monitor-exit(r20)     // Catch:{ all -> 0x010a }
            throw r2
        L_0x010d:
            r15.add(r4)
            r0 = r21
            com.google.android.gms.internal.zzgl r3 = r0.zzsn
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]
            r5 = 0
            java.lang.String r6 = "mlf"
            r4[r5] = r6
            r0 = r19
            r3.zza(r0, r4)
            com.google.android.gms.internal.zzkb r3 = r2.zzLj
            if (r3 == 0) goto L_0x003d
            android.os.Handler r3 = com.google.android.gms.internal.zzpo.zzXC
            com.google.android.gms.internal.zzjy$1 r4 = new com.google.android.gms.internal.zzjy$1
            r0 = r21
            r4.<init>(r0, r2)
            r3.post(r4)
            goto L_0x003d
        L_0x0133:
            boolean r2 = r15.isEmpty()
            if (r2 != 0) goto L_0x0148
            r0 = r21
            com.google.android.gms.internal.zzgl r2 = r0.zzsn
            java.lang.String r3 = "mediation_networks_fail"
            java.lang.String r4 = ","
            java.lang.String r4 = android.text.TextUtils.join(r4, r15)
            r2.zzh(r3, r4)
        L_0x0148:
            com.google.android.gms.internal.zzjv r2 = new com.google.android.gms.internal.zzjv
            r3 = 1
            r2.<init>(r3)
            goto L_0x0065
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzjy.zzd(java.util.List):com.google.android.gms.internal.zzjv");
    }

    public List<zzjv> zzgU() {
        return this.zzLu;
    }
}
