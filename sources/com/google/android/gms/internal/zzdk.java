package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import java.util.PriorityQueue;

@zzme
public class zzdk {

    public static class zza {
        final long value;
        final String zzyu;
        final int zzyv;

        zza(long j, String str, int i) {
            this.value = j;
            this.zzyu = str;
            this.zzyv = i;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == null || !(obj instanceof zza)) {
                return false;
            }
            return ((zza) obj).value == this.value && ((zza) obj).zzyv == this.zzyv;
        }

        public int hashCode() {
            return (int) this.value;
        }
    }

    static long zza(int i, int i2, long j, long j2, long j3) {
        return ((((((j + 1073807359) - ((((((long) i) + 2147483647L) % 1073807359) * j2) % 1073807359)) % 1073807359) * j3) % 1073807359) + ((((long) i2) + 2147483647L) % 1073807359)) % 1073807359;
    }

    static long zza(long j, int i) {
        if (i == 0) {
            return 1;
        }
        return i != 1 ? i % 2 == 0 ? zza((j * j) % 1073807359, i / 2) % 1073807359 : ((zza((j * j) % 1073807359, i / 2) % 1073807359) * j) % 1073807359 : j;
    }

    static String zza(String[] strArr, int i, int i2) {
        if (strArr.length < i + i2) {
            zzpk.m20e("Unable to construct shingle");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 < (i + i2) - 1; i3++) {
            stringBuffer.append(strArr[i3]);
            stringBuffer.append(' ');
        }
        stringBuffer.append(strArr[(i + i2) - 1]);
        return stringBuffer.toString();
    }

    static void zza(int i, long j, String str, int i2, PriorityQueue<zza> priorityQueue) {
        zza zza2 = new zza(j, str, i2);
        if ((priorityQueue.size() != i || (priorityQueue.peek().zzyv <= zza2.zzyv && priorityQueue.peek().value <= zza2.value)) && !priorityQueue.contains(zza2)) {
            priorityQueue.add(zza2);
            if (priorityQueue.size() > i) {
                priorityQueue.poll();
            }
        }
    }

    public static void zza(String[] strArr, int i, int i2, PriorityQueue<zza> priorityQueue) {
        if (strArr.length < i2) {
            zza(i, zzb(strArr, 0, strArr.length), zza(strArr, 0, strArr.length), strArr.length, priorityQueue);
            return;
        }
        long zzb = zzb(strArr, 0, i2);
        zza(i, zzb, zza(strArr, 0, i2), i2, priorityQueue);
        long zza2 = zza(16785407, i2 - 1);
        int i3 = 1;
        while (i3 < (strArr.length - i2) + 1) {
            long zza3 = zza(zzdi.zzH(strArr[i3 - 1]), zzdi.zzH(strArr[(i3 + i2) - 1]), zzb, zza2, 16785407);
            zza(i, zza3, zza(strArr, i3, i2), strArr.length, priorityQueue);
            i3++;
            zzb = zza3;
        }
    }

    private static long zzb(String[] strArr, int i, int i2) {
        long zzH = (((long) zzdi.zzH(strArr[i])) + 2147483647L) % 1073807359;
        for (int i3 = i + 1; i3 < i + i2; i3++) {
            zzH = (((zzH * 16785407) % 1073807359) + ((((long) zzdi.zzH(strArr[i3])) + 2147483647L) % 1073807359)) % 1073807359;
        }
        return zzH;
    }
}
