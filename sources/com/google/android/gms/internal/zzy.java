package com.google.android.gms.internal;

import com.google.android.gms.internal.zzb;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public class zzy {
    public static String zza(Map<String, String> map) {
        return zza(map, "ISO-8859-1");
    }

    public static String zza(Map<String, String> map, String str) {
        String str2 = map.get("Content-Type");
        if (str2 == null) {
            return str;
        }
        String[] split = str2.split(";");
        for (int i = 1; i < split.length; i++) {
            String[] split2 = split[i].trim().split("=");
            if (split2.length == 2 && split2[0].equals("charset")) {
                return split2[1];
            }
        }
        return str;
    }

    public static zzb.zza zzb(zzj zzj) {
        boolean z;
        boolean z2;
        long j;
        long j2;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = zzj.zzz;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        String str = map.get("Date");
        if (str != null) {
            j3 = zzg(str);
        }
        String str2 = map.get("Cache-Control");
        if (str2 != null) {
            String[] split = str2.split(",");
            z = false;
            long j6 = 0;
            long j7 = 0;
            for (String trim : split) {
                String trim2 = trim.trim();
                if (trim2.equals("no-cache") || trim2.equals("no-store")) {
                    return null;
                }
                if (trim2.startsWith("max-age=")) {
                    try {
                        j7 = Long.parseLong(trim2.substring(8));
                    } catch (Exception e) {
                    }
                } else if (trim2.startsWith("stale-while-revalidate=")) {
                    try {
                        j6 = Long.parseLong(trim2.substring(23));
                    } catch (Exception e2) {
                    }
                } else if (trim2.equals("must-revalidate") || trim2.equals("proxy-revalidate")) {
                    z = true;
                }
            }
            j4 = j7;
            j5 = j6;
            z2 = true;
        } else {
            z = false;
            z2 = false;
        }
        String str3 = map.get("Expires");
        long zzg = str3 != null ? zzg(str3) : 0;
        String str4 = map.get("Last-Modified");
        long zzg2 = str4 != null ? zzg(str4) : 0;
        String str5 = map.get("ETag");
        if (z2) {
            j2 = currentTimeMillis + (1000 * j4);
            j = z ? j2 : (1000 * j5) + j2;
        } else if (j3 <= 0 || zzg < j3) {
            j = 0;
            j2 = 0;
        } else {
            j = (zzg - j3) + currentTimeMillis;
            j2 = j;
        }
        zzb.zza zza = new zzb.zza();
        zza.data = zzj.data;
        zza.zza = str5;
        zza.zze = j2;
        zza.zzd = j;
        zza.zzb = j3;
        zza.zzc = zzg2;
        zza.zzf = map;
        return zza;
    }

    public static long zzg(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException e) {
            return 0;
        }
    }
}
