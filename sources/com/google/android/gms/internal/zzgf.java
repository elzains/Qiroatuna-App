package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzw;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@zzme
public class zzgf {
    final Context mContext;
    BlockingQueue<zzgl> zzFA;
    ExecutorService zzFB;
    LinkedHashMap<String, String> zzFC = new LinkedHashMap<>();
    Map<String, zzgi> zzFD = new HashMap();
    private AtomicBoolean zzFE;
    private File zzFF;
    String zzFy;
    final String zzwd;

    public zzgf(Context context, String str, String str2, Map<String, String> map) {
        File externalStorageDirectory;
        this.mContext = context;
        this.zzwd = str;
        this.zzFy = str2;
        this.zzFE = new AtomicBoolean(false);
        this.zzFE.set(zzgd.zzCb.get().booleanValue());
        if (this.zzFE.get() && (externalStorageDirectory = Environment.getExternalStorageDirectory()) != null) {
            this.zzFF = new File(externalStorageDirectory, "sdk_csi_data.txt");
        }
        for (Map.Entry next : map.entrySet()) {
            this.zzFC.put((String) next.getKey(), (String) next.getValue());
        }
        this.zzFA = new ArrayBlockingQueue(30);
        this.zzFB = Executors.newSingleThreadExecutor();
        this.zzFB.execute(new Runnable() {
            public void run() {
                zzgf.this.zzfx();
            }
        });
        this.zzFD.put("action", zzgi.zzFI);
        this.zzFD.put("ad_format", zzgi.zzFI);
        this.zzFD.put("e", zzgi.zzFJ);
    }

    private void zzb(Map<String, String> map, String str) {
        String zza = zza(this.zzFy, map, str);
        if (this.zzFE.get()) {
            zzc(this.zzFF, zza);
        } else {
            zzw.zzcM().zzf(this.mContext, this.zzwd, zza);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0029 A[SYNTHETIC, Splitter:B:16:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0038 A[SYNTHETIC, Splitter:B:23:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void zzc(@android.support.annotation.Nullable java.io.File r4, java.lang.String r5) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0043
            r2 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0020, all -> 0x0034 }
            r0 = 1
            r1.<init>(r4, r0)     // Catch:{ IOException -> 0x0020, all -> 0x0034 }
            byte[] r0 = r5.getBytes()     // Catch:{ IOException -> 0x004b }
            r1.write(r0)     // Catch:{ IOException -> 0x004b }
            r0 = 10
            r1.write(r0)     // Catch:{ IOException -> 0x004b }
            r1.close()     // Catch:{ IOException -> 0x0019 }
        L_0x0018:
            return
        L_0x0019:
            r0 = move-exception
            java.lang.String r1 = "CsiReporter: Cannot close file: sdk_csi_data.txt."
            com.google.android.gms.internal.zzpk.zzc(r1, r0)
            goto L_0x0018
        L_0x0020:
            r0 = move-exception
            r1 = r2
        L_0x0022:
            java.lang.String r2 = "CsiReporter: Cannot write to file: sdk_csi_data.txt."
            com.google.android.gms.internal.zzpk.zzc(r2, r0)     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x0018
            r1.close()     // Catch:{ IOException -> 0x002d }
            goto L_0x0018
        L_0x002d:
            r0 = move-exception
            java.lang.String r1 = "CsiReporter: Cannot close file: sdk_csi_data.txt."
            com.google.android.gms.internal.zzpk.zzc(r1, r0)
            goto L_0x0018
        L_0x0034:
            r0 = move-exception
            r1 = r2
        L_0x0036:
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ IOException -> 0x003c }
        L_0x003b:
            throw r0
        L_0x003c:
            r1 = move-exception
            java.lang.String r2 = "CsiReporter: Cannot close file: sdk_csi_data.txt."
            com.google.android.gms.internal.zzpk.zzc(r2, r1)
            goto L_0x003b
        L_0x0043:
            java.lang.String r0 = "CsiReporter: File doesn't exists. Cannot write CSI data to file."
            com.google.android.gms.internal.zzpk.zzbh(r0)
            goto L_0x0018
        L_0x0049:
            r0 = move-exception
            goto L_0x0036
        L_0x004b:
            r0 = move-exception
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzgf.zzc(java.io.File, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void zzfx() {
        while (true) {
            try {
                zzgl take = this.zzFA.take();
                String zzfD = take.zzfD();
                if (!TextUtils.isEmpty(zzfD)) {
                    zzb(zza(this.zzFC, take.zzfE()), zzfD);
                }
            } catch (InterruptedException e) {
                zzpk.zzc("CsiReporter:reporter interrupted", e);
                return;
            }
        }
    }

    public zzgi zzV(String str) {
        zzgi zzgi = this.zzFD.get(str);
        return zzgi != null ? zzgi : zzgi.zzFH;
    }

    /* access modifiers changed from: package-private */
    public String zza(String str, Map<String, String> map, @NonNull String str2) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        for (Map.Entry next : map.entrySet()) {
            buildUpon.appendQueryParameter((String) next.getKey(), (String) next.getValue());
        }
        StringBuilder sb = new StringBuilder(buildUpon.build().toString());
        sb.append("&").append("it").append("=").append(str2);
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zza(Map<String, String> map, @Nullable Map<String, String> map2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Map.Entry next : map2.entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) linkedHashMap.get(str);
            linkedHashMap.put(str, zzV(str).zzg(str2, (String) next.getValue()));
        }
        return linkedHashMap;
    }

    public boolean zza(zzgl zzgl) {
        return this.zzFA.offer(zzgl);
    }

    public void zzc(@Nullable List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.zzFC.put("e", TextUtils.join(",", list));
        }
    }
}
