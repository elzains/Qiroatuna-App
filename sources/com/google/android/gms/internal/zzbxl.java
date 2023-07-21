package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbxl {
    private final byte[] buffer;
    private int zzcuq;
    private int zzcur;
    private int zzcus;
    private int zzcut;
    private int zzcuu;
    private int zzcuv = Integer.MAX_VALUE;
    private int zzcuw;
    private int zzcux = 64;
    private int zzcuy = 67108864;

    private zzbxl(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzcuq = i;
        this.zzcur = i + i2;
        this.zzcut = i;
    }

    public static long zzaZ(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    private void zzaeA() {
        this.zzcur += this.zzcus;
        int i = this.zzcur;
        if (i > this.zzcuv) {
            this.zzcus = i - this.zzcuv;
            this.zzcur -= this.zzcus;
            return;
        }
        this.zzcus = 0;
    }

    public static zzbxl zzaf(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static zzbxl zzb(byte[] bArr, int i, int i2) {
        return new zzbxl(bArr, i, i2);
    }

    public static int zzqY(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public int getPosition() {
        return this.zzcut - this.zzcuq;
    }

    public byte[] readBytes() throws IOException {
        int zzaew = zzaew();
        if (zzaew < 0) {
            throw zzbxs.zzaeL();
        } else if (zzaew == 0) {
            return zzbxw.zzcuV;
        } else {
            if (zzaew > this.zzcur - this.zzcut) {
                throw zzbxs.zzaeK();
            }
            byte[] bArr = new byte[zzaew];
            System.arraycopy(this.buffer, this.zzcut, bArr, 0, zzaew);
            this.zzcut = zzaew + this.zzcut;
            return bArr;
        }
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(zzaez());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(zzaey());
    }

    public String readString() throws IOException {
        int zzaew = zzaew();
        if (zzaew < 0) {
            throw zzbxs.zzaeL();
        } else if (zzaew > this.zzcur - this.zzcut) {
            throw zzbxs.zzaeK();
        } else {
            String str = new String(this.buffer, this.zzcut, zzaew, zzbxr.UTF_8);
            this.zzcut = zzaew + this.zzcut;
            return str;
        }
    }

    public byte[] zzI(int i, int i2) {
        if (i2 == 0) {
            return zzbxw.zzcuV;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzcuq + i, bArr, 0, i2);
        return bArr;
    }

    public void zza(zzbxt zzbxt) throws IOException {
        int zzaew = zzaew();
        if (this.zzcuw >= this.zzcux) {
            throw zzbxs.zzaeQ();
        }
        int zzqZ = zzqZ(zzaew);
        this.zzcuw++;
        zzbxt.zzb(this);
        zzqW(0);
        this.zzcuw--;
        zzra(zzqZ);
    }

    public void zza(zzbxt zzbxt, int i) throws IOException {
        if (this.zzcuw >= this.zzcux) {
            throw zzbxs.zzaeQ();
        }
        this.zzcuw++;
        zzbxt.zzb(this);
        zzqW(zzbxw.zzO(i, 4));
        this.zzcuw--;
    }

    public int zzaeB() {
        if (this.zzcuv == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzcuv - this.zzcut;
    }

    public boolean zzaeC() {
        return this.zzcut == this.zzcur;
    }

    public byte zzaeD() throws IOException {
        if (this.zzcut == this.zzcur) {
            throw zzbxs.zzaeK();
        }
        byte[] bArr = this.buffer;
        int i = this.zzcut;
        this.zzcut = i + 1;
        return bArr[i];
    }

    public int zzaen() throws IOException {
        if (zzaeC()) {
            this.zzcuu = 0;
            return 0;
        }
        this.zzcuu = zzaew();
        if (this.zzcuu != 0) {
            return this.zzcuu;
        }
        throw zzbxs.zzaeN();
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void zzaeo() throws java.io.IOException {
        /*
            r1 = this;
        L_0x0000:
            int r0 = r1.zzaen()
            if (r0 == 0) goto L_0x000c
            boolean r0 = r1.zzqX(r0)
            if (r0 != 0) goto L_0x0000
        L_0x000c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbxl.zzaeo():void");
    }

    public long zzaep() throws IOException {
        return zzaex();
    }

    public long zzaeq() throws IOException {
        return zzaex();
    }

    public int zzaer() throws IOException {
        return zzaew();
    }

    public long zzaes() throws IOException {
        return zzaez();
    }

    public boolean zzaet() throws IOException {
        return zzaew() != 0;
    }

    public int zzaeu() throws IOException {
        return zzqY(zzaew());
    }

    public long zzaev() throws IOException {
        return zzaZ(zzaex());
    }

    public int zzaew() throws IOException {
        byte zzaeD = zzaeD();
        if (zzaeD >= 0) {
            return zzaeD;
        }
        byte b = zzaeD & Byte.MAX_VALUE;
        byte zzaeD2 = zzaeD();
        if (zzaeD2 >= 0) {
            return b | (zzaeD2 << 7);
        }
        byte b2 = b | ((zzaeD2 & Byte.MAX_VALUE) << 7);
        byte zzaeD3 = zzaeD();
        if (zzaeD3 >= 0) {
            return b2 | (zzaeD3 << 14);
        }
        byte b3 = b2 | ((zzaeD3 & Byte.MAX_VALUE) << 14);
        byte zzaeD4 = zzaeD();
        if (zzaeD4 >= 0) {
            return b3 | (zzaeD4 << 21);
        }
        byte b4 = b3 | ((zzaeD4 & Byte.MAX_VALUE) << 21);
        byte zzaeD5 = zzaeD();
        byte b5 = b4 | (zzaeD5 << 28);
        if (zzaeD5 >= 0) {
            return b5;
        }
        for (int i = 0; i < 5; i++) {
            if (zzaeD() >= 0) {
                return b5;
            }
        }
        throw zzbxs.zzaeM();
    }

    public long zzaex() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzaeD = zzaeD();
            j |= ((long) (zzaeD & Byte.MAX_VALUE)) << i;
            if ((zzaeD & 128) == 0) {
                return j;
            }
        }
        throw zzbxs.zzaeM();
    }

    public int zzaey() throws IOException {
        return (zzaeD() & 255) | ((zzaeD() & 255) << 8) | ((zzaeD() & 255) << 16) | ((zzaeD() & 255) << 24);
    }

    public long zzaez() throws IOException {
        byte zzaeD = zzaeD();
        byte zzaeD2 = zzaeD();
        return ((((long) zzaeD2) & 255) << 8) | (((long) zzaeD) & 255) | ((((long) zzaeD()) & 255) << 16) | ((((long) zzaeD()) & 255) << 24) | ((((long) zzaeD()) & 255) << 32) | ((((long) zzaeD()) & 255) << 40) | ((((long) zzaeD()) & 255) << 48) | ((((long) zzaeD()) & 255) << 56);
    }

    public void zzqW(int i) throws zzbxs {
        if (this.zzcuu != i) {
            throw zzbxs.zzaeO();
        }
    }

    public boolean zzqX(int i) throws IOException {
        switch (zzbxw.zzrq(i)) {
            case 0:
                zzaer();
                return true;
            case 1:
                zzaez();
                return true;
            case 2:
                zzrc(zzaew());
                return true;
            case 3:
                zzaeo();
                zzqW(zzbxw.zzO(zzbxw.zzrr(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                zzaey();
                return true;
            default:
                throw zzbxs.zzaeP();
        }
    }

    public int zzqZ(int i) throws zzbxs {
        if (i < 0) {
            throw zzbxs.zzaeL();
        }
        int i2 = this.zzcut + i;
        int i3 = this.zzcuv;
        if (i2 > i3) {
            throw zzbxs.zzaeK();
        }
        this.zzcuv = i2;
        zzaeA();
        return i3;
    }

    public void zzra(int i) {
        this.zzcuv = i;
        zzaeA();
    }

    public void zzrb(int i) {
        if (i > this.zzcut - this.zzcuq) {
            throw new IllegalArgumentException(new StringBuilder(50).append("Position ").append(i).append(" is beyond current ").append(this.zzcut - this.zzcuq).toString());
        } else if (i < 0) {
            throw new IllegalArgumentException(new StringBuilder(24).append("Bad position ").append(i).toString());
        } else {
            this.zzcut = this.zzcuq + i;
        }
    }

    public void zzrc(int i) throws IOException {
        if (i < 0) {
            throw zzbxs.zzaeL();
        } else if (this.zzcut + i > this.zzcuv) {
            zzrc(this.zzcuv - this.zzcut);
            throw zzbxs.zzaeK();
        } else if (i <= this.zzcur - this.zzcut) {
            this.zzcut += i;
        } else {
            throw zzbxs.zzaeK();
        }
    }
}
