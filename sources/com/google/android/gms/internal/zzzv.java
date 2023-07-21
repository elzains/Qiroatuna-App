package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzzv implements Parcelable.Creator<zzzu> {
    static void zza(zzzu zzzu, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzzu.packageName, false);
        zzc.zzc(parcel, 3, zzzu.zzaxZ);
        zzc.zzc(parcel, 4, zzzu.zzaya);
        zzc.zza(parcel, 5, zzzu.zzayb, false);
        zzc.zza(parcel, 6, zzzu.zzayc, false);
        zzc.zza(parcel, 7, zzzu.zzayd);
        zzc.zza(parcel, 8, zzzu.zzaye, false);
        zzc.zza(parcel, 9, zzzu.zzayf);
        zzc.zzc(parcel, 10, zzzu.zzayg);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaI */
    public zzzu createFromParcel(Parcel parcel) {
        String str = null;
        int i = 0;
        int zzaY = zzb.zzaY(parcel);
        boolean z = true;
        boolean z2 = false;
        String str2 = null;
        String str3 = null;
        int i2 = 0;
        int i3 = 0;
        String str4 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str4 = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 5:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 6:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 7:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 8:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 9:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 10:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzzu(str4, i3, i2, str3, str2, z, str, z2, i);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzct */
    public zzzu[] newArray(int i) {
        return new zzzu[i];
    }
}
