package com.google.android.gms.internal;

import android.os.Build;
import android.os.ConditionVariable;
import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzzk;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;

public class zzaq {
    /* access modifiers changed from: private */
    public static final ConditionVariable zzpA = new ConditionVariable();
    protected static volatile zzzk zzpB = null;
    private static volatile Random zzpD = null;
    protected volatile Boolean zzpC;
    /* access modifiers changed from: private */
    public zzbd zzpz;

    public zzaq(zzbd zzbd) {
        this.zzpz = zzbd;
        zza((Executor) zzbd.zzaI());
    }

    private static Random zzR() {
        if (zzpD == null) {
            synchronized (zzaq.class) {
                if (zzpD == null) {
                    zzpD = new Random();
                }
            }
        }
        return zzpD;
    }

    private void zza(Executor executor) {
        executor.execute(new Runnable() {
            public void run() {
                if (zzaq.this.zzpC == null) {
                    synchronized (zzaq.zzpA) {
                        if (zzaq.this.zzpC == null) {
                            boolean booleanValue = zzgd.zzDN.get().booleanValue();
                            if (booleanValue) {
                                try {
                                    zzaq.zzpB = new zzzk(zzaq.this.zzpz.getContext(), "ADSHIELD", (String) null);
                                } catch (Throwable th) {
                                    booleanValue = false;
                                }
                            }
                            zzaq.this.zzpC = Boolean.valueOf(booleanValue);
                            zzaq.zzpA.open();
                        }
                    }
                }
            }
        });
    }

    public int zzQ() {
        try {
            return Build.VERSION.SDK_INT >= 21 ? ThreadLocalRandom.current().nextInt() : zzR().nextInt();
        } catch (RuntimeException e) {
            return zzR().nextInt();
        }
    }

    public void zza(int i, int i2, long j) throws IOException {
        try {
            zzpA.block();
            if (this.zzpC.booleanValue() && zzpB != null && this.zzpz.zzaO()) {
                zzaf.zza zza = new zzaf.zza();
                zza.zzaS = this.zzpz.getContext().getPackageName();
                zza.zzaT = Long.valueOf(j);
                zzzk.zza zzm = zzpB.zzm(zzbxt.zzf(zza));
                zzm.zzcr(i2);
                zzm.zzcq(i);
                zzm.zze(this.zzpz.zzaM());
            }
        } catch (Exception e) {
        }
    }
}
