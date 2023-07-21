package com.google.android.gms.internal;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzdd;

@zzme
public class zzdo {
    @Nullable
    private Context mContext;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();
    private final Runnable zzyG = new Runnable() {
        public void run() {
            zzdo.this.disconnect();
        }
    };
    /* access modifiers changed from: private */
    @Nullable
    public zzdr zzyH;
    /* access modifiers changed from: private */
    @Nullable
    public zzdv zzyI;

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect() {
        /*
            r3 = this;
            java.lang.Object r1 = r3.zzrJ
            monitor-enter(r1)
            android.content.Context r0 = r3.mContext     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x000b
            com.google.android.gms.internal.zzdr r0 = r3.zzyH     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x000d
        L_0x000b:
            monitor-exit(r1)     // Catch:{ all -> 0x0024 }
        L_0x000c:
            return
        L_0x000d:
            com.google.android.gms.internal.zzdo$3 r0 = new com.google.android.gms.internal.zzdo$3     // Catch:{ all -> 0x0024 }
            r0.<init>()     // Catch:{ all -> 0x0024 }
            com.google.android.gms.internal.zzdo$4 r2 = new com.google.android.gms.internal.zzdo$4     // Catch:{ all -> 0x0024 }
            r2.<init>()     // Catch:{ all -> 0x0024 }
            com.google.android.gms.internal.zzdr r0 = r3.zza((com.google.android.gms.common.internal.zzf.zzb) r0, (com.google.android.gms.common.internal.zzf.zzc) r2)     // Catch:{ all -> 0x0024 }
            r3.zzyH = r0     // Catch:{ all -> 0x0024 }
            com.google.android.gms.internal.zzdr r0 = r3.zzyH     // Catch:{ all -> 0x0024 }
            r0.zzxz()     // Catch:{ all -> 0x0024 }
            monitor-exit(r1)     // Catch:{ all -> 0x0024 }
            goto L_0x000c
        L_0x0024:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0024 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdo.connect():void");
    }

    /* access modifiers changed from: private */
    public void disconnect() {
        synchronized (this.zzrJ) {
            if (this.zzyH != null) {
                if (this.zzyH.isConnected() || this.zzyH.isConnecting()) {
                    this.zzyH.disconnect();
                }
                this.zzyH = null;
                this.zzyI = null;
                Binder.flushPendingCommands();
                zzw.zzdc().zzlc();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initialize(android.content.Context r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0003
        L_0x0002:
            return
        L_0x0003:
            java.lang.Object r1 = r2.zzrJ
            monitor-enter(r1)
            android.content.Context r0 = r2.mContext     // Catch:{ all -> 0x000c }
            if (r0 == 0) goto L_0x000f
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            goto L_0x0002
        L_0x000c:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            throw r0
        L_0x000f:
            android.content.Context r0 = r3.getApplicationContext()     // Catch:{ all -> 0x000c }
            r2.mContext = r0     // Catch:{ all -> 0x000c }
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r0 = com.google.android.gms.internal.zzgd.zzFf     // Catch:{ all -> 0x000c }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x000c }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x000c }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x000c }
            if (r0 == 0) goto L_0x0028
            r2.connect()     // Catch:{ all -> 0x000c }
        L_0x0026:
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            goto L_0x0002
        L_0x0028:
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r0 = com.google.android.gms.internal.zzgd.zzFe     // Catch:{ all -> 0x000c }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x000c }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x000c }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x000c }
            if (r0 == 0) goto L_0x0026
            com.google.android.gms.internal.zzdo$2 r0 = new com.google.android.gms.internal.zzdo$2     // Catch:{ all -> 0x000c }
            r0.<init>()     // Catch:{ all -> 0x000c }
            r2.zza((com.google.android.gms.internal.zzdd.zzb) r0)     // Catch:{ all -> 0x000c }
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdo.initialize(android.content.Context):void");
    }

    public zzdp zza(zzds zzds) {
        zzdp zzdp;
        synchronized (this.zzrJ) {
            if (this.zzyI == null) {
                zzdp = new zzdp();
            } else {
                try {
                    zzdp = this.zzyI.zza(zzds);
                } catch (RemoteException e) {
                    zzpk.zzb("Unable to call into cache service.", e);
                    zzdp = new zzdp();
                }
            }
        }
        return zzdp;
    }

    /* access modifiers changed from: protected */
    public zzdr zza(zzf.zzb zzb, zzf.zzc zzc) {
        return new zzdr(this.mContext, zzw.zzdc().zzlb(), zzb, zzc);
    }

    /* access modifiers changed from: protected */
    public void zza(zzdd.zzb zzb) {
        zzw.zzcP().zza(zzb);
    }

    public void zzev() {
        if (zzgd.zzFg.get().booleanValue()) {
            synchronized (this.zzrJ) {
                connect();
                zzw.zzcM();
                zzpo.zzXC.removeCallbacks(this.zzyG);
                zzw.zzcM();
                zzpo.zzXC.postDelayed(this.zzyG, zzgd.zzFh.get().longValue());
            }
        }
    }
}
