package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.internal.zzmk;
import com.google.android.gms.internal.zzpb;

@zzme
public class zzmf {

    public interface zza {
        void zza(zzpb.zza zza);
    }

    public zzpj zza(Context context, zzmk.zza zza2, zza zza3) {
        zzpj zzmx = zza2.zzRy.extras.getBundle("sdk_less_server_data") != null ? new zzmx(context, zza2, zza3) : new zzmg(context, zza2, zza3);
        zzmx.zziP();
        return zzmx;
    }
}
