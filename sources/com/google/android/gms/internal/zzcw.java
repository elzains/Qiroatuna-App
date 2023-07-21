package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzqp;
import java.util.Map;
import org.json.JSONObject;

@zzme
public class zzcw implements zzcx {
    /* access modifiers changed from: private */
    public final zzct zzwG;
    private final zzid zzwI = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            if (zzcw.this.zzwG.zzb(map)) {
                zzcw.this.zzwG.zzb(zzqw, map);
            }
        }
    };
    private final zzid zzwJ = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            if (zzcw.this.zzwG.zzb(map)) {
                zzcw.this.zzwG.zza((zzcx) zzcw.this, map);
            }
        }
    };
    private final zzid zzwK = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            if (zzcw.this.zzwG.zzb(map)) {
                zzcw.this.zzwG.zzc(map);
            }
        }
    };
    private zzji.zzc zzwM;
    /* access modifiers changed from: private */
    public boolean zzwN;
    private final zzid zzwO = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            if (zzcw.this.zzwG.zzb(map)) {
                zzic.zzHS.zza(zzqw, map);
            }
        }
    };

    public zzcw(zzct zzct, zzji zzji) {
        this.zzwG = zzct;
        this.zzwM = zzji.zzgO();
        this.zzwM.zza(new zzqp.zzc<zzjj>() {
            /* renamed from: zzb */
            public void zzd(zzjj zzjj) {
                boolean unused = zzcw.this.zzwN = true;
                zzcw.this.zzc(zzjj);
            }
        }, new zzqp.zza() {
            public void run() {
                zzcw.this.zzwG.zzb((zzcx) zzcw.this);
            }
        });
        String valueOf = String.valueOf(this.zzwG.zzdR().zzdC());
        zzpk.zzbf(valueOf.length() != 0 ? "Core JS tracking ad unit: ".concat(valueOf) : new String("Core JS tracking ad unit: "));
    }

    /* access modifiers changed from: package-private */
    public void zzc(zzjj zzjj) {
        zzjj.zza("/updateActiveView", this.zzwI);
        zzjj.zza("/untrackActiveViewUnit", this.zzwJ);
        zzjj.zza("/visibilityChanged", this.zzwK);
        if (zzw.zzdl().zzjQ()) {
            zzjj.zza("/logScionEvent", this.zzwO);
        }
    }

    public void zzc(final JSONObject jSONObject, boolean z) {
        this.zzwM.zza(new zzqp.zzc<zzjj>(this) {
            /* renamed from: zzb */
            public void zzd(zzjj zzjj) {
                zzjj.zza("AFMA_updateActiveView", jSONObject);
            }
        }, new zzqp.zzb());
    }

    /* access modifiers changed from: package-private */
    public void zzd(zzjj zzjj) {
        zzjj.zzb("/visibilityChanged", this.zzwK);
        zzjj.zzb("/untrackActiveViewUnit", this.zzwJ);
        zzjj.zzb("/updateActiveView", this.zzwI);
        if (zzw.zzdl().zzjQ()) {
            zzjj.zzb("/logScionEvent", this.zzwO);
        }
    }

    public boolean zzdV() {
        return this.zzwN;
    }

    public void zzdW() {
        this.zzwM.zza(new zzqp.zzc<zzjj>() {
            /* renamed from: zzb */
            public void zzd(zzjj zzjj) {
                zzcw.this.zzd(zzjj);
            }
        }, new zzqp.zzb());
        this.zzwM.release();
    }
}
