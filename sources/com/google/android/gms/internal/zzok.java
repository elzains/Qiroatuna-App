package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzof;
import com.google.android.gms.internal.zzpb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Future;

@zzme
public class zzok extends zzpj implements zzoj {
    private final Context mContext;
    private final zzpb.zza zzPR;
    private final ArrayList<Future> zzVG;
    private final ArrayList<String> zzVH;
    private final HashMap<String, zzoe> zzVI;
    private final List<zzof> zzVJ;
    private final HashSet<String> zzVK;
    /* access modifiers changed from: private */
    public final zzns zzVL;
    private final long zzVv;
    private final Object zzrJ;

    public zzok(Context context, zzpb.zza zza, zzns zzns) {
        this(context, zza, zzns, zzgd.zzCO.get().longValue());
    }

    zzok(Context context, zzpb.zza zza, zzns zzns, long j) {
        this.zzVG = new ArrayList<>();
        this.zzVH = new ArrayList<>();
        this.zzVI = new HashMap<>();
        this.zzVJ = new ArrayList();
        this.zzVK = new HashSet<>();
        this.zzrJ = new Object();
        this.mContext = context;
        this.zzPR = zza;
        this.zzVL = zzns;
        this.zzVv = j;
    }

    private static int zzT(int i) {
        switch (i) {
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
                return 4;
            case 6:
                return 0;
            case 7:
                return 3;
            default:
                return 6;
        }
    }

    private zzpb zza(int i, @Nullable String str, @Nullable zzjq zzjq) {
        return new zzpb(this.zzPR.zzTi.zzRy, (zzqw) null, this.zzPR.zzWm.zzKF, i, this.zzPR.zzWm.zzKG, this.zzPR.zzWm.zzSp, this.zzPR.zzWm.orientation, this.zzPR.zzWm.zzKL, this.zzPR.zzTi.zzRB, this.zzPR.zzWm.zzSn, zzjq, (zzkb) null, str, this.zzPR.zzWc, (zzjt) null, this.zzPR.zzWm.zzSo, this.zzPR.zzvr, this.zzPR.zzWm.zzSm, this.zzPR.zzWg, this.zzPR.zzWm.zzSr, this.zzPR.zzWm.zzSs, this.zzPR.zzWa, (zzha.zza) null, this.zzPR.zzWm.zzSC, this.zzPR.zzWm.zzSD, this.zzPR.zzWm.zzSE, this.zzPR.zzWm.zzSF, this.zzPR.zzWm.zzSG, zzjM(), this.zzPR.zzWm.zzKI, this.zzPR.zzWm.zzSJ);
    }

    private zzpb zza(String str, zzjq zzjq) {
        return zza(-2, str, zzjq);
    }

    private static String zza(zzof zzof) {
        String str = zzof.zzKq;
        int zzT = zzT(zzof.errorCode);
        return new StringBuilder(String.valueOf(str).length() + 33).append(str).append(".").append(zzT).append(".").append(zzof.zzLn).toString();
    }

    private void zza(String str, String str2, zzjq zzjq) {
        synchronized (this.zzrJ) {
            zzol zzaN = this.zzVL.zzaN(str);
            if (zzaN == null || zzaN.zzjO() == null || zzaN.zzjN() == null) {
                this.zzVJ.add(new zzof.zza().zzaQ(zzjq.zzKq).zzaP(str).zzl(0).zzae(7).zzjK());
                return;
            }
            zzoe zza = zza(str, str2, zzjq, zzaN);
            this.zzVG.add((Future) zza.zziP());
            this.zzVH.add(str);
            this.zzVI.put(str, zza);
        }
    }

    private zzpb zzjL() {
        return zza(3, (String) null, (zzjq) null);
    }

    private String zzjM() {
        StringBuilder sb = new StringBuilder("");
        if (this.zzVJ == null) {
            return sb.toString();
        }
        for (zzof next : this.zzVJ) {
            if (next != null && !TextUtils.isEmpty(next.zzKq)) {
                sb.append(String.valueOf(zza(next)).concat("_"));
            }
        }
        return sb.substring(0, Math.max(0, sb.length() - 1));
    }

    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public zzoe zza(String str, String str2, zzjq zzjq, zzol zzol) {
        return new zzoe(this.mContext, str, str2, zzjq, this.zzPR, zzol, this, this.zzVv);
    }

    public void zza(String str, int i) {
    }

