package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.zzacs;

public class zzacn extends zza {
    public static final Parcelable.Creator<zzacn> CREATOR = new zzaco();
    private final zzacp zzaGR;
    final int zzaiI;

    zzacn(int i, zzacp zzacp) {
        this.zzaiI = i;
        this.zzaGR = zzacp;
    }

    private zzacn(zzacp zzacp) {
        this.zzaiI = 1;
        this.zzaGR = zzacp;
    }

    public static zzacn zza(zzacs.zzb<?, ?> zzb) {
        if (zzb instanceof zzacp) {
            return new zzacn((zzacp) zzb);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzaco.zza(this, parcel, i);
    }

    /* access modifiers changed from: package-private */
    public zzacp zzyo() {
        return this.zzaGR;
    }

    public zzacs.zzb<?, ?> zzyp() {
        if (this.zzaGR != null) {
            return this.zzaGR;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
}
