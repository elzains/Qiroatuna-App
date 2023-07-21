package com.google.android.gms.ads.internal.purchase;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zza implements Parcelable.Creator<GInAppPurchaseManagerInfoParcel> {
    static void zza(GInAppPurchaseManagerInfoParcel gInAppPurchaseManagerInfoParcel, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zza(parcel, 3, gInAppPurchaseManagerInfoParcel.zziF(), false);
        zzc.zza(parcel, 4, gInAppPurchaseManagerInfoParcel.zziG(), false);
        zzc.zza(parcel, 5, gInAppPurchaseManagerInfoParcel.zziH(), false);
        zzc.zza(parcel, 6, gInAppPurchaseManagerInfoParcel.zziE(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzO */
    public GInAppPurchaseManagerInfoParcel[] newArray(int i) {
        return new GInAppPurchaseManagerInfoParcel[i];
    }

    /* renamed from: zzm */
    public GInAppPurchaseManagerInfoParcel createFromParcel(Parcel parcel) {
        IBinder iBinder = null;
        int zzaY = zzb.zzaY(parcel);
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        IBinder iBinder4 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 3:
                    iBinder4 = zzb.zzr(parcel, zzaX);
                    break;
                case 4:
                    iBinder3 = zzb.zzr(parcel, zzaX);
                    break;
                case 5:
                    iBinder2 = zzb.zzr(parcel, zzaX);
                    break;
                case 6:
                    iBinder = zzb.zzr(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new GInAppPurchaseManagerInfoParcel(iBinder4, iBinder3, iBinder2, iBinder);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }
}
