package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzm;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;
import java.util.LinkedList;

@zzme
class zzjb {
    private final LinkedList<zza> zzJd = new LinkedList<>();
    /* access modifiers changed from: private */
    public zzec zzJe;
    private final int zzJf;
    private boolean zzJg;
    /* access modifiers changed from: private */
    public final String zzts;

    class zza {
        zzm zzJh;
        @Nullable
        zzec zzJi;
        zzix zzJj;
        long zzJk;
        boolean zzJl;
        boolean zzJm;

        zza(zziw zziw) {
            this.zzJh = zziw.zzah(zzjb.this.zzts);
            this.zzJj = new zzix();
            this.zzJj.zzc(this.zzJh);
        }

        zza(zzjb zzjb, zziw zziw, zzec zzec) {
            this(zziw);
            this.zzJi = zzec;
        }

        /* access modifiers changed from: package-private */
        public boolean load() {
            if (this.zzJl) {
                return false;
            }
            this.zzJm = this.zzJh.zzb(zziz.zzk(this.zzJi != null ? this.zzJi : zzjb.this.zzJe));
            this.zzJl = true;
            this.zzJk = zzw.zzcS().currentTimeMillis();
            return true;
        }
    }

    zzjb(zzec zzec, String str, int i) {
        zzac.zzw(zzec);
        zzac.zzw(str);
        this.zzJe = zzec;
        this.zzts = str;
        this.zzJf = i;
    }

    /* access modifiers changed from: package-private */
    public String getAdUnitId() {
        return this.zzts;
    }

    /* access modifiers changed from: package-private */
    public int getNetworkType() {
        return this.zzJf;
    }

    /* access modifiers changed from: package-private */
    public int size() {
        return this.zzJd.size();
    }

    /* access modifiers changed from: package-private */
    public void zza(zziw zziw, zzec zzec) {
        this.zzJd.add(new zza(this, zziw, zzec));
    }

    /* access modifiers changed from: package-private */
    public boolean zzb(zziw zziw) {
        zza zza2 = new zza(zziw);
        this.zzJd.add(zza2);
        return zza2.load();
    }

    /* access modifiers changed from: package-private */
    public void zzgA() {
        this.zzJg = true;
    }

    /* access modifiers changed from: package-private */
    public boolean zzgB() {
        return this.zzJg;
    }

    /* access modifiers changed from: package-private */
    public zzec zzgx() {
        return this.zzJe;
    }

    /* access modifiers changed from: package-private */
    public int zzgy() {
        int i = 0;
        Iterator it = this.zzJd.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = ((zza) it.next()).zzJl ? i2 + 1 : i2;
        }
    }

    /* access modifiers changed from: package-private */
    public int zzgz() {
        int i = 0;
        Iterator it = this.zzJd.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            i = ((zza) it.next()).load() ? i2 + 1 : i2;
        }
    }

    /* access modifiers changed from: package-private */
    public zza zzo(@Nullable zzec zzec) {
        if (zzec != null) {
            this.zzJe = zzec;
        }
        return this.zzJd.remove();
    }
}
