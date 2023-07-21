package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzob implements Parcelable.Creator<zzoa> {
    static void zza(zzoa zzoa, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) zzoa.zzRy, i, false);
        zzc.zza(parcel, 3, zzoa.zzvl, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzac */
    public zzoa[] newArray(int i) {
        return new zzoa[i];
    }

    /* renamed from: zzt */
    public zzoa createFromParcel(Parcel parcel) {
        String zzq;
        zzec zzec;
        String str = null;
        int zzaY = zzb.zzaY(parcel);
        zzec zzec2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    String str2 = str;
                    zzec = (zzec) zzb.zza(parcel, zzaX, zzec.CREATOR);
                    zzq = str2;
                    break;
                case 3:
                    zzq = zzb.zzq(parcel, zzaX);
                    zzec = zzec2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzq = str;
                    zzec = zzec2;
                    break;
            }
            zzec2 = zzec;
            str = zzq;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzoa(zzec2, str);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }
}
