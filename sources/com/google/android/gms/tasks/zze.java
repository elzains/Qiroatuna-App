package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult> implements zzf<TResult> {
    private final Executor zzbFQ;
    /* access modifiers changed from: private */
    public OnSuccessListener<? super TResult> zzbNC;
    /* access modifiers changed from: private */
    public final Object zzrJ = new Object();

    public zze(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzbFQ = executor;
        this.zzbNC = onSuccessListener;
    }

    public void cancel() {
        synchronized (this.zzrJ) {
            this.zzbNC = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.zzrJ) {
                if (this.zzbNC != null) {
                    this.zzbFQ.execute(new Runnable() {
                        public void run() {
                            synchronized (zze.this.zzrJ) {
                                if (zze.this.zzbNC != null) {
                                    zze.this.zzbNC.onSuccess(task.getResult());
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
