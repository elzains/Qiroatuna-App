package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzdt implements Parcelable.Creator<zzds> {
    static void zza(zzds zzds, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzds.url, false);
        zzc.zza(parcel, 3, zzds.zzyL);
        zzc.zza(parcel, 4, zzds.zzyM, false);
        zzc.zza(parcel, 5, zzds.zzyN, false);
        zzc.zza(parcel, 6, zzds.zzyO, false);
        zzc.zza(parcel, 7, zzds.zzyP, false);
        zzc.zza(parcel, 8, zzds.zzyQ);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzd */
    public zzds createFromParcel(Parcel parcel) {
        Bundle bundle = null;
        int zzaY = zzb.zzaY(parcel);
        long j = 0;
        boolean z = false;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    str4 = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    j = zzb.zzi(parcel, zzaX);
                    break;
                case 4:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 5:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 6:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 7:
                    bundle = zzb.zzs(parcel, zzaX);
                    break;
                case 8:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzds(str4, j, str3, str2, str, bundle, z);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzu */
    public zzds[] newArray(int i) {
        return new zzds[i];
    }
}
