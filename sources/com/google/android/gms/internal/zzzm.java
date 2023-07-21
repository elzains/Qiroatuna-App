package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zzbxy;
import com.google.android.gms.internal.zzzk;
import java.util.Arrays;

public class zzzm extends zza {
    public static final Parcelable.Creator<zzzm> CREATOR = new zzzn();
    public zzzu zzaxI;
    public byte[] zzaxJ;
    public int[] zzaxK;
    public String[] zzaxL;
    public int[] zzaxM;
    public byte[][] zzaxN;
    public boolean zzaxO;
    public final zzbxy.zzd zzaxP;
    public final zzzk.zzc zzaxQ;
    public final zzzk.zzc zzaxR;

    public zzzm(zzzu zzzu, zzbxy.zzd zzd, zzzk.zzc zzc, zzzk.zzc zzc2, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr, boolean z) {
        this.zzaxI = zzzu;
        this.zzaxP = zzd;
        this.zzaxQ = zzc;
        this.zzaxR = zzc2;
        this.zzaxK = iArr;
        this.zzaxL = strArr;
        this.zzaxM = iArr2;
        this.zzaxN = bArr;
        this.zzaxO = z;
    }

    zzzm(zzzu zzzu, byte[] bArr, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr2, boolean z) {
        this.zzaxI = zzzu;
        this.zzaxJ = bArr;
        this.zzaxK = iArr;
        this.zzaxL = strArr;
        this.zzaxP = null;
        this.zzaxQ = null;
        this.zzaxR = null;
        this.zzaxM = iArr2;
        this.zzaxN = bArr2;
        this.zzaxO = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzzm)) {
            return false;
        }
        zzzm zzzm = (zzzm) obj;
        return zzaa.equal(this.zzaxI, zzzm.zzaxI) && Arrays.equals(this.zzaxJ, zzzm.zzaxJ) && Arrays.equals(this.zzaxK, zzzm.zzaxK) && Arrays.equals(this.zzaxL, zzzm.zzaxL) && zzaa.equal(this.zzaxP, zzzm.zzaxP) && zzaa.equal(this.zzaxQ, zzzm.zzaxQ) && zzaa.equal(this.zzaxR, zzzm.zzaxR) && Arrays.equals(this.zzaxM, zzzm.zzaxM) && Arrays.deepEquals(this.zzaxN, zzzm.zzaxN) && this.zzaxO == zzzm.zzaxO;
    }

    public int hashCode() {
        return zzaa.hashCode(this.zzaxI, this.zzaxJ, this.zzaxK, this.zzaxL, this.zzaxP, this.zzaxQ, this.zzaxR, this.zzaxM, this.zzaxN, Boolean.valueOf(this.zzaxO));
    }

    public String toString() {
        return "LogEventParcelable[" + this.zzaxI + ", " + "LogEventBytes: " + (this.zzaxJ == null ? null : new String(this.zzaxJ)) + ", " + "TestCodes: " + Arrays.toString(this.zzaxK) + ", " + "MendelPackages: " + Arrays.toString(this.zzaxL) + ", " + "LogEvent: " + this.zzaxP + ", " + "ExtensionProducer: " + this.zzaxQ + ", " + "VeProducer: " + this.zzaxR + ", " + "ExperimentIDs: " + Arrays.toString(this.zzaxM) + ", " + "ExperimentTokens: " + Arrays.toString(this.zzaxN) + ", " + "AddPhenotypeExperimentTokens: " + this.zzaxO + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzzn.zza(this, parcel, i);
    }
}
