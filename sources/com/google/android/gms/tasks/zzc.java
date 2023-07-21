package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zzc<TResult> implements zzf<TResult> {
    private final Executor zzbFQ;
    /* access modifiers changed from: private */
    public OnCompleteListener<TResult> zzbNy;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();

    public zzc(@NonNull Executor executor, @NonNull OnCompleteListener<TResult> onCompleteListener) {
        this.zzbFQ = executor;
        this.zzbNy = onCompleteListener;
    }

    public void cancel() {
        synchronized (this.zzrJ) {
            this.zzbNy = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        synchronized (this.zzrJ) {
            if (this.zzbNy != null) {
                this.zzbFQ.execute(new Runnable() {
                    public void run() {
                        synchronized (zzc.this.zzrJ) {
                            if (zzc.this.zzbNy != null) {
                                zzc.this.zzbNy.onComplete(task);
                            }
                        }
                    }
                });
            }
        }
    }
}
