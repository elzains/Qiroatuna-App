package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

@zzme
public final class zzoa extends zza {
    public static final Parcelable.Creator<zzoa> CREATOR = new zzob();
    public final zzec zzRy;
    public final String zzvl;

    public zzoa(zzec zzec, String str) {
        this.zzRy = zzec;
        this.zzvl = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzob.zza(this, parcel, i);
    }
}
