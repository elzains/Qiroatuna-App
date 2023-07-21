package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

@Deprecated
public class zzan extends zza {
    public static final Parcelable.Creator<zzan> CREATOR = new zzao();
    final int zzaiI;

    zzan(int i) {
        this.zzaiI = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzao.zza(this, parcel, i);
    }
}
