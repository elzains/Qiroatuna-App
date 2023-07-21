package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzacs;

public class zzacu implements Parcelable.Creator<zzacs.zza> {
    static void zza(zzacs.zza zza, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zza.getVersionCode());
        zzc.zzc(parcel, 2, zza.zzys());
        zzc.zza(parcel, 3, zza.zzyt());
        zzc.zzc(parcel, 4, zza.zzyu());
        zzc.zza(parcel, 5, zza.zzyv());
        zzc.zza(parcel, 6, zza.zzyw(), false);
        zzc.zzc(parcel, 7, zza.zzyx());
        zzc.zza(parcel, 8, zza.zzyz(), false);
        zzc.zza(parcel, 9, (Parcelable) zza.zzyB(), i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzbe */
    public zzacs.zza createFromParcel(Parcel parcel) {
        zzacn zzacn = null;
        int i = 0;
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        String str2 = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i4 = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 3:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 4:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 5:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 6:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 7:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 8:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 9:
                    zzacn = (zzacn) zzb.zza(parcel, zzaX, zzacn.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzacs.zza(i4, i3, z2, i2, z, str2, i, str, zzacn);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzdi */
    public zzacs.zza[] newArray(int i) {
        return new zzacs.zza[i];
    }
}
