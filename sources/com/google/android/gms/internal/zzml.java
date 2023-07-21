package com.google.android.gms.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.MotionEventCompat;
import com.google.ads.AdSize;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zzml implements Parcelable.Creator<zzmk> {
    static void zza(zzmk zzmk, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzmk.versionCode);
        zzc.zza(parcel, 2, zzmk.zzRx, false);
        zzc.zza(parcel, 3, (Parcelable) zzmk.zzRy, i, false);
        zzc.zza(parcel, 4, (Parcelable) zzmk.zzvr, i, false);
        zzc.zza(parcel, 5, zzmk.zzvl, false);
        zzc.zza(parcel, 6, (Parcelable) zzmk.applicationInfo, i, false);
        zzc.zza(parcel, 7, (Parcelable) zzmk.zzRz, i, false);
        zzc.zza(parcel, 8, zzmk.zzRA, false);
        zzc.zza(parcel, 9, zzmk.zzRB, false);
        zzc.zza(parcel, 10, zzmk.zzRC, false);
        zzc.zza(parcel, 11, (Parcelable) zzmk.zzvn, i, false);
        zzc.zza(parcel, 12, zzmk.zzRD, false);
        zzc.zzc(parcel, 13, zzmk.zzRE);
        zzc.zzb(parcel, 14, zzmk.zzvK, false);
        zzc.zza(parcel, 15, zzmk.zzRF, false);
        zzc.zza(parcel, 16, zzmk.zzRG);
        zzc.zzc(parcel, 18, zzmk.zzRH);
        zzc.zzc(parcel, 19, zzmk.zzRI);
        zzc.zza(parcel, 20, zzmk.zzxk);
        zzc.zza(parcel, 21, zzmk.zzRJ, false);
        zzc.zza(parcel, 25, zzmk.zzRK);
        zzc.zza(parcel, 26, zzmk.zzRL, false);
        zzc.zzb(parcel, 27, zzmk.zzRM, false);
        zzc.zza(parcel, 28, zzmk.zzvk, false);
        zzc.zza(parcel, 29, (Parcelable) zzmk.zzvF, i, false);
        zzc.zzb(parcel, 30, zzmk.zzRN, false);
        zzc.zza(parcel, 31, zzmk.zzRO);
        zzc.zza(parcel, 32, (Parcelable) zzmk.zzRP, i, false);
        zzc.zza(parcel, 33, zzmk.zzRQ, false);
        zzc.zza(parcel, 34, zzmk.zzRR);
        zzc.zzc(parcel, 35, zzmk.zzRS);
        zzc.zzc(parcel, 36, zzmk.zzRT);
        zzc.zza(parcel, 37, zzmk.zzRU);
        zzc.zza(parcel, 38, zzmk.zzRV);
        zzc.zza(parcel, 39, zzmk.zzRW, false);
        zzc.zza(parcel, 40, zzmk.zzRX);
        zzc.zza(parcel, 41, zzmk.zzRY, false);
        zzc.zza(parcel, 42, zzmk.zzKJ);
        zzc.zzc(parcel, 43, zzmk.zzRZ);
        zzc.zza(parcel, 44, zzmk.zzSa, false);
        zzc.zza(parcel, 45, zzmk.zzSb, false);
        zzc.zza(parcel, 46, (Parcelable) zzmk.zzvH, i, false);
        zzc.zza(parcel, 47, zzmk.zzSc);
        zzc.zza(parcel, 48, zzmk.zzSd, false);
        zzc.zza(parcel, 49, zzmk.zzSe, false);
        zzc.zza(parcel, 50, zzmk.zzSf, false);
        zzc.zza(parcel, 51, zzmk.zzSg, false);
        zzc.zza(parcel, 52, zzmk.zzSh);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzV */
    public zzmk[] newArray(int i) {
        return new zzmk[i];
    }

    /* renamed from: zzn */
    public zzmk createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        Bundle bundle = null;
        zzec zzec = null;
        zzeg zzeg = null;
        String str = null;
        ApplicationInfo applicationInfo = null;
        PackageInfo packageInfo = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        zzqh zzqh = null;
        Bundle bundle2 = null;
        int i2 = 0;
        ArrayList<String> arrayList = null;
        Bundle bundle3 = null;
        boolean z = false;
        int i3 = 0;
        int i4 = 0;
        float f = 0.0f;
        String str5 = null;
        long j = 0;
        String str6 = null;
        ArrayList<String> arrayList2 = null;
        String str7 = null;
        zzhc zzhc = null;
        ArrayList<String> arrayList3 = null;
        long j2 = 0;
        zzmr zzmr = null;
        String str8 = null;
        float f2 = 0.0f;
        boolean z2 = false;
        int i5 = 0;
        int i6 = 0;
        boolean z3 = false;
        boolean z4 = false;
        String str9 = null;
        String str10 = null;
        boolean z5 = false;
        int i7 = 0;
        Bundle bundle4 = null;
        String str11 = null;
        zzfc zzfc = null;
        boolean z6 = false;
        Bundle bundle5 = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
        boolean z7 = false;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    bundle = zzb.zzs(parcel, zzaX);
                    break;
                case 3:
                    zzec = (zzec) zzb.zza(parcel, zzaX, zzec.CREATOR);
                    break;
                case 4:
                    zzeg = (zzeg) zzb.zza(parcel, zzaX, zzeg.CREATOR);
                    break;
                case 5:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 6:
                    applicationInfo = (ApplicationInfo) zzb.zza(parcel, zzaX, ApplicationInfo.CREATOR);
                    break;
                case 7:
                    packageInfo = (PackageInfo) zzb.zza(parcel, zzaX, PackageInfo.CREATOR);
                    break;
                case 8:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 9:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 10:
                    str4 = zzb.zzq(parcel, zzaX);
                    break;
                case 11:
                    zzqh = (zzqh) zzb.zza(parcel, zzaX, zzqh.CREATOR);
                    break;
                case MotionEventCompat.AXIS_RX:
                    bundle2 = zzb.zzs(parcel, zzaX);
                    break;
                case 13:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 14:
                    arrayList = zzb.zzE(parcel, zzaX);
                    break;
                case 15:
                    bundle3 = zzb.zzs(parcel, zzaX);
                    break;
                case 16:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 18:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 19:
                    i4 = zzb.zzg(parcel, zzaX);
                    break;
                case 20:
                    f = zzb.zzl(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_WHEEL:
                    str5 = zzb.zzq(parcel, zzaX);
                    break;
                case 25:
                    j = zzb.zzi(parcel, zzaX);
                    break;
                case 26:
                    str6 = zzb.zzq(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_RELATIVE_X:
                    arrayList2 = zzb.zzE(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_RELATIVE_Y:
                    str7 = zzb.zzq(parcel, zzaX);
                    break;
                case 29:
                    zzhc = (zzhc) zzb.zza(parcel, zzaX, zzhc.CREATOR);
                    break;
                case 30:
                    arrayList3 = zzb.zzE(parcel, zzaX);
                    break;
                case 31:
                    j2 = zzb.zzi(parcel, zzaX);
                    break;
                case 32:
                    zzmr = (zzmr) zzb.zza(parcel, zzaX, zzmr.CREATOR);
                    break;
                case MotionEventCompat.AXIS_GENERIC_2:
                    str8 = zzb.zzq(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_3:
                    f2 = zzb.zzl(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_4:
                    i5 = zzb.zzg(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_5:
                    i6 = zzb.zzg(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_6:
                    z3 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_7:
                    z4 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_8:
                    str9 = zzb.zzq(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_9:
                    z2 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_10:
                    str10 = zzb.zzq(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_11:
                    z5 = zzb.zzc(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_12:
                    i7 = zzb.zzg(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_13:
                    bundle4 = zzb.zzs(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_14:
                    str11 = zzb.zzq(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_GENERIC_15:
                    zzfc = (zzfc) zzb.zza(parcel, zzaX, zzfc.CREATOR);
                    break;
                case MotionEventCompat.AXIS_GENERIC_16:
                    z6 = zzb.zzc(parcel, zzaX);
                    break;
                case 48:
                    bundle5 = zzb.zzs(parcel, zzaX);
                    break;
                case 49:
                    str12 = zzb.zzq(parcel, zzaX);
                    break;
                case AdSize.PORTRAIT_AD_HEIGHT:
                    str13 = zzb.zzq(parcel, zzaX);
                    break;
                case 51:
                    str14 = zzb.zzq(parcel, zzaX);
                    break;
                case 52:
                    z7 = zzb.zzc(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzmk(i, bundle, zzec, zzeg, str, applicationInfo, packageInfo, str2, str3, str4, zzqh, bundle2, i2, arrayList, bundle3, z, i3, i4, f, str5, j, str6, arrayList2, str7, zzhc, arrayList3, j2, zzmr, str8, f2, z2, i5, i6, z3, z4, str9, str10, z5, i7, bundle4, str11, zzfc, z6, bundle5, str12, str13, str14, z7);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }
}
