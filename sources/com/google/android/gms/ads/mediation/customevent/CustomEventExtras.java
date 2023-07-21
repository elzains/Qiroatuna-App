package com.google.android.gms.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

@Deprecated
public final class CustomEventExtras implements NetworkExtras {
    private final HashMap<String, Object> zzaaW = new HashMap<>();

    public Object getExtra(String str) {
        return this.zzaaW.get(str);
    }

    public void setExtra(String str, Object obj) {
        this.zzaaW.put(str, obj);
    }
}
