package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzof;
import com.google.android.gms.internal.zzpb;

@zzme
public class zzoe extends zzpj implements zzog, zzoj {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final String zzKW;
    private final zzpb.zza zzPR;
    private int zzPY = 3;
    private final zzol zzVr;
    private final zzoj zzVs;
    /* access modifiers changed from: private */
    public final String zzVt;
    private final zzjq zzVu;
    private final long zzVv;
    private int zzVw = 0;
    private zzof zzVx;
    private final Object zzrJ;

    public zzoe(Context context, String str, String str2, zzjq zzjq, zzpb.zza zza, zzol zzol, zzoj zzoj, long j) {
        this.mContext = context;
        this.zzKW = str;
        this.zzVt = str2;
        this.zzVu = zzjq;
        this.zzPR = zza;
        this.zzVr = zzol;
        this.zzrJ = new Object();
        this.zzVs = zzoj;
        this.zzVv = j;
    }

    /* access modifiers changed from: private */
    public void zza(zzec zzec, zzkb zzkb) {
        this.zzVr.zzjO().zza((zzoj) this);
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzKW)) {
                zzkb.zza(zzec, this.zzVt, this.zzVu.zzKn);
            } else {
                zzkb.zzd(zzec, this.zzVt);
            }
        } catch (RemoteException e) {
            zzpk.zzc("Fail to load ad from adapter.", e);
            zza(this.zzKW, 0);
        }
    }

    private void zzk(long j) {
        while (true) {
            synchronized (this.zzrJ) {
                if (this.zzVw != 0) {
                    this.zzVx = new zzof.zza().zzl(zzw.zzcS().elapsedRealtime() - j).zzae(1 == this.zzVw ? 6 : this.zzPY).zzaP(this.zzKW).zzaQ(this.zzVu.zzKq).zzjK();
                    return;
                } else if (!zzf(j)) {
                    this.zzVx = new zzof.zza().zzae(this.zzPY).zzl(zzw.zzcS().elapsedRealtime() - j).zzaP(this.zzKW).zzaQ(this.zzVu.zzKq).zzjK();
                    return;
                }
            }
        }
    }

    public void onStop() {
    }

    public void zza(String str, int i) {
        synchronized (this.zzrJ) {
            this.zzVw = 2;
            this.zzPY = i;
            this.zzrJ.notify();
        }
    }

    public void zzaO(String str) {
        synchronized (this.zzrJ) {
            this.zzVw = 1;
            this.zzrJ.notify();
        }
    }

    public void zzad(int i) {
        zza(this.zzKW, 0);
    }

    public void zzco() {
        if (this.zzVr != null && this.zzVr.zzjO() != null && this.zzVr.zzjN() != null) {
            final zzoi zzjO = this.zzVr.zzjO();
            zzjO.zza((zzoj) null);
            zzjO.zza((zzog) this);
            final zzec zzec = this.zzPR.zzTi.zzRy;
            final zzkb zzjN = this.zzVr.zzjN();
            try {
                if (zzjN.isInitialized()) {
                    zzqe.zzYP.post(new Runnable() {
                        public void run() {
                            zzoe.this.zza(zzec, zzjN);
                        }
                    });
                } else {
                    zzqe.zzYP.post(new Runnable() {
                        public void run() {
                            try {
                                zzjN.zza(zzd.zzA(zzoe.this.mContext), zzec, (String) null, (zzom) zzjO, zzoe.this.zzVt);
                            } catch (RemoteException e) {
                                RemoteException remoteException = e;
                                String valueOf = String.valueOf(zzoe.this.zzKW);
                                zzpk.zzc(valueOf.length() != 0 ? "Fail to initialize adapter ".concat(valueOf) : new String("Fail to initialize adapter "), remoteException);
                                zzoe.this.zza(zzoe.this.zzKW, 0);
                            }
                        }
                    });
                }
            } catch (RemoteException e) {
                zzpk.zzc("Fail to check if adapter is initialized.", e);
                zza(this.zzKW, 0);
            }
            zzk(zzw.zzcS().elapsedRealtime());
            zzjO.zza((zzoj) null);
            zzjO.zza((zzog) null);
            if (this.zzVw == 1) {
                this.zzVs.zzaO(this.zzKW);
            } else {
                this.zzVs.zza(this.zzKW, this.zzPY);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzf(long j) {
        long elapsedRealtime = this.zzVv - (zzw.zzcS().elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            this.zzPY = 4;
            return false;
        }
        try {
            this.zzrJ.wait(elapsedRealtime);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            this.zzPY = 5;
            return false;
        }
    }

    public zzof zzjH() {
        zzof zzof;
        synchronized (this.zzrJ) {
            zzof = this.zzVx;
        }
        return zzof;
    }

    public zzjq zzjI() {
        return this.zzVu;
    }

    public void zzjJ() {
        zza(this.zzPR.zzTi.zzRy, this.zzVr.zzjN());
    }
}
