package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.common.internal.safeparcel.zza;

@zzme
public class zzhc extends zza {
    public static final Parcelable.Creator<zzhc> CREATOR = new zzhd();
    public final int versionCode;
    public final boolean zzHa;
    public final int zzHb;
    public final boolean zzHc;
    public final int zzHd;
    @Nullable
    public final zzft zzHe;

    public zzhc(int i, boolean z, int i2, boolean z2, int i3, zzft zzft) {
        this.versionCode = i;
        this.zzHa = z;
        this.zzHb = i2;
        this.zzHc = z2;
        this.zzHd = i3;
        this.zzHe = zzft;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzhc(NativeAdOptions nativeAdOptions) {
        this(3, nativeAdOptions.shouldReturnUrlsForImageAssets(), nativeAdOptions.getImageOrientation(), nativeAdOptions.shouldRequestMultipleImages(), nativeAdOptions.getAdChoicesPlacement(), nativeAdOptions.getVideoOptions() != null ? new zzft(nativeAdOptions.getVideoOptions()) : null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzhd.zza(this, parcel, i);
    }
}
