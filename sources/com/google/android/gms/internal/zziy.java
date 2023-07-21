package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzm;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzep;
import java.util.Random;

@zzme
class zziy {
    @Nullable
    zzev zzIV;
    @Nullable
    zzle zzIW;
    @Nullable
    zzgp zzIX;
    @Nullable
    zzeo zzIY;
    @Nullable
    zznw zzIZ;
    @Nullable
    zzep zztk;

    private static class zza extends zzep.zza {
        private final zzep zzJa;

        zza(zzep zzep) {
            this.zzJa = zzep;
        }

        public void onAdClosed() throws RemoteException {
            zzjc zzgC = zzjc.zzgC();
            float floatValue = zzgd.zzDm.get().floatValue();
            if (((float) (zzgC.zzgI() + zzgC.zzgH())) <= ((float) zzgC.zzgJ()) * floatValue || Float.isNaN(floatValue)) {
                int intValue = zzgd.zzDk.get().intValue();
                int intValue2 = zzgd.zzDl.get().intValue();
                if (intValue <= 0 || intValue2 < 0) {
                    zzw.zzdb().zzgv();
                } else {
                    zzpo.zzXC.postDelayed(new Runnable(this) {
                        public void run() {
                            zzw.zzdb().zzgv();
                        }
                    }, (long) (new Random().nextInt(intValue2 + 1) + intValue));
                }
            }
            this.zzJa.onAdClosed();
        }

        public void onAdFailedToLoad(int i) throws RemoteException {
            this.zzJa.onAdFailedToLoad(i);
        }

        public void onAdLeftApplication() throws RemoteException {
            this.zzJa.onAdLeftApplication();
        }

        public void onAdLoaded() throws RemoteException {
            this.zzJa.onAdLoaded();
        }

        public void onAdOpened() throws RemoteException {
            this.zzJa.onAdOpened();
        }
    }

    zziy() {
    }

    /* access modifiers changed from: package-private */
    public void zzc(zzm zzm) {
        if (this.zztk != null) {
            zzm.zza((zzep) new zza(this.zztk));
        }
        if (this.zzIV != null) {
            zzm.zza(this.zzIV);
        }
        if (this.zzIW != null) {
            zzm.zza(this.zzIW);
        }
        if (this.zzIX != null) {
            zzm.zza(this.zzIX);
        }
        if (this.zzIY != null) {
            zzm.zza(this.zzIY);
        }
        if (this.zzIZ != null) {
            zzm.zza(this.zzIZ);
        }
    }
}
