package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaf;

public class zzbaw extends zza {
    public static final Parcelable.Creator<zzbaw> CREATOR = new zzbax();
    private final ConnectionResult zzaGE;
    final int zzaiI;
    private final zzaf zzbEy;

    public zzbaw(int i) {
        this(new ConnectionResult(i, (PendingIntent) null), (zzaf) null);
    }

    zzbaw(int i, ConnectionResult connectionResult, zzaf zzaf) {
        this.zzaiI = i;
        this.zzaGE = connectionResult;
        this.zzbEy = zzaf;
    }

    public zzbaw(ConnectionResult connectionResult, zzaf zzaf) {
        this(1, connectionResult, zzaf);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzbax.zza(this, parcel, i);
    }

    public zzaf zzPT() {
        return this.zzbEy;
    }

    public ConnectionResult zzyh() {
        return this.zzaGE;
    }
}
