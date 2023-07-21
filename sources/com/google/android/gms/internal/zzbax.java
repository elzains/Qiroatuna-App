package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzaf;

public class zzbax implements Parcelable.Creator<zzbaw> {
    static void zza(zzbaw zzbaw, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzbaw.zzaiI);
        zzc.zza(parcel, 2, (Parcelable) zzbaw.zzyh(), i, false);
        zzc.zza(parcel, 3, (Parcelable) zzbaw.zzPT(), i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzjy */
    public zzbaw createFromParcel(Parcel parcel) {
        zzaf zzaf;
        ConnectionResult connectionResult;
        int i;
        zzaf zzaf2 = null;
        int zzaY = zzb.zzaY(parcel);
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    zzaf zzaf3 = zzaf2;
                    connectionResult = connectionResult2;
                    i = zzb.zzg(parcel, zzaX);
                    zzaf = zzaf3;
                    break;
                case 2:
                    i = i2;
                    ConnectionResult connectionResult3 = (ConnectionResult) zzb.zza(parcel, zzaX, ConnectionResult.CREATOR);
                    zzaf = zzaf2;
                    connectionResult = connectionResult3;
                    break;
                case 3:
                    zzaf = (zzaf) zzb.zza(parcel, zzaX, zzaf.CREATOR);
                    connectionResult = connectionResult2;
                    i = i2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzaf = zzaf2;
                    connectionResult = connectionResult2;
                    i = i2;
                    break;
            }
            i2 = i;
            connectionResult2 = connectionResult;
            zzaf2 = zzaf;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzbaw(i2, connectionResult2, zzaf2);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzny */
    public zzbaw[] newArray(int i) {
        return new zzbaw[i];
    }
}
