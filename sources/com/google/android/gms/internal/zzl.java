package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzn;
import com.google.android.gms.internal.zzt;
import java.util.Collections;
import java.util.Map;

public abstract class zzl<T> implements Comparable<zzl<T>> {
    /* access modifiers changed from: private */
    public final zzt.zza zzC;
    private final int zzD;
    private final String zzE;
    private final int zzF;
    private final zzn.zza zzG;
    private Integer zzH;
    private zzm zzI;
    private boolean zzJ;
    private boolean zzK;
    private boolean zzL;
    private boolean zzM;
    private zzp zzN;
    private zzb.zza zzO;

    public enum zza {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    public zzl(int i, String str, zzn.zza zza2) {
        this.zzC = zzt.zza.zzaj ? new zzt.zza() : null;
        this.zzJ = true;
        this.zzK = false;
        this.zzL = false;
        this.zzM = false;
        this.zzO = null;
        this.zzD = i;
        this.zzE = str;
        this.zzG = zza2;
        zza((zzp) new zze());
        this.zzF = zzb(str);
    }

    private static int zzb(String str) {
        Uri parse;
        String host;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null || (host = parse.getHost()) == null) {
            return 0;
        }
        return host.hashCode();
    }

    public Map<String, String> getHeaders() throws zza {
        return Collections.emptyMap();
    }

    public int getMethod() {
        return this.zzD;
    }

    public String getUrl() {
        return this.zzE;
    }

    public String toString() {
        return "[ ] " + getUrl() + " " + ("0x" + Integer.toHexString(zzf())) + " " + zzo() + " " + this.zzH;
    }

    public final zzl<?> zza(int i) {
        this.zzH = Integer.valueOf(i);
        return this;
    }

    public zzl<?> zza(zzb.zza zza2) {
        this.zzO = zza2;
        return this;
    }

    public zzl<?> zza(zzm zzm) {
        this.zzI = zzm;
        return this;
    }

    public zzl<?> zza(zzp zzp) {
        this.zzN = zzp;
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract zzn<T> zza(zzj zzj);

    /* access modifiers changed from: protected */
    public abstract void zza(T t);

    /* access modifiers changed from: protected */
    public zzs zzb(zzs zzs) {
        return zzs;
    }

    /* renamed from: zzc */
    public int compareTo(zzl<T> zzl) {
        zza zzo = zzo();
        zza zzo2 = zzl.zzo();
        return zzo == zzo2 ? this.zzH.intValue() - zzl.zzH.intValue() : zzo2.ordinal() - zzo.ordinal();
    }

    public void zzc(zzs zzs) {
        if (this.zzG != null) {
            this.zzG.zze(zzs);
        }
    }

    public void zzc(String str) {
        if (zzt.zza.zzaj) {
            this.zzC.zza(str, Thread.currentThread().getId());
        }
    }

    /* access modifiers changed from: package-private */
    public void zzd(final String str) {
        if (this.zzI != null) {
            this.zzI.zzf(this);
        }
        if (zzt.zza.zzaj) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        zzl.this.zzC.zza(str, id);
                        zzl.this.zzC.zzd(toString());
                    }
                });
                return;
            }
            this.zzC.zza(str, id);
            this.zzC.zzd(toString());
        }
    }

    public int zzf() {
        return this.zzF;
    }

    public String zzg() {
        return getUrl();
    }

    public zzb.zza zzh() {
        return this.zzO;
    }

    @Deprecated
    public String zzi() {
        return zzl();
    }

    @Deprecated
    public byte[] zzj() throws zza {
        return null;
    }

    /* access modifiers changed from: protected */
    public String zzk() {
        return "UTF-8";
    }

    public String zzl() {
        return "application/x-www-form-urlencoded; charset=" + zzk();
    }

    public byte[] zzm() throws zza {
        return null;
    }

    public final boolean zzn() {
        return this.zzJ;
    }

    public zza zzo() {
        return zza.NORMAL;
    }

    public final int zzp() {
        return this.zzN.zzc();
    }

    public zzp zzq() {
        return this.zzN;
    }

    public void zzr() {
        this.zzL = true;
    }

    public boolean zzs() {
        return this.zzL;
    }
}
