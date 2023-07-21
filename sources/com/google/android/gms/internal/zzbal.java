package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbal implements Parcelable.Creator<zzbak> {
    static void zza(zzbak zzbak, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzbak.zzaiI);
        zzc.zzc(parcel, 2, zzbak.zzPO());
        zzc.zza(parcel, 3, (Parcelable) zzbak.zzPP(), i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzju */
    public zzbak createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaY = zzb.zzaY(parcel);
        Intent intent = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 3:
                    intent = (Intent) zzb.zza(parcel, zzaX, Intent.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzbak(i2, i, intent);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zznt */
    public zzbak[] newArray(int i) {
        return new zzbak[i];
    }
}
