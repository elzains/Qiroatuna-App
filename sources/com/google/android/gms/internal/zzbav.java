package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzad;

public class zzbav implements Parcelable.Creator<zzbau> {
    static void zza(zzbau zzbau, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzbau.zzaiI);
        zzc.zza(parcel, 2, (Parcelable) zzbau.zzPS(), i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzjx */
    public zzbau createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        zzad zzad = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    zzad = (zzad) zzb.zza(parcel, zzaX, zzad.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzbau(i, zzad);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zznx */
    public zzbau[] newArray(int i) {
        return new zzbau[i];
    }
}
