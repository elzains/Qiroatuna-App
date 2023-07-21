package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

@zzme
public class zzmy extends zza {
    public static final Parcelable.Creator<zzmy> CREATOR = new zzmz();
    String zzFV;

    public zzmy(String str) {
        this.zzFV = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzmz.zza(this, parcel, i);
    }

    public String zzjt() {
        return this.zzFV;
    }
}
