package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzaad;
import com.google.android.gms.internal.zzaci;

public final class zzach implements zzacg {

    private static class zza extends zzace {
        private final zzaad.zzb<Status> zzaGN;

        public zza(zzaad.zzb<Status> zzb) {
            this.zzaGN = zzb;
        }

        public void zzdd(int i) throws RemoteException {
            this.zzaGN.setResult(new Status(i));
        }
    }

    public PendingResult<Status> zzg(GoogleApiClient googleApiClient) {
        return googleApiClient.zzb(new zzaci.zza(this, googleApiClient) {
            /* access modifiers changed from: protected */
            public void zza(zzacj zzacj) throws RemoteException {
                ((zzacl) zzacj.zzxD()).zza(new zza(this));
            }
        });
    }
}
