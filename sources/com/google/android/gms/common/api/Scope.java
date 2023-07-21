package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;

public final class Scope extends zza implements ReflectedParcelable {
    public static final Parcelable.Creator<Scope> CREATOR = new zzg();
    final int zzaiI;
    private final String zzazw;

    Scope(int i, String str) {
        zzac.zzh(str, "scopeUri must not be null or empty");
        this.zzaiI = i;
        this.zzazw = str;
    }

    public Scope(String str) {
        this(1, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        return this.zzazw.equals(((Scope) obj).zzazw);
    }

    public int hashCode() {
        return this.zzazw.hashCode();
    }

    public String toString() {
        return this.zzazw;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzg.zza(this, parcel, i);
    }

    public String zzvt() {
        return this.zzazw;
    }
}
