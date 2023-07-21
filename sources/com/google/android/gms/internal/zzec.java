package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import java.util.List;

@zzme
public final class zzec extends zza {
    public static final Parcelable.Creator<zzec> CREATOR = new zzee();
    public final Bundle extras;
    public final int versionCode;
    public final long zzyT;
    public final int zzyU;
    public final List<String> zzyV;
    public final boolean zzyW;
    public final int zzyX;
    public final boolean zzyY;
    public final String zzyZ;
    public final zzfp zzza;
    public final Location zzzb;
    public final String zzzc;
    public final Bundle zzzd;
    public final Bundle zzze;
    public final List<String> zzzf;
    public final String zzzg;
    public final String zzzh;
    public final boolean zzzi;

    public zzec(int i, long j, Bundle bundle, int i2, List<String> list, boolean z, int i3, boolean z2, String str, zzfp zzfp, Location location, String str2, Bundle bundle2, Bundle bundle3, List<String> list2, String str3, String str4, boolean z3) {
        this.versionCode = i;
        this.zzyT = j;
        this.extras = bundle == null ? new Bundle() : bundle;
        this.zzyU = i2;
        this.zzyV = list;
        this.zzyW = z;
        this.zzyX = i3;
        this.zzyY = z2;
        this.zzyZ = str;
        this.zzza = zzfp;
        this.zzzb = location;
        this.zzzc = str2;
        this.zzzd = bundle2 == null ? new Bundle() : bundle2;
        this.zzze = bundle3;
        this.zzzf = list2;
        this.zzzg = str3;
        this.zzzh = str4;
        this.zzzi = z3;
    }

    public static void zzi(zzec zzec) {
        zzec.zzzd.putBundle("com.google.ads.mediation.admob.AdMobAdapter", zzec.extras);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzec)) {
            return false;
        }
        zzec zzec = (zzec) obj;
        return this.versionCode == zzec.versionCode && this.zzyT == zzec.zzyT && zzaa.equal(this.extras, zzec.extras) && this.zzyU == zzec.zzyU && zzaa.equal(this.zzyV, zzec.zzyV) && this.zzyW == zzec.zzyW && this.zzyX == zzec.zzyX && this.zzyY == zzec.zzyY && zzaa.equal(this.zzyZ, zzec.zzyZ) && zzaa.equal(this.zzza, zzec.zzza) && zzaa.equal(this.zzzb, zzec.zzzb) && zzaa.equal(this.zzzc, zzec.zzzc) && zzaa.equal(this.zzzd, zzec.zzzd) && zzaa.equal(this.zzze, zzec.zzze) && zzaa.equal(this.zzzf, zzec.zzzf) && zzaa.equal(this.zzzg, zzec.zzzg) && zzaa.equal(this.zzzh, zzec.zzzh) && this.zzzi == zzec.zzzi;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.versionCode), Long.valueOf(this.zzyT), this.extras, Integer.valueOf(this.zzyU), this.zzyV, Boolean.valueOf(this.zzyW), Integer.valueOf(this.zzyX), Boolean.valueOf(this.zzyY), this.zzyZ, this.zzza, this.zzzb, this.zzzc, this.zzzd, this.zzze, this.zzzf, this.zzzg, this.zzzh, Boolean.valueOf(this.zzzi));
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzee.zza(this, parcel, i);
    }
}
