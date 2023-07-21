package com.google.android.gms.internal;

import android.os.Process;
import com.google.android.gms.ads.internal.zzw;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@zzme
public final class zzpn {
    private static final ThreadPoolExecutor zzXu = new ThreadPoolExecutor(10, 10, 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), zzaW("Default"));
    private static final ThreadPoolExecutor zzXv = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingQueue(), zzaW("Loader"));

    static {
        zzXu.allowCoreThreadTimeOut(true);
        zzXv.allowCoreThreadTimeOut(true);
    }

    public static zzqm<Void> zza(int i, final Runnable runnable) {
        return i == 1 ? zza((ExecutorService) zzXv, new Callable<Void>() {
            /* renamed from: zzbk */
            public Void call() {
                runnable.run();
                return null;
            }
        }) : zza((ExecutorService) zzXu, new Callable<Void>() {
            /* renamed from: zzbk */
            public Void call() {
                runnable.run();
                return null;
            }
        });
    }

    public static zzqm<Void> zza(Runnable runnable) {
        return zza(0, runnable);
    }

    public static <T> zzqm<T> zza(Callable<T> callable) {
        return zza((ExecutorService) zzXu, callable);
    }

    public static <T> zzqm<T> zza(ExecutorService executorService, final Callable<T> callable) {
        final zzqj zzqj = new zzqj();
        try {
            final Future<?> submit = executorService.submit(new Runnable() {
                public void run() {
                    try {
                        Process.setThreadPriority(10);
                        zzqj.this.zzh(callable.call());
                    } catch (Exception e) {
                        zzw.zzcQ().zza((Throwable) e, "AdThreadPool.submit");
                        zzqj.this.zze(e);
                    }
                }
            });
            zzqj.zzd(new Runnable() {
                public void run() {
                    if (zzqj.this.isCancelled()) {
                        submit.cancel(true);
                    }
                }
            });
        } catch (RejectedExecutionException e) {
            zzpk.zzc("Thread execution is rejected.", e);
            zzqj.cancel(true);
        }
        return zzqj;
    }

    private static ThreadFactory zzaW(final String str) {
        return new ThreadFactory() {
            private final AtomicInteger zzXA = new AtomicInteger(1);

            public Thread newThread(Runnable runnable) {
                String str = str;
                return new Thread(runnable, new StringBuilder(String.valueOf(str).length() + 23).append("AdWorker(").append(str).append(") #").append(this.zzXA.getAndIncrement()).toString());
            }
        };
    }
}
