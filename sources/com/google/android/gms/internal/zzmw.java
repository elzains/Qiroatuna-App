package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzmw implements Parcelable.Creator<zzmv> {
    static void zza(zzmv zzmv, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) zzmv.zzSQ, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzZ */
    public zzmv[] newArray(int i) {
        return new zzmv[i];
    }

    /* renamed from: zzr */
    public zzmv createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        ParcelFileDescriptor parcelFileDescriptor = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    parcelFileDescriptor = (ParcelFileDescriptor) zzb.zza(parcel, zzaX, ParcelFileDescriptor.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzmv(parcelFileDescriptor);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }
}
