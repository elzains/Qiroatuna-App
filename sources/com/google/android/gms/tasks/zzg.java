package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.Queue;

class zzg<TResult> {
    private Queue<zzf<TResult>> zzbNE;
    private boolean zzbNF;
    private final Object zzrJ = new Object();

    zzg() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        r1 = r2.zzrJ;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0013, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0 = r2.zzbNE.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        if (r0 != null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001e, code lost:
        r2.zzbNF = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0021, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x002a, code lost:
        r0.onComplete(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(@android.support.annotation.NonNull com.google.android.gms.tasks.Task<TResult> r3) {
        /*
            r2 = this;
            java.lang.Object r1 = r2.zzrJ
            monitor-enter(r1)
            java.util.Queue<com.google.android.gms.tasks.zzf<TResult>> r0 = r2.zzbNE     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x000b
            boolean r0 = r2.zzbNF     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x000d
        L_0x000b:
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
        L_0x000c:
            return
        L_0x000d:
            r0 = 1
            r2.zzbNF = r0     // Catch:{ all -> 0x0026 }
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
        L_0x0011:
            java.lang.Object r1 = r2.zzrJ
            monitor-enter(r1)
            java.util.Queue<com.google.android.gms.tasks.zzf<TResult>> r0 = r2.zzbNE     // Catch:{ all -> 0x0023 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0023 }
            com.google.android.gms.tasks.zzf r0 = (com.google.android.gms.tasks.zzf) r0     // Catch:{ all -> 0x0023 }
            if (r0 != 0) goto L_0x0029
            r0 = 0
            r2.zzbNF = r0     // Catch:{ all -> 0x0023 }
            monitor-exit(r1)     // Catch:{ all -> 0x0023 }
            goto L_0x000c
        L_0x0023:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0023 }
            throw r0
        L_0x0026:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            throw r0
        L_0x0029:
            monitor-exit(r1)     // Catch:{ all -> 0x0023 }
            r0.onComplete(r3)
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tasks.zzg.zza(com.google.android.gms.tasks.Task):void");
    }

    public void zza(@NonNull zzf<TResult> zzf) {
        synchronized (this.zzrJ) {
            if (this.zzbNE == null) {
                this.zzbNE = new ArrayDeque();
            }
            this.zzbNE.add(zzf);
        }
    }
}
