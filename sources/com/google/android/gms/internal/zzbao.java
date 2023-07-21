package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzbao implements Parcelable.Creator<zzban> {
    static void zza(zzban zzban, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzban.zzaiI);
        zzc.zza(parcel, 2, zzban.zzbEt);
        zzc.zzc(parcel, 3, zzban.zzbEu, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzjv */
    public zzban createFromParcel(Parcel parcel) {
        boolean z = false;
        int zzaY = zzb.zzaY(parcel);
        ArrayList<Scope> arrayList = null;
        int i = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 3:
                    arrayList = zzb.zzc(parcel, zzaX, Scope.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzban(i, z, arrayList);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zznu */
    public zzban[] newArray(int i) {
        return new zzban[i];
    }
}
