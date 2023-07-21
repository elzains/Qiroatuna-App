package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;

public class zzzu extends zza {
    public static final Parcelable.Creator<zzzu> CREATOR = new zzzv();
    public final String packageName;
    public final int zzaxZ;
    public final int zzaya;
    public final String zzayb;
    public final String zzayc;
    public final boolean zzayd;
    public final String zzaye;
    public final boolean zzayf;
    public final int zzayg;

    public zzzu(String str, int i, int i2, String str2, String str3, String str4, boolean z, int i3) {
        this.packageName = (String) zzac.zzw(str);
        this.zzaxZ = i;
        this.zzaya = i2;
        this.zzaye = str2;
        this.zzayb = str3;
        this.zzayc = str4;
        this.zzayd = !z;
        this.zzayf = z;
        this.zzayg = i3;
    }

    public zzzu(String str, int i, int i2, String str2, String str3, boolean z, String str4, boolean z2, int i3) {
        this.packageName = str;
        this.zzaxZ = i;
        this.zzaya = i2;
        this.zzayb = str2;
        this.zzayc = str3;
        this.zzayd = z;
        this.zzaye = str4;
        this.zzayf = z2;
        this.zzayg = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzu)) {
            return false;
        }
        zzzu zzzu = (zzzu) obj;
        return this.packageName.equals(zzzu.packageName) && this.zzaxZ == zzzu.zzaxZ && this.zzaya == zzzu.zzaya && zzaa.equal(this.zzaye, zzzu.zzaye) && zzaa.equal(this.zzayb, zzzu.zzayb) && zzaa.equal(this.zzayc, zzzu.zzayc) && this.zzayd == zzzu.zzayd && this.zzayf == zzzu.zzayf && this.zzayg == zzzu.zzayg;
    }

    public int hashCode() {
        return zzaa.hashCode(this.packageName, Integer.valueOf(this.zzaxZ), Integer.valueOf(this.zzaya), this.zzaye, this.zzayb, this.zzayc, Boolean.valueOf(this.zzayd), Boolean.valueOf(this.zzayf), Integer.valueOf(this.zzayg));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PlayLoggerContext[");
        sb.append("package=").append(this.packageName).append(',');
        sb.append("packageVersionCode=").append(this.zzaxZ).append(',');
        sb.append("logSource=").append(this.zzaya).append(',');
        sb.append("logSourceName=").append(this.zzaye).append(',');
        sb.append("uploadAccount=").append(this.zzayb).append(',');
        sb.append("loggingId=").append(this.zzayc).append(',');
        sb.append("logAndroidId=").append(this.zzayd).append(',');
        sb.append("isAnonymous=").append(this.zzayf).append(',');
        sb.append("qosTier=").append(this.zzayg);
        sb.append("]");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzzv.zza(this, parcel, i);
    }
}
