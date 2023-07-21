package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzacs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class zzacw extends com.google.android.gms.common.internal.safeparcel.zza {
    public static final Parcelable.Creator<zzacw> CREATOR = new zzacx();
    private final HashMap<String, Map<String, zzacs.zza<?, ?>>> zzaHh;
    private final ArrayList<zza> zzaHi = null;
    private final String zzaHj;
    final int zzaiI;

    public static class zza extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final Parcelable.Creator<zza> CREATOR = new zzacy();
        final String className;
        final int versionCode;
        final ArrayList<zzb> zzaHk;

        zza(int i, String str, ArrayList<zzb> arrayList) {
            this.versionCode = i;
            this.className = str;
            this.zzaHk = arrayList;
        }

        zza(String str, Map<String, zzacs.zza<?, ?>> map) {
            this.versionCode = 1;
            this.className = str;
            this.zzaHk = zzX(map);
        }

        private static ArrayList<zzb> zzX(Map<String, zzacs.zza<?, ?>> map) {
            if (map == null) {
                return null;
            }
            ArrayList<zzb> arrayList = new ArrayList<>();
            for (String next : map.keySet()) {
                arrayList.add(new zzb(next, map.get(next)));
            }
            return arrayList;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzacy.zza(this, parcel, i);
        }

        /* access modifiers changed from: package-private */
        public HashMap<String, zzacs.zza<?, ?>> zzyG() {
            HashMap<String, zzacs.zza<?, ?>> hashMap = new HashMap<>();
            int size = this.zzaHk.size();
            for (int i = 0; i < size; i++) {
                zzb zzb = this.zzaHk.get(i);
                hashMap.put(zzb.zzaB, zzb.zzaHl);
            }
            return hashMap;
        }
    }

    public static class zzb extends com.google.android.gms.common.internal.safeparcel.zza {
        public static final Parcelable.Creator<zzb> CREATOR = new zzacv();
        final int versionCode;
        final String zzaB;
        final zzacs.zza<?, ?> zzaHl;

        zzb(int i, String str, zzacs.zza<?, ?> zza) {
            this.versionCode = i;
            this.zzaB = str;
            this.zzaHl = zza;
        }

        zzb(String str, zzacs.zza<?, ?> zza) {
            this.versionCode = 1;
            this.zzaB = str;
            this.zzaHl = zza;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzacv.zza(this, parcel, i);
        }
    }

    zzacw(int i, ArrayList<zza> arrayList, String str) {
        this.zzaiI = i;
        this.zzaHh = zzi(arrayList);
        this.zzaHj = (String) zzac.zzw(str);
        zzyD();
    }

    private static HashMap<String, Map<String, zzacs.zza<?, ?>>> zzi(ArrayList<zza> arrayList) {
        HashMap<String, Map<String, zzacs.zza<?, ?>>> hashMap = new HashMap<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            zza zza2 = arrayList.get(i);
            hashMap.put(zza2.className, zza2.zzyG());
        }
        return hashMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String next : this.zzaHh.keySet()) {
            sb.append(next).append(":\n");
            Map map = this.zzaHh.get(next);
            for (String str : map.keySet()) {
                sb.append("  ").append(str).append(": ");
                sb.append(map.get(str));
            }
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzacx.zza(this, parcel, i);
    }

    public Map<String, zzacs.zza<?, ?>> zzdw(String str) {
        return this.zzaHh.get(str);
    }

    public void zzyD() {
        for (String str : this.zzaHh.keySet()) {
            Map map = this.zzaHh.get(str);
            for (String str2 : map.keySet()) {
                ((zzacs.zza) map.get(str2)).zza(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<zza> zzyE() {
        ArrayList<zza> arrayList = new ArrayList<>();
        for (String next : this.zzaHh.keySet()) {
            arrayList.add(new zza(next, this.zzaHh.get(next)));
        }
        return arrayList;
    }

    public String zzyF() {
        return this.zzaHj;
    }
}
