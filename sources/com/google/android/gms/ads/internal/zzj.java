package com.google.android.gms.ads.internal;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.zzar;
import com.google.android.gms.internal.zzav;
import com.google.android.gms.internal.zzel;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpn;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@zzme
class zzj implements zzar, Runnable {
    private zzx zzss;
    private final List<Object[]> zzth = new Vector();
    private final AtomicReference<zzar> zzti = new AtomicReference<>();
    CountDownLatch zztj = new CountDownLatch(1);

    public zzj(zzx zzx) {
        this.zzss = zzx;
        if (zzel.zzeT().zzlj()) {
            zzpn.zza((Runnable) this);
        } else {
            run();
        }
    }

    private void zzch() {
        if (!this.zzth.isEmpty()) {
            for (Object[] next : this.zzth) {
                if (next.length == 1) {
                    this.zzti.get().zza((MotionEvent) next[0]);
                } else if (next.length == 3) {
                    this.zzti.get().zza(((Integer) next[0]).intValue(), ((Integer) next[1]).intValue(), ((Integer) next[2]).intValue());
                }
            }
            this.zzth.clear();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000f, code lost:
        r0 = r2.getApplicationContext();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.content.Context zzg(android.content.Context r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r0 = com.google.android.gms.internal.zzgd.zzBs
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x000f
        L_0x000e:
            return r2
        L_0x000f:
            android.content.Context r0 = r2.getApplicationContext()
            if (r0 == 0) goto L_0x000e
            r2 = r0
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzj.zzg(android.content.Context):android.content.Context");
    }

    public void run() {
        try {
            zza(zzd(this.zzss.zzvn.zzba, zzg(this.zzss.zzqn), !zzgd.zzCU.get().booleanValue() && (this.zzss.zzvn.zzYY || !zzgd.zzBO.get().booleanValue())));
        } finally {
            this.zztj.countDown();
            this.zzss = null;
        }
    }

    public String zza(Context context, String str, View view) {
        zzar zzar;
        if (!zzcg() || (zzar = this.zzti.get()) == null) {
            return "";
        }
        zzch();
        return zzar.zza(zzg(context), str, view);
    }

    public String zza(Context context, byte[] bArr) {
        zzar zzar;
        if (!zzcg() || (zzar = this.zzti.get()) == null) {
            return "";
        }
        zzch();
        return zzar.zzb(zzg(context));
    }

    public void zza(int i, int i2, int i3) {
        zzar zzar = this.zzti.get();
        if (zzar != null) {
            zzch();
            zzar.zza(i, i2, i3);
            return;
        }
        this.zzth.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public void zza(MotionEvent motionEvent) {
        zzar zzar = this.zzti.get();
        if (zzar != null) {
            zzch();
            zzar.zza(motionEvent);
            return;
        }
        this.zzth.add(new Object[]{motionEvent});
    }

    /* access modifiers changed from: protected */
    public void zza(zzar zzar) {
        this.zzti.set(zzar);
    }

    public String zzb(Context context) {
        return zza(context, (byte[]) null);
    }

    /* access modifiers changed from: protected */
    public boolean zzcg() {
        try {
            this.zztj.await();
            return true;
        } catch (InterruptedException e) {
            zzpk.zzc("Interrupted during GADSignals creation.", e);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public zzar zzd(String str, Context context, boolean z) {
        return zzav.zza(str, context, z);
    }
}
