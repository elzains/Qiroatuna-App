package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

@zzme
public final class zzoz implements zzpa {
    public zzqm<AdvertisingIdClient.Info> zzG(final Context context) {
        final zzqj zzqj = new zzqj();
        if (zzel.zzeT().zzag(context)) {
            zzpn.zza((Runnable) new Runnable(this) {
                public void run() {
                    try {
                        zzqj.zzh(AdvertisingIdClient.getAdvertisingIdInfo(context));
                    } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
                        zzqj.zze(e);
                        zzqf.zzb("Exception while getting advertising Id info", e);
                    }
                }
            });
        }
        return zzqj;
    }

    public zzqm<String> zzg(zzmk zzmk) {
        return new zzqk(zzmk.zzRA);
    }
}
