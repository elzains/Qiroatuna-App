package com.google.android.gms.internal;

public class zzbwl {
    private final byte[] zzcsH = new byte[256];
    private int zzcsI;
    private int zzcsJ;

    public zzbwl(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.zzcsH[i] = (byte) i;
        }
        byte b = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            b = (b + this.zzcsH[i2] + bArr[i2 % bArr.length]) & 255;
            byte b2 = this.zzcsH[i2];
            this.zzcsH[i2] = this.zzcsH[b];
            this.zzcsH[b] = b2;
        }
        this.zzcsI = 0;
        this.zzcsJ = 0;
    }

    public void zzaa(byte[] bArr) {
        int i = this.zzcsI;
        int i2 = this.zzcsJ;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = (i2 + this.zzcsH[i]) & 255;
            byte b = this.zzcsH[i];
            this.zzcsH[i] = this.zzcsH[i2];
            this.zzcsH[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.zzcsH[(this.zzcsH[i] + this.zzcsH[i2]) & 255]);
        }
        this.zzcsI = i;
        this.zzcsJ = i2;
    }
}
