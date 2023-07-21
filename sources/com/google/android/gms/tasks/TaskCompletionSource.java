package com.google.android.gms.tasks;

import android.support.annotation.NonNull;

public class TaskCompletionSource<TResult> {
    private final zzh<TResult> zzbNG = new zzh<>();

    @NonNull
    public Task<TResult> getTask() {
        return this.zzbNG;
    }

    public void setException(@NonNull Exception exc) {
        this.zzbNG.setException(exc);
    }

    public void setResult(TResult tresult) {
        this.zzbNG.setResult(tresult);
    }

    public boolean trySetException(@NonNull Exception exc) {
        return this.zzbNG.trySetException(exc);
    }

    public boolean trySetResult(TResult tresult) {
        return this.zzbNG.trySetResult(tresult);
    }
}
