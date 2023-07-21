package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzo implements Parcelable.Creator<zzn> {
    static void zza(zzn zzn, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, zzn.zztK);
        zzc.zza(parcel, 3, zzn.zztL);
        zzc.zza(parcel, 4, zzn.zztM, false);
        zzc.zza(parcel, 5, zzn.zztN);
        zzc.zza(parcel, 6, zzn.zztO);
        zzc.zzc(parcel, 7, zzn.zztP);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzb */
    public zzn createFromParcel(Parcel parcel) {
        int i = 0;
        int zzaY = zzb.zzaY(parcel);
        String str = null;
        float f = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    z3 = zzb.zzc(parcel, zzaX);
                    break;
                case 3:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 4:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 5:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 6:
                    f = zzb.zzl(parcel, zzaX);
                    break;
                case 7:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzn(z3, z2, str, z, f, i);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzi */
    public zzn[] newArray(int i) {
        return new zzn[i];
    }
}
