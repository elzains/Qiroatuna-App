package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbxw {
    static final int zzcuK = zzO(1, 3);
    static final int zzcuL = zzO(1, 4);
    static final int zzcuM = zzO(2, 0);
    static final int zzcuN = zzO(3, 2);
    public static final int[] zzcuO = new int[0];
    public static final long[] zzcuP = new long[0];
    public static final float[] zzcuQ = new float[0];
    public static final double[] zzcuR = new double[0];
    public static final boolean[] zzcuS = new boolean[0];
    public static final String[] zzcuT = new String[0];
    public static final byte[][] zzcuU = new byte[0][];
    public static final byte[] zzcuV = new byte[0];

    public static int zzO(int i, int i2) {
        return (i << 3) | i2;
    }

    public static final int zzb(zzbxl zzbxl, int i) throws IOException {
        int i2 = 1;
        int position = zzbxl.getPosition();
        zzbxl.zzqX(i);
        while (zzbxl.zzaen() == i) {
            zzbxl.zzqX(i);
            i2++;
        }
        zzbxl.zzrb(position);
        return i2;
    }

    static int zzrq(int i) {
        return i & 7;
    }

    public static int zzrr(int i) {
        return i >>> 3;
    }
}
