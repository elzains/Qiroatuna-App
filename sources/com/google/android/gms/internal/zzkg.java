package com.google.android.gms.internal;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.Set;

@zzme
public final class zzkg implements MediationAdRequest {
    private final int zzLB;
    private final Date zzcR;
    private final Set<String> zzcT;
    private final boolean zzcU;
    private final Location zzcV;
    private final int zzzk;
    private final boolean zzzw;

    public zzkg(@Nullable Date date, int i, @Nullable Set<String> set, @Nullable Location location, boolean z, int i2, boolean z2) {
        this.zzcR = date;
        this.zzzk = i;
        this.zzcT = set;
        this.zzcV = location;
        this.zzcU = z;
        this.zzLB = i2;
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
