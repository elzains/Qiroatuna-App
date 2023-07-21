package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zze;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

public class zzab {
    private static final zzb zzaGw = new zzb() {
        public com.google.android.gms.common.api.zza zzH(Status status) {
            return zzb.zzG(status);
        }
    };

    public interface zza<R extends Result, T> {
        T zzf(R r);
    }

    public interface zzb {
        com.google.android.gms.common.api.zza zzH(Status status);
    }

    public static <R extends Result, T extends zze<R>> Task<T> zza(PendingResult<R> pendingResult, final T t) {
        return zza(pendingResult, new zza<R, T>() {
            /* renamed from: zze */
            public T zzf(R r) {
                zze.this.zzb(r);
                return zze.this;
            }
        });
    }

    public static <R extends Result, T> Task<T> zza(PendingResult<R> pendingResult, zza<R, T> zza2) {
        return zza(pendingResult, zza2, zzaGw);
    }

    public static <R extends Result, T> Task<T> zza(final PendingResult<R> pendingResult, final zza<R, T> zza2, final zzb zzb2) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        pendingResult.zza(new PendingResult.zza() {
            public void zzy(Status status) {
                if (status.isSuccess()) {
                    taskCompletionSource.setResult(zza2.zzf(PendingResult.this.await(0, TimeUnit.MILLISECONDS)));
                    return;
                }
                taskCompletionSource.setException(zzb2.zzH(status));
            }
        });
        return taskCompletionSource.getTask();
    }
}
