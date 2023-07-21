package com.google.android.gms.internal;

import java.util.Map;
import org.json.JSONObject;

@zzme
public class zzcv implements zzcx {
    /* access modifiers changed from: private */
    public final zzct zzwG;
    private final zzjj zzwH;
    private final zzid zzwI = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            zzcv.this.zzwG.zzb(zzqw, map);
        }
    };
    private final zzid zzwJ = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            zzcv.this.zzwG.zza((zzcx) zzcv.this, map);
        }
    };
    private final zzid zzwK = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            zzcv.this.zzwG.zzc(map);
        }
    };

    public zzcv(zzct zzct, zzjj zzjj) {
        this.zzwG = zzct;
        this.zzwH = zzjj;
        zzc(this.zzwH);
        String valueOf = String.valueOf(this.zzwG.zzdR().zzdC());
        zzpk.zzbf(valueOf.length() != 0 ? "Custom JS tracking ad unit: ".concat(valueOf) : new String("Custom JS tracking ad unit: "));
    }

    /* access modifiers changed from: package-private */
    public void zzc(zzjj zzjj) {
        zzjj.zza("/updateActiveView", this.zzwI);
        zzjj.zza("/untrackActiveViewUnit", this.zzwJ);
        zzjj.zza("/visibilityChanged", this.zzwK);
    }

    public void zzc(JSONObject jSONObject, boolean z) {
        if (!z) {
            this.zzwH.zza("AFMA_updateActiveView", jSONObject);
        } else {
            this.zzwG.zzb((zzcx) this);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzd(zzjj zzjj) {
        zzjj.zzb("/visibilityChanged", this.zzwK);
        zzjj.zzb("/untrackActiveViewUnit", this.zzwJ);
        zzjj.zzb("/updateActiveView", this.zzwI);
    }

    public boolean zzdV() {
        return true;
    }

    public void zzdW() {
        zzd(this.zzwH);
    }
}
