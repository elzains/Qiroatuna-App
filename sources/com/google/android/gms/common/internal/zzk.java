package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzk implements Parcelable.Creator<zzj> {
    static void zza(zzj zzj, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzj.version);
        zzc.zzc(parcel, 2, zzj.zzaFK);
        zzc.zzc(parcel, 3, zzj.zzaFL);
        zzc.zza(parcel, 4, zzj.zzaFM, false);
        zzc.zza(parcel, 5, zzj.zzaFN, false);
        zzc.zza(parcel, 6, (T[]) zzj.zzaFO, i, false);
        zzc.zza(parcel, 7, zzj.zzaFP, false);
        zzc.zza(parcel, 8, (Parcelable) zzj.zzaFQ, i, false);
        zzc.zza(parcel, 10, (T[]) zzj.zzaFR, i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaS */
    public zzj createFromParcel(Parcel parcel) {
        int i = 0;
        com.google.android.gms.common.zzc[] zzcArr = null;
        int zzaY = zzb.zzaY(parcel);
        Account account = null;
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i3 = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 3:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 4:
                    str = zzb.zzq(parcel, zzaX);
                    break;
                case 5:
                    iBinder = zzb.zzr(parcel, zzaX);
                    break;
                case 6:
                    scopeArr = (Scope[]) zzb.zzb(parcel, zzaX, Scope.CREATOR);
                    break;
                case 7:
                    bundle = zzb.zzs(parcel, zzaX);
                    break;
                case 8:
                    account = (Account) zzb.zza(parcel, zzaX, Account.CREATOR);
                    break;
                case 10:
                    zzcArr = (com.google.android.gms.common.zzc[]) zzb.zzb(parcel, zzaX, com.google.android.gms.common.zzc.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzj(i3, i2, i, str, iBinder, scopeArr, bundle, account, zzcArr);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzcU */
    public zzj[] newArray(int i) {
        return new zzj[i];
    }
}
