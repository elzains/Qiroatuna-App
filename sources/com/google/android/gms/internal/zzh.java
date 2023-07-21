package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public class zzh extends Thread {
    private final zzb zzi;
    private final zzo zzj;
    private volatile boolean zzk = false;
    private final BlockingQueue<zzl<?>> zzx;
    private final zzg zzy;

    public zzh(BlockingQueue<zzl<?>> blockingQueue, zzg zzg, zzb zzb, zzo zzo) {
        this.zzx = blockingQueue;
        this.zzy = zzg;
        this.zzi = zzb;
        this.zzj = zzo;
    }

    @TargetApi(14)
    private void zzb(zzl<?> zzl) {
        int i = Build.VERSION.SDK_INT;
        TrafficStats.setThreadStatsTag(zzl.zzf());
    }

    private void zzb(zzl<?> zzl, zzs zzs) {
        this.zzj.zza(zzl, zzl.zzb(zzs));
    }

    public void quit() {
        this.zzk = true;
        interrupt();
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                zzl take = this.zzx.take();
                try {
                    take.zzc("network-queue-take");
                    zzb(take);
                    zzj zza = this.zzy.zza(take);
                    take.zzc("network-http-complete");
                    if (!zza.zzA || !take.zzs()) {
                        zzn zza2 = take.zza(zza);
                        take.zzc("network-parse-complete");
                        if (take.zzn() && zza2.zzaf != null) {
                            this.zzi.zza(take.zzg(), zza2.zzaf);
                            take.zzc("network-cache-written");
                        }
                        take.zzr();
                        this.zzj.zza((zzl<?>) take, (zzn<?>) zza2);
                    } else {
                        take.zzd("not-modified");
                    }
                } catch (zzs e) {
                    e.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    zzb(take, e);
                } catch (Exception e2) {
                    zzt.zza(e2, "Unhandled exception %s", e2.toString());
                    zzs zzs = new zzs((Throwable) e2);
                    zzs.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
                    this.zzj.zza((zzl<?>) take, zzs);
                }
            } catch (InterruptedException e3) {
                if (this.zzk) {
                    return;
                }
            }
        }
    }
}
