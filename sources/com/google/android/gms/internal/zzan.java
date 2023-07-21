package com.google.android.gms.internal;

import java.util.HashMap;

public class zzan extends zzal<Integer, Object> {
    public String zzaN;
    public String zzaP;
    public String zzaQ;
    public String zzaR;
    public long zzlE;

    public zzan() {
        this.zzaN = "E";
        this.zzlE = -1;
        this.zzaP = "E";
        this.zzaQ = "E";
        this.zzaR = "E";
    }

    public zzan(String str) {
        this();
        zzk(str);
    }

    /* access modifiers changed from: protected */
    public HashMap<Integer, Object> zzN() {
        HashMap<Integer, Object> hashMap = new HashMap<>();
        hashMap.put(0, this.zzaN);
        hashMap.put(4, this.zzaR);
        hashMap.put(3, this.zzaQ);
        hashMap.put(2, this.zzaP);
        hashMap.put(1, Long.valueOf(this.zzlE));
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void zzk(String str) {
        HashMap zzl = zzl(str);
        if (zzl != null) {
            this.zzaN = zzl.get(0) == null ? "E" : (String) zzl.get(0);
            this.zzlE = zzl.get(1) == null ? -1 : ((Long) zzl.get(1)).longValue();
            this.zzaP = zzl.get(2) == null ? "E" : (String) zzl.get(2);
            this.zzaQ = zzl.get(3) == null ? "E" : (String) zzl.get(3);
            this.zzaR = zzl.get(4) == null ? "E" : (String) zzl.get(4);
        }
    }
}
