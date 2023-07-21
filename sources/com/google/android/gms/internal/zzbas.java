package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzbas implements Parcelable.Creator<zzbar> {
    static void zza(zzbar zzbar, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzbar.zzaiI);
        zzc.zza(parcel, 2, (Parcelable) zzbar.getAccount(), i, false);
        zzc.zza(parcel, 3, (T[]) zzbar.zzPQ(), i, false);
        zzc.zza(parcel, 4, zzbar.getServerClientId(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzjw */
    public zzbar createFromParcel(Parcel parcel) {
        String zzq;
        Scope[] scopeArr;
        Account account;
        int i;
        String str = null;
        int zzaY = zzb.zzaY(parcel);
        int i2 = 0;
        Scope[] scopeArr2 = null;
        Account account2 = null;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    String str2 = str;
                    scopeArr = scopeArr2;
                    account = account2;
                    i = zzb.zzg(parcel, zzaX);
                    zzq = str2;
                    break;
                case 2:
                    i = i2;
                    Scope[] scopeArr3 = scopeArr2;
                    account = (Account) zzb.zza(parcel, zzaX, Account.CREATOR);
                    zzq = str;
                    scopeArr = scopeArr3;
                    break;
                case 3:
                    account = account2;
                    i = i2;
                    String str3 = str;
                    scopeArr = (Scope[]) zzb.zzb(parcel, zzaX, Scope.CREATOR);
                    zzq = str3;
                    break;
                case 4:
                    zzq = zzb.zzq(parcel, zzaX);
                    scopeArr = scopeArr2;
                    account = account2;
                    i = i2;
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    zzq = str;
                    scopeArr = scopeArr2;
                    account = account2;
                    i = i2;
                    break;
            }
            i2 = i;
            account2 = account;
            scopeArr2 = scopeArr;
            str = zzq;
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzbar(i2, account2, scopeArr2, str);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zznw */
    public zzbar[] newArray(int i) {
        return new zzbar[i];
    }
}
