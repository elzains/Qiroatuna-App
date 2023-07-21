package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzd<TResult> implements zzf<TResult> {
    private final Executor zzbFQ;
    /* access modifiers changed from: private */
    public OnFailureListener zzbNA;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();

    public zzd(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
        this.zzbFQ = executor;
        this.zzbNA = onFailureListener;
    }

    public void cancel() {
        synchronized (this.zzrJ) {
            this.zzbNA = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (!task.isSuccessful()) {
            synchronized (this.zzrJ) {
                if (this.zzbNA != null) {
                    this.zzbFQ.execute(new Runnable() {
                        public void run() {
                            synchronized (zzd.this.zzrJ) {
                                if (zzd.this.zzbNA != null) {
                                    zzd.this.zzbNA.onFailure(task.getException());
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
