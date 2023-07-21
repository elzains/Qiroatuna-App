package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzayy implements Parcelable.Creator<zzayx> {
    static void zza(zzayx zzayx, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 2, zzayx.zzbBC);
        zzc.zza(parcel, 3, (T[]) zzayx.zzbBD, i, false);
        zzc.zza(parcel, 4, zzayx.zzbBE, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzja */
    public zzayx createFromParcel(Parcel parcel) {
        String[] zzC;
        zzayz[] zzayzArr;
        int i;
        String[] strArr = null;
        int zzaY = zzb.zzaY(parcel);
        int i2 = 0;
        zzayz[] zzayzArr2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    String[] strArr2 = strArr;
                    zzayzArr = zzayzArr2;
                    i = zzb.zzg(parcel, zzaX);
                    zzC = strArr2;
                    break;
                case 3:
                    i = i2;
                    zzayz[] zzayzArr3 = (zzayz[]) zzb.zzb(parcel, zzaX, zzayz.CREATOR);
                    zzC = strArr;
                    zzayzArr = zzayzArr3;
                    break;
                case 4:
                    zzC = zzb.zzC(parcel, zzaX);
                    zzayzArr = zzayzArr2;
                    i = i2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzC = strArr;
                    zzayzArr = zzayzArr2;
                    i = i2;
                    break;
            }
            i2 = i;
            zzayzArr2 = zzayzArr;
            strArr = zzC;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzayx(i2, zzayzArr2, strArr);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzmX */
    public zzayx[] newArray(int i) {
        return new zzayx[i];
    }
}
