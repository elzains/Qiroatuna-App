package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzw;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzme
public class zzqj<T> implements zzqm<T> {
    private T mValue;
    private boolean zzLs;
    private Throwable zzZa;
    private boolean zzZb;
    private final zzqn zzZc = new zzqn();
    private final Object zzrJ = new Object();

    private boolean zzll() {
        return this.zzZa != null || this.zzZb;
    }

    public boolean cancel(boolean z) {
        if (!z) {
            return false;
        }
        synchronized (this.zzrJ) {
            if (zzll()) {
                return false;
            }
            this.zzLs = true;
            this.zzZb = true;
            this.zzrJ.notifyAll();
            this.zzZc.zzlm();
            return true;
        }
    }

    public T get() throws CancellationException, ExecutionException, InterruptedException {
        T t;
        synchronized (this.zzrJ) {
            if (!zzll()) {
                try {
                    this.zzrJ.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzZa != null) {
                throw new ExecutionException(this.zzZa);
            } else if (this.zzLs) {
                throw new CancellationException("CallbackFuture was cancelled.");
            } else {
                t = this.mValue;
            }
        }
        return t;
    }

    public T get(long j, TimeUnit timeUnit) throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        T t;
        synchronized (this.zzrJ) {
            if (!zzll()) {
                try {
                    long millis = timeUnit.toMillis(j);
                    if (millis != 0) {
                        this.zzrJ.wait(millis);
                    }
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            if (this.zzZa != null) {
                throw new ExecutionException(this.zzZa);
            } else if (!this.zzZb) {
                throw new TimeoutException("CallbackFuture timed out.");
            } else if (this.zzLs) {
                throw new CancellationException("CallbackFuture was cancelled.");
            } else {
                t = this.mValue;
            }
        }
        return t;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.zzrJ) {
            z = this.zzLs;
        }
        return z;
    }

    public boolean isDone() {
        boolean zzll;
        synchronized (this.zzrJ) {
            zzll = zzll();
        }
        return zzll;
    }

    public void zzc(Runnable runnable) {
        this.zzZc.zzc(runnable);
    }

    public void zzd(Runnable runnable) {
        this.zzZc.zzd(runnable);
    }

    public void zze(Throwable th) {
        synchronized (this.zzrJ) {
            if (!this.zzLs) {
                if (zzll()) {
                    zzw.zzcQ().zza((Throwable) new IllegalStateException("Provided CallbackFuture with multiple values."), "CallbackFuture.provideException");
                    return;
                }
                this.zzZa = th;
                this.zzrJ.notifyAll();
                this.zzZc.zzlm();
            }
        }
    }

    public void zzh(@Nullable T t) {
        synchronized (this.zzrJ) {
            if (!this.zzLs) {
                if (zzll()) {
                    zzw.zzcQ().zza((Throwable) new IllegalStateException("Provided CallbackFuture with multiple values."), "CallbackFuture.provideValue");
                    return;
                }
                this.zzZb = true;
                this.mValue = t;
                this.zzrJ.notifyAll();
                this.zzZc.zzlm();
            }
        }
    }
}
