package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzc;

@zzme
public class zzfr extends zzeg {
    public zzfr(zzeg zzeg) {
        super(zzeg.zzzy, zzeg.height, zzeg.heightPixels, zzeg.zzzz, zzeg.width, zzeg.widthPixels, zzeg.zzzA, zzeg.zzzB, zzeg.zzzC, zzeg.zzzD);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, this.zzzy, false);
        zzc.zzc(parcel, 3, this.height);
        zzc.zzc(parcel, 6, this.width);
        zzc.zzJ(parcel, zzaZ);
    }
}
