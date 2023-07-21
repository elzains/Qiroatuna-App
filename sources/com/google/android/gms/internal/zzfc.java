package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

@zzme
public class zzfc extends zza {
    public static final Parcelable.Creator<zzfc> CREATOR = new zzfd();
    public final int zzzZ;

    public zzfc(int i) {
        this.zzzZ = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzfd.zza(this, parcel, i);
    }
}
