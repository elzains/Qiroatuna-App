package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.zzt;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.zzdd;
import com.google.android.gms.internal.zzpm;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@zzme
public class zzpe implements zzdd.zzb, zzpm.zzb {
    private Context mContext;
    private String zzIA;
    private boolean zzUe = true;
    private boolean zzUf = true;
    private boolean zzUg = true;
    private boolean zzUo = false;
    private final String zzWD;
    private final zzpf zzWE;
    private BigInteger zzWF = BigInteger.ONE;
    private final HashSet<zzpc> zzWG = new HashSet<>();
    private final HashMap<String, zzph> zzWH = new HashMap<>();
    private boolean zzWI = false;
    private int zzWJ = 0;
    private zzgf zzWK = null;
    private zzde zzWL = null;
    private String zzWM;
    private String zzWN;
    private Boolean zzWO = null;
    private boolean zzWP = false;
    private boolean zzWQ = false;
    private boolean zzWR = false;
    private String zzWS = "";
    private long zzWT = 0;
    private long zzWU = 0;
    private long zzWV = 0;
    private int zzWW = -1;
    private final AtomicInteger zzWX = new AtomicInteger(0);
    private final Object zzrJ = new Object();
    private zzcs zzsu;
    private boolean zztZ = false;
    private zzqh zztt;
    private zzdc zzxP = null;

    public zzpe(zzpo zzpo) {
        this.zzWD = zzpo.zzkM();
        this.zzWE = new zzpf(this.zzWD);
    }

