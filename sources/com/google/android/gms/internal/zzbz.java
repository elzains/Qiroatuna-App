package com.google.android.gms.internal;

import com.google.android.gms.internal.zzay;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzbz {
    protected static final String TAG = zzbz.class.getSimpleName();
    private final String className;
    private final zzbd zzpz;
    private final String zzrc;
    private final int zzrd = 2;
    private volatile Method zzre = null;
    private List<Class> zzrf;
    private CountDownLatch zzrg = new CountDownLatch(1);

    public zzbz(zzbd zzbd, String str, String str2, List<Class> list) {
        this.zzpz = zzbd;
        this.className = str;
        this.zzrc = str2;
        this.zzrf = new ArrayList(list);
        this.zzpz.zzaI().submit(new Runnable() {
            public void run() {
                zzbz.this.zzbl();
            }
        });
    }

    /* access modifiers changed from: private */
    public void zzbl() {
        try {
            Class loadClass = this.zzpz.zzaJ().loadClass(zzd(this.zzpz.zzaL(), this.className));
            if (loadClass != null) {
                this.zzre = loadClass.getMethod(zzd(this.zzpz.zzaL(), this.zzrc), (Class[]) this.zzrf.toArray(new Class[this.zzrf.size()]));
                if (this.zzre == null) {
                    this.zzrg.countDown();
                } else {
                    this.zzrg.countDown();
                }
            }
        } catch (zzay.zza e) {
        } catch (UnsupportedEncodingException e2) {
        } catch (ClassNotFoundException e3) {
        } catch (NoSuchMethodException e4) {
        } catch (NullPointerException e5) {
        } finally {
            this.zzrg.countDown();
        }
    }

    private String zzd(byte[] bArr, String str) throws zzay.zza, UnsupportedEncodingException {
        return new String(this.zzpz.zzaK().zzc(bArr, str), "UTF-8");
    }

    public Method zzbm() {
        if (this.zzre != null) {
            return this.zzre;
        }
        try {
            if (this.zzrg.await(2, TimeUnit.SECONDS)) {
                return this.zzre;
            }
            return null;
        } catch (InterruptedException e) {
            return null;
        }
    }
}
