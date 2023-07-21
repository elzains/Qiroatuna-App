package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzmf;
import com.google.android.gms.internal.zzmh;
import com.google.android.gms.internal.zzmk;
import com.google.android.gms.internal.zzpb;
import org.json.JSONException;
import org.json.JSONObject;

@zzme
public class zzmg extends zzpj implements zzmh.zza {
    private final Context mContext;
    zzjr zzKY;
    private zzmk zzLo;
    zzmn zzPS;
    /* access modifiers changed from: private */
    public Runnable zzPT;
    /* access modifiers changed from: private */
    public final Object zzPU = new Object();
    private final zzmf.zza zzRl;
    /* access modifiers changed from: private */
    public final zzmk.zza zzRm;
    zzpq zzRn;

    @zzme
    static final class zza extends Exception {
        private final int zzPY;

        public zza(String str, int i) {
            super(str);
            this.zzPY = i;
        }

        public int getErrorCode() {
            return this.zzPY;
        }
    }

    public zzmg(Context context, zzmk.zza zza2, zzmf.zza zza3) {
        this.zzRl = zza3;
        this.mContext = context;
        this.zzRm = zza2;
    }

    /* access modifiers changed from: private */
    public void zzd(int i, String str) {
        if (i == 3 || i == -1) {
            zzpk.zzbg(str);
        } else {
            zzpk.zzbh(str);
        }
        if (this.zzPS == null) {
            this.zzPS = new zzmn(i);
        } else {
            this.zzPS = new zzmn(i, this.zzPS.zzKL);
        }
        this.zzRl.zza(new zzpb.zza(this.zzLo != null ? this.zzLo : new zzmk(this.zzRm, -1, (String) null, (String) null, (String) null), this.zzPS, this.zzKY, (zzeg) null, i, -1, this.zzPS.zzSr, (JSONObject) null));
    }

