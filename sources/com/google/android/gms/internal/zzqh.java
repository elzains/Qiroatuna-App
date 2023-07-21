package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

@zzme
public final class zzqh extends zza {
    public static final Parcelable.Creator<zzqh> CREATOR = new zzqi();
    public int zzYW;
    public int zzYX;
    public boolean zzYY;
    public boolean zzYZ;
    public String zzba;

    public zzqh(int i, int i2, boolean z) {
        this(i, i2, z, false, false);
    }

    public zzqh(int i, int i2, boolean z, boolean z2) {
        this(i, i2, z, false, z2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzqh(int r7, int r8, boolean r9, boolean r10, boolean r11) {
        /*
            r6 = this;
            java.lang.String r0 = "afma-sdk-a-v"
            java.lang.String r1 = java.lang.String.valueOf(r0)
            if (r9 == 0) goto L_0x004b
            java.lang.String r0 = "0"
        L_0x000a:
            java.lang.String r2 = java.lang.String.valueOf(r1)
            int r2 = r2.length()
            int r2 = r2 + 24
            java.lang.String r3 = java.lang.String.valueOf(r0)
            int r3 = r3.length()
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.StringBuilder r1 = r1.append(r7)
            java.lang.String r2 = "."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r8)
            java.lang.String r2 = "."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = r0.toString()
            r0 = r6
            r2 = r7
            r3 = r8
            r4 = r9
            r5 = r11
            r0.<init>((java.lang.String) r1, (int) r2, (int) r3, (boolean) r4, (boolean) r5)
            return
        L_0x004b:
            if (r10 == 0) goto L_0x0050
            java.lang.String r0 = "2"
            goto L_0x000a
        L_0x0050:
            java.lang.String r0 = "1"
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqh.<init>(int, int, boolean, boolean, boolean):void");
    }

    zzqh(String str, int i, int i2, boolean z, boolean z2) {
        this.zzba = str;
        this.zzYW = i;
        this.zzYX = i2;
        this.zzYY = z;
        this.zzYZ = z2;
    }

    public static zzqh zzlk() {
        return new zzqh(10298208, 10298208, true);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzqi.zza(this, parcel, i);
    }
}
