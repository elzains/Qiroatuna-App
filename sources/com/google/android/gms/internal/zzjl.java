package com.google.android.gms.internal;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONObject;

@zzme
public class zzjl implements zzjk {
    private final zzjj zzKl;
    private final HashSet<AbstractMap.SimpleEntry<String, zzid>> zzKm = new HashSet<>();

    public zzjl(zzjj zzjj) {
        this.zzKl = zzjj;
    }

    public void zza(String str, zzid zzid) {
        this.zzKl.zza(str, zzid);
        this.zzKm.add(new AbstractMap.SimpleEntry(str, zzid));
    }

    public void zza(String str, JSONObject jSONObject) {
        this.zzKl.zza(str, jSONObject);
    }

    public void zzb(String str, zzid zzid) {
        this.zzKl.zzb(str, zzid);
        this.zzKm.remove(new AbstractMap.SimpleEntry(str, zzid));
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzKl.zzb(str, jSONObject);
    }

    public void zzgT() {
        Iterator<AbstractMap.SimpleEntry<String, zzid>> it = this.zzKm.iterator();
        while (it.hasNext()) {
            AbstractMap.SimpleEntry next = it.next();
            String valueOf = String.valueOf(((zzid) next.getValue()).toString());
            zzpk.m19v(valueOf.length() != 0 ? "Unregistering eventhandler: ".concat(valueOf) : new String("Unregistering eventhandler: "));
            this.zzKl.zzb((String) next.getKey(), (zzid) next.getValue());
        }
        this.zzKm.clear();
    }

    public void zzj(String str, String str2) {
        this.zzKl.zzj(str, str2);
    }
}
