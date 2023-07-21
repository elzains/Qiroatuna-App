package com.google.android.gms.internal;

import com.google.android.gms.internal.zzha;
import com.google.android.gms.internal.zzlq;
import com.google.android.gms.internal.zzpb;
import java.util.List;

@zzme
public class zzls extends zzpj {
    /* access modifiers changed from: private */
    public final zzlq.zza zzPQ;
    private final zzpb.zza zzPR;
    private final zzmn zzPS = this.zzPR.zzWm;

    public zzls(zzpb.zza zza, zzlq.zza zza2) {
        this.zzPR = zza;
        this.zzPQ = zza2;
    }

    private zzpb zzS(int i) {
        return new zzpb(this.zzPR.zzTi.zzRy, (zzqw) null, (List<String>) null, i, (List<String>) null, (List<String>) null, this.zzPS.orientation, this.zzPS.zzKL, this.zzPR.zzTi.zzRB, false, (zzjq) null, (zzkb) null, (String) null, (zzjr) null, (zzjt) null, this.zzPS.zzSo, this.zzPR.zzvr, this.zzPS.zzSm, this.zzPR.zzWg, this.zzPS.zzSr, this.zzPS.zzSs, this.zzPR.zzWa, (zzha.zza) null, (zzoo) null, (List<String>) null, (List<String>) null, this.zzPR.zzWm.zzSF, this.zzPR.zzWm.zzSG, (String) null, (List<String>) null, (String) null);
    }

    public void onStop() {
    }

    public void zzco() {
        final zzpb zzS = zzS(0);
        zzpo.zzXC.post(new Runnable() {
            public void run() {
                zzls.this.zzPQ.zzb(zzS);
            }
        });
    }
}
