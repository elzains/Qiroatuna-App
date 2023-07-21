package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzl implements Parcelable.Creator<PlaceReport> {
    static void zza(PlaceReport placeReport, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, placeReport.zzaiI);
        zzc.zza(parcel, 2, placeReport.getPlaceId(), false);
        zzc.zza(parcel, 3, placeReport.getTag(), false);
        zzc.zza(parcel, 4, placeReport.getSource(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzhf */
    public PlaceReport createFromParcel(Parcel parcel) {
        String str = null;
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 4:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new PlaceReport(i, str3, str2, str);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzkL */
    public PlaceReport[] newArray(int i) {
        return new PlaceReport[i];
    }
}
