package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzag;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class zzav extends zzau {
    private static final String TAG = zzav.class.getSimpleName();

    protected zzav(Context context, String str, boolean z) {
        super(context, str, z);
    }

    public static zzav zza(String str, Context context, boolean z) {
        zza(context, z);
        return new zzav(context, str, z);
    }

    /* access modifiers changed from: protected */
    public List<Callable<Void>> zzb(zzbd zzbd, zzag.zza zza, zzae.zza zza2) {
        if (zzbd.zzaI() == null || !this.zzpU) {
            return super.zzb(zzbd, zza, zza2);
        }
        int zzQ = zzbd.zzQ();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(super.zzb(zzbd, zza, zza2));
        arrayList.add(new zzbo(zzbd, zzaz.zzai(), zzaz.zzaj(), zza, zzQ, 24));
        return arrayList;
    }
}