    public Resources getResources() {
        if (this.zztt.zzYY) {
            return this.mContext.getResources();
        }
        try {
            DynamiteModule zza = DynamiteModule.zza(this.mContext, DynamiteModule.zzaRU, ModuleDescriptor.MODULE_ID);
            if (zza != null) {
                return zza.zzBR().getResources();
            }
            return null;
        } catch (DynamiteModule.zza e) {
            zzpk.zzc("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public String getSessionId() {
        return this.zzWD;
    }

    public void zzF(boolean z) {
        synchronized (this.zzrJ) {
            if (this.zzUf != z) {
                zzpm.zze(this.mContext, z);
            }
            this.zzUf = z;
            zzde zzH = zzH(this.mContext);
            if (zzH != null && !zzH.isAlive()) {
                zzpk.zzbg("start fetching content...");
                zzH.zzej();
            }
        }
    }

    public void zzG(boolean z) {
        synchronized (this.zzrJ) {
            if (this.zzUg != z) {
                zzpm.zze(this.mContext, z);
            }
            zzpm.zze(this.mContext, z);
            this.zzUg = z;
            zzde zzH = zzH(this.mContext);
            if (zzH != null && !zzH.isAlive()) {
                zzpk.zzbg("start fetching content...");
                zzH.zzej();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzde zzH(android.content.Context r6) {
        /*
            r5 = this;
            r1 = 0
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r0 = com.google.android.gms.internal.zzgd.zzCc
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0011
            r0 = r1
        L_0x0010:
            return r0
        L_0x0011:
            com.google.android.gms.common.util.zzt.zzzg()
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r0 = com.google.android.gms.internal.zzgd.zzCk
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0032
            com.google.android.gms.internal.zzfz<java.lang.Boolean> r0 = com.google.android.gms.internal.zzgd.zzCi
            java.lang.Object r0 = r0.get()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0032
            r0 = r1
            goto L_0x0010
        L_0x0032:
            boolean r0 = r5.zzkg()
            if (r0 == 0) goto L_0x0040
            boolean r0 = r5.zzkh()
            if (r0 == 0) goto L_0x0040
            r0 = r1
            goto L_0x0010
        L_0x0040:
            java.lang.Object r2 = r5.zzrJ
            monitor-enter(r2)
            android.os.Looper r0 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x0077 }
            if (r0 == 0) goto L_0x004b
            if (r6 != 0) goto L_0x004e
        L_0x004b:
            monitor-exit(r2)     // Catch:{ all -> 0x0077 }
            r0 = r1
            goto L_0x0010
        L_0x004e:
            com.google.android.gms.internal.zzdc r0 = r5.zzxP     // Catch:{ all -> 0x0077 }
            if (r0 != 0) goto L_0x0059
            com.google.android.gms.internal.zzdc r0 = new com.google.android.gms.internal.zzdc     // Catch:{ all -> 0x0077 }
            r0.<init>()     // Catch:{ all -> 0x0077 }
            r5.zzxP = r0     // Catch:{ all -> 0x0077 }
        L_0x0059:
            com.google.android.gms.internal.zzde r0 = r5.zzWL     // Catch:{ all -> 0x0077 }
            if (r0 != 0) goto L_0x006e
            com.google.android.gms.internal.zzde r0 = new com.google.android.gms.internal.zzde     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.zzdc r1 = r5.zzxP     // Catch:{ all -> 0x0077 }
            android.content.Context r3 = r5.mContext     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.zzqh r4 = r5.zztt     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.zzmd r3 = com.google.android.gms.internal.zzmc.zzb(r3, r4)     // Catch:{ all -> 0x0077 }
            r0.<init>(r1, r3)     // Catch:{ all -> 0x0077 }
            r5.zzWL = r0     // Catch:{ all -> 0x0077 }
        L_0x006e:
            com.google.android.gms.internal.zzde r0 = r5.zzWL     // Catch:{ all -> 0x0077 }
            r0.zzej()     // Catch:{ all -> 0x0077 }
            com.google.android.gms.internal.zzde r0 = r5.zzWL     // Catch:{ all -> 0x0077 }
            monitor-exit(r2)     // Catch:{ all -> 0x0077 }
            goto L_0x0010
        L_0x0077:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0077 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzpe.zzH(android.content.Context):com.google.android.gms.internal.zzde");
    }

    public void zzH(boolean z) {
        this.zzWR = z;
    }

    public void zzI(boolean z) {
        synchronized (this.zzrJ) {
            this.zzWP = z;
        }
    }

    public Bundle zza(Context context, zzpg zzpg, String str) {
        Bundle bundle;
        synchronized (this.zzrJ) {
            bundle = new Bundle();
            bundle.putBundle("app", this.zzWE.zzo(context, str));
            Bundle bundle2 = new Bundle();
            for (String next : this.zzWH.keySet()) {
                bundle2.putBundle(next, this.zzWH.get(next).toBundle());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList arrayList = new ArrayList();
            Iterator<zzpc> it = this.zzWG.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toBundle());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            zzpg.zza(this.zzWG);
            this.zzWG.clear();
        }
        return bundle;
    }

    public void zza(zzpc zzpc) {
        synchronized (this.zzrJ) {
            this.zzWG.add(zzpc);
        }
    }

    public void zza(Boolean bool) {
        synchronized (this.zzrJ) {
            this.zzWO = bool;
        }
    }

    public void zza(String str, zzph zzph) {
        synchronized (this.zzrJ) {
            this.zzWH.put(str, zzph);
        }
    }

    public void zza(Throwable th, String str) {
        zzmc.zzb(this.mContext, this.zztt).zza(th, str);
    }

    public Future zzaU(String str) {
        Future future;
        synchronized (this.zzrJ) {
            if (str != null) {
                if (!str.equals(this.zzWM)) {
                    this.zzWM = str;
                    future = zzpm.zzp(this.mContext, str);
                }
            }
            future = null;
        }
        return future;
    }

    public Future zzaV(String str) {
        Future future;
        synchronized (this.zzrJ) {
            if (str != null) {
                if (!str.equals(this.zzWN)) {
                    this.zzWN = str;
                    future = zzpm.zzq(this.mContext, str);
                }
            }
            future = null;
        }
        return future;
    }

    /* access modifiers changed from: package-private */
    public Future zzah(int i) {
        Future zza;
        synchronized (this.zzrJ) {
            this.zzWW = i;
            zza = zzpm.zza(this.mContext, i);
        }
        return zza;
    }

    public void zzb(HashSet<zzpc> hashSet) {
        synchronized (this.zzrJ) {
            this.zzWG.addAll(hashSet);
        }
    }

    public Future zzc(Context context, boolean z) {
        Future future;
        synchronized (this.zzrJ) {
            if (z != this.zzUe) {
                this.zzUe = z;
                future = zzpm.zzc(context, z);
            } else {
                future = null;
            }
        }
        return future;
    }

    @TargetApi(23)
    public void zzc(Context context, zzqh zzqh) {
        synchronized (this.zzrJ) {
            if (!this.zztZ) {
                this.mContext = context.getApplicationContext();
                this.zztt = zzqh;
                zzw.zzcP().zza(this);
                zzpm.zza(context, (zzpm.zzb) this);
                zzpm.zzb(context, (zzpm.zzb) this);
                zzpm.zzc(context, (zzpm.zzb) this);
                zzpm.zzd(context, this);
                zzpm.zze(context, (zzpm.zzb) this);
                zzpm.zzf(context, (zzpm.zzb) this);
                zzpm.zzg(context, this);
                zzpm.zzh(context, this);
                zzpm.zzi(context, this);
                zzky();
                this.zzIA = zzw.zzcM().zzu(context, zzqh.zzba);
                if (zzt.zzzp() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
                    this.zzWQ = true;
                }
                this.zzsu = new zzcs(context.getApplicationContext(), this.zztt, zzw.zzcM().zzd(context, zzqh));
                zzkA();
                zzw.zzda().zzv(this.mContext);
                this.zztZ = true;
            }
        }
    }

    public Future zzd(Context context, boolean z) {
        Future future;
        synchronized (this.zzrJ) {
            if (z != this.zzUo) {
                this.zzUo = z;
                future = zzpm.zzf(context, z);
            } else {
                future = null;
            }
        }
        return future;
    }

    public void zzh(Bundle bundle) {
        synchronized (this.zzrJ) {
            this.zzUe = bundle.getBoolean("use_https", this.zzUe);
            this.zzWJ = bundle.getInt("webview_cache_version", this.zzWJ);
            if (bundle.containsKey("content_url_opted_out")) {
                zzF(bundle.getBoolean("content_url_opted_out"));
            }
            if (bundle.containsKey("content_url_hashes")) {
                this.zzWM = bundle.getString("content_url_hashes");
            }
            this.zzUo = bundle.getBoolean("auto_collect_location", this.zzUo);
            if (bundle.containsKey("content_vertical_opted_out")) {
                zzG(bundle.getBoolean("content_vertical_opted_out"));
            }
            if (bundle.containsKey("content_vertical_hashes")) {
                this.zzWN = bundle.getString("content_vertical_hashes");
            }
            this.zzWS = bundle.containsKey("app_settings_json") ? bundle.getString("app_settings_json") : this.zzWS;
            this.zzWT = bundle.getLong("app_settings_last_update_ms", this.zzWT);
            this.zzWU = bundle.getLong("app_last_background_time_ms", this.zzWU);
            this.zzWW = bundle.getInt("request_in_session_count", this.zzWW);
            this.zzWV = bundle.getLong("first_ad_req_time_ms", this.zzWV);
        }
    }

    public void zzk(Context context, String str) {
        zzpm.zzr(context, str);
    }

    public void zzk(boolean z) {
        long currentTimeMillis = zzw.zzcS().currentTimeMillis();
        if (!z) {
            zzo(currentTimeMillis);
            zzah(this.zzWE.zzku());
        } else if (currentTimeMillis - this.zzWU > zzgd.zzCR.get().longValue()) {
            this.zzWE.zzai(-1);
        } else {
            this.zzWE.zzai(this.zzWW);
        }
    }

    /* access modifiers changed from: package-private */
    public void zzkA() {
        try {
            this.zzWK = zzw.zzcT().zza(new zzge(this.mContext, this.zztt.zzba));
        } catch (IllegalArgumentException e) {
            zzpk.zzc("Cannot initialize CSI reporter.", e);
        }
    }

    public void zzkB() {
        this.zzWX.incrementAndGet();
    }

    public void zzkC() {
        this.zzWX.decrementAndGet();
    }

    public int zzkD() {
        return this.zzWX.get();
    }

    public boolean zzkg() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzUf;
        }
        return z;
    }

    public boolean zzkh() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzUg;
        }
        return z;
    }

    public String zzki() {
        String bigInteger;
        synchronized (this.zzrJ) {
            bigInteger = this.zzWF.toString();
            this.zzWF = this.zzWF.add(BigInteger.ONE);
        }
        return bigInteger;
    }

    public zzpf zzkj() {
        zzpf zzpf;
        synchronized (this.zzrJ) {
            zzpf = this.zzWE;
        }
        return zzpf;
    }

    public zzgf zzkk() {
        zzgf zzgf;
        synchronized (this.zzrJ) {
            zzgf = this.zzWK;
        }
        return zzgf;
    }

    public boolean zzkl() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzWI;
            this.zzWI = true;
        }
        return z;
    }

