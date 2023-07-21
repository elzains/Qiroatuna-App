package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks {

    private static final class zza implements zzb {
        private final CountDownLatch zztj;

        private zza() {
            this.zztj = new CountDownLatch(1);
        }

        public void await() throws InterruptedException {
            this.zztj.await();
        }

        public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.zztj.await(j, timeUnit);
        }

        public void onFailure(@NonNull Exception exc) {
            this.zztj.countDown();
        }

        public void onSuccess(Object obj) {
            this.zztj.countDown();
        }
    }

    interface zzb extends OnFailureListener, OnSuccessListener<Object> {
    }

    private static final class zzc implements zzb {
        private final zzh<Void> zzbNG;
        private Exception zzbNL;
        private final int zzbNN;
        private int zzbNO;
        private int zzbNP;
        private final Object zzrJ = new Object();

        public zzc(int i, zzh<Void> zzh) {
            this.zzbNN = i;
            this.zzbNG = zzh;
        }

        private void zzTI() {
            if (this.zzbNO + this.zzbNP != this.zzbNN) {
                return;
            }
            if (this.zzbNL == null) {
                this.zzbNG.setResult(null);
                return;
            }
            zzh<Void> zzh = this.zzbNG;
            int i = this.zzbNP;
            zzh.setException(new ExecutionException(new StringBuilder(54).append(i).append(" out of ").append(this.zzbNN).append(" underlying tasks failed").toString(), this.zzbNL));
        }

        public void onFailure(@NonNull Exception exc) {
            synchronized (this.zzrJ) {
                this.zzbNP++;
                this.zzbNL = exc;
                zzTI();
            }
        }

        public void onSuccess(Object obj) {
            synchronized (this.zzrJ) {
                this.zzbNO++;
                zzTI();
            }
        }
    }

    private Tasks() {
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task) throws ExecutionException, InterruptedException {
        zzac.zzye();
        zzac.zzb(task, (Object) "Task must not be null");
        if (task.isComplete()) {
            return zzb(task);
        }
        zza zza2 = new zza();
        zza(task, zza2);
        zza2.await();
        return zzb(task);
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        zzac.zzye();
        zzac.zzb(task, (Object) "Task must not be null");
        zzac.zzb(timeUnit, (Object) "TimeUnit must not be null");
        if (task.isComplete()) {
            return zzb(task);
        }
        zza zza2 = new zza();
        zza(task, zza2);
        if (zza2.await(j, timeUnit)) {
            return zzb(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    public static <TResult> Task<TResult> call(@NonNull Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }

    public static <TResult> Task<TResult> call(@NonNull Executor executor, @NonNull final Callable<TResult> callable) {
        zzac.zzb(executor, (Object) "Executor must not be null");
        zzac.zzb(callable, (Object) "Callback must not be null");
        final zzh zzh = new zzh();
        executor.execute(new Runnable() {
            public void run() {
                try {
                    zzh.this.setResult(callable.call());
                } catch (Exception e) {
                    zzh.this.setException(e);
                }
            }
        });
        return zzh;
    }

    public static <TResult> Task<TResult> forException(@NonNull Exception exc) {
        zzh zzh = new zzh();
        zzh.setException(exc);
        return zzh;
    }

    public static <TResult> Task<TResult> forResult(TResult tresult) {
        zzh zzh = new zzh();
        zzh.setResult(tresult);
        return zzh;
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return forResult((Object) null);
        }
        for (Task task : collection) {
            if (task == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
        }
        zzh zzh = new zzh();
        zzc zzc2 = new zzc(collection.size(), zzh);
        for (Task zza2 : collection) {
            zza(zza2, zzc2);
        }
        return zzh;
    }

    public static Task<Void> whenAll(Task<?>... taskArr) {
        return taskArr.length == 0 ? forResult((Object) null) : whenAll((Collection<? extends Task<?>>) Arrays.asList(taskArr));
    }

    private static void zza(Task<?> task, zzb zzb2) {
        task.addOnSuccessListener(TaskExecutors.zzbNH, (OnSuccessListener<? super Object>) zzb2);
        task.addOnFailureListener(TaskExecutors.zzbNH, (OnFailureListener) zzb2);
    }

    private static <TResult> TResult zzb(Task<TResult> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }
}
