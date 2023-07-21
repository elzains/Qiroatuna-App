package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzc extends zza {
    public static final Parcelable.Creator<zzc> CREATOR = new zzd();

    /* renamed from: name  reason: collision with root package name */
    public final String f16name;
    public final int version;

    public zzc(String str, int i) {
        this.f16name = str;
        this.version = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}
