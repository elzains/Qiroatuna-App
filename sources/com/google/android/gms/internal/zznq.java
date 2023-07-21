package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.internal.zznt;

@zzme
public class zznq extends zznt.zza {
    private final String zzUA;
    private final int zzVi;

    public zznq(String str, int i) {
        this.zzUA = str;
        this.zzVi = i;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zznq)) {
            return false;
        }
        zznq zznq = (zznq) obj;
        return zzaa.equal(getType(), zznq.getType()) && zzaa.equal(Integer.valueOf(getAmount()), Integer.valueOf(zznq.getAmount()));
    }

    public int getAmount() {
        return this.zzVi;
    }

    public String getType() {
        return this.zzUA;
    }
}
