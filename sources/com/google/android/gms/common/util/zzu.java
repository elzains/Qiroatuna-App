package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class zzu {
    private static String zzaIn = null;
    private static final int zzaIo = Process.myPid();

    static String zzdq(int i) {
        BufferedReader bufferedReader;
        Throwable th;
        StrictMode.ThreadPolicy allowThreadDiskReads;
        String str = null;
        if (i > 0) {
            try {
                allowThreadDiskReads = StrictMode.allowThreadDiskReads();
                bufferedReader = new BufferedReader(new FileReader(new StringBuilder(25).append("/proc/").append(i).append("/cmdline").toString()));
                try {
                    StrictMode.setThreadPolicy(allowThreadDiskReads);
                    str = bufferedReader.readLine().trim();
                    zzp.zzb(bufferedReader);
                } catch (IOException e) {
                    zzp.zzb(bufferedReader);
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                    zzp.zzb(bufferedReader);
                    throw th;
                }
            } catch (IOException e2) {
                bufferedReader = null;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                bufferedReader = null;
                th = th4;
                zzp.zzb(bufferedReader);
                throw th;
            }
        }
        return str;
    }

    public static String zzzq() {
        if (zzaIn == null) {
            zzaIn = zzdq(zzaIo);
        }
        return zzaIn;
    }
}
