package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.internal.zzacs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class zzacp extends com.google.android.gms.common.internal.safeparcel.zza implements zzacs.zzb<String, Integer> {
    public static final Parcelable.Creator<zzacp> CREATOR = new zzacq();
    private final HashMap<String, Integer> zzaGS;
    private final SparseArray<String> zzaGT;
    private final ArrayList<zza> zzaGU;
    final int zzaiI;

    public static final class zza extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final Parcelable.Creator<zza> CREATOR = new zzacr();
        final int versionCode;
        final String zzaGV;
        final int zzaGW;

        zza(int i, String str, int i2) {
            this.versionCode = i;
            this.zzaGV = str;
            this.zzaGW = i2;
        }

        zza(String str, int i) {
            this.versionCode = 1;
            this.zzaGV = str;
            this.zzaGW = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzacr.zza(this, parcel, i);
        }
    }

    public zzacp() {
        this.zzaiI = 1;
        this.zzaGS = new HashMap<>();
        this.zzaGT = new SparseArray<>();
        this.zzaGU = null;
    }

    zzacp(int i, ArrayList<zza> arrayList) {
        this.zzaiI = i;
        this.zzaGS = new HashMap<>();
        this.zzaGT = new SparseArray<>();
        this.zzaGU = null;
        zzh(arrayList);
    }

    private void zzh(ArrayList<zza> arrayList) {
        Iterator<zza> it = arrayList.iterator();
        while (it.hasNext()) {
            zza next = it.next();
            zzj(next.zzaGV, next.zzaGW);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzacq.zza(this, parcel, i);
    }

    /* renamed from: zzd */
    public String convertBack(Integer num) {
        String str = this.zzaGT.get(num.intValue());
        return (str != null || !this.zzaGS.containsKey("gms_unknown")) ? str : "gms_unknown";
    }

    public zzacp zzj(String str, int i) {
        this.zzaGS.put(str, Integer.valueOf(i));
        this.zzaGT.put(i, str);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<zza> zzyq() {
        ArrayList<zza> arrayList = new ArrayList<>();
        for (String next : this.zzaGS.keySet()) {
            arrayList.add(new zza(next, this.zzaGS.get(next).intValue()));
        }
        return arrayList;
    }
}
