package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.zzh;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzf;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzjf;
import com.google.android.gms.internal.zzqx;
import org.json.JSONObject;

@zzme
public class zzjh implements zzjf {
    /* access modifiers changed from: private */
    public final zzqw zzIs;

    public zzjh(Context context, zzqh zzqh, @Nullable zzaw zzaw, zze zze) {
        this.zzIs = zzw.zzcN().zza(context, new zzeg(), false, false, zzaw, zzqh, (zzgl) null, (zzu) null, zze);
        this.zzIs.getWebView().setWillNotDraw(true);
    }

    private void runOnUiThread(Runnable runnable) {
        if (zzel.zzeT().zzlj()) {
            runnable.run();
        } else {
            zzpo.zzXC.post(runnable);
        }
    }

    public void destroy() {
        this.zzIs.destroy();
    }

    public void zza(zzdx zzdx, zzh zzh, zzhz zzhz, zzq zzq, boolean z, zzif zzif, zzih zzih, zzf zzf, zzkx zzkx) {
        this.zzIs.zzlv().zza(zzdx, zzh, zzhz, zzq, z, zzif, zzih, new zzf(this.zzIs.getContext(), false), zzkx, (zzot) null);
    }

    public void zza(final zzjf.zza zza) {
        this.zzIs.zzlv().zza((zzqx.zza) new zzqx.zza(this) {
            public void zza(zzqw zzqw, boolean z) {
                zza.zzgN();
            }
        });
    }

    public void zza(String str, zzid zzid) {
        this.zzIs.zzlv().zza(str, zzid);
    }

    public void zza(final String str, final JSONObject jSONObject) {
        runOnUiThread(new Runnable() {
            public void run() {
                zzjh.this.zzIs.zza(str, jSONObject);
            }
        });
    }

    public void zzam(String str) {
        final String format = String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", new Object[]{str});
        runOnUiThread(new Runnable() {
            public void run() {
                zzjh.this.zzIs.loadData(format, "text/html", "UTF-8");
            }
        });
    }

    public void zzan(final String str) {
        runOnUiThread(new Runnable() {
            public void run() {
                zzjh.this.zzIs.loadUrl(str);
            }
        });
    }

    public void zzao(final String str) {
        runOnUiThread(new Runnable() {
            public void run() {
                zzjh.this.zzIs.loadData(str, "text/html", "UTF-8");
            }
        });
    }

    public void zzb(String str, zzid zzid) {
        this.zzIs.zzlv().zzb(str, zzid);
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzIs.zzb(str, jSONObject);
    }

    public zzjk zzgM() {
        return new zzjl(this);
    }

    public void zzj(final String str, final String str2) {
        runOnUiThread(new Runnable() {
            public void run() {
                zzjh.this.zzIs.zzj(str, str2);
            }
        });
    }
}
