package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzii implements zzid {
    final HashMap<String, zzqj<JSONObject>> zzIn = new HashMap<>();

    public void zza(zzqw zzqw, Map<String, String> map) {
        zzi(map.get("request_id"), map.get("fetched_ad"));
    }

    public Future<JSONObject> zzab(String str) {
        zzqj zzqj = new zzqj();
        this.zzIn.put(str, zzqj);
        return zzqj;
    }

    public void zzac(String str) {
        zzqj zzqj = this.zzIn.get(str);
        if (zzqj == null) {
            zzpk.m20e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        if (!zzqj.isDone()) {
            zzqj.cancel(true);
        }
        this.zzIn.remove(str);
    }

    public void zzi(String str, String str2) {
        zzpk.zzbf("Received ad from the cache.");
        zzqj zzqj = this.zzIn.get(str);
        if (zzqj == null) {
            zzpk.m20e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        try {
            zzqj.zzh(new JSONObject(str2));
        } catch (JSONException e) {
            zzpk.zzb("Failed constructing JSON object from value passed from javascript", e);
            zzqj.zzh(null);
        } finally {
            this.zzIn.remove(str);
        }
    }
}