    public void onStop() {
        synchronized (this.zzPU) {
            if (this.zzRn != null) {
                this.zzRn.cancel();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public zzpq zza(zzqh zzqh, zzqp<zzmk> zzqp) {
        return zzmh.zza(this.mContext, zzqh, zzqp, this);
    }

    public void zza(@NonNull zzmn zzmn) {
        JSONObject jSONObject;
        zzpk.zzbf("Received ad response.");
        this.zzPS = zzmn;
        long elapsedRealtime = zzw.zzcS().elapsedRealtime();
        synchronized (this.zzPU) {
            this.zzRn = null;
        }
        zzw.zzcQ().zzd(this.mContext, this.zzPS.zzRV);
        if (zzgd.zzDc.get().booleanValue()) {
            if (this.zzPS.zzSh) {
                zzw.zzcQ().zzk(this.mContext, this.zzLo.zzvl);
            } else {
                zzw.zzcQ().zzl(this.mContext, this.zzLo.zzvl);
            }
        }
        try {
            if (this.zzPS.errorCode == -2 || this.zzPS.errorCode == -3) {
                zzjm();
                zzeg zzb = this.zzLo.zzvr.zzzA != null ? zzb(this.zzLo) : null;
                zzw.zzcQ().zzF(this.zzPS.zzSx);
                zzw.zzcQ().zzG(this.zzPS.zzSK);
                if (!TextUtils.isEmpty(this.zzPS.zzSv)) {
                    try {
                        jSONObject = new JSONObject(this.zzPS.zzSv);
                    } catch (Exception e) {
                        zzpk.zzb("Error parsing the JSON for Active View.", e);
                    }
                    this.zzRl.zza(new zzpb.zza(this.zzLo, this.zzPS, this.zzKY, zzb, -2, elapsedRealtime, this.zzPS.zzSr, jSONObject));
                    zzpo.zzXC.removeCallbacks(this.zzPT);
                    return;
                }
                jSONObject = null;
                this.zzRl.zza(new zzpb.zza(this.zzLo, this.zzPS, this.zzKY, zzb, -2, elapsedRealtime, this.zzPS.zzSr, jSONObject));
                zzpo.zzXC.removeCallbacks(this.zzPT);
                return;
            }
            throw new zza(new StringBuilder(66).append("There was a problem getting an ad response. ErrorCode: ").append(this.zzPS.errorCode).toString(), this.zzPS.errorCode);
        } catch (zza e2) {
            zzd(e2.getErrorCode(), e2.getMessage());
            zzpo.zzXC.removeCallbacks(this.zzPT);
        }
    }

    /* access modifiers changed from: protected */
    public zzeg zzb(zzmk zzmk) throws zza {
        if (this.zzPS.zzzC) {
            for (zzeg zzeg : zzmk.zzvr.zzzA) {
                if (zzeg.zzzC) {
                    return new zzeg(zzeg, zzmk.zzvr.zzzA);
                }
            }
        }
        if (this.zzPS.zzSq == null) {
            throw new zza("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.zzPS.zzSq.split("x");
        if (split.length != 2) {
            String valueOf = String.valueOf(this.zzPS.zzSq);
            throw new zza(valueOf.length() != 0 ? "Invalid ad size format from the ad response: ".concat(valueOf) : new String("Invalid ad size format from the ad response: "), 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            for (zzeg zzeg2 : zzmk.zzvr.zzzA) {
                float f = this.mContext.getResources().getDisplayMetrics().density;
                int i = zzeg2.width == -1 ? (int) (((float) zzeg2.widthPixels) / f) : zzeg2.width;
                int i2 = zzeg2.height == -2 ? (int) (((float) zzeg2.heightPixels) / f) : zzeg2.height;
                if (parseInt == i && parseInt2 == i2 && !zzeg2.zzzC) {
                    return new zzeg(zzeg2, zzmk.zzvr.zzzA);
                }
            }
            String valueOf2 = String.valueOf(this.zzPS.zzSq);
            throw new zza(valueOf2.length() != 0 ? "The ad size from the ad response was not one of the requested sizes: ".concat(valueOf2) : new String("The ad size from the ad response was not one of the requested sizes: "), 0);
        } catch (NumberFormatException e) {
            String valueOf3 = String.valueOf(this.zzPS.zzSq);
            throw new zza(valueOf3.length() != 0 ? "Invalid ad size number from the ad response: ".concat(valueOf3) : new String("Invalid ad size number from the ad response: "), 0);
        }
    }

    public void zzco() {
        String string;
        zzpk.zzbf("AdLoaderBackgroundTask started.");
        this.zzPT = new Runnable() {
            public void run() {
                synchronized (zzmg.this.zzPU) {
                    if (zzmg.this.zzRn != null) {
                        zzmg.this.onStop();
                        zzmg.this.zzd(2, "Timed out waiting for ad response.");
                    }
                }
            }
        };
        zzpo.zzXC.postDelayed(this.zzPT, zzgd.zzDL.get().longValue());
        long elapsedRealtime = zzw.zzcS().elapsedRealtime();
        if (!zzgd.zzDK.get().booleanValue() || this.zzRm.zzRy.extras == null || (string = this.zzRm.zzRy.extras.getString("_ad")) == null) {
            final zzqq zzqq = new zzqq();
            zzpn.zza((Runnable) new Runnable() {
                public void run() {
                    synchronized (zzmg.this.zzPU) {
                        zzmg.this.zzRn = zzmg.this.zza(zzmg.this.zzRm.zzvn, zzqq);
                        if (zzmg.this.zzRn == null) {
                            zzmg.this.zzd(0, "Could not start the ad request service.");
                            zzpo.zzXC.removeCallbacks(zzmg.this.zzPT);
                        }
                    }
                }
            });
            String zzD = zzw.zzdl().zzD(this.mContext);
            String zzE = zzw.zzdl().zzE(this.mContext);
            String zzF = zzw.zzdl().zzF(this.mContext);
            zzw.zzdl().zzh(this.mContext, zzF);
            this.zzLo = new zzmk(this.zzRm, elapsedRealtime, zzD, zzE, zzF);
            zzqq.zzg(this.zzLo);
            return;
        }
        this.zzLo = new zzmk(this.zzRm, elapsedRealtime, (String) null, (String) null, (String) null);
        zza(zznd.zza(this.mContext, this.zzLo, string));
    }

    /* access modifiers changed from: protected */
    public void zzjm() throws zza {
        if (this.zzPS.errorCode != -3) {
            if (TextUtils.isEmpty(this.zzPS.body)) {
                throw new zza("No fill from ad server.", 3);
            }
            zzw.zzcQ().zzc(this.mContext, this.zzPS.zzRG);
            if (this.zzPS.zzSn) {
                try {
                    this.zzKY = new zzjr(this.zzPS.body);
                    zzw.zzcQ().zzH(this.zzKY.zzKJ);
                } catch (JSONException e) {
                    zzpk.zzb("Could not parse mediation config.", e);
                    String valueOf = String.valueOf(this.zzPS.body);
                    throw new zza(valueOf.length() != 0 ? "Could not parse mediation config: ".concat(valueOf) : new String("Could not parse mediation config: "), 0);
                }
            } else {
                zzw.zzcQ().zzH(this.zzPS.zzKJ);
            }
            if (!TextUtils.isEmpty(this.zzPS.zzRW) && zzgd.zzFb.get().booleanValue()) {
                zzpk.zzbf("Received cookie from server. Setting webview cookie in CookieManager.");
                CookieManager zzX = zzw.zzcO().zzX(this.mContext);
                if (zzX != null) {
                    zzX.setCookie("googleads.g.doubleclick.net", this.zzPS.zzRW);
                }
            }
        }
    }
}
