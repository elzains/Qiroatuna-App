package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzfu implements Parcelable.Creator<zzft> {
    static void zza(zzft zzft, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzft.zzAU);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzB */
    public zzft[] newArray(int i) {
        return new zzft[i];
    }

    /* renamed from: zzi */
    public zzft createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        boolean z = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzft(z);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }
}
