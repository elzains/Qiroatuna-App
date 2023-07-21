package com.google.android.gms.internal;

import java.util.concurrent.TimeUnit;

@zzme
public class zzqk<T> implements zzqm<T> {
    private final T mValue;
    private final zzqn zzZc = new zzqn();

    public zzqk(T t) {
        this.mValue = t;
        this.zzZc.zzlm();
    }

    public boolean cancel(boolean z) {
        return false;
    }

    public T get() {
        return this.mValue;
    }

    public T get(long j, TimeUnit timeUnit) {
        return this.mValue;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }

    public void zzc(Runnable runnable) {
        this.zzZc.zzc(runnable);
    }

    public void zzd(Runnable runnable) {
        this.zzZc.zzd(runnable);
    }
}
