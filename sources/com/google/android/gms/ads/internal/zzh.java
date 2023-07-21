package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.internal.zzgd;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zzji;
import com.google.android.gms.internal.zzjj;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzpd;
import com.google.android.gms.internal.zzpk;
import com.google.android.gms.internal.zzpo;
import com.google.android.gms.internal.zzqh;
import com.google.android.gms.internal.zzqp;
import com.google.android.gms.internal.zzqw;
import java.util.Map;
import org.json.JSONObject;

@zzme
public class zzh {
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();

    private static boolean zza(@Nullable zzpd zzpd) {
        if (zzpd == null) {
            return true;
        }
        return (((zzw.zzcS().currentTimeMillis() - zzpd.zzkb()) > zzgd.zzEL.get().longValue() ? 1 : ((zzw.zzcS().currentTimeMillis() - zzpd.zzkb()) == zzgd.zzEL.get().longValue() ? 0 : -1)) > 0) || !zzpd.zzkc();
    }

    public void zza(Context context, zzqh zzqh, String str, zzpd zzpd) {
        zza(context, zzqh, false, zzpd, zzpd != null ? null : zzpd.zzke(), str, (Runnable) null);
    }

    public void zza(Context context, zzqh zzqh, String str, @Nullable Runnable runnable) {
        zza(context, zzqh, true, (zzpd) null, str, (String) null, runnable);
    }

    /* access modifiers changed from: package-private */
    public void zza(Context context, zzqh zzqh, boolean z, @Nullable zzpd zzpd, String str, @Nullable String str2, @Nullable Runnable runnable) {
        if (zza(zzpd)) {
            if (context == null) {
                zzpk.zzbh("Context not provided to fetch application settings");
            } else if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                this.mContext = context;
                final zzji zzd = zzw.zzcM().zzd(context, zzqh);
                final Runnable runnable2 = runnable;
                final C01981 r4 = new zzid() {
                    public void zza(zzqw zzqw, Map<String, String> map) {
                        zzqw.zzb("/appSettingsFetched", (zzid) this);
                        synchronized (zzh.this.zzrJ) {
                            if (map != null) {
                                if ("true".equalsIgnoreCase(map.get("isSuccessful"))) {
                                    zzw.zzcQ().zzn(zzh.this.mContext, map.get("appSettingsJson"));
                                    try {
                                        if (runnable2 != null) {
                                            runnable2.run();
                                        }
                                    } catch (Throwable th) {
                                        zzw.zzcQ().zza(th, "ConfigLoader.maybeFetchNewAppSettings");
                                        zzpk.zzc("ConfigLoader post task failed.", th);
                                    }
                                }
                            }
                        }
                    }
                };
                final String str3 = str;
                final String str4 = str2;
                final boolean z2 = z;
                final Context context2 = context;
                zzpo.zzXC.post(new Runnable(this) {
                    public void run() {
                        zzd.zzgO().zza(new zzqp.zzc<zzjj>() {
                            /* renamed from: zzb */
                            public void zzd(zzjj zzjj) {
                                zzjj.zza("/appSettingsFetched", r4);
                                try {
                                    JSONObject jSONObject = new JSONObject();
                                    if (!TextUtils.isEmpty(str3)) {
                                        jSONObject.put("app_id", str3);
                                    } else if (!TextUtils.isEmpty(str4)) {
                                        jSONObject.put("ad_unit_id", str4);
                                    }
                                    jSONObject.put("is_init", z2);
                                    jSONObject.put("pn", context2.getPackageName());
                                    zzjj.zza("AFMA_fetchAppSettings", jSONObject);
                                } catch (Exception e) {
                                    zzjj.zzb("/appSettingsFetched", r4);
                                    zzpk.zzb("Error requesting application settings", e);
                                }
                            }
                        }, new zzqp.zzb());
                    }
                });
            } else {
                zzpk.zzbh("App settings could not be fetched. Required parameters missing");
            }
        }
    }
}
