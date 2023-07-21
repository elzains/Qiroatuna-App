package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzai implements Parcelable.Creator<zzah> {
    static void zza(zzah zzah, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzah.zzaiI);
        zzc.zzc(parcel, 2, zzah.zzyk());
        zzc.zzc(parcel, 3, zzah.zzyl());
        zzc.zza(parcel, 4, (T[]) zzah.zzym(), i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaV */
    public zzah createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaY = zzb.zzaY(parcel);
        Scope[] scopeArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 3:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 4:
                    scopeArr = (Scope[]) zzb.zzb(parcel, zzaX, Scope.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzah(i3, i2, i, scopeArr);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzda */
    public zzah[] newArray(int i) {
        return new zzah[i];
    }
}
