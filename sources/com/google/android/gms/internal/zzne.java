package com.google.android.gms.internal;

import com.google.android.gms.internal.zzji;
import java.util.Map;
import java.util.concurrent.Future;

@zzme
public final class zzne {
    /* access modifiers changed from: private */
    public String zzOV;
    /* access modifiers changed from: private */
    public String zzTK;
    /* access modifiers changed from: private */
    public zzqj<zznh> zzTL = new zzqj<>();
    zzji.zzc zzTM;
    public final zzid zzTN = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            synchronized (zzne.this.zzrJ) {
                if (!zzne.this.zzTL.isDone()) {
                    if (zzne.this.zzOV.equals(map.get("request_id"))) {
                        zznh zznh = new zznh(1, map);
                        String valueOf = String.valueOf(zznh.getType());
                        String valueOf2 = String.valueOf(zznh.zzjx());
                        zzpk.zzbh(new StringBuilder(String.valueOf(valueOf).length() + 24 + String.valueOf(valueOf2).length()).append("Invalid ").append(valueOf).append(" request error: ").append(valueOf2).toString());
                        zzne.this.zzTL.zzh(zznh);
                    }
                }
            }
        }
    };
    public final zzid zzTO = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            synchronized (zzne.this.zzrJ) {
                if (!zzne.this.zzTL.isDone()) {
                    zznh zznh = new zznh(-2, map);
                    if (zzne.this.zzOV.equals(zznh.getRequestId())) {
                        String url = zznh.getUrl();
                        if (url == null) {
                            zzpk.zzbh("URL missing in loadAdUrl GMSG.");
                            return;
                        }
                        if (url.contains("%40mediation_adapters%40")) {
                            String replaceAll = url.replaceAll("%40mediation_adapters%40", zzpi.zzc(zzqw.getContext(), map.get("check_adapters"), zzne.this.zzTK));
                            zznh.setUrl(replaceAll);
                            String valueOf = String.valueOf(replaceAll);
                            zzpk.m19v(valueOf.length() != 0 ? "Ad request URL modified to ".concat(valueOf) : new String("Ad request URL modified to "));
                        }
                        zzne.this.zzTL.zzh(zznh);
                    }
                }
            }
        }
    };
    public final zzid zzTP = new zzid() {
        public void zza(zzqw zzqw, Map<String, String> map) {
            synchronized (zzne.this.zzrJ) {
                if (!zzne.this.zzTL.isDone()) {
                    zznh zznh = new zznh(-2, map);
                    if (zzne.this.zzOV.equals(zznh.getRequestId())) {
                        zzne.this.zzTL.zzh(zznh);
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();

    public zzne(String str, String str2) {
        this.zzTK = str2;
        this.zzOV = str;
    }

    public void zzb(zzji.zzc zzc) {
        this.zzTM = zzc;
    }

    public zzji.zzc zzjv() {
        return this.zzTM;
    }

    public Future<zznh> zzjw() {
        return this.zzTL;
    }
}
