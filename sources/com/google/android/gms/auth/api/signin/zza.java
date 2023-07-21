package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p000v4.view.MotionEventCompat;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;

public class zza implements Parcelable.Creator<GoogleSignInAccount> {
    static void zza(GoogleSignInAccount googleSignInAccount, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, googleSignInAccount.versionCode);
        zzc.zza(parcel, 2, googleSignInAccount.getId(), false);
        zzc.zza(parcel, 3, googleSignInAccount.getIdToken(), false);
        zzc.zza(parcel, 4, googleSignInAccount.getEmail(), false);
        zzc.zza(parcel, 5, googleSignInAccount.getDisplayName(), false);
        zzc.zza(parcel, 6, (Parcelable) googleSignInAccount.getPhotoUrl(), i, false);
        zzc.zza(parcel, 7, googleSignInAccount.getServerAuthCode(), false);
        zzc.zza(parcel, 8, googleSignInAccount.zzre());
        zzc.zza(parcel, 9, googleSignInAccount.zzrf(), false);
        zzc.zzc(parcel, 10, googleSignInAccount.zzaiN, false);
        zzc.zza(parcel, 11, googleSignInAccount.getGivenName(), false);
        zzc.zza(parcel, 12, googleSignInAccount.getFamilyName(), false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzW */
    public GoogleSignInAccount createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        String str5 = null;
        long j = 0;
        String str6 = null;
        ArrayList<Scope> arrayList = null;
        String str7 = null;
        String str8 = null;
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
                    str3 = zzb.zzq(parcel, zzaX);
                    break;
                case 5:
                    str4 = zzb.zzq(parcel, zzaX);
                    break;
                case 6:
                    uri = (Uri) zzb.zza(parcel, zzaX, Uri.CREATOR);
                    break;
                case 7:
                    str5 = zzb.zzq(parcel, zzaX);
                    break;
                case 8:
                    j = zzb.zzi(parcel, zzaX);
                    break;
                case 9:
                    str6 = zzb.zzq(parcel, zzaX);
                    break;
                case 10:
                    arrayList = zzb.zzc(parcel, zzaX, Scope.CREATOR);
                    break;
                case 11:
                    str7 = zzb.zzq(parcel, zzaX);
                    break;
                case MotionEventCompat.AXIS_RX:
                    str8 = zzb.zzq(parcel, zzaX);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new GoogleSignInAccount(i, str, str2, str3, str4, uri, str5, j, str6, arrayList, str7, str8);
        }
        throw new zzb.zza(new StringBuilder(37).append("Overread allowed size end=").append(zzaY).toString(), parcel);
    }

    /* renamed from: zzbl */
    public GoogleSignInAccount[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}
