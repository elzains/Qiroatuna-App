package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzf;
import com.google.android.gms.internal.zzaad;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class zzaby {
    public static final Status zzaDu = new Status(8, "The connection to Google Play services was lost");
    private static final zzaaf<?>[] zzaDv = new zzaaf[0];
    private final Map<Api.zzc<?>, Api.zze> zzaBQ;
    final Set<zzaaf<?>> zzaDw = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private final zzb zzaDx = new zzb() {
        public void zzc(zzaaf<?> zzaaf) {
            zzaby.this.zzaDw.remove(zzaaf);
            if (zzaaf.zzvr() != null) {
                zzaby.zza(zzaby.this);
            }
        }
    };

    private static class zza implements IBinder.DeathRecipient, zzb {
        private final WeakReference<zzf> zzaDA;
        private final WeakReference<IBinder> zzaDB;
        private final WeakReference<zzaaf<?>> zzaDz;

        private zza(zzaaf<?> zzaaf, zzf zzf, IBinder iBinder) {
            this.zzaDA = new WeakReference<>(zzf);
            this.zzaDz = new WeakReference<>(zzaaf);
            this.zzaDB = new WeakReference<>(iBinder);
        }

        private void zzxe() {
            zzaaf zzaaf = (zzaaf) this.zzaDz.get();
            zzf zzf = (zzf) this.zzaDA.get();
            if (!(zzf == null || zzaaf == null)) {
                zzf.remove(zzaaf.zzvr().intValue());
            }
            IBinder iBinder = (IBinder) this.zzaDB.get();
            if (iBinder != null) {
                iBinder.unlinkToDeath(this, 0);
            }
        }

        public void binderDied() {
            zzxe();
        }

        public void zzc(zzaaf<?> zzaaf) {
            zzxe();
        }
    }

    interface zzb {
        void zzc(zzaaf<?> zzaaf);
    }

    public zzaby(Map<Api.zzc<?>, Api.zze> map) {
        this.zzaBQ = map;
    }

    static /* synthetic */ zzf zza(zzaby zzaby) {
        return null;
    }

    private static void zza(zzaaf<?> zzaaf, zzf zzf, IBinder iBinder) {
        if (zzaaf.isReady()) {
            zzaaf.zza((zzb) new zza(zzaaf, zzf, iBinder));
        } else if (iBinder == null || !iBinder.isBinderAlive()) {
            zzaaf.zza((zzb) null);
            zzaaf.cancel();
            zzf.remove(zzaaf.zzvr().intValue());
        } else {
            zza zza2 = new zza(zzaaf, zzf, iBinder);
            zzaaf.zza((zzb) zza2);
            try {
                iBinder.linkToDeath(zza2, 0);
            } catch (RemoteException e) {
                zzaaf.cancel();
                zzf.remove(zzaaf.zzvr().intValue());
            }
        }
    }

    public void dump(PrintWriter printWriter) {
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zzaDw.size());
    }

    public void release() {
        for (zzaaf zzaaf : (zzaaf[]) this.zzaDw.toArray(zzaDv)) {
            zzaaf.zza((zzb) null);
            if (zzaaf.zzvr() != null) {
                zzaaf.zzvH();
                zza(zzaaf, (zzf) null, this.zzaBQ.get(((zzaad.zza) zzaaf).zzvg()).zzvi());
                this.zzaDw.remove(zzaaf);
            } else if (zzaaf.zzvF()) {
                this.zzaDw.remove(zzaaf);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzaaf<? extends Result> zzaaf) {
        this.zzaDw.add(zzaaf);
        zzaaf.zza(this.zzaDx);
    }

    public void zzxd() {
        for (zzaaf zzC : (zzaaf[]) this.zzaDw.toArray(zzaDv)) {
            zzC.zzC(zzaDu);
        }
    }
}
