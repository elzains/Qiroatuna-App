package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.common.util.zzw;
import com.google.android.gms.common.util.zzz;

public class zzbay {
    private static boolean DEBUG = false;
    private static String TAG = "WakeLock";
    private static String zzbEz = "*gcore*:";
    private final Context mContext;
    private final String zzaHF;
    private final String zzaHH;
    private final PowerManager.WakeLock zzbEA;
    private final int zzbEB;
    private final String zzbEC;
    private boolean zzbED;
    private int zzbEE;
    private int zzbEF;
    private WorkSource zzbjq;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzbay(Context context, int i, String str) {
        this(context, i, str, (String) null, context == null ? null : context.getPackageName());
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zzbay(Context context, int i, String str, String str2, String str3) {
        this(context, i, str, str2, str3, (String) null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    public zzbay(Context context, int i, String str, String str2, String str3, String str4) {
        this.zzbED = true;
        zzac.zzh(str, "Wake lock name can NOT be empty");
        this.zzbEB = i;
        this.zzbEC = str2;
        this.zzaHH = str4;
        this.mContext = context.getApplicationContext();
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            String valueOf = String.valueOf(zzbEz);
            String valueOf2 = String.valueOf(str);
            this.zzaHF = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        } else {
            this.zzaHF = str;
        }
        this.zzbEA = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (zzz.zzbf(this.mContext)) {
            this.zzbjq = zzz.zzF(context, zzw.zzdz(str3) ? context.getPackageName() : str3);
            zzc(this.zzbjq);
        }
    }

    private void zzd(WorkSource workSource) {
        try {
            this.zzbEA.setWorkSource(workSource);
        } catch (IllegalArgumentException e) {
            Log.wtf(TAG, e.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        if (r9.zzbEF == 1) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r0 == false) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzgM(java.lang.String r10) {
        /*
            r9 = this;
            boolean r0 = r9.zzgN(r10)
            java.lang.String r5 = r9.zzo((java.lang.String) r10, (boolean) r0)
            monitor-enter(r9)
            boolean r1 = r9.zzbED     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0017
            int r1 = r9.zzbEE     // Catch:{ all -> 0x0045 }
            int r1 = r1 + -1
            r9.zzbEE = r1     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0020
            if (r0 != 0) goto L_0x0020
        L_0x0017:
            boolean r0 = r9.zzbED     // Catch:{ all -> 0x0045 }
            if (r0 != 0) goto L_0x0043
            int r0 = r9.zzbEF     // Catch:{ all -> 0x0045 }
            r1 = 1
            if (r0 != r1) goto L_0x0043
        L_0x0020:
            com.google.android.gms.common.stats.zze r0 = com.google.android.gms.common.stats.zze.zzyX()     // Catch:{ all -> 0x0045 }
            android.content.Context r1 = r9.mContext     // Catch:{ all -> 0x0045 }
            android.os.PowerManager$WakeLock r2 = r9.zzbEA     // Catch:{ all -> 0x0045 }
            java.lang.String r2 = com.google.android.gms.common.stats.zzc.zza(r2, r5)     // Catch:{ all -> 0x0045 }
            r3 = 8
            java.lang.String r4 = r9.zzaHF     // Catch:{ all -> 0x0045 }
            java.lang.String r6 = r9.zzaHH     // Catch:{ all -> 0x0045 }
            int r7 = r9.zzbEB     // Catch:{ all -> 0x0045 }
            android.os.WorkSource r8 = r9.zzbjq     // Catch:{ all -> 0x0045 }
            java.util.List r8 = com.google.android.gms.common.util.zzz.zzb(r8)     // Catch:{ all -> 0x0045 }
            r0.zza(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0045 }
            int r0 = r9.zzbEF     // Catch:{ all -> 0x0045 }
            int r0 = r0 + -1
            r9.zzbEF = r0     // Catch:{ all -> 0x0045 }
        L_0x0043:
            monitor-exit(r9)     // Catch:{ all -> 0x0045 }
            return
        L_0x0045:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0045 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbay.zzgM(java.lang.String):void");
    }

    private boolean zzgN(String str) {
        return !TextUtils.isEmpty(str) && !str.equals(this.zzbEC);
    }

    private String zzo(String str, boolean z) {
        return this.zzbED ? z ? str : this.zzbEC : this.zzbEC;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        if (r12.zzbEF == 0) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r0 == false) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzo(java.lang.String r13, long r14) {
        /*
            r12 = this;
            boolean r0 = r12.zzgN(r13)
            java.lang.String r6 = r12.zzo((java.lang.String) r13, (boolean) r0)
            monitor-enter(r12)
            boolean r1 = r12.zzbED     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x0017
            int r1 = r12.zzbEE     // Catch:{ all -> 0x0044 }
            int r2 = r1 + 1
            r12.zzbEE = r2     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x001f
            if (r0 != 0) goto L_0x001f
        L_0x0017:
            boolean r0 = r12.zzbED     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0042
            int r0 = r12.zzbEF     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x0042
        L_0x001f:
            com.google.android.gms.common.stats.zze r1 = com.google.android.gms.common.stats.zze.zzyX()     // Catch:{ all -> 0x0044 }
            android.content.Context r2 = r12.mContext     // Catch:{ all -> 0x0044 }
            android.os.PowerManager$WakeLock r0 = r12.zzbEA     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = com.google.android.gms.common.stats.zzc.zza(r0, r6)     // Catch:{ all -> 0x0044 }
            r4 = 7
            java.lang.String r5 = r12.zzaHF     // Catch:{ all -> 0x0044 }
            java.lang.String r7 = r12.zzaHH     // Catch:{ all -> 0x0044 }
            int r8 = r12.zzbEB     // Catch:{ all -> 0x0044 }
            android.os.WorkSource r0 = r12.zzbjq     // Catch:{ all -> 0x0044 }
            java.util.List r9 = com.google.android.gms.common.util.zzz.zzb(r0)     // Catch:{ all -> 0x0044 }
            r10 = r14
            r1.zza(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0044 }
            int r0 = r12.zzbEF     // Catch:{ all -> 0x0044 }
            int r0 = r0 + 1
            r12.zzbEF = r0     // Catch:{ all -> 0x0044 }
        L_0x0042:
            monitor-exit(r12)     // Catch:{ all -> 0x0044 }
            return
        L_0x0044:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0044 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbay.zzo(java.lang.String, long):void");
    }

    public void acquire(long j) {
        zzt.zzzg();
        zzo((String) null, j);
        this.zzbEA.acquire(j);
    }

    public boolean isHeld() {
        return this.zzbEA.isHeld();
    }

    public void release() {
        zzgM((String) null);
        this.zzbEA.release();
    }

    public void setReferenceCounted(boolean z) {
        this.zzbEA.setReferenceCounted(z);
        this.zzbED = z;
    }

    public void zzc(WorkSource workSource) {
        if (workSource != null && zzz.zzbf(this.mContext)) {
            if (this.zzbjq != null) {
                this.zzbjq.add(workSource);
            } else {
                this.zzbjq = workSource;
            }
            zzd(this.zzbjq);
        }
    }
}
