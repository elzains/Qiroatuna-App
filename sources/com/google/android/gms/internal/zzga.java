package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@zzme
public class zzga {
    private final Collection<zzfz> zzAZ = new ArrayList();
    private final Collection<zzfz<String>> zzBa = new ArrayList();
    private final Collection<zzfz<String>> zzBb = new ArrayList();

    public void zza(zzfz zzfz) {
        this.zzAZ.add(zzfz);
    }

    public void zzb(zzfz<String> zzfz) {
        this.zzBa.add(zzfz);
    }

    public void zzc(zzfz<String> zzfz) {
        this.zzBb.add(zzfz);
    }

    public List<String> zzfs() {
        ArrayList arrayList = new ArrayList();
        for (zzfz<String> zzfz : this.zzBa) {
            String str = (String) zzfz.get();
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public List<String> zzft() {
        List<String> zzfs = zzfs();
        for (zzfz<String> zzfz : this.zzBb) {
            String str = (String) zzfz.get();
            if (str != null) {
                zzfs.add(str);
            }
        }
        return zzfs;
    }
}
