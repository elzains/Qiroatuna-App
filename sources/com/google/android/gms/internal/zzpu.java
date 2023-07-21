package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzaa;
import java.util.ArrayList;
import java.util.List;
import org.apache.cordova.globalization.Globalization;

@zzme
public class zzpu {
    private final String[] zzYb;
    private final double[] zzYc;
    private final double[] zzYd;
    private final int[] zzYe;
    private int zzYf;

    public static class zza {
        public final int count;

        /* renamed from: name  reason: collision with root package name */
        public final String f19name;
        public final double zzYg;
        public final double zzYh;
        public final double zzYi;

        public zza(String str, double d, double d2, double d3, int i) {
            this.f19name = str;
            this.zzYh = d;
            this.zzYg = d2;
            this.zzYi = d3;
            this.count = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return zzaa.equal(this.f19name, zza.f19name) && this.zzYg == zza.zzYg && this.zzYh == zza.zzYh && this.count == zza.count && Double.compare(this.zzYi, zza.zzYi) == 0;
        }

        public int hashCode() {
            return zzaa.hashCode(this.f19name, Double.valueOf(this.zzYg), Double.valueOf(this.zzYh), Double.valueOf(this.zzYi), Integer.valueOf(this.count));
        }

        public String toString() {
            return zzaa.zzv(this).zzg("name", this.f19name).zzg("minBound", Double.valueOf(this.zzYh)).zzg("maxBound", Double.valueOf(this.zzYg)).zzg(Globalization.PERCENT, Double.valueOf(this.zzYi)).zzg("count", Integer.valueOf(this.count)).toString();
        }
    }

    public static class zzb {
        /* access modifiers changed from: private */
        public final List<String> zzYj = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Double> zzYk = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Double> zzYl = new ArrayList();

        public zzb zza(String str, double d, double d2) {
            int i;
            int i2 = 0;
            while (true) {
                i = i2;
                if (i >= this.zzYj.size()) {
                    break;
                }
                double doubleValue = this.zzYl.get(i).doubleValue();
                double doubleValue2 = this.zzYk.get(i).doubleValue();
                if (d < doubleValue || (doubleValue == d && d2 < doubleValue2)) {
                    break;
                }
                i2 = i + 1;
            }
            this.zzYj.add(i, str);
            this.zzYl.add(i, Double.valueOf(d));
            this.zzYk.add(i, Double.valueOf(d2));
            return this;
        }

        public zzpu zzla() {
            return new zzpu(this);
        }
    }

    private zzpu(zzb zzb2) {
        int size = zzb2.zzYk.size();
        this.zzYb = (String[]) zzb2.zzYj.toArray(new String[size]);
        this.zzYc = zzn(zzb2.zzYk);
        this.zzYd = zzn(zzb2.zzYl);
        this.zzYe = new int[size];
        this.zzYf = 0;
    }

    private double[] zzn(List<Double> list) {
        double[] dArr = new double[list.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= dArr.length) {
                return dArr;
            }
            dArr[i2] = list.get(i2).doubleValue();
            i = i2 + 1;
        }
    }

    public List<zza> getBuckets() {
        ArrayList arrayList = new ArrayList(this.zzYb.length);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.zzYb.length) {
                return arrayList;
            }
            arrayList.add(new zza(this.zzYb[i2], this.zzYd[i2], this.zzYc[i2], ((double) this.zzYe[i2]) / ((double) this.zzYf), this.zzYe[i2]));
            i = i2 + 1;
        }
    }

    public void zza(double d) {
        this.zzYf++;
        int i = 0;
        while (i < this.zzYd.length) {
            if (this.zzYd[i] <= d && d < this.zzYc[i]) {
                int[] iArr = this.zzYe;
                iArr[i] = iArr[i] + 1;
            }
            if (d >= this.zzYd[i]) {
                i++;
            } else {
                return;
            }
        }
    }
}
