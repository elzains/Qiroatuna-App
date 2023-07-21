package com.google.android.gms.internal;

import android.os.SystemClock;
import com.google.android.gms.internal.zzb;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class zzw implements zzb {
    private final Map<String, zza> zzaw;
    private long zzax;
    private final File zzay;
    private final int zzaz;

    static class zza {
        public String zza;
        public long zzaA;
        public String zzaB;
        public long zzb;
        public long zzc;
        public long zzd;
        public long zze;
        public Map<String, String> zzf;

        private zza() {
        }

        public zza(String str, zzb.zza zza2) {
            this.zzaB = str;
            this.zzaA = (long) zza2.data.length;
            this.zza = zza2.zza;
            this.zzb = zza2.zzb;
            this.zzc = zza2.zzc;
            this.zzd = zza2.zzd;
            this.zze = zza2.zze;
            this.zzf = zza2.zzf;
        }

        public static zza zzf(InputStream inputStream) throws IOException {
            zza zza2 = new zza();
            if (zzw.zzb(inputStream) != 538247942) {
                throw new IOException();
            }
            zza2.zzaB = zzw.zzd(inputStream);
            zza2.zza = zzw.zzd(inputStream);
            if (zza2.zza.equals("")) {
                zza2.zza = null;
            }
            zza2.zzb = zzw.zzc(inputStream);
            zza2.zzc = zzw.zzc(inputStream);
            zza2.zzd = zzw.zzc(inputStream);
            zza2.zze = zzw.zzc(inputStream);
            zza2.zzf = zzw.zze(inputStream);
            return zza2;
        }

        public boolean zza(OutputStream outputStream) {
            try {
                zzw.zza(outputStream, 538247942);
                zzw.zza(outputStream, this.zzaB);
                zzw.zza(outputStream, this.zza == null ? "" : this.zza);
                zzw.zza(outputStream, this.zzb);
                zzw.zza(outputStream, this.zzc);
                zzw.zza(outputStream, this.zzd);
                zzw.zza(outputStream, this.zze);
                zzw.zza(this.zzf, outputStream);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                zzt.zzb("%s", e.toString());
                return false;
            }
        }

        public zzb.zza zzb(byte[] bArr) {
            zzb.zza zza2 = new zzb.zza();
            zza2.data = bArr;
            zza2.zza = this.zza;
            zza2.zzb = this.zzb;
            zza2.zzc = this.zzc;
            zza2.zzd = this.zzd;
            zza2.zze = this.zze;
            zza2.zzf = this.zzf;
            return zza2;
        }
    }

    private static class zzb extends FilterInputStream {
        /* access modifiers changed from: private */
        public int zzaC;

        private zzb(InputStream inputStream) {
            super(inputStream);
            this.zzaC = 0;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.zzaC++;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.zzaC += read;
            }
            return read;
        }
    }

    public zzw(File file) {
        this(file, 5242880);
    }

    public zzw(File file, int i) {
        this.zzaw = new LinkedHashMap(16, 0.75f, true);
        this.zzax = 0;
        this.zzay = file;
        this.zzaz = i;
    }

    private void removeEntry(String str) {
        zza zza2 = this.zzaw.get(str);
        if (zza2 != null) {
            this.zzax -= zza2.zzaA;
            this.zzaw.remove(str);
        }
    }

    private static int zza(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void zza(OutputStream outputStream, int i) throws IOException {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    static void zza(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) (j >>> 0)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static void zza(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        zza(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    private void zza(String str, zza zza2) {
        if (!this.zzaw.containsKey(str)) {
            this.zzax += zza2.zzaA;
        } else {
            this.zzax = (zza2.zzaA - this.zzaw.get(str).zzaA) + this.zzax;
        }
        this.zzaw.put(str, zza2);
    }

    static void zza(Map<String, String> map, OutputStream outputStream) throws IOException {
        if (map != null) {
            zza(outputStream, map.size());
            for (Map.Entry next : map.entrySet()) {
                zza(outputStream, (String) next.getKey());
                zza(outputStream, (String) next.getValue());
            }
            return;
        }
        zza(outputStream, 0);
    }

    private static byte[] zza(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException("Expected " + i + " bytes, read " + i2 + " bytes");
    }

    static int zzb(InputStream inputStream) throws IOException {
        return (zza(inputStream) << 0) | 0 | (zza(inputStream) << 8) | (zza(inputStream) << 16) | (zza(inputStream) << 24);
    }

    static long zzc(InputStream inputStream) throws IOException {
        return 0 | ((((long) zza(inputStream)) & 255) << 0) | ((((long) zza(inputStream)) & 255) << 8) | ((((long) zza(inputStream)) & 255) << 16) | ((((long) zza(inputStream)) & 255) << 24) | ((((long) zza(inputStream)) & 255) << 32) | ((((long) zza(inputStream)) & 255) << 40) | ((((long) zza(inputStream)) & 255) << 48) | ((((long) zza(inputStream)) & 255) << 56);
    }

    private void zzc(int i) {
        int i2;
        if (this.zzax + ((long) i) >= ((long) this.zzaz)) {
            if (zzt.DEBUG) {
                zzt.zza("Pruning old cache entries.", new Object[0]);
            }
            long j = this.zzax;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, zza>> it = this.zzaw.entrySet().iterator();
            int i3 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i2 = i3;
                    break;
                }
                zza zza2 = (zza) it.next().getValue();
                if (zzf(zza2.zzaB).delete()) {
                    this.zzax -= zza2.zzaA;
                } else {
                    zzt.zzb("Could not delete cache entry for key=%s, filename=%s", zza2.zzaB, zze(zza2.zzaB));
                }
                it.remove();
                i2 = i3 + 1;
                if (((float) (this.zzax + ((long) i))) < ((float) this.zzaz) * 0.9f) {
                    break;
                }
                i3 = i2;
            }
            if (zzt.DEBUG) {
                zzt.zza("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.zzax - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
    }

    static String zzd(InputStream inputStream) throws IOException {
        return new String(zza(inputStream, (int) zzc(inputStream)), "UTF-8");
    }

    private String zze(String str) {
        int length = str.length() / 2;
        return String.valueOf(str.substring(0, length).hashCode()) + String.valueOf(str.substring(length).hashCode());
    }

    static Map<String, String> zze(InputStream inputStream) throws IOException {
        int zzb2 = zzb(inputStream);
        Map<String, String> emptyMap = zzb2 == 0 ? Collections.emptyMap() : new HashMap<>(zzb2);
        for (int i = 0; i < zzb2; i++) {
            emptyMap.put(zzd(inputStream).intern(), zzd(inputStream).intern());
        }
        return emptyMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0058 A[SYNTHETIC, Splitter:B:27:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005d A[SYNTHETIC, Splitter:B:30:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0066 A[SYNTHETIC, Splitter:B:35:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0050 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void initialize() {
        /*
            r9 = this;
            r0 = 0
            monitor-enter(r9)
            java.io.File r1 = r9.zzay     // Catch:{ all -> 0x006a }
            boolean r1 = r1.exists()     // Catch:{ all -> 0x006a }
            if (r1 != 0) goto L_0x0025
            java.io.File r0 = r9.zzay     // Catch:{ all -> 0x006a }
            boolean r0 = r0.mkdirs()     // Catch:{ all -> 0x006a }
            if (r0 != 0) goto L_0x0023
            java.lang.String r0 = "Unable to create cache dir %s"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x006a }
            r2 = 0
            java.io.File r3 = r9.zzay     // Catch:{ all -> 0x006a }
            java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ all -> 0x006a }
            r1[r2] = r3     // Catch:{ all -> 0x006a }
            com.google.android.gms.internal.zzt.zzc(r0, r1)     // Catch:{ all -> 0x006a }
        L_0x0023:
            monitor-exit(r9)
            return
        L_0x0025:
            java.io.File r1 = r9.zzay     // Catch:{ all -> 0x006a }
            java.io.File[] r3 = r1.listFiles()     // Catch:{ all -> 0x006a }
            if (r3 == 0) goto L_0x0023
            int r4 = r3.length     // Catch:{ all -> 0x006a }
            r2 = r0
        L_0x002f:
            if (r2 >= r4) goto L_0x0023
            r5 = r3[r2]     // Catch:{ all -> 0x006a }
            r1 = 0
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0054, all -> 0x0063 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0054, all -> 0x0063 }
            r6.<init>(r5)     // Catch:{ IOException -> 0x0054, all -> 0x0063 }
            r0.<init>(r6)     // Catch:{ IOException -> 0x0054, all -> 0x0063 }
            com.google.android.gms.internal.zzw$zza r1 = com.google.android.gms.internal.zzw.zza.zzf(r0)     // Catch:{ IOException -> 0x0076 }
            long r6 = r5.length()     // Catch:{ IOException -> 0x0076 }
            r1.zzaA = r6     // Catch:{ IOException -> 0x0076 }
            java.lang.String r6 = r1.zzaB     // Catch:{ IOException -> 0x0076 }
            r9.zza((java.lang.String) r6, (com.google.android.gms.internal.zzw.zza) r1)     // Catch:{ IOException -> 0x0076 }
            r0.close()     // Catch:{ IOException -> 0x006d }
        L_0x0050:
            int r0 = r2 + 1
            r2 = r0
            goto L_0x002f
        L_0x0054:
            r0 = move-exception
            r0 = r1
        L_0x0056:
            if (r5 == 0) goto L_0x005b
            r5.delete()     // Catch:{ all -> 0x0071 }
        L_0x005b:
            if (r0 == 0) goto L_0x0050
            r0.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x0050
        L_0x0061:
            r0 = move-exception
            goto L_0x0050
        L_0x0063:
            r0 = move-exception
        L_0x0064:
            if (r1 == 0) goto L_0x0069
            r1.close()     // Catch:{ IOException -> 0x006f }
        L_0x0069:
            throw r0     // Catch:{ all -> 0x006a }
        L_0x006a:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        L_0x006d:
            r0 = move-exception
            goto L_0x0050
        L_0x006f:
            r1 = move-exception
            goto L_0x0069
        L_0x0071:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L_0x0064
        L_0x0076:
            r1 = move-exception
            goto L_0x0056
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzw.initialize():void");
    }

    public synchronized void remove(String str) {
        boolean delete = zzf(str).delete();
        removeEntry(str);
        if (!delete) {
            zzt.zzb("Could not delete cache entry for key=%s, filename=%s", str, zze(str));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0069 A[SYNTHETIC, Splitter:B:32:0x0069] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.google.android.gms.internal.zzb.zza zza(java.lang.String r9) {
        /*
            r8 = this;
            r1 = 0
            monitor-enter(r8)
            java.util.Map<java.lang.String, com.google.android.gms.internal.zzw$zza> r0 = r8.zzaw     // Catch:{ all -> 0x006d }
            java.lang.Object r0 = r0.get(r9)     // Catch:{ all -> 0x006d }
            com.google.android.gms.internal.zzw$zza r0 = (com.google.android.gms.internal.zzw.zza) r0     // Catch:{ all -> 0x006d }
            if (r0 != 0) goto L_0x000f
            r0 = r1
        L_0x000d:
            monitor-exit(r8)
            return r0
        L_0x000f:
            java.io.File r3 = r8.zzf(r9)     // Catch:{ all -> 0x006d }
            com.google.android.gms.internal.zzw$zzb r2 = new com.google.android.gms.internal.zzw$zzb     // Catch:{ IOException -> 0x0040, all -> 0x0065 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0040, all -> 0x0065 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0040, all -> 0x0065 }
            r5.<init>(r3)     // Catch:{ IOException -> 0x0040, all -> 0x0065 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0040, all -> 0x0065 }
            r5 = 0
            r2.<init>(r4)     // Catch:{ IOException -> 0x0040, all -> 0x0065 }
            com.google.android.gms.internal.zzw.zza.zzf(r2)     // Catch:{ IOException -> 0x0075 }
            long r4 = r3.length()     // Catch:{ IOException -> 0x0075 }
            int r6 = r2.zzaC     // Catch:{ IOException -> 0x0075 }
            long r6 = (long) r6     // Catch:{ IOException -> 0x0075 }
            long r4 = r4 - r6
            int r4 = (int) r4     // Catch:{ IOException -> 0x0075 }
            byte[] r4 = zza((java.io.InputStream) r2, (int) r4)     // Catch:{ IOException -> 0x0075 }
            com.google.android.gms.internal.zzb$zza r0 = r0.zzb(r4)     // Catch:{ IOException -> 0x0075 }
            r2.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x000d
        L_0x003d:
            r0 = move-exception
            r0 = r1
            goto L_0x000d
        L_0x0040:
            r0 = move-exception
            r2 = r1
        L_0x0042:
            java.lang.String r4 = "%s: %s"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x0073 }
            r6 = 0
            java.lang.String r3 = r3.getAbsolutePath()     // Catch:{ all -> 0x0073 }
            r5[r6] = r3     // Catch:{ all -> 0x0073 }
            r3 = 1
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0073 }
            r5[r3] = r0     // Catch:{ all -> 0x0073 }
            com.google.android.gms.internal.zzt.zzb(r4, r5)     // Catch:{ all -> 0x0073 }
            r8.remove(r9)     // Catch:{ all -> 0x0073 }
            if (r2 == 0) goto L_0x0060
            r2.close()     // Catch:{ IOException -> 0x0062 }
        L_0x0060:
            r0 = r1
            goto L_0x000d
        L_0x0062:
            r0 = move-exception
            r0 = r1
            goto L_0x000d
        L_0x0065:
            r0 = move-exception
            r2 = r1
        L_0x0067:
            if (r2 == 0) goto L_0x006c
            r2.close()     // Catch:{ IOException -> 0x0070 }
        L_0x006c:
            throw r0     // Catch:{ all -> 0x006d }
        L_0x006d:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        L_0x0070:
            r0 = move-exception
            r0 = r1
            goto L_0x000d
        L_0x0073:
            r0 = move-exception
            goto L_0x0067
        L_0x0075:
            r0 = move-exception
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzw.zza(java.lang.String):com.google.android.gms.internal.zzb$zza");
    }

    public synchronized void zza(String str, zzb.zza zza2) {
        zzc(zza2.data.length);
        File zzf = zzf(str);
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(zzf));
            zza zza3 = new zza(str, zza2);
            if (!zza3.zza(bufferedOutputStream)) {
                bufferedOutputStream.close();
                zzt.zzb("Failed to write header for %s", zzf.getAbsolutePath());
                throw new IOException();
            }
            bufferedOutputStream.write(zza2.data);
            bufferedOutputStream.close();
            zza(str, zza3);
        } catch (IOException e) {
            if (!zzf.delete()) {
                zzt.zzb("Could not clean up file %s", zzf.getAbsolutePath());
            }
        }
    }

    public File zzf(String str) {
        return new File(this.zzay, zze(str));
    }
}
