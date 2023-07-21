package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzqi implements Parcelable.Creator<zzqh> {
    static void zza(zzqh zzqh, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzqh.zzba, false);
        zzc.zzc(parcel, 3, zzqh.zzYW);
        zzc.zzc(parcel, 4, zzqh.zzYX);
        zzc.zza(parcel, 5, zzqh.zzYY);
        zzc.zza(parcel, 6, zzqh.zzYZ);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzal */
    public zzqh[] newArray(int i) {
        return new zzqh[i];
    }

    /* renamed from: zzw */
    public zzqh createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        boolean z2 = false;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 4:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 5:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 6:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzqh(str, i2, i, z2, z);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }
}
