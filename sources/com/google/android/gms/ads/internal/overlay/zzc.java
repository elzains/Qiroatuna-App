package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.zzme;

@zzme
public final class zzc extends zza {
    public static final Parcelable.Creator<zzc> CREATOR = new zzb();
    public final Intent intent;
    public final String mimeType;
    public final String packageName;
    public final String url;
    public final String zzMG;
    public final String zzMH;
    public final String zzMI;
    public final String zzMJ;

    public zzc(Intent intent2) {
        this((String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, intent2);
    }

    public zzc(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this(str, str2, str3, str4, str5, str6, str7, (Intent) null);
    }

    public zzc(String str, String str2, String str3, String str4, String str5, String str6, String str7, Intent intent2) {
        this.zzMG = str;
        this.url = str2;
        this.mimeType = str3;
        this.packageName = str4;
        this.zzMH = str5;
        this.zzMI = str6;
        this.zzMJ = str7;
        this.intent = intent2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}
