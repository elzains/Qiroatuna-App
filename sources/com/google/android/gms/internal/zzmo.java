package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.MotionEventCompat;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzmo implements Parcelable.Creator<zzmn> {
    static void zza(zzmn zzmn, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzmn.versionCode);
        zzc.zza(parcel, 2, zzmn.zzNJ, false);
        zzc.zza(parcel, 3, zzmn.body, false);
        zzc.zzb(parcel, 4, zzmn.zzKF, false);
        zzc.zzc(parcel, 5, zzmn.errorCode);
        zzc.zzb(parcel, 6, zzmn.zzKG, false);
        zzc.zza(parcel, 7, zzmn.zzSm);
        zzc.zza(parcel, 8, zzmn.zzSn);
        zzc.zza(parcel, 9, zzmn.zzSo);
        zzc.zzb(parcel, 10, zzmn.zzSp, false);
        zzc.zza(parcel, 11, zzmn.zzKL);
        zzc.zzc(parcel, 12, zzmn.orientation);
        zzc.zza(parcel, 13, zzmn.zzSq, false);
        zzc.zza(parcel, 14, zzmn.zzSr);
        zzc.zza(parcel, 15, zzmn.zzSs, false);
        zzc.zza(parcel, 18, zzmn.zzSt);
        zzc.zza(parcel, 19, zzmn.zzSu, false);
        zzc.zza(parcel, 21, zzmn.zzSv, false);
        zzc.zza(parcel, 22, zzmn.zzSw);
        zzc.zza(parcel, 23, zzmn.zzzB);
        zzc.zza(parcel, 24, zzmn.zzRG);
        zzc.zza(parcel, 25, zzmn.zzSx);
        zzc.zza(parcel, 26, zzmn.zzSy);
        zzc.zza(parcel, 28, (Parcelable) zzmn.zzSz, i, false);
        zzc.zza(parcel, 29, zzmn.zzSA, false);
        zzc.zza(parcel, 30, zzmn.zzSB, false);
        zzc.zza(parcel, 31, zzmn.zzzC);
        zzc.zza(parcel, 32, zzmn.zzzD);
        zzc.zza(parcel, 33, (Parcelable) zzmn.zzSC, i, false);
        zzc.zzb(parcel, 34, zzmn.zzSD, false);
        zzc.zzb(parcel, 35, zzmn.zzSE, false);
        zzc.zza(parcel, 36, zzmn.zzSF);
        zzc.zza(parcel, 37, (Parcelable) zzmn.zzSG, i, false);
        zzc.zza(parcel, 38, zzmn.zzRV);
        zzc.zza(parcel, 39, zzmn.zzRW, false);
        zzc.zzb(parcel, 40, zzmn.zzKI, false);
        zzc.zza(parcel, 42, zzmn.zzKJ);
        zzc.zza(parcel, 43, zzmn.zzSH, false);
        zzc.zza(parcel, 44, (Parcelable) zzmn.zzSI, i, false);
        zzc.zza(parcel, 45, zzmn.zzSJ, false);
        zzc.zza(parcel, 46, zzmn.zzSK);
        zzc.zza(parcel, 47, zzmn.zzSh);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzW */
    public zzmn[] newArray(int i) {
        return new zzmn[i];
    }

    /* renamed from: zzo */
    public zzmn createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        ArrayList<String> arrayList = null;
        int i2 = 0;
        ArrayList<String> arrayList2 = null;
        long j = 0;
        boolean z = false;
        long j2 = 0;
        ArrayList<String> arrayList3 = null;
        long j3 = 0;
        int i3 = 0;
        String str3 = null;
        long j4 = 0;
        String str4 = null;
        boolean z2 = false;
        String str5 = null;
        String str6 = null;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        zzmv zzmv = null;
        String str7 = null;
        String str8 = null;
        boolean z8 = false;
        boolean z9 = false;
        zzoo zzoo = null;
        ArrayList<String> arrayList4 = null;
        ArrayList<String> arrayList5 = null;
        boolean z10 = false;
        zzmp zzmp = null;
        boolean z11 = false;
        String str9 = null;
        ArrayList<String> arrayList6 = null;
        boolean z12 = false;
        String str10 = null;
        zzor zzor = null;
        String str11 = null;
        boolean z13 = false;
        boolean z14 = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 3:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 4:
                    arrayList = zzb.zzE(parcel, zzaX);
                    break;
                case 5:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 6:
                    arrayList2 = zzb.zzE(parcel, zzaX);
                    break;
                case 7:
                    j = zzb.zzi(parcel, zzaX);
                    break;
                case 8:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 9:
                    j2 = zzb.zzi(parcel, zzaX);
                    break;
                case 10:
                    arrayList3 = zzb.zzE(parcel, zzaX);
                    break;
                case 11:
                    j3 = zzb.zzi(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_RX:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 13:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 14:
                    j4 = zzb.zzi(parcel, zzaX);
                    break;
                case 15:
                    str4 = zzb.zzq(parcel, zzaX);
                    break;
                case 18:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case 19:
                    str5 = zzb.zzq(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_WHEEL:
                    str6 = zzb.zzq(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GAS:
                    z3 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_BRAKE:
                    z4 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_DISTANCE:
                    z5 = zzb.zzc(parcel, zzaX);
                    break;
                case 25:
                    z6 = zzb.zzc(parcel, zzaX);
                    break;
                case 26:
                    z7 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_RELATIVE_Y:
                    zzmv = (zzmv) zzb.zza(parcel, zzaX, zzmv.CREATOR);
                    break;
                case 29:
                    str7 = zzb.zzq(parcel, zzaX);
                    break;
                case 30:
                    str8 = zzb.zzq(parcel, zzaX);
                    break;
                case 31:
                    z8 = zzb.zzc(parcel, zzaX);
                    break;
                case 32:
                    z9 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_2:
                    zzoo = (zzoo) zzb.zza(parcel, zzaX, zzoo.CREATOR);
                    break;
                case MotionEventCompat.AXIS_GENERIC_3:
                    arrayList4 = zzb.zzE(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_4:
                    arrayList5 = zzb.zzE(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_5:
                    z10 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_6:
                    zzmp = (zzmp) zzb.zza(parcel, zzaX, zzmp.CREATOR);
                    break;
                case MotionEventCompat.AXIS_GENERIC_7:
                    z11 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_8:
                    str9 = zzb.zzq(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_9:
                    arrayList6 = zzb.zzE(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_11:
                    z12 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_12:
                    str10 = zzb.zzq(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_13:
                    zzor = (zzor) zzb.zza(parcel, zzaX, zzor.CREATOR);
                    break;
                case MotionEventCompat.AXIS_GENERIC_14:
                    str11 = zzb.zzq(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_15:
                    z13 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_16:
                    z14 = zzb.zzc(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzmn(i, str, str2, arrayList, i2, arrayList2, j, z, j2, arrayList3, j3, i3, str3, j4, str4, z2, str5, str6, z3, z4, z5, z6, z7, zzmv, str7, str8, z8, z9, zzoo, arrayList4, arrayList5, z10, zzmp, z11, str9, arrayList6, z12, str10, zzor, str11, z13, z14);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }
}
