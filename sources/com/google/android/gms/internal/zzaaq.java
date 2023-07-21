package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.internal.zzaad;
import com.google.android.gms.internal.zzaav;

public class zzaaq implements zzaau {
    /* access modifiers changed from: private */
    public final zzaav zzaBk;
    private boolean zzaBl = false;

    public zzaaq(zzaav zzaav) {
        this.zzaBk = zzaav;
    }

    private <A extends Api.zzb> void zze(zzaad.zza<? extends Result, A> zza) throws DeadObjectException {
        this.zzaBk.zzaAw.zzaBW.zzb(zza);
        A zzc = this.zzaBk.zzaAw.zzc((Api.zzc<?>) zza.zzvg());
        if (zzc.isConnected() || !this.zzaBk.zzaCf.containsKey(zza.zzvg())) {
            if (zzc instanceof zzal) {
                zzc = ((zzal) zzc).zzyn();
            }
            zza.zzb(zzc);
            return;
        }
        zza.zzB(new Status(17));
    }

    public void begin() {
    }

    public void connect() {
        if (this.zzaBl) {
            this.zzaBl = false;
            this.zzaBk.zza((zzaav.zza) new zzaav.zza(this) {
                public void zzwe() {
                    zzaaq.this.zzaBk.zzaCj.zzo((Bundle) null);
                }
            });
        }
    }

    public boolean disconnect() {
        if (this.zzaBl) {
            return false;
        }
        if (this.zzaBk.zzaAw.zzwq()) {
            this.zzaBl = true;
            for (zzabx zzxb : this.zzaBk.zzaAw.zzaBV) {
                zzxb.zzxb();
            }
            return false;
        }
        this.zzaBk.zzh((ConnectionResult) null);
        return true;
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
        this.zzaBk.zzh((ConnectionResult) null);
        this.zzaBk.zzaCj.zzc(i, this.zzaBl);
    }

    public <A extends Api.zzb, R extends Result, T extends zzaad.zza<R, A>> T zza(T t) {
        return zzb(t);
    }

    public void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    public <A extends Api.zzb, T extends zzaad.zza<? extends Result, A>> T zzb(T t) {
        try {
            zze(t);
        } catch (DeadObjectException e) {
            this.zzaBk.zza((zzaav.zza) new zzaav.zza(this) {
                public void zzwe() {
                    zzaaq.this.onConnectionSuspended(1);
                }
            });
        }
        return t;
    }

    /* access modifiers changed from: package-private */
    public void zzwd() {
        if (this.zzaBl) {
            this.zzaBl = false;
            this.zzaBk.zzaAw.zzaBW.release();
            disconnect();
        }
    }
}