    public void zzaO(String str) {
        synchronized (this.zzrJ) {
            this.zzVK.add(str);
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public void zzco() {
        /*
            r6 = this;
            com.google.android.gms.internal.zzpb$zza r0 = r6.zzPR
            com.google.android.gms.internal.zzjr r0 = r0.zzWc
            java.util.List<com.google.android.gms.internal.zzjq> r0 = r0.zzKD
            java.util.Iterator r2 = r0.iterator()
        L_0x000a:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0050
            java.lang.Object r0 = r2.next()
            com.google.android.gms.internal.zzjq r0 = (com.google.android.gms.internal.zzjq) r0
            java.lang.String r3 = r0.zzKv
            java.util.List<java.lang.String> r1 = r0.zzKp
            java.util.Iterator r4 = r1.iterator()
        L_0x001e:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x000a
            java.lang.Object r1 = r4.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r5 = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L_0x003a
            java.lang.String r5 = "com.google.ads.mediation.customevent.CustomEventAdapter"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0045
        L_0x003a:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0049 }
            r1.<init>(r3)     // Catch:{ JSONException -> 0x0049 }
            java.lang.String r5 = "class_name"
            java.lang.String r1 = r1.getString(r5)     // Catch:{ JSONException -> 0x0049 }
        L_0x0045:
            r6.zza((java.lang.String) r1, (java.lang.String) r3, (com.google.android.gms.internal.zzjq) r0)
            goto L_0x001e
        L_0x0049:
            r1 = move-exception
            java.lang.String r5 = "Unable to determine custom event class name, skipping..."
            com.google.android.gms.internal.zzpk.zzb(r5, r1)
            goto L_0x001e
        L_0x0050:
            r0 = 0
            r1 = r0
        L_0x0052:
            java.util.ArrayList<java.util.concurrent.Future> r0 = r6.zzVG
            int r0 = r0.size()
            if (r1 >= r0) goto L_0x00f7
            java.util.ArrayList<java.util.concurrent.Future> r0 = r6.zzVG     // Catch:{ InterruptedException -> 0x00ca, Exception -> 0x0109 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ InterruptedException -> 0x00ca, Exception -> 0x0109 }
            java.util.concurrent.Future r0 = (java.util.concurrent.Future) r0     // Catch:{ InterruptedException -> 0x00ca, Exception -> 0x0109 }
            r0.get()     // Catch:{ InterruptedException -> 0x00ca, Exception -> 0x0109 }
            java.lang.Object r2 = r6.zzrJ
            monitor-enter(r2)
            java.util.ArrayList<java.lang.String> r0 = r6.zzVH     // Catch:{ all -> 0x00c7 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x00c7 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x00c7 }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x00c7 }
            if (r3 != 0) goto L_0x0089
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r3 = r6.zzVI     // Catch:{ all -> 0x00c7 }
            java.lang.Object r0 = r3.get(r0)     // Catch:{ all -> 0x00c7 }
            com.google.android.gms.internal.zzoe r0 = (com.google.android.gms.internal.zzoe) r0     // Catch:{ all -> 0x00c7 }
            if (r0 == 0) goto L_0x0089
            java.util.List<com.google.android.gms.internal.zzof> r3 = r6.zzVJ     // Catch:{ all -> 0x00c7 }
            com.google.android.gms.internal.zzof r0 = r0.zzjH()     // Catch:{ all -> 0x00c7 }
            r3.add(r0)     // Catch:{ all -> 0x00c7 }
        L_0x0089:
            monitor-exit(r2)     // Catch:{ all -> 0x00c7 }
            java.lang.Object r2 = r6.zzrJ
            monitor-enter(r2)
            java.util.HashSet<java.lang.String> r0 = r6.zzVK     // Catch:{ all -> 0x016c }
            java.util.ArrayList<java.lang.String> r3 = r6.zzVH     // Catch:{ all -> 0x016c }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x016c }
            boolean r0 = r0.contains(r3)     // Catch:{ all -> 0x016c }
            if (r0 == 0) goto L_0x016a
            java.util.ArrayList<java.lang.String> r0 = r6.zzVH     // Catch:{ all -> 0x016c }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x016c }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x016c }
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r1 = r6.zzVI     // Catch:{ all -> 0x016c }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x016c }
            if (r1 == 0) goto L_0x0167
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r1 = r6.zzVI     // Catch:{ all -> 0x016c }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x016c }
            com.google.android.gms.internal.zzoe r1 = (com.google.android.gms.internal.zzoe) r1     // Catch:{ all -> 0x016c }
            com.google.android.gms.internal.zzjq r1 = r1.zzjI()     // Catch:{ all -> 0x016c }
        L_0x00b7:
            com.google.android.gms.internal.zzpb r0 = r6.zza((java.lang.String) r0, (com.google.android.gms.internal.zzjq) r1)     // Catch:{ all -> 0x016c }
            android.os.Handler r1 = com.google.android.gms.internal.zzqe.zzYP     // Catch:{ all -> 0x016c }
            com.google.android.gms.internal.zzok$1 r3 = new com.google.android.gms.internal.zzok$1     // Catch:{ all -> 0x016c }
            r3.<init>(r0)     // Catch:{ all -> 0x016c }
            r1.post(r3)     // Catch:{ all -> 0x016c }
            monitor-exit(r2)     // Catch:{ all -> 0x016c }
        L_0x00c6:
            return
        L_0x00c7:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x00c7 }
            throw r0
        L_0x00ca:
            r0 = move-exception
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x013c }
            r0.interrupt()     // Catch:{ all -> 0x013c }
            java.lang.Object r2 = r6.zzrJ
            monitor-enter(r2)
            java.util.ArrayList<java.lang.String> r0 = r6.zzVH     // Catch:{ all -> 0x0106 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0106 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0106 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0106 }
            if (r1 != 0) goto L_0x00f6
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r1 = r6.zzVI     // Catch:{ all -> 0x0106 }
            java.lang.Object r0 = r1.get(r0)     // Catch:{ all -> 0x0106 }
            com.google.android.gms.internal.zzoe r0 = (com.google.android.gms.internal.zzoe) r0     // Catch:{ all -> 0x0106 }
            if (r0 == 0) goto L_0x00f6
            java.util.List<com.google.android.gms.internal.zzof> r1 = r6.zzVJ     // Catch:{ all -> 0x0106 }
            com.google.android.gms.internal.zzof r0 = r0.zzjH()     // Catch:{ all -> 0x0106 }
            r1.add(r0)     // Catch:{ all -> 0x0106 }
        L_0x00f6:
            monitor-exit(r2)     // Catch:{ all -> 0x0106 }
        L_0x00f7:
            com.google.android.gms.internal.zzpb r0 = r6.zzjL()
            android.os.Handler r1 = com.google.android.gms.internal.zzqe.zzYP
            com.google.android.gms.internal.zzok$2 r2 = new com.google.android.gms.internal.zzok$2
            r2.<init>(r0)
            r1.post(r2)
            goto L_0x00c6
        L_0x0106:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0106 }
            throw r0
        L_0x0109:
            r0 = move-exception
            java.lang.String r2 = "Unable to resolve rewarded adapter."
            com.google.android.gms.internal.zzpk.zzc(r2, r0)     // Catch:{ all -> 0x013c }
            java.lang.Object r2 = r6.zzrJ
            monitor-enter(r2)
            java.util.ArrayList<java.lang.String> r0 = r6.zzVH     // Catch:{ all -> 0x0139 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0139 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0139 }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0139 }
            if (r3 != 0) goto L_0x0133
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r3 = r6.zzVI     // Catch:{ all -> 0x0139 }
            java.lang.Object r0 = r3.get(r0)     // Catch:{ all -> 0x0139 }
            com.google.android.gms.internal.zzoe r0 = (com.google.android.gms.internal.zzoe) r0     // Catch:{ all -> 0x0139 }
            if (r0 == 0) goto L_0x0133
            java.util.List<com.google.android.gms.internal.zzof> r3 = r6.zzVJ     // Catch:{ all -> 0x0139 }
            com.google.android.gms.internal.zzof r0 = r0.zzjH()     // Catch:{ all -> 0x0139 }
            r3.add(r0)     // Catch:{ all -> 0x0139 }
        L_0x0133:
            monitor-exit(r2)     // Catch:{ all -> 0x0139 }
        L_0x0134:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x0052
        L_0x0139:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0139 }
            throw r0
        L_0x013c:
            r0 = move-exception
            r2 = r0
            java.lang.Object r3 = r6.zzrJ
            monitor-enter(r3)
            java.util.ArrayList<java.lang.String> r0 = r6.zzVH     // Catch:{ all -> 0x0164 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0164 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0164 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0164 }
            if (r1 != 0) goto L_0x0162
            java.util.HashMap<java.lang.String, com.google.android.gms.internal.zzoe> r1 = r6.zzVI     // Catch:{ all -> 0x0164 }
            java.lang.Object r0 = r1.get(r0)     // Catch:{ all -> 0x0164 }
            com.google.android.gms.internal.zzoe r0 = (com.google.android.gms.internal.zzoe) r0     // Catch:{ all -> 0x0164 }
            if (r0 == 0) goto L_0x0162
            java.util.List<com.google.android.gms.internal.zzof> r1 = r6.zzVJ     // Catch:{ all -> 0x0164 }
            com.google.android.gms.internal.zzof r0 = r0.zzjH()     // Catch:{ all -> 0x0164 }
            r1.add(r0)     // Catch:{ all -> 0x0164 }
        L_0x0162:
            monitor-exit(r3)     // Catch:{ all -> 0x0164 }
            throw r2
        L_0x0164:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0164 }
            throw r0
        L_0x0167:
            r1 = 0
            goto L_0x00b7
        L_0x016a:
            monitor-exit(r2)     // Catch:{ all -> 0x016c }
            goto L_0x0134
        L_0x016c:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x016c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzok.zzco():void");
    }
}
