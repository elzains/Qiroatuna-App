package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.MotionEventCompat;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzfq implements Parcelable.Creator<zzfp> {
    static void zza(zzfp zzfp, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 2, zzfp.zzAH);
        zzc.zzc(parcel, 3, zzfp.backgroundColor);
        zzc.zzc(parcel, 4, zzfp.zzAI);
        zzc.zzc(parcel, 5, zzfp.zzAJ);
        zzc.zzc(parcel, 6, zzfp.zzAK);
        zzc.zzc(parcel, 7, zzfp.zzAL);
        zzc.zzc(parcel, 8, zzfp.zzAM);
        zzc.zzc(parcel, 9, zzfp.zzAN);
        zzc.zza(parcel, 10, zzfp.zzAO, false);
        zzc.zzc(parcel, 11, zzfp.zzAP);
        zzc.zza(parcel, 12, zzfp.zzAQ, false);
        zzc.zzc(parcel, 13, zzfp.zzAR);
        zzc.zzc(parcel, 14, zzfp.zzAS);
        zzc.zza(parcel, 15, zzfp.zzAT, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzA */
    public zzfp[] newArray(int i) {
        return new zzfp[i];
    }

    /* renamed from: zzh */
    public zzfp createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        String str = null;
        int i9 = 0;
        String str2 = null;
        int i10 = 0;
        int i11 = 0;
        String str3 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 3:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 4:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 5:
                    i4 = zzb.zzg(parcel, zzaX);
                    break;
                case 6:
                    i5 = zzb.zzg(parcel, zzaX);
                    break;
                case 7:
                    i6 = zzb.zzg(parcel, zzaX);
                    break;
                case 8:
                    i7 = zzb.zzg(parcel, zzaX);
                    break;
                case 9:
                    i8 = zzb.zzg(parcel, zzaX);
                    break;
                case 10:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 11:
                    i9 = zzb.zzg(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_RX:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 13:
                    i10 = zzb.zzg(parcel, zzaX);
                    break;
                case 14:
                    i11 = zzb.zzg(parcel, zzaX);
                    break;
                case 15:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzfp(i, i2, i3, i4, i5, i6, i7, i8, str, i9, str2, i10, i11, str3);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }
}
