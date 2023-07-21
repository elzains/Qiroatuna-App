package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import java.util.Map;

@zzme
public final class zzib implements zzid {
    private void zzc(zzqw zzqw, Map<String, String> map) {
        String str = map.get("label");
        String str2 = map.get("start_label");
        String str3 = map.get("timestamp");
        if (TextUtils.isEmpty(str)) {
            zzpk.zzbh("No label given for CSI tick.");
        } else if (TextUtils.isEmpty(str3)) {
            zzpk.zzbh("No timestamp given for CSI tick.");
        } else {
            try {
                long zzd = zzd(Long.parseLong(str3));
                if (TextUtils.isEmpty(str2)) {
                    str2 = "native:view_load";
                }
                zzqw.zzlF().zza(str, str2, zzd);
            } catch (NumberFormatException e) {
                zzpk.zzc("Malformed timestamp for CSI tick.", e);
            }
        }
    }

    private long zzd(long j) {
        return (j - zzw.zzcS().currentTimeMillis()) + zzw.zzcS().elapsedRealtime();
    }

    private void zzd(zzqw zzqw, Map<String, String> map) {
        String str = map.get("value");
        if (TextUtils.isEmpty(str)) {
            zzpk.zzbh("No value given for CSI experiment.");
            return;
        }
        zzgl zzfA = zzqw.zzlF().zzfA();
        if (zzfA == null) {
            zzpk.zzbh("No ticker for WebView, dropping experiment ID.");
        } else {
            zzfA.zzh("e", str);
        }
    }

    private void zze(zzqw zzqw, Map<String, String> map) {
        String str = map.get("name");
        String str2 = map.get("value");
        if (TextUtils.isEmpty(str2)) {
            zzpk.zzbh("No value given for CSI extra.");
        } else if (TextUtils.isEmpty(str)) {
            zzpk.zzbh("No name given for CSI extra.");
        } else {
            zzgl zzfA = zzqw.zzlF().zzfA();
            if (zzfA == null) {
                zzpk.zzbh("No ticker for WebView, dropping extra parameter.");
            } else {
                zzfA.zzh(str, str2);
            }
        }
    }

    public void zza(zzqw zzqw, Map<String, String> map) {
        String str = map.get("action");
        if ("tick".equals(str)) {
            zzc(zzqw, map);
        } else if ("experiment".equals(str)) {
            zzd(zzqw, map);
        } else if ("extra".equals(str)) {
            zze(zzqw, map);
        }
    }
}
