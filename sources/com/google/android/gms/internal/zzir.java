package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzw;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzme
public class zzir implements Iterable<zziq> {
    private final List<zziq> zzIz = new LinkedList();

    private zziq zzg(zzqw zzqw) {
        Iterator<zziq> it = zzw.zzdj().iterator();
        while (it.hasNext()) {
            zziq next = it.next();
            if (next.zzIs == zzqw) {
                return next;
            }
        }
        return null;
    }

    public Iterator<zziq> iterator() {
        return this.zzIz.iterator();
    }

    public void zza(zziq zziq) {
        this.zzIz.add(zziq);
    }

    public void zzb(zziq zziq) {
        this.zzIz.remove(zziq);
    }

    public boolean zze(zzqw zzqw) {
        zziq zzg = zzg(zzqw);
        if (zzg == null) {
            return false;
        }
        zzg.zzIw.abort();
        return true;
    }

    public boolean zzf(zzqw zzqw) {
        return zzg(zzqw) != null;
    }

    public int zzgr() {
        return this.zzIz.size();
    }
}
