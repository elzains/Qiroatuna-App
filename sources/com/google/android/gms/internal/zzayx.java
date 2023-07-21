package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class zzayx extends zza implements Comparable<zzayx> {
    public static final Parcelable.Creator<zzayx> CREATOR = new zzayy();
    public final int zzbBC;
    public final zzayz[] zzbBD;
    public final String[] zzbBE;
    public final Map<String, zzayz> zzbBF = new TreeMap();

    public zzayx(int i, zzayz[] zzayzArr, String[] strArr) {
        this.zzbBC = i;
        this.zzbBD = zzayzArr;
        for (zzayz zzayz : zzayzArr) {
            this.zzbBF.put(zzayz.f18name, zzayz);
        }
        this.zzbBE = strArr;
        if (this.zzbBE != null) {
            Arrays.sort(this.zzbBE);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzayx)) {
            return false;
        }
        zzayx zzayx = (zzayx) obj;
        return this.zzbBC == zzayx.zzbBC && zzaa.equal(this.zzbBF, zzayx.zzbBF) && Arrays.equals(this.zzbBE, zzayx.zzbBE);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Configuration(");
        sb.append(this.zzbBC);
        sb.append(", ");
        sb.append("(");
        for (zzayz append : this.zzbBF.values()) {
            sb.append(append);
            sb.append(", ");
        }
        sb.append(")");
        sb.append(", ");
        sb.append("(");
        if (this.zzbBE != null) {
            for (String append2 : this.zzbBE) {
                sb.append(append2);
                sb.append(", ");
            }
        } else {
            sb.append("null");
        }
        sb.append(")");
        sb.append(")");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzayy.zza(this, parcel, i);
    }

    /* renamed from: zza */
    public int compareTo(zzayx zzayx) {
        return this.zzbBC - zzayx.zzbBC;
    }
}
