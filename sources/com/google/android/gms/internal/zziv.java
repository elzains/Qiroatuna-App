package com.google.android.gms.internal;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzme
public class zziv extends zzis {
    private static final Set<String> zzIJ = Collections.synchronizedSet(new HashSet());
    private static final DecimalFormat zzIK = new DecimalFormat("#,###");
    private File zzIL;
    private boolean zzIM;

    public zziv(zzqw zzqw) {
        super(zzqw);
        File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzpk.zzbh("Context.getCacheDir() returned null");
            return;
        }
        this.zzIL = new File(cacheDir, "admobVideoStreams");
        if (!this.zzIL.isDirectory() && !this.zzIL.mkdirs()) {
            String valueOf = String.valueOf(this.zzIL.getAbsolutePath());
            zzpk.zzbh(valueOf.length() != 0 ? "Could not create preload cache directory at ".concat(valueOf) : new String("Could not create preload cache directory at "));
            this.zzIL = null;
        } else if (!this.zzIL.setReadable(true, false) || !this.zzIL.setExecutable(true, false)) {
            String valueOf2 = String.valueOf(this.zzIL.getAbsolutePath());
            zzpk.zzbh(valueOf2.length() != 0 ? "Could not set cache file permissions at ".concat(valueOf2) : new String("Could not set cache file permissions at "));
            this.zzIL = null;
        }
    }

    private File zzb(File file) {
        return new File(this.zzIL, String.valueOf(file.getName()).concat(".done"));
    }

    private static void zzc(File file) {
        if (file.isFile()) {
            file.setLastModified(System.currentTimeMillis());
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
    }

    public void abort() {
        this.zzIM = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0306, code lost:
        r5 = r5 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0307, code lost:
        if (r5 <= r14) goto L_0x0336;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        r3 = java.lang.String.valueOf(java.lang.Integer.toString(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0319, code lost:
        if (r3.length() == 0) goto L_0x032b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x031b, code lost:
        r3 = "File too big for full file cache. Size: ".concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0326, code lost:
        throw new java.io.IOException("stream cache file size limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0327, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0328, code lost:
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:?, code lost:
        r3 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0331, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0332, code lost:
        r3 = null;
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:?, code lost:
        r17.flip();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x033d, code lost:
        if (r16.write(r17) > 0) goto L_0x0339;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x033f, code lost:
        r17.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x034e, code lost:
        if ((r18.currentTimeMillis() - r20) <= (1000 * r22)) goto L_0x0389;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:?, code lost:
        r2 = java.lang.String.valueOf(java.lang.Long.toString(r22));
        r3 = new java.lang.StringBuilder(java.lang.String.valueOf(r2).length() + 29).append("Timeout exceeded. Limit: ").append(r2).append(" sec").toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0384, code lost:
        throw new java.io.IOException("stream cache time limit exceeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0385, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0386, code lost:
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x038d, code lost:
        if (r26.zzIM == false) goto L_0x039e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0398, code lost:
        throw new java.io.IOException("abort requested");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0399, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x039a, code lost:
        r3 = null;
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x03a2, code lost:
        if (r0.tryAcquire() == false) goto L_0x02fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x03a4, code lost:
        zza(r27, r12.getAbsolutePath(), r5, r6, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x03b2, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x03b3, code lost:
        r3 = null;
        r4 = "error";
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x03b8, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x03c0, code lost:
        if (com.google.android.gms.internal.zzpk.zzak(3) == false) goto L_0x03fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x03c2, code lost:
        r2 = zzIK.format((long) r5);
        com.google.android.gms.internal.zzpk.zzbf(new java.lang.StringBuilder((java.lang.String.valueOf(r2).length() + 22) + java.lang.String.valueOf(r27).length()).append("Preloaded ").append(r2).append(" bytes from ").append(r27).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x03fe, code lost:
        r12.setReadable(true, false);
        zzc(r13);
        zza(r27, r12.getAbsolutePath(), r5);
        zzIJ.remove(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0416, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0419, code lost:
        com.google.android.gms.internal.zzpk.zzc(new java.lang.StringBuilder(java.lang.String.valueOf(r27).length() + 25).append("Preload failed for URL \"").append(r27).append("\"").toString(), r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0443, code lost:
        r2 = new java.lang.String("Could not delete partial cache file at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0450, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0451, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0457, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0458, code lost:
        r3 = null;
        r4 = "error";
        r5 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e8, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r3 = com.google.android.gms.ads.internal.zzw.zzcZ().zzb(r27, com.google.android.gms.internal.zzgd.zzBC.get().intValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0104, code lost:
        if ((r3 instanceof java.net.HttpURLConnection) == false) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0106, code lost:
        r2 = r3.getResponseCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0110, code lost:
        if (r2 < 400) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0112, code lost:
        r4 = "badUrl";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r3 = java.lang.String.valueOf(java.lang.Integer.toString(r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0122, code lost:
        if (r3.length() == 0) goto L_0x01cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0124, code lost:
        r3 = "HTTP request failed. Code: ".concat(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0156, code lost:
        throw new java.io.IOException(new java.lang.StringBuilder(java.lang.String.valueOf(r27).length() + 32).append("HTTP status code ").append(r2).append(" at ").append(r27).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0157, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x015a, code lost:
        if ((r2 instanceof java.lang.RuntimeException) != false) goto L_0x015c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x015c, code lost:
        com.google.android.gms.ads.internal.zzw.zzcQ().zza(r2, "VideoStreamFullFileCache.preload");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x016c, code lost:
        if (r26.zzIM != false) goto L_0x016e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x016e, code lost:
        com.google.android.gms.internal.zzpk.zzbg(new java.lang.StringBuilder(java.lang.String.valueOf(r27).length() + 26).append("Preload aborted for URL \"").append(r27).append("\"").toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a2, code lost:
        r2 = java.lang.String.valueOf(r12.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01b0, code lost:
        if (r2.length() != 0) goto L_0x01b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01b2, code lost:
        r2 = "Could not delete partial cache file at ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01b6, code lost:
        com.google.android.gms.internal.zzpk.zzbh(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01b9, code lost:
        zza(r27, r12.getAbsolutePath(), r4, r3);
        zzIJ.remove(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r3 = new java.lang.String("HTTP request failed. Code: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01d3, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01d4, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        r6 = r3.getContentLength();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01da, code lost:
        if (r6 >= 0) goto L_0x0210;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01dc, code lost:
        r2 = java.lang.String.valueOf(r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01e6, code lost:
        if (r2.length() == 0) goto L_0x0205;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01e8, code lost:
        r2 = "Stream cache aborted, missing content-length header at ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01ec, code lost:
        com.google.android.gms.internal.zzpk.zzbh(r2);
        zza(r27, r12.getAbsolutePath(), "contentLengthMissing", (java.lang.String) null);
        zzIJ.remove(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0205, code lost:
        r2 = new java.lang.String("Stream cache aborted, missing content-length header at ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x020b, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x020c, code lost:
        r3 = null;
        r4 = "error";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0210, code lost:
        r4 = zzIK.format((long) r6);
        r14 = com.google.android.gms.internal.zzgd.zzBx.get().intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0223, code lost:
        if (r6 <= r14) goto L_0x028a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0225, code lost:
        com.google.android.gms.internal.zzpk.zzbh(new java.lang.StringBuilder((java.lang.String.valueOf(r4).length() + 33) + java.lang.String.valueOf(r27).length()).append("Content length ").append(r4).append(" exceeds limit at ").append(r27).toString());
        r2 = java.lang.String.valueOf(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0264, code lost:
        if (r2.length() == 0) goto L_0x027f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0266, code lost:
        r2 = "File too big for full file cache. Size: ".concat(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x026a, code lost:
        zza(r27, r12.getAbsolutePath(), "sizeExceeded", r2);
        zzIJ.remove(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x027f, code lost:
        r2 = new java.lang.String("File too big for full file cache. Size: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0285, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0286, code lost:
        r3 = null;
        r4 = "error";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x028a, code lost:
        com.google.android.gms.internal.zzpk.zzbf(new java.lang.StringBuilder((java.lang.String.valueOf(r4).length() + 20) + java.lang.String.valueOf(r27).length()).append("Caching ").append(r4).append(" bytes from ").append(r27).toString());
        r15 = java.nio.channels.Channels.newChannel(r3.getInputStream());
        r11 = new java.io.FileOutputStream(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        r16 = r11.getChannel();
        r17 = java.nio.ByteBuffer.allocate(1048576);
        r18 = com.google.android.gms.ads.internal.zzw.zzcS();
        r5 = 0;
        r20 = r18.currentTimeMillis();
        r0 = new com.google.android.gms.internal.zzpz(com.google.android.gms.internal.zzgd.zzBB.get().longValue());
        r22 = com.google.android.gms.internal.zzgd.zzBA.get().longValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02fe, code lost:
        r2 = r15.read(r17);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0304, code lost:
        if (r2 < 0) goto L_0x03b8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0419  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0443  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean zzad(java.lang.String r27) {
        /*
            r26 = this;
            r0 = r26
            java.io.File r2 = r0.zzIL
            if (r2 != 0) goto L_0x0013
            r2 = 0
            java.lang.String r3 = "noCacheDir"
            r4 = 0
            r0 = r26
            r1 = r27
            r0.zza(r1, r2, r3, r4)
            r2 = 0
        L_0x0012:
            return r2
        L_0x0013:
            int r3 = r26.zzgs()
            com.google.android.gms.internal.zzfz<java.lang.Integer> r2 = com.google.android.gms.internal.zzgd.zzBw
            java.lang.Object r2 = r2.get()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            if (r3 <= r2) goto L_0x003d
            boolean r2 = r26.zzgt()
            if (r2 != 0) goto L_0x0013
            java.lang.String r2 = "Unable to expire stream cache"
            com.google.android.gms.internal.zzpk.zzbh(r2)
            r2 = 0
            java.lang.String r3 = "expireFailed"
            r4 = 0
            r0 = r26
            r1 = r27
            r0.zza(r1, r2, r3, r4)
            r2 = 0
            goto L_0x0012
        L_0x003d:
            java.lang.String r2 = r26.zzae(r27)
            java.io.File r12 = new java.io.File
            r0 = r26
            java.io.File r3 = r0.zzIL
            r12.<init>(r3, r2)
            r0 = r26
            java.io.File r13 = r0.zzb(r12)
            boolean r2 = r12.isFile()
            if (r2 == 0) goto L_0x0087
            boolean r2 = r13.isFile()
            if (r2 == 0) goto L_0x0087
            long r2 = r12.length()
            int r3 = (int) r2
            java.lang.String r4 = "Stream cache hit at "
            java.lang.String r2 = java.lang.String.valueOf(r27)
            int r5 = r2.length()
            if (r5 == 0) goto L_0x0081
            java.lang.String r2 = r4.concat(r2)
        L_0x0071:
            com.google.android.gms.internal.zzpk.zzbf(r2)
            java.lang.String r2 = r12.getAbsolutePath()
            r0 = r26
            r1 = r27
            r0.zza((java.lang.String) r1, (java.lang.String) r2, (int) r3)
            r2 = 1
            goto L_0x0012
        L_0x0081:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r4)
            goto L_0x0071
        L_0x0087:
            r0 = r26
            java.io.File r2 = r0.zzIL
            java.lang.String r2 = r2.getAbsolutePath()
            java.lang.String r3 = java.lang.String.valueOf(r2)
            java.lang.String r2 = java.lang.String.valueOf(r27)
            int r4 = r2.length()
            if (r4 == 0) goto L_0x00d5
            java.lang.String r2 = r3.concat(r2)
            r8 = r2
        L_0x00a2:
            java.util.Set<java.lang.String> r3 = zzIJ
            monitor-enter(r3)
            java.util.Set<java.lang.String> r2 = zzIJ     // Catch:{ all -> 0x00d2 }
            boolean r2 = r2.contains(r8)     // Catch:{ all -> 0x00d2 }
            if (r2 == 0) goto L_0x00e2
            java.lang.String r4 = "Stream cache already in progress at "
            java.lang.String r2 = java.lang.String.valueOf(r27)     // Catch:{ all -> 0x00d2 }
            int r5 = r2.length()     // Catch:{ all -> 0x00d2 }
            if (r5 == 0) goto L_0x00dc
            java.lang.String r2 = r4.concat(r2)     // Catch:{ all -> 0x00d2 }
        L_0x00bd:
            com.google.android.gms.internal.zzpk.zzbh(r2)     // Catch:{ all -> 0x00d2 }
            java.lang.String r2 = r12.getAbsolutePath()     // Catch:{ all -> 0x00d2 }
            java.lang.String r4 = "inProgress"
            r5 = 0
            r0 = r26
            r1 = r27
            r0.zza(r1, r2, r4, r5)     // Catch:{ all -> 0x00d2 }
            r2 = 0
            monitor-exit(r3)     // Catch:{ all -> 0x00d2 }
            goto L_0x0012
        L_0x00d2:
            r2 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x00d2 }
            throw r2
        L_0x00d5:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r3)
            r8 = r2
            goto L_0x00a2
        L_0x00dc:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x00d2 }
            r2.<init>(r4)     // Catch:{ all -> 0x00d2 }
            goto L_0x00bd
        L_0x00e2:
            java.util.Set<java.lang.String> r2 = zzIJ     // Catch:{ all -> 0x00d2 }
            r2.add(r8)     // Catch:{ all -> 0x00d2 }
            monitor-exit(r3)     // Catch:{ all -> 0x00d2 }
            r5 = 0
            java.lang.String r10 = "error"
            r9 = 0
            com.google.android.gms.internal.zzqo r3 = com.google.android.gms.ads.internal.zzw.zzcZ()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            com.google.android.gms.internal.zzfz<java.lang.Integer> r2 = com.google.android.gms.internal.zzgd.zzBC     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.Object r2 = r2.get()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r2 = r2.intValue()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r0 = r27
            java.net.HttpURLConnection r3 = r3.zzb(r0, r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            boolean r2 = r3 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            if (r2 == 0) goto L_0x01d6
            r0 = r3
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r2 = r0
            int r2 = r2.getResponseCode()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r4 = 400(0x190, float:5.6E-43)
            if (r2 < r4) goto L_0x01d6
            java.lang.String r4 = "badUrl"
            java.lang.String r6 = "HTTP request failed. Code: "
            java.lang.String r3 = java.lang.Integer.toString(r2)     // Catch:{ IOException -> 0x01d3, RuntimeException -> 0x0450 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x01d3, RuntimeException -> 0x0450 }
            int r7 = r3.length()     // Catch:{ IOException -> 0x01d3, RuntimeException -> 0x0450 }
            if (r7 == 0) goto L_0x01cc
            java.lang.String r3 = r6.concat(r3)     // Catch:{ IOException -> 0x01d3, RuntimeException -> 0x0450 }
        L_0x0128:
            java.io.IOException r6 = new java.io.IOException     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
            java.lang.String r7 = java.lang.String.valueOf(r27)     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
            int r7 = r7.length()     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
            int r7 = r7 + 32
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
            r9.<init>(r7)     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
            java.lang.String r7 = "HTTP status code "
            java.lang.StringBuilder r7 = r9.append(r7)     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
            java.lang.StringBuilder r2 = r7.append(r2)     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
            java.lang.String r7 = " at "
            java.lang.StringBuilder r2 = r2.append(r7)     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
            r0 = r27
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
            r6.<init>(r2)     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
            throw r6     // Catch:{ IOException -> 0x0157, RuntimeException -> 0x0454 }
        L_0x0157:
            r2 = move-exception
        L_0x0158:
            boolean r6 = r2 instanceof java.lang.RuntimeException
            if (r6 == 0) goto L_0x0165
            com.google.android.gms.internal.zzpe r6 = com.google.android.gms.ads.internal.zzw.zzcQ()
            java.lang.String r7 = "VideoStreamFullFileCache.preload"
            r6.zza((java.lang.Throwable) r2, (java.lang.String) r7)
        L_0x0165:
            r5.close()     // Catch:{ IOException -> 0x044a, NullPointerException -> 0x044d }
        L_0x0168:
            r0 = r26
            boolean r5 = r0.zzIM
            if (r5 == 0) goto L_0x0419
            java.lang.String r2 = java.lang.String.valueOf(r27)
            int r2 = r2.length()
            int r2 = r2 + 26
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r2)
            java.lang.String r2 = "Preload aborted for URL \""
            java.lang.StringBuilder r2 = r5.append(r2)
            r0 = r27
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r5 = "\""
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.String r2 = r2.toString()
            com.google.android.gms.internal.zzpk.zzbg(r2)
        L_0x0196:
            boolean r2 = r12.exists()
            if (r2 == 0) goto L_0x01b9
            boolean r2 = r12.delete()
            if (r2 != 0) goto L_0x01b9
            java.lang.String r5 = "Could not delete partial cache file at "
            java.lang.String r2 = r12.getAbsolutePath()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r6 = r2.length()
            if (r6 == 0) goto L_0x0443
            java.lang.String r2 = r5.concat(r2)
        L_0x01b6:
            com.google.android.gms.internal.zzpk.zzbh(r2)
        L_0x01b9:
            java.lang.String r2 = r12.getAbsolutePath()
            r0 = r26
            r1 = r27
            r0.zza(r1, r2, r4, r3)
            java.util.Set<java.lang.String> r2 = zzIJ
            r2.remove(r8)
            r2 = 0
            goto L_0x0012
        L_0x01cc:
            java.lang.String r3 = new java.lang.String     // Catch:{ IOException -> 0x01d3, RuntimeException -> 0x0450 }
            r3.<init>(r6)     // Catch:{ IOException -> 0x01d3, RuntimeException -> 0x0450 }
            goto L_0x0128
        L_0x01d3:
            r2 = move-exception
            r3 = r9
            goto L_0x0158
        L_0x01d6:
            int r6 = r3.getContentLength()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            if (r6 >= 0) goto L_0x0210
            java.lang.String r3 = "Stream cache aborted, missing content-length header at "
            java.lang.String r2 = java.lang.String.valueOf(r27)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r4 = r2.length()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            if (r4 == 0) goto L_0x0205
            java.lang.String r2 = r3.concat(r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
        L_0x01ec:
            com.google.android.gms.internal.zzpk.zzbh(r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.String r2 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.String r3 = "contentLengthMissing"
            r4 = 0
            r0 = r26
            r1 = r27
            r0.zza(r1, r2, r3, r4)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.util.Set<java.lang.String> r2 = zzIJ     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r2.remove(r8)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r2 = 0
            goto L_0x0012
        L_0x0205:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            goto L_0x01ec
        L_0x020b:
            r2 = move-exception
            r3 = r9
            r4 = r10
            goto L_0x0158
        L_0x0210:
            java.text.DecimalFormat r2 = zzIK     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            long r14 = (long) r6     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.String r4 = r2.format(r14)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            com.google.android.gms.internal.zzfz<java.lang.Integer> r2 = com.google.android.gms.internal.zzgd.zzBx     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.Object r2 = r2.get()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r14 = r2.intValue()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            if (r6 <= r14) goto L_0x028a
            java.lang.String r2 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r2 = r2.length()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r2 = r2 + 33
            java.lang.String r3 = java.lang.String.valueOf(r27)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r3 = r3.length()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.String r2 = "Content length "
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.String r3 = " exceeds limit at "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r0 = r27
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            com.google.android.gms.internal.zzpk.zzbh(r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.String r3 = "File too big for full file cache. Size: "
            java.lang.String r2 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r4 = r2.length()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            if (r4 == 0) goto L_0x027f
            java.lang.String r2 = r3.concat(r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
        L_0x026a:
            java.lang.String r3 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.String r4 = "sizeExceeded"
            r0 = r26
            r1 = r27
            r0.zza(r1, r3, r4, r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.util.Set<java.lang.String> r2 = zzIJ     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r2.remove(r8)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r2 = 0
            goto L_0x0012
        L_0x027f:
            java.lang.String r2 = new java.lang.String     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            goto L_0x026a
        L_0x0285:
            r2 = move-exception
            r3 = r9
            r4 = r10
            goto L_0x0158
        L_0x028a:
            java.lang.String r2 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r2 = r2.length()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r2 = r2 + 20
            java.lang.String r7 = java.lang.String.valueOf(r27)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r7 = r7.length()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            int r2 = r2 + r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r7.<init>(r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.String r2 = "Caching "
            java.lang.StringBuilder r2 = r7.append(r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.String r4 = " bytes from "
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r0 = r27
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            com.google.android.gms.internal.zzpk.zzbf(r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.io.InputStream r2 = r3.getInputStream()     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.nio.channels.ReadableByteChannel r15 = java.nio.channels.Channels.newChannel(r2)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            r11.<init>(r12)     // Catch:{ IOException -> 0x020b, RuntimeException -> 0x0285 }
            java.nio.channels.FileChannel r16 = r11.getChannel()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            r2 = 1048576(0x100000, float:1.469368E-39)
            java.nio.ByteBuffer r17 = java.nio.ByteBuffer.allocate(r2)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            com.google.android.gms.common.util.zze r18 = com.google.android.gms.ads.internal.zzw.zzcS()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            r5 = 0
            long r20 = r18.currentTimeMillis()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            com.google.android.gms.internal.zzfz<java.lang.Long> r2 = com.google.android.gms.internal.zzgd.zzBB     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.lang.Object r2 = r2.get()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            long r2 = r2.longValue()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            com.google.android.gms.internal.zzpz r19 = new com.google.android.gms.internal.zzpz     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            r0 = r19
            r0.<init>(r2)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            com.google.android.gms.internal.zzfz<java.lang.Long> r2 = com.google.android.gms.internal.zzgd.zzBA     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.lang.Object r2 = r2.get()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.lang.Long r2 = (java.lang.Long) r2     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            long r22 = r2.longValue()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
        L_0x02fe:
            r0 = r17
            int r2 = r15.read(r0)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            if (r2 < 0) goto L_0x03b8
            int r5 = r5 + r2
            if (r5 <= r14) goto L_0x0336
            java.lang.String r4 = "sizeExceeded"
            java.lang.String r2 = "File too big for full file cache. Size: "
            java.lang.String r3 = java.lang.Integer.toString(r5)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            int r5 = r3.length()     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            if (r5 == 0) goto L_0x032b
            java.lang.String r3 = r2.concat(r3)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
        L_0x031f:
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x0327, RuntimeException -> 0x0385 }
            java.lang.String r5 = "stream cache file size limit exceeded"
            r2.<init>(r5)     // Catch:{ IOException -> 0x0327, RuntimeException -> 0x0385 }
            throw r2     // Catch:{ IOException -> 0x0327, RuntimeException -> 0x0385 }
        L_0x0327:
            r2 = move-exception
            r5 = r11
            goto L_0x0158
        L_0x032b:
            java.lang.String r3 = new java.lang.String     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            goto L_0x031f
        L_0x0331:
            r2 = move-exception
            r3 = r9
            r5 = r11
            goto L_0x0158
        L_0x0336:
            r17.flip()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
        L_0x0339:
            int r2 = r16.write(r17)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            if (r2 > 0) goto L_0x0339
            r17.clear()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            long r2 = r18.currentTimeMillis()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            long r2 = r2 - r20
            r24 = 1000(0x3e8, double:4.94E-321)
            long r24 = r24 * r22
            int r2 = (r2 > r24 ? 1 : (r2 == r24 ? 0 : -1))
            if (r2 <= 0) goto L_0x0389
            java.lang.String r4 = "downloadTimeout"
            java.lang.String r2 = java.lang.Long.toString(r22)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            int r3 = r3.length()     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            int r3 = r3 + 29
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            r5.<init>(r3)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            java.lang.String r3 = "Timeout exceeded. Limit: "
            java.lang.StringBuilder r3 = r5.append(r3)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            java.lang.String r3 = " sec"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            java.lang.String r3 = r2.toString()     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x0327, RuntimeException -> 0x0385 }
            java.lang.String r5 = "stream cache time limit exceeded"
            r2.<init>(r5)     // Catch:{ IOException -> 0x0327, RuntimeException -> 0x0385 }
            throw r2     // Catch:{ IOException -> 0x0327, RuntimeException -> 0x0385 }
        L_0x0385:
            r2 = move-exception
            r5 = r11
            goto L_0x0158
        L_0x0389:
            r0 = r26
            boolean r2 = r0.zzIM     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            if (r2 == 0) goto L_0x039e
            java.lang.String r4 = "externalAbort"
            java.io.IOException r2 = new java.io.IOException     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            java.lang.String r3 = "abort requested"
            r2.<init>(r3)     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
            throw r2     // Catch:{ IOException -> 0x0331, RuntimeException -> 0x0399 }
        L_0x0399:
            r2 = move-exception
            r3 = r9
            r5 = r11
            goto L_0x0158
        L_0x039e:
            boolean r2 = r19.tryAcquire()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            if (r2 == 0) goto L_0x02fe
            java.lang.String r4 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            r7 = 0
            r2 = r26
            r3 = r27
            r2.zza(r3, r4, r5, r6, r7)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            goto L_0x02fe
        L_0x03b2:
            r2 = move-exception
            r3 = r9
            r4 = r10
            r5 = r11
            goto L_0x0158
        L_0x03b8:
            r11.close()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            r2 = 3
            boolean r2 = com.google.android.gms.internal.zzpk.zzak(r2)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            if (r2 == 0) goto L_0x03fe
            java.text.DecimalFormat r2 = zzIK     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            long r6 = (long) r5     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.lang.String r2 = r2.format(r6)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            int r3 = r3.length()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            int r3 = r3 + 22
            java.lang.String r4 = java.lang.String.valueOf(r27)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            int r4 = r4.length()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.lang.String r3 = "Preloaded "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.lang.String r3 = " bytes from "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            r0 = r27
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            com.google.android.gms.internal.zzpk.zzbf(r2)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
        L_0x03fe:
            r2 = 1
            r3 = 0
            r12.setReadable(r2, r3)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            zzc(r13)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.lang.String r2 = r12.getAbsolutePath()     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            r0 = r26
            r1 = r27
            r0.zza((java.lang.String) r1, (java.lang.String) r2, (int) r5)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            java.util.Set<java.lang.String> r2 = zzIJ     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            r2.remove(r8)     // Catch:{ IOException -> 0x03b2, RuntimeException -> 0x0457 }
            r2 = 1
            goto L_0x0012
        L_0x0419:
            java.lang.String r5 = java.lang.String.valueOf(r27)
            int r5 = r5.length()
            int r5 = r5 + 25
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r5)
            java.lang.String r5 = "Preload failed for URL \""
            java.lang.StringBuilder r5 = r6.append(r5)
            r0 = r27
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r6 = "\""
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.google.android.gms.internal.zzpk.zzc(r5, r2)
            goto L_0x0196
        L_0x0443:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r5)
            goto L_0x01b6
        L_0x044a:
            r5 = move-exception
            goto L_0x0168
        L_0x044d:
            r5 = move-exception
            goto L_0x0168
        L_0x0450:
            r2 = move-exception
            r3 = r9
            goto L_0x0158
        L_0x0454:
            r2 = move-exception
            goto L_0x0158
        L_0x0457:
            r2 = move-exception
            r3 = r9
            r4 = r10
            r5 = r11
            goto L_0x0158
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zziv.zzad(java.lang.String):boolean");
    }

    public int zzgs() {
        int i = 0;
        if (this.zzIL != null) {
            for (File name2 : this.zzIL.listFiles()) {
                if (!name2.getName().endsWith(".done")) {
                    i++;
                }
            }
        }
        return i;
    }

    public boolean zzgt() {
        boolean z;
        long j;
        File file;
        if (this.zzIL == null) {
            return false;
        }
        File file2 = null;
        long j2 = Long.MAX_VALUE;
        File[] listFiles = this.zzIL.listFiles();
        int length = listFiles.length;
        int i = 0;
        while (i < length) {
            File file3 = listFiles[i];
            if (!file3.getName().endsWith(".done")) {
                j = file3.lastModified();
                if (j < j2) {
                    file = file3;
                    i++;
                    file2 = file;
                    j2 = j;
                }
            }
            j = j2;
            file = file2;
            i++;
            file2 = file;
            j2 = j;
        }
        if (file2 != null) {
            z = file2.delete();
            File zzb = zzb(file2);
            if (zzb.isFile()) {
                z &= zzb.delete();
            }
        } else {
            z = false;
        }
        return z;
    }
}
