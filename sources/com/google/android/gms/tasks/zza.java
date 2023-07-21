package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zza<TResult, TContinuationResult> implements zzf<TResult> {
    private final Executor zzbFQ;
    /* access modifiers changed from: private */
    public final Continuation<TResult, TContinuationResult> zzbNt;
    /* access modifiers changed from: private */
    public final zzh<TContinuationResult> zzbNu;

    public zza(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation, @NonNull zzh<TContinuationResult> zzh) {
        this.zzbFQ = executor;
        this.zzbNt = continuation;
        this.zzbNu = zzh;
    }

    public void cancel() {
        throw new UnsupportedOperationException();
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        this.zzbFQ.execute(new Runnable() {
            public void run() {
                try {
                    zza.this.zzbNu.setResult(zza.this.zzbNt.then(task));
                } catch (RuntimeExecutionException e) {
                    if (e.getCause() instanceof Exception) {
                        zza.this.zzbNu.setException((Exception) e.getCause());
                    } else {
                        zza.this.zzbNu.setException(e);
                    }
                } catch (Exception e2) {
                    zza.this.zzbNu.setException(e2);
                }
            }
        });
    }
}
