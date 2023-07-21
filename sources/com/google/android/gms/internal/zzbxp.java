package com.google.android.gms.internal;

public final class zzbxp implements Cloneable {
    private static final zzbxq zzcuC = new zzbxq();
    private int mSize;
    private boolean zzcuD;
    private int[] zzcuE;
    private zzbxq[] zzcuF;

    zzbxp() {
        this(10);
    }

    zzbxp(int i) {
        this.zzcuD = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzcuE = new int[idealIntArraySize];
        this.zzcuF = new zzbxq[idealIntArraySize];
        this.mSize = 0;
    }

    private int idealByteArraySize(int i) {
        for (int i2 = 4; i2 < 32; i2++) {
            if (i <= (1 << i2) - 12) {
                return (1 << i2) - 12;
            }
        }
        return i;
    }

    private int idealIntArraySize(int i) {
        return idealByteArraySize(i * 4) / 4;
    }

    private boolean zza(int[] iArr, int[] iArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private boolean zza(zzbxq[] zzbxqArr, zzbxq[] zzbxqArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (!zzbxqArr[i2].equals(zzbxqArr2[i2])) {
                return false;
            }
        }
        return true;
    }

    private int zzrp(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzcuE[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbxp)) {
            return false;
        }
        zzbxp zzbxp = (zzbxp) obj;
        if (size() != zzbxp.size()) {
            return false;
        }
        return zza(this.zzcuE, zzbxp.zzcuE, this.mSize) && zza(this.zzcuF, zzbxp.zzcuF, this.mSize);
    }

    public int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzcuE[i2]) * 31) + this.zzcuF[i2].hashCode();
        }
        return i;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.mSize;
    }

    /* access modifiers changed from: package-private */
    public void zza(int i, zzbxq zzbxq) {
        int zzrp = zzrp(i);
        if (zzrp >= 0) {
            this.zzcuF[zzrp] = zzbxq;
            return;
        }
        int i2 = zzrp ^ -1;
        if (i2 >= this.mSize || this.zzcuF[i2] != zzcuC) {
            if (this.mSize >= this.zzcuE.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zzbxq[] zzbxqArr = new zzbxq[idealIntArraySize];
                System.arraycopy(this.zzcuE, 0, iArr, 0, this.zzcuE.length);
                System.arraycopy(this.zzcuF, 0, zzbxqArr, 0, this.zzcuF.length);
                this.zzcuE = iArr;
                this.zzcuF = zzbxqArr;
            }
            if (this.mSize - i2 != 0) {
                System.arraycopy(this.zzcuE, i2, this.zzcuE, i2 + 1, this.mSize - i2);
                System.arraycopy(this.zzcuF, i2, this.zzcuF, i2 + 1, this.mSize - i2);
            }
            this.zzcuE[i2] = i;
            this.zzcuF[i2] = zzbxq;
            this.mSize++;
            return;
        }
        this.zzcuE[i2] = i;
        this.zzcuF[i2] = zzbxq;
    }

    /* renamed from: zzaeI */
    public final zzbxp clone() {
        int size = size();
        zzbxp zzbxp = new zzbxp(size);
        System.arraycopy(this.zzcuE, 0, zzbxp.zzcuE, 0, size);
        for (int i = 0; i < size; i++) {
            if (this.zzcuF[i] != null) {
                zzbxp.zzcuF[i] = (zzbxq) this.zzcuF[i].clone();
            }
        }
        zzbxp.mSize = size;
        return zzbxp;
    }

    /* access modifiers changed from: package-private */
    public zzbxq zzrn(int i) {
        int zzrp = zzrp(i);
        if (zzrp < 0 || this.zzcuF[zzrp] == zzcuC) {
            return null;
        }
        return this.zzcuF[zzrp];
    }

    /* access modifiers changed from: package-private */
    public zzbxq zzro(int i) {
        return this.zzcuF[i];
    }
}
