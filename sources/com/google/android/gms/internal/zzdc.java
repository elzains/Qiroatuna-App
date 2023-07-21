package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.support.p000v4.widget.ExploreByTouchHelper;
import com.google.android.gms.ads.internal.zzw;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzme
public class zzdc {
    private final Object zzrJ = new Object();
    private int zzxE;
    private List<zzdb> zzxF = new LinkedList();

    public boolean zza(zzdb zzdb) {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzxF.contains(zzdb);
        }
        return z;
    }

    public boolean zzb(zzdb zzdb) {
        synchronized (this.zzrJ) {
            Iterator<zzdb> it = this.zzxF.iterator();
            while (it.hasNext()) {
                zzdb next = it.next();
                if (!zzgd.zzCi.get().booleanValue() || zzw.zzcQ().zzkg()) {
                    if (zzgd.zzCk.get().booleanValue() && !zzw.zzcQ().zzkh() && zzdb != next && next.zzec().equals(zzdb.zzec())) {
                        it.remove();
                        return true;
                    }
                } else if (zzdb != next && next.zzea().equals(zzdb.zzea())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public void zzc(zzdb zzdb) {
        synchronized (this.zzrJ) {
            if (this.zzxF.size() >= 10) {
                zzpk.zzbf(new StringBuilder(41).append("Queue is full, current size = ").append(this.zzxF.size()).toString());
                this.zzxF.remove(0);
            }
            int i = this.zzxE;
            this.zzxE = i + 1;
            zzdb.zzn(i);
            this.zzxF.add(zzdb);
        }
    }

    @Nullable
    public zzdb zzei() {
        int i;
        zzdb zzdb;
        int i2;
        zzdb zzdb2 = null;
        int i3 = 0;
        synchronized (this.zzrJ) {
            if (this.zzxF.size() == 0) {
                zzpk.zzbf("Queue empty");
                return null;
            } else if (this.zzxF.size() >= 2) {
                int i4 = ExploreByTouchHelper.INVALID_ID;
                int i5 = 0;
                for (zzdb next : this.zzxF) {
                    int score = next.getScore();
                    if (score > i4) {
                        i2 = score;
                        zzdb = next;
                        i = i5;
                    } else {
                        i = i3;
                        zzdb = zzdb2;
                        i2 = i4;
                    }
                    i5++;
                    i4 = i2;
                    zzdb2 = zzdb;
                    i3 = i;
                }
                this.zzxF.remove(i3);
                return zzdb2;
            } else {
                zzdb zzdb3 = this.zzxF.get(0);
                zzdb3.zzed();
                return zzdb3;
            }
        }
    }
}
