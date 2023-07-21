package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzacs;
import com.google.android.gms.internal.zzacw;

public class zzacv implements Parcelable.Creator<zzacw.zzb> {
    static void zza(zzacw.zzb zzb, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzb.versionCode);
        zzc.zza(parcel, 2, zzb.zzaB, false);
        zzc.zza(parcel, 3, (Parcelable) zzb.zzaHl, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzbf */
    public zzacw.zzb createFromParcel(Parcel parcel) {
        zzacs.zza zza = null;
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    zza = (zzacs.zza) zzb.zza(parcel, zzaX, zzacs.zza.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzacw.zzb(i, str, zza);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzdj */
    public zzacw.zzb[] newArray(int i) {
        return new zzacw.zzb[i];
    }
}
