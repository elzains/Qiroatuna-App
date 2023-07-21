package com.google.android.gms.internal;

import com.google.android.gms.internal.zzex;
import java.util.Random;

@zzme
public class zzem extends zzex.zza {
    private Object zzrJ = new Object();
    private final Random zzzW = new Random();
    private long zzzX;

    public zzem() {
        zzeV();
    }

    public long getValue() {
        return this.zzzX;
    }

    public void zzeV() {
        synchronized (this.zzrJ) {
            int i = 3;
            long j = 0;
            while (true) {
                i--;
                if (i <= 0) {
                    break;
                }
                j = ((long) this.zzzW.nextInt()) + 2147483648L;
                if (j != this.zzzX && j != 0) {
                    break;
                }
            }
            this.zzzX = j;
        }
    }
}
