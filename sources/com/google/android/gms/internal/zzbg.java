package com.google.android.gms.internal;

import java.util.HashMap;

public class zzbg extends zzal<Integer, Long> {
    public Long zzqQ;

    public zzbg() {
    }

    public zzbg(String str) {
        zzk(str);
    }

    /* access modifiers changed from: protected */
    public HashMap<Integer, Long> zzN() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(0, this.zzqQ);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void zzk(String str) {
        HashMap zzl = zzl(str);
        if (zzl != null) {
            this.zzqQ = (Long) zzl.get(0);
        }
    }
}
