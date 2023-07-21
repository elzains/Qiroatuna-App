package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class zzat extends zzau {
    private static final String TAG = zzat.class.getSimpleName();
    private AdvertisingIdClient.Info zzpR;

    protected zzat(Context context) {
        super(context, "");
    }

    public static zzat zzc(Context context) {
        zza(context, true);
        return new zzat(context);
    }

    /* access modifiers changed from: protected */
    public zzag.zza zza(Context context, View view) {
        return null;
    }

    public String zza(String str, String str2) {
        return zzao.zza(str, str2, true);
    }

    public void zza(AdvertisingIdClient.Info info) {
        this.zzpR = info;
    }

    /* access modifiers changed from: protected */
    public void zza(zzbd zzbd, zzag.zza zza, zzae.zza zza2) {
        if (!zzbd.zzaN()) {
            zza(zzb(zzbd, zza, zza2));
        } else if (this.zzpR != null) {
            String id = this.zzpR.getId();
            if (!TextUtils.isEmpty(id)) {
                zza.zzbW = zzbf.zzr(id);
                zza.zzbX = 5;
                zza.zzbY = Boolean.valueOf(this.zzpR.isLimitAdTrackingEnabled());
            }
            this.zzpR = null;
        }
    }

    /* access modifiers changed from: protected */
    public List<Callable<Void>> zzb(zzbd zzbd, zzag.zza zza, zzae.zza zza2) {
        ArrayList arrayList = new ArrayList();
        if (zzbd.zzaI() == null) {
            return arrayList;
        }
        zzbd zzbd2 = zzbd;
        arrayList.add(new zzbo(zzbd2, zzaz.zzai(), zzaz.zzaj(), zza, zzbd.zzQ(), 24));
        return arrayList;
    }
}
