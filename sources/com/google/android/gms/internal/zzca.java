package com.google.android.gms.internal;

import com.google.android.gms.internal.zzag;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzca implements Callable {
    protected final String TAG = getClass().getSimpleName();
    protected final String className;
    protected final zzbd zzpz;
    protected final zzag.zza zzqV;
    protected final String zzrc;
    protected Method zzre;
    protected final int zzri;
    protected final int zzrj;

    public zzca(zzbd zzbd, String str, String str2, zzag.zza zza, int i, int i2) {
        this.zzpz = zzbd;
        this.className = str;
        this.zzrc = str2;
        this.zzqV = zza;
        this.zzri = i;
        this.zzrj = i2;
    }

    /* access modifiers changed from: protected */
    public abstract void zzbd() throws IllegalAccessException, InvocationTargetException;

    /* renamed from: zzbk */
    public Void call() throws Exception {
        try {
            long nanoTime = System.nanoTime();
            this.zzre = this.zzpz.zzc(this.className, this.zzrc);
            if (this.zzre != null) {
                zzbd();
                zzaq zzaP = this.zzpz.zzaP();
                if (!(zzaP == null || this.zzri == Integer.MIN_VALUE)) {
                    zzaP.zza(this.zzrj, this.zzri, (System.nanoTime() - nanoTime) / 1000);
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
        }
        return null;
    }
}
