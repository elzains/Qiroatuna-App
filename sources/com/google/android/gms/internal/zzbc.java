package com.google.android.gms.internal;

import java.util.HashMap;

public class zzbc extends zzal<Integer, Long> {
    public Long zzql;
    public Long zzqm;

    public zzbc() {
    }

    public zzbc(String str) {
        zzk(str);
    }

    /* access modifiers changed from: protected */
    public HashMap<Integer, Long> zzN() {
        HashMap<Integer, Long> hashMap = new HashMap<>();
        hashMap.put(0, this.zzql);
        hashMap.put(1, this.zzqm);
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void zzk(String str) {
        HashMap zzl = zzl(str);
        if (zzl != null) {
            this.zzql = (Long) zzl.get(0);
            this.zzqm = (Long) zzl.get(1);
        }
    }
}
