package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzbxt {
    protected volatile int zzcuJ = -1;

    public static final <T extends zzbxt> T zza(T t, byte[] bArr) throws zzbxs {
        return zzb(t, bArr, 0, bArr.length);
    }

    public static final void zza(zzbxt zzbxt, byte[] bArr, int i, int i2) {
        try {
            zzbxm zzc = zzbxm.zzc(bArr, i, i2);
            zzbxt.zza(zzc);
            zzc.zzaeF();
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public static final <T extends zzbxt> T zzb(T t, byte[] bArr, int i, int i2) throws zzbxs {
        try {
            zzbxl zzb = zzbxl.zzb(bArr, i, i2);
            t.zzb(zzb);
            zzb.zzqW(0);
            return t;
        } catch (zzbxs e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }

    public static final byte[] zzf(zzbxt zzbxt) {
        byte[] bArr = new byte[zzbxt.zzaeS()];
        zza(zzbxt, bArr, 0, bArr.length);
        return bArr;
    }

    public String toString() {
        return zzbxu.zzg(this);
    }

    public void zza(zzbxm zzbxm) throws IOException {
    }

    /* renamed from: zzaeH */
    public zzbxt clone() throws CloneNotSupportedException {
        return (zzbxt) super.clone();
    }

    public int zzaeR() {
        if (this.zzcuJ < 0) {
            zzaeS();
        }
        return this.zzcuJ;
    }

    public int zzaeS() {
        int zzu = zzu();
        this.zzcuJ = zzu;
        return zzu;
    }

    public abstract zzbxt zzb(zzbxl zzbxl) throws IOException;

    /* access modifiers changed from: protected */
    public int zzu() {
        return 0;
    }
}
