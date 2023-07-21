package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zze implements Parcelable.Creator<zzd> {
    static void zza(zzd zzd, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzd.zzaiI);
        zzc.zza(parcel, 2, zzd.zzaEW, false);
        zzc.zza(parcel, 3, (T[]) zzd.zzaEX, i, false);
        zzc.zza(parcel, 4, zzd.zzaEY, false);
        zzc.zza(parcel, 5, zzd.zzaEZ, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaQ */
    public zzd createFromParcel(Parcel parcel) {
        Integer num = null;
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        Integer num2 = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    iBinder = zzb.zzr(parcel, zzaX);
                    break;
                case 3:
                    scopeArr = (Scope[]) zzb.zzb(parcel, zzaX, Scope.CREATOR);
                    break;
                case 4:
                    num2 = zzb.zzh(parcel, zzaX);
                    break;
                case 5:
                    num = zzb.zzh(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzd(i, iBinder, scopeArr, num2, num);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzcR */
    public zzd[] newArray(int i) {
        return new zzd[i];
    }
}
