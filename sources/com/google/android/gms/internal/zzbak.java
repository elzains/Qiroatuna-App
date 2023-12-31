package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzbak extends zza implements Result {
    public static final Parcelable.Creator<zzbak> CREATOR = new zzbal();
    final int zzaiI;
    private int zzbEr;
    private Intent zzbEs;

    public zzbak() {
        this(0, (Intent) null);
    }

    zzbak(int i, int i2, Intent intent) {
        this.zzaiI = i;
        this.zzbEr = i2;
        this.zzbEs = intent;
    }

    public zzbak(int i, Intent intent) {
        this(2, i, intent);
    }

    public Status getStatus() {
        return this.zzbEr == 0 ? Status.zzazx : Status.zzazB;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzbal.zza(this, parcel, i);
    }

    public int zzPO() {
        return this.zzbEr;
    }

    public Intent zzPP() {
        return this.zzbEs;
    }
}
