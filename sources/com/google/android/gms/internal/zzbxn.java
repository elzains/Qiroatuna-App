package com.google.android.gms.internal;

import com.google.android.gms.internal.zzbxn;
import java.io.IOException;

public abstract class zzbxn<M extends zzbxn<M>> extends zzbxt {
    protected zzbxp zzcuA;

    private void zza(int i, zzbxv zzbxv) {
        zzbxq zzbxq = null;
        if (this.zzcuA == null) {
            this.zzcuA = new zzbxp();
        } else {
            zzbxq = this.zzcuA.zzrn(i);
        }
        if (zzbxq == null) {
            zzbxq = new zzbxq();
            this.zzcuA.zza(i, zzbxq);
        }
        zzbxq.zza(zzbxv);
    }

    public final <T> T zza(zzbxo<M, T> zzbxo) {
        zzbxq zzrn;
        if (this.zzcuA == null || (zzrn = this.zzcuA.zzrn(zzbxw.zzrr(zzbxo.tag))) == null) {
            return null;
        }
        return zzrn.zzb(zzbxo);
    }

    public void zza(zzbxm zzbxm) throws IOException {
        if (this.zzcuA != null) {
            for (int i = 0; i < this.zzcuA.size(); i++) {
                this.zzcuA.zzro(i).zza(zzbxm);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzbxl zzbxl, int i) throws IOException {
        int position = zzbxl.getPosition();
        if (!zzbxl.zzqX(i)) {
            return false;
        }
        zza(zzbxw.zzrr(i), new zzbxv(i, zzbxl.zzI(position, zzbxl.getPosition() - position)));
        return true;
    }

    /* renamed from: zzaeG */
    public M clone() throws CloneNotSupportedException {
        M m = (zzbxn) super.clone();
        zzbxr.zza(this, (zzbxn) m);
        return m;
    }

    public /* synthetic */ zzbxt zzaeH() throws CloneNotSupportedException {
        return (zzbxn) clone();
    }

    /* access modifiers changed from: protected */
    public int zzu() {
        if (this.zzcuA == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.zzcuA.size(); i2++) {
            i += this.zzcuA.zzro(i2).zzu();
        }
        return i;
    }
}
