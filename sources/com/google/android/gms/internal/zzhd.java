package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzhd implements Parcelable.Creator<zzhc> {
    static void zza(zzhc zzhc, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzhc.versionCode);
        zzc.zza(parcel, 2, zzhc.zzHa);
        zzc.zzc(parcel, 3, zzhc.zzHb);
        zzc.zza(parcel, 4, zzhc.zzHc);
        zzc.zzc(parcel, 5, zzhc.zzHd);
        zzc.zza(parcel, 6, (Parcelable) zzhc.zzHe, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzD */
    public zzhc[] newArray(int i) {
        return new zzhc[i];
    }

    /* renamed from: zzj */
    public zzhc createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaY = zzb.zzaY(parcel);
        zzft zzft = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 3:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 4:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 5:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 6:
                    zzft = (zzft) zzb.zza(parcel, zzaX, zzft.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzhc(i3, z2, i2, z, i, zzft);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }
}
