package com.google.android.gms.internal;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.internal.zznm;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

@zzme
public class zzna {
    @Nullable
    public String zzRA;
    @Nullable
    public Bundle zzRF;
    @Nullable
    public List<String> zzRM = new ArrayList();
    @Nullable
    public Bundle zzTf;
    @Nullable
    public zznm.zza zzTg;
    @Nullable
    public String zzTh;
    public zzmk zzTi;
    public zzni zzTj;
    public JSONObject zzTk = new JSONObject();
    @Nullable
    public AdvertisingIdClient.Info zzpR;
    @Nullable
    public Location zzzb;

    public zzna zza(zzni zzni) {
        this.zzTj = zzni;
        return this;
    }

    public zzna zza(zznm.zza zza) {
        this.zzTg = zza;
        return this;
    }

    public zzna zzaK(String str) {
        this.zzRA = str;
        return this;
    }

    public zzna zzaL(String str) {
        this.zzTh = str;
        return this;
    }

    public zzna zzb(AdvertisingIdClient.Info info) {
        this.zzpR = info;
        return this;
    }

    public zzna zzc(Location location) {
        this.zzzb = location;
        return this;
    }

    public zzna zze(Bundle bundle) {
        this.zzTf = bundle;
        return this;
    }

    public zzna zzf(Bundle bundle) {
        this.zzRF = bundle;
        return this;
    }

    public zzna zzf(zzmk zzmk) {
        this.zzTi = zzmk;
        return this;
    }

    public zzna zzg(JSONObject jSONObject) {
        this.zzTk = jSONObject;
        return this;
    }

    public zzna zzk(List<String> list) {
        if (list == null) {
            this.zzRM.clear();
        }
        this.zzRM = list;
        return this;
    }
}
