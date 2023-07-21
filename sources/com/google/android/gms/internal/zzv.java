package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class zzv {
    protected static final Comparator<byte[]> zzav = new Comparator<byte[]>() {
        /* renamed from: zza */
        public int compare(byte[] bArr, byte[] bArr2) {
            return bArr.length - bArr2.length;
        }
    };
    private List<byte[]> zzar = new LinkedList();
    private List<byte[]> zzas = new ArrayList(64);
    private int zzat = 0;
    private final int zzau;

    public zzv(int i) {
        this.zzau = i;
    }

    private synchronized void zzt() {
        while (this.zzat > this.zzau) {
            byte[] remove = this.zzar.remove(0);
            this.zzas.remove(remove);
            this.zzat -= remove.length;
        }
    }

    public synchronized void zza(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.zzau) {
                this.zzar.add(bArr);
                int binarySearch = Collections.binarySearch(this.zzas, bArr, zzav);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.zzas.add(binarySearch, bArr);
                this.zzat += bArr.length;
                zzt();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = new byte[r5];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized byte[] zzb(int r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            r1 = r0
        L_0x0003:
            java.util.List<byte[]> r0 = r4.zzas     // Catch:{ all -> 0x002f }
            int r0 = r0.size()     // Catch:{ all -> 0x002f }
            if (r1 >= r0) goto L_0x002c
            java.util.List<byte[]> r0 = r4.zzas     // Catch:{ all -> 0x002f }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x002f }
            byte[] r0 = (byte[]) r0     // Catch:{ all -> 0x002f }
            int r2 = r0.length     // Catch:{ all -> 0x002f }
            if (r2 < r5) goto L_0x0028
            int r2 = r4.zzat     // Catch:{ all -> 0x002f }
            int r3 = r0.length     // Catch:{ all -> 0x002f }
            int r2 = r2 - r3
            r4.zzat = r2     // Catch:{ all -> 0x002f }
            java.util.List<byte[]> r2 = r4.zzas     // Catch:{ all -> 0x002f }
            r2.remove(r1)     // Catch:{ all -> 0x002f }
            java.util.List<byte[]> r1 = r4.zzar     // Catch:{ all -> 0x002f }
            r1.remove(r0)     // Catch:{ all -> 0x002f }
        L_0x0026:
            monitor-exit(r4)
            return r0
        L_0x0028:
            int r0 = r1 + 1
            r1 = r0
            goto L_0x0003
        L_0x002c:
            byte[] r0 = new byte[r5]     // Catch:{ all -> 0x002f }
            goto L_0x0026
        L_0x002f:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzv.zzb(int):byte[]");
    }
}
