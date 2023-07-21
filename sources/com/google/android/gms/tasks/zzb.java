package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzb<TResult, TContinuationResult> implements OnFailureListener, OnSuccessListener<TContinuationResult>, zzf<TResult> {
    private final Executor zzbFQ;
    /* access modifiers changed from: private */
    public final Continuation<TResult, Task<TContinuationResult>> zzbNt;
    /* access modifiers changed from: private */
    public final zzh<TContinuationResult> zzbNu;

    public zzb(@NonNull Executor executor, @NonNull Continuation<TResult, Task<TContinuationResult>> continuation, @NonNull zzh<TContinuationResult> zzh) {
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
                    Task task = (Task) zzb.this.zzbNt.then(task);
                    if (task == null) {
                        zzb.this.onFailure(new NullPointerException("Continuation returned null"));
                        return;
                    }
                    task.addOnSuccessListener(TaskExecutors.zzbNH, zzb.this);
                    task.addOnFailureListener(TaskExecutors.zzbNH, (OnFailureListener) zzb.this);
                } catch (RuntimeExecutionException e) {
                    if (e.getCause() instanceof Exception) {
                        zzb.this.zzbNu.setException((Exception) e.getCause());
                    } else {
                        zzb.this.zzbNu.setException(e);
                    }
                } catch (Exception e2) {
                    zzb.this.zzbNu.setException(e2);
                }
            }
        });
    }

    public void onFailure(@NonNull Exception exc) {
        this.zzbNu.setException(exc);
    }

    public void onSuccess(TContinuationResult tcontinuationresult) {
        this.zzbNu.setResult(tcontinuationresult);
    }
}
