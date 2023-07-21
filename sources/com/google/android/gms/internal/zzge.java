package com.google.android.gms.internal;

import android.content.Context;
import android.os.Build;
import com.google.android.gms.ads.internal.zzw;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

@zzme
public class zzge {
    private Context mContext = null;
    private boolean zzFx;
    private String zzFy;
    private Map<String, String> zzFz;
    private String zzwd = null;

    public zzge(Context context, String str) {
        this.mContext = context;
        this.zzwd = str;
        this.zzFx = zzgd.zzBZ.get().booleanValue();
        this.zzFy = zzgd.zzCa.get();
        this.zzFz = new LinkedHashMap();
        this.zzFz.put("s", "gmob_sdk");
        this.zzFz.put("v", "3");
        this.zzFz.put("os", Build.VERSION.RELEASE);
        this.zzFz.put("sdk", Build.VERSION.SDK);
        this.zzFz.put("device", zzw.zzcM().zzkN());
        this.zzFz.put("app", context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        this.zzFz.put("is_lite_sdk", zzw.zzcM().zzU(context) ? "1" : "0");
        Future<zzni> zzA = zzw.zzcV().zzA(this.mContext);
        try {
            zzA.get();
            this.zzFz.put("network_coarse", Integer.toString(zzA.get().zzUQ));
            this.zzFz.put("network_fine", Integer.toString(zzA.get().zzUR));
        } catch (Exception e) {
            zzw.zzcQ().zza((Throwable) e, "CsiConfiguration.CsiConfiguration");
        }
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: package-private */
    public String zzdA() {
        return this.zzwd;
    }

    /* access modifiers changed from: package-private */
    public boolean zzfu() {
        return this.zzFx;
    }

    /* access modifiers changed from: package-private */
    public String zzfv() {
        return this.zzFy;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zzfw() {
        return this.zzFz;
    }
}
