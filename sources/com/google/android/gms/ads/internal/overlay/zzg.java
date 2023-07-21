package com.google.android.gms.ads.internal.overlay;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.MotionEventCompat;
import com.google.android.gms.ads.internal.zzn;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.internal.zzqh;

public class zzg implements Parcelable.Creator<AdOverlayInfoParcel> {
    static void zza(AdOverlayInfoParcel adOverlayInfoParcel, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 2, (Parcelable) adOverlayInfoParcel.zzNE, i, false);
        zzc.zza(parcel, 3, adOverlayInfoParcel.zzhN(), false);
        zzc.zza(parcel, 4, adOverlayInfoParcel.zzhO(), false);
        zzc.zza(parcel, 5, adOverlayInfoParcel.zzhP(), false);
        zzc.zza(parcel, 6, adOverlayInfoParcel.zzhQ(), false);
        zzc.zza(parcel, 7, adOverlayInfoParcel.zzNJ, false);
        zzc.zza(parcel, 8, adOverlayInfoParcel.zzNK);
        zzc.zza(parcel, 9, adOverlayInfoParcel.zzNL, false);
        zzc.zza(parcel, 10, adOverlayInfoParcel.zzhS(), false);
        zzc.zzc(parcel, 11, adOverlayInfoParcel.orientation);
        zzc.zzc(parcel, 12, adOverlayInfoParcel.zzNN);
        zzc.zza(parcel, 13, adOverlayInfoParcel.url, false);
        zzc.zza(parcel, 14, (Parcelable) adOverlayInfoParcel.zzvn, i, false);
        zzc.zza(parcel, 15, adOverlayInfoParcel.zzhR(), false);
        zzc.zza(parcel, 16, adOverlayInfoParcel.zzNP, false);
        zzc.zza(parcel, 17, (Parcelable) adOverlayInfoParcel.zzNQ, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzN */
    public AdOverlayInfoParcel[] newArray(int i) {
        return new AdOverlayInfoParcel[i];
    }

    /* renamed from: zzl */
    public AdOverlayInfoParcel createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        zzc zzc = null;
        IBinder iBinder = null;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        IBinder iBinder4 = null;
        String str = null;
        boolean z = false;
        String str2 = null;
        IBinder iBinder5 = null;
        int i = 0;
        int i2 = 0;
        String str3 = null;
        zzqh zzqh = null;
        IBinder iBinder6 = null;
        String str4 = null;
        zzn zzn = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 2:
                    zzc = (zzc) zzb.zza(parcel, zzaX, zzc.CREATOR);
                    break;
                case 3:
                    iBinder = zzb.zzr(parcel, zzaX);
                    break;
                case 4:
                    iBinder2 = zzb.zzr(parcel, zzaX);
                    break;
                case 5:
                    iBinder3 = zzb.zzr(parcel, zzaX);
                    break;
                case 6:
                    iBinder4 = zzb.zzr(parcel, zzaX);
                    break;
                case 7:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 8:
                    z = zzb.zzc(parcel, zzaX);
                    break;
                case 9:
                    str2 = zzb.zzq(parcel, zzaX);
                    break;
                case 10:
                    iBinder5 = zzb.zzr(parcel, zzaX);
                    break;
                case 11:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_RX:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 13:
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 14:
                    zzqh = (zzqh) zzb.zza(parcel, zzaX, zzqh.CREATOR);
                    break;
                case 15:
                    iBinder6 = zzb.zzr(parcel, zzaX);
                    break;
                case 16:
                    str4 = zzb.zzq(parcel, zzaX);
                    break;
                case 17:
                    zzn = (zzn) zzb.zza(parcel, zzaX, zzn.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new AdOverlayInfoParcel(zzc, iBinder, iBinder2, iBinder3, iBinder4, str, z, str2, iBinder5, i, i2, str3, zzqh, iBinder6, str4, zzn);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }
}
