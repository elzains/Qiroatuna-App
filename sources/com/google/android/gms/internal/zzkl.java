package com.google.android.gms.internal;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zzme
public final class zzkl implements NativeMediationAdRequest {
    private final int zzLB;
    private final Date zzcR;
    private final Set<String> zzcT;
    private final boolean zzcU;
    private final Location zzcV;
    private final zzhc zztp;
    private final List<String> zztq;
    private final int zzzk;
    private final boolean zzzw;

    public zzkl(@Nullable Date date, int i, @Nullable Set<String> set, @Nullable Location location, boolean z, int i2, zzhc zzhc, List<String> list, boolean z2) {
        this.zzcR = date;
        this.zzzk = i;
        this.zzcT = set;
        this.zzcV = location;
        this.zzcU = z;
        this.zzLB = i2;
        this.zztp = zzhc;
        this.zztq = list;
        this.zzzw = z2;
    }

    public Date getBirthday() {
        return this.zzcR;
    }

    public int getGender() {
        return this.zzzk;
    }

    public Set<String> getKeywords() {
        return this.zzcT;
    }

    public Location getLocation() {
        return this.zzcV;
    }

    public NativeAdOptions getNativeAdOptions() {
        if (this.zztp == null) {
            return null;
        }
        NativeAdOptions.Builder requestMultipleImages = new NativeAdOptions.Builder().setReturnUrlsForImageAssets(this.zztp.zzHa).setImageOrientation(this.zztp.zzHb).setRequestMultipleImages(this.zztp.zzHc);
        if (this.zztp.versionCode >= 2) {
            requestMultipleImages.setAdChoicesPlacement(this.zztp.zzHd);
        }
        if (this.zztp.versionCode >= 3 && this.zztp.zzHe != null) {
            requestMultipleImages.setVideoOptions(new VideoOptions.Builder().setStartMuted(this.zztp.zzHe.zzAU).build());
        }
        return requestMultipleImages.build();
    }

    public boolean isAppInstallAdRequested() {
        return this.zztq != null && this.zztq.contains("2");
    }

    public boolean isContentAdRequested() {
        return this.zztq != null && this.zztq.contains("1");
    }

    public boolean isDesignedForFamilies() {
        return this.zzzw;
    }

    public boolean isTesting() {
        return this.zzcU;
    }

    public int taggedForChildDirectedTreatment() {
        return this.zzLB;
    }
}
