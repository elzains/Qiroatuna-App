package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.io.InputStream;

@zzme
public class zzdp extends zza {
    public static final Parcelable.Creator<zzdp> CREATOR = new zzdq();
    @Nullable
    private ParcelFileDescriptor zzyK;

    public zzdp() {
        this((ParcelFileDescriptor) null);
    }

    public zzdp(@Nullable ParcelFileDescriptor parcelFileDescriptor) {
        this.zzyK = parcelFileDescriptor;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzdq.zza(this, parcel, i);
    }

    public synchronized boolean zzew() {
        return this.zzyK != null;
    }

    @Nullable
    public synchronized InputStream zzex() {
        ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = null;
        synchronized (this) {
            if (this.zzyK != null) {
                autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(this.zzyK);
                this.zzyK = null;
            }
        }
        return autoCloseInputStream;
    }

    /* access modifiers changed from: package-private */
    public synchronized ParcelFileDescriptor zzey() {
        return this.zzyK;
    }
}
