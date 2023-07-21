package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzaco implements Parcelable.Creator<zzacn> {
    static void zza(zzacn zzacn, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzacn.zzaiI);
        zzc.zza(parcel, 2, (Parcelable) zzacn.zzyo(), i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzbb */
    public zzacn createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        zzacp zzacp = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    zzacp = (zzacp) zzb.zza(parcel, zzaX, zzacp.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzacn(i, zzacp);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzdf */
    public zzacn[] newArray(int i) {
        return new zzacn[i];
    }
}
