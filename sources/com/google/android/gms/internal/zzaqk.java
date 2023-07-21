package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.zzag;

public final class zzaqk extends zza {
    public static final Parcelable.Creator<zzaqk> CREATOR = new zzaql();
    public final int versionCode;
    private zzag.zza zzbgk = null;
    private byte[] zzbgl;

    zzaqk(int i, byte[] bArr) {
        this.versionCode = i;
        this.zzbgl = bArr;
        zzzS();
    }

    private void zzzQ() {
        if (!zzzR()) {
            try {
                this.zzbgk = zzag.zza.zzd(this.zzbgl);
                this.zzbgl = null;
            } catch (zzbxs e) {
                throw new IllegalStateException(e);
            }
        }
        zzzS();
    }

    private boolean zzzR() {
        return this.zzbgk != null;
    }

    private void zzzS() {
        if (this.zzbgk == null && this.zzbgl != null) {
            return;
        }
        if (this.zzbgk != null && this.zzbgl == null) {
            return;
        }
        if (this.zzbgk != null && this.zzbgl != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        } else if (this.zzbgk == null && this.zzbgl == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        } else {
            throw new IllegalStateException("Impossible");
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzaql.zza(this, parcel, i);
    }

    public byte[] zzGL() {
        return this.zzbgl != null ? this.zzbgl : zzbxt.zzf(this.zzbgk);
    }

    public zzag.zza zzGM() {
        zzzQ();
        return this.zzbgk;
    }
}