    public boolean zzkm() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzUe || this.zzWQ;
        }
        return z;
    }

    public String zzkn() {
        String str;
        synchronized (this.zzrJ) {
            str = this.zzIA;
        }
        return str;
    }

    public String zzko() {
        String str;
        synchronized (this.zzrJ) {
            str = this.zzWM;
        }
        return str;
    }

    public String zzkp() {
        String str;
        synchronized (this.zzrJ) {
            str = this.zzWN;
        }
        return str;
    }

    public Boolean zzkq() {
        Boolean bool;
        synchronized (this.zzrJ) {
            bool = this.zzWO;
        }
        return bool;
    }

    public boolean zzkr() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzUo;
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public long zzks() {
        long j;
        synchronized (this.zzrJ) {
            j = this.zzWU;
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public long zzkt() {
        long j;
        synchronized (this.zzrJ) {
            j = this.zzWV;
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public int zzku() {
        int i;
        synchronized (this.zzrJ) {
            i = this.zzWW;
        }
        return i;
    }

    public boolean zzkv() {
        return this.zzWR;
    }

    public zzpd zzkw() {
        zzpd zzpd;
        synchronized (this.zzrJ) {
            zzpd = new zzpd(this.zzWS, this.zzWT);
        }
        return zzpd;
    }

    public zzcs zzkx() {
        return this.zzsu;
    }

    public void zzky() {
        zzmc.zzb(this.mContext, this.zztt);
    }

    public boolean zzkz() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzWP;
        }
        return z;
    }

    public void zzl(Context context, String str) {
        zzpm.zzs(context, str);
    }

    public boolean zzm(Context context, String str) {
        return zzpm.zzt(context, str);
    }

    public Future zzn(Context context, String str) {
        Future future;
        this.zzWT = zzw.zzcS().currentTimeMillis();
        synchronized (this.zzrJ) {
            if (str != null) {
                if (!str.equals(this.zzWS)) {
                    this.zzWS = str;
                    future = zzpm.zza(context, str, this.zzWT);
                }
            }
            future = null;
        }
        return future;
    }

    /* access modifiers changed from: package-private */
    public Future zzo(long j) {
        Future zza;
        synchronized (this.zzrJ) {
            this.zzWU = j;
            zza = zzpm.zza(this.mContext, j);
        }
        return zza;
    }

    /* access modifiers changed from: package-private */
    public Future zzp(long j) {
        Future zzb;
        synchronized (this.zzrJ) {
            this.zzWV = j;
            zzb = zzpm.zzb(this.mContext, j);
        }
        return zzb;
    }
}
