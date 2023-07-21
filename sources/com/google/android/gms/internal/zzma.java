package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.internal.zzlx;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzma implements zzlx.zza<zzgw> {
    private final boolean zzRc;
    private final boolean zzRd;

    public zzma(boolean z, boolean z2) {
        this.zzRc = z;
        this.zzRd = z2;
    }

    /* renamed from: zzc */
    public zzgw zza(zzlx zzlx, JSONObject jSONObject) throws JSONException, InterruptedException, ExecutionException {
        View view = null;
        List<zzqm<zzgu>> zza = zzlx.zza(jSONObject, "images", true, this.zzRc, this.zzRd);
        zzqm<zzgu> zza2 = zzlx.zza(jSONObject, "secondary_image", false, this.zzRc);
        zzqm<zzgs> zzd = zzlx.zzd(jSONObject);
        zzqm<zzqw> zzc = zzlx.zzc(jSONObject, "video");
        ArrayList arrayList = new ArrayList();
        for (zzqm<zzgu> zzqm : zza) {
            arrayList.add((zzgu) zzqm.get());
        }
        zzqw zzb = zzlx.zzb(zzc);
        String string = jSONObject.getString("headline");
        String string2 = jSONObject.getString("body");
        zzhf zzhf = (zzhf) zza2.get();
        String string3 = jSONObject.getString("call_to_action");
        String string4 = jSONObject.getString("advertiser");
        zzgs zzgs = (zzgs) zzd.get();
        Bundle bundle = new Bundle();
        zzrb zzlG = zzb != null ? zzb.zzlG() : null;
        if (zzb != null) {
            view = zzb.getView();
        }
        return new zzgw(string, arrayList, string2, zzhf, string3, string4, zzgs, bundle, zzlG, view);
    }
}
