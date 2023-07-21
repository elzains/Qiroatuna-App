package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzbxv {
    final int tag;
    final byte[] zzbyd;

    zzbxv(int i, byte[] bArr) {
        this.tag = i;
        this.zzbyd = bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbxv)) {
            return false;
        }
        zzbxv zzbxv = (zzbxv) obj;
        return this.tag == zzbxv.tag && Arrays.equals(this.zzbyd, zzbxv.zzbyd);
    }

    public int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzbyd);
    }

    /* access modifiers changed from: package-private */
    public void zza(zzbxm zzbxm) throws IOException {
        zzbxm.zzrj(this.tag);
        zzbxm.zzaj(this.zzbyd);
    }

    /* access modifiers changed from: package-private */
    public int zzu() {
        return zzbxm.zzrk(this.tag) + 0 + this.zzbyd.length;
    }
}
